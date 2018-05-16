package view;

import controller.Controller;
import vacancy.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class ViewHtml implements View {
    private Controller controller;
    /*private final String filePath = "C:\\Users\\Sergey\\IdeaProjects\\JavaRushTasks\\" +
            "4.JavaCollections\\src\\com\\javarush\\task\\task28\\task2810\\view\\vacancy.html";*/
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancy.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
        updateFile(getUpdatedFileContent(vacancies));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){
        try {
            Document doc = getDocument();
            Element template = doc.getElementsByClass("template").first();
            Element cloneTemp = template.clone();
            cloneTemp.removeClass("template");
            cloneTemp.removeAttr("style");
            doc.select("tr[class=vacancy]").remove();
            for (Vacancy vacancy : vacancies) {
                Element vac = template.clone();
                vac.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                vac.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                vac.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                vac.getElementsByAttribute("href").get(0).attr("href", vacancy.getUrl()).text(vacancy.getTitle());
                template.before(vac);//.outerHtml()
            }
            return doc.html();
        }catch (IOException e){
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String s){
        try(FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
            outputStream.write(s.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath) , "UTF-8");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        if(controller!=null){
            controller.onCitySelect("Odessa");
        }
    }
}
