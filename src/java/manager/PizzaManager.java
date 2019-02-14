package manager;

import entities.Pizza;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PizzaManager {
    
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
        Pizza p = em.find(Pizza.class, id);
        
        p.setName(changeTo.getName());
        p.setPrice(changeTo.getPrice());
        p.setDescription(changeTo.getDescription());
    }
    public Pizza update2(Pizza p){
        return em.merge(p);
    }
    
    public Pizza fixedMerge(long id, Pizza changeTo){
        Pizza oldPizza = em.find(Pizza.class, id);
        changeTo.setId(id);
        em.merge(changeTo);
        return oldPizza;
    }
}
