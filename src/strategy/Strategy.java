package strategy;

import vacancy.Vacancy;

import java.util.List;

/**
 * будет отвечать за получение данных с сайта
 */
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
