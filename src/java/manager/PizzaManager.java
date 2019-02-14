package manager;

import Facades.PizzaManagerFacade;
import entities.Pizza;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PizzaManager implements PizzaManagerFacade {
    
    @PersistenceContext
    EntityManager em;
    
    public void create(Pizza p){
        em.persist(p);
    }
    public void delete(Long id) {
//        em.remove(em.find(Pizza.class, id));
        em.remove(getPizza(id));
    }
    public void delete(Pizza p){
        delete(p.getId());
    }
    public Pizza getPizza(Long id){
        return em.find(Pizza.class, id);
    }
    public List<Pizza> getAll() {
        return em.createQuery("SELECT p FROM Pizza p")
                .getResultList();
    }
    public void update(Long id, Pizza changeTo){
        changeTo.setId(id);
        em.merge(changeTo);
    }
    public void update(Pizza p){
        em.merge(p);
    }
}
