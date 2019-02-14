package controller;

import manager.PizzaManager;
import entities.Pizza;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "pizzaController")
@RequestScoped
public class PizzaController {
    
    @Inject
    PizzaManager pm;
    
    private String name;
    private double price;
    private String description;
    private List<Pizza> pizzas;
    
    public PizzaController() {
    }
    
    private boolean checkFields() {
        return !(name == null || price == 0 || description == null || name.equals("") || description.equals(""));
    }
    
    public void submit() {
        if (checkFields())
            pm.create(new Pizza(name, price, description));
        updateList();
    }
    
    public void delete(Long id) {
        pm.delete(id);
        updateList();
    }
    
    public void edit(Long id) {
        if (checkFields())
            pm.update(id, new Pizza(name, price, description));
        updateList();
    }
    
    @PostConstruct
    public void updateList() {
        pizzas = pm.getAll();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    
    
}
