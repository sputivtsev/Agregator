package model;

import view.View;
import vacancy.Vacancy;

import java.util.ArrayList;

public class Model {
    private View view;
    private Helper[] helpers;

    public Model(View view, Helper... providers) {
        if(view == null || providers == null || providers.length==0) throw new IllegalArgumentException();
        this.view = view;
        this.helpers = providers;
    }

    public void selectCity(String city){
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        for (Helper helper : helpers){
            vacancies.addAll(helper.getJavaVacancies(city));
        }
        view.update(vacancies);
    }
}
