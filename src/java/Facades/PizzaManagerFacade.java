package Facades;

import entities.Pizza;
import java.util.List;

public interface PizzaManagerFacade {
    void create(Pizza p);
    void delete(Long id);
    void delete(Pizza p);
    Pizza getPizza(Long id);
    List<Pizza> getAll();
    void update(Long id, Pizza changeTo);
    void update(Pizza p);
}
