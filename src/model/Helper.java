package model;


import strategy.Strategy;
import vacancy.Vacancy;

import java.util.List;

/**
 * Этот класс будет обобщать способ получения данных о вакансиях
 */
public class Helper {
    private Strategy strategy;

    public Helper(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * метод будет возвращать все java вакансии с выбранного сайта (ресурса).
     * @param searchString
     * @return
     */
    public List<Vacancy> getJavaVacancies(String searchString){
        List<Vacancy> vacancies = strategy.getVacancies(searchString);
        return vacancies;
    }
}
