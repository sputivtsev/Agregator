import controller.Controller;
import strategy.HeadHunterStrategy;
import model.Model;
import strategy.MoikrugStrategy;
import model.Helper;
import view.ViewHtml;

public class Aggregator {
    public static void main(String[] args) {


        //new MoikrugStrategy().getVacancies("Москва");

        ViewHtml view = new ViewHtml();
        Helper hhProvider = new Helper(new HeadHunterStrategy());
        Helper moikrugProvider = new Helper(new MoikrugStrategy());
        Model model = new Model(view, hhProvider, moikrugProvider);
        //Model model = new Model(view, hhProvider);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod ();
    }
}
