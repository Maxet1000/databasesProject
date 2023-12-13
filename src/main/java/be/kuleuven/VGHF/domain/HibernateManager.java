package be.kuleuven.VGHF.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.print.Collation;

public class HibernateManager {
    
    private final EntityManager entityManager;

    public HibernateManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Console> getAllConsoles() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Console.class);
        var root = query.from(Console.class);

        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    public List<Copy> getAllCopies() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Copy.class);
        var root = query.from(Copy.class);
    
        query.select(root);
    
        return entityManager.createQuery(query).getResultList();
    }
    
    public List<Customer> getAllCustomers() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Customer.class);
        var root = query.from(Customer.class);
    
        query.select(root);
    
        return entityManager.createQuery(query).getResultList();
    }
    
    public List<Developer> getAllDevelopers() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Developer.class);
        var root = query.from(Developer.class);
    
        query.select(root);
    
        return entityManager.createQuery(query).getResultList();
    }
    
    public List<Game> getAllGames() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Game.class);
        var root = query.from(Game.class);
    
        query.select(root);
    
        return entityManager.createQuery(query).getResultList();
    }
    
    public List<Genre> getAllGenres() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Genre.class);
        var root = query.from(Genre.class);
    
        query.select(root);
    
        return entityManager.createQuery(query).getResultList();
    }
    
    public List<MonetaryTransaction> getAllMonetaryTransactions() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(MonetaryTransaction.class);
        var root = query.from(MonetaryTransaction.class);
    
        query.select(root);
    
        return entityManager.createQuery(query).getResultList();
    }    
    public void saveNewConsole(Console console) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(console);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public void saveNewCopy(Copy copy) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(copy);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public void saveNewCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public void saveNewDeveloper(Developer developer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(developer);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public void saveNewGame(Game game) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(game);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public void saveNewGenre(Genre genre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public void saveNewMonetaryTransaction(MonetaryTransaction monetaryTransaction) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(monetaryTransaction);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public void updateConsole(Console console) {
        try {
            entityManager.merge(console);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void updateCopy(Copy copy) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(copy);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void updateCustomer(Customer customer) {
        try {
            entityManager.merge(customer);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void updateDeveloper(Developer developer) {
        try {
            entityManager.merge(developer);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void updateGame(Game game) {
        try {
            entityManager.merge(game);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void updateGenre(Genre genre) {
        try {
            entityManager.merge(genre);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void updateMonetaryTransaction(MonetaryTransaction monetaryTransaction) {
        try {
            entityManager.merge(monetaryTransaction);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public List<Game> getGamesByTitle(String title) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Game.class);
            var root = query.from(Game.class);

            query.where(criteriaBuilder.equal(root.get("title"), title));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }  

    public List<MonetaryTransaction> getMonetaryTransactionsByCustomerID(int customerID) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(MonetaryTransaction.class);
            var root = query.from(MonetaryTransaction.class);
            query.where(criteriaBuilder.equal(root.get("customer"), customerID));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Copy getCopyById(int copyID) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Copy.class);
            var root = query.from(Copy.class);
            query.where(criteriaBuilder.equal(root.get("copyID"), copyID));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
