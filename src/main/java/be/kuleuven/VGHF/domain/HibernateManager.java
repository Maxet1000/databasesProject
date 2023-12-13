package be.kuleuven.VGHF.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

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
        entityManager.getTransaction().begin();
        entityManager.persist(console);
        entityManager.getTransaction().commit();
    }

    public void saveNewCopy(Copy copy) {
        entityManager.getTransaction().begin();
        entityManager.persist(copy);
        entityManager.getTransaction().commit();
    }

    public void saveNewCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    public void saveNewDeveloper(Developer developer) {
        entityManager.getTransaction().begin();
        entityManager.persist(developer);
        entityManager.getTransaction().commit();
    }

    public void saveNewGame(Game game) {
        entityManager.getTransaction().begin();
        entityManager.persist(game);
        entityManager.getTransaction().commit();
    }

    public void saveNewGenre(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
    }

    public void saveNewMonetaryTransaction(MonetaryTransaction monetaryTransaction) {
        entityManager.getTransaction().begin();
        entityManager.persist(monetaryTransaction);
        entityManager.getTransaction().commit();
    }
    
    public void updateConsole(Console console) {
        entityManager.merge(console);
    }

    public void updateCopy(Copy copy) {
        entityManager.merge(copy);
    }

    public void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }

    public void updateDeveloper(Developer developer) {
        entityManager.merge(developer);
    }

    public void updateGame(Game game) {
        entityManager.merge(game);
    }

    public void updateGenre(Genre genre) {
        entityManager.merge(genre);
    }

    public void updateMonetaryTransaction(MonetaryTransaction monetaryTransaction) {
        entityManager.merge(monetaryTransaction);
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



}
