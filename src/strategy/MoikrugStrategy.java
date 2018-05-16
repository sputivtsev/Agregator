package strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vacancy.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    //private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String URL_FORMAT = "https://moikrug.ru/vacancy?q=java+%s&page=%d";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> Vacancies = new ArrayList<>();
        int pageNum = 0;
        Document doc = null;
        while(true)
        {
            try {
                doc = getDocument(searchString, pageNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements vacancies = doc.getElementsByClass("job");
            if (vacancies.size()==0) break;
            for (Element element: vacancies)
            {
                if (element != null)
                {
                    Vacancy vac = new Vacancy();
                    vac.setTitle(element.getElementsByAttributeValue("class", "title").text());
                    vac.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vac.setSiteName(URL_FORMAT);
                    vac.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                    String salary = element.getElementsByAttributeValue("class", "salary").text();
                    String city = element.getElementsByAttributeValue("class", "location").text();
                    vac.setSalary(salary.length()==0 ? "" : salary);
                    vac.setCity(city.length()==0 ? "" : city);
                    Vacancies.add(vac);
                }
            }
            pageNum++;
        }
        return Vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT,searchString,page);
        Document document = Jsoup.connect(url).userAgent("Chrome (jsoup)").referrer("https://www.google.com.ua/").get();
        return document;
    }
}
