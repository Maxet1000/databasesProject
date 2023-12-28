package be.kuleuven.VGHF.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import be.kuleuven.VGHF.enums.Availability;
import javafx.print.Collation;

public class HibernateManager {
    
    private EntityManager entityManager;
    private EntityManagerFactory sessionFactory;

    public HibernateManager(EntityManager entityManager, EntityManagerFactory sessionFactory) {
        this.entityManager = entityManager;
        this.sessionFactory = sessionFactory;
    }

    //for testing
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    // In geval van nood
    public void clearCloseAndResetEntityManager() {
        entityManager.clear();
        entityManager.close();
        var newEntityManager = sessionFactory.createEntityManager();
        this.entityManager = newEntityManager;
    }

    public List<Console> getAllConsoles() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Console.class);
            var root = query.from(Console.class);

            query.select(root);

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Copy> getAllCopies() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Copy.class);
            var root = query.from(Copy.class);
        
            query.select(root);
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<User> getAllUsers() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(User.class);
            var root = query.from(User.class);
        
            query.select(root);
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Developer> getAllDevelopers() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Developer.class);
            var root = query.from(Developer.class);
        
            query.select(root);
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Game> getAllGames() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Game.class);
            var root = query.from(Game.class);
        
            query.select(root);
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Genre> getAllGenres() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Genre.class);
            var root = query.from(Genre.class);
        
            query.select(root);
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<MonetaryTransaction> getAllMonetaryTransactions() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(MonetaryTransaction.class);
            var root = query.from(MonetaryTransaction.class);
        
            query.select(root);
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    

    public <T> void saveNewEntity(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }
    
    public <T> void updateEntity(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public <T> void deleteEntity(T entity) {
        if (entity != null) {
            try{
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                if (entityManager.getTransaction() != null) {
                    entityManager.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public List<MonetaryTransaction> getMonetaryTransactionsByUserID(int userID) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(MonetaryTransaction.class);
            var root = query.from(MonetaryTransaction.class);
            query.where(criteriaBuilder.equal(root.get("user"), userID));
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

    public Game getGameByTitle(String title) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Game.class);
            var root = query.from(Game.class);

            query.where(criteriaBuilder.equal(root.get("title"), title));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }  

    public User getUserByEmail(String email) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(User.class);
            var root = query.from(User.class);
            query.where(criteriaBuilder.equal(root.get("email"), email));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public Developer getdeveloperByName(String developerName) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Developer.class);
            var root = query.from(Developer.class);
            query.where(criteriaBuilder.equal(root.get("developerName"), developerName));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public Console getConsoleByName(String consoleName) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Console.class);
            var root = query.from(Console.class);
            query.where(criteriaBuilder.equal(root.get("consoleName"), consoleName));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public Genre getgenreByName(String genreName) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Genre.class);
            var root = query.from(Genre.class);
            query.where(criteriaBuilder.equal(root.get("genreName"), genreName));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public <T> List<Copy> getPageOfCopies(int firstCopyIndex, int pageLength, List<List<T>> filterLists) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Copy.class);
            var root = query.from(Copy.class);
            List<Predicate> filterPredicates = new ArrayList<>();
            Predicate allFiltersPredicate = criteriaBuilder.conjunction();
            Join <Copy, Game> gameJoin = root.join("game", JoinType.INNER);
            if (filterLists != null) {
                for (List<T> list : filterLists) {

                    Predicate filter = criteriaBuilder.conjunction();
                    if (list.get(0) instanceof Console) {
                        filter = root.get("console").in(list);
                    }
                    if (list.get(0) instanceof Developer) {
                        filter = gameJoin.join("developers").in(list);
                    }
                    if (list.get(0) instanceof Genre) {
                        filter = gameJoin.join("genres").in(list);
                    }
                    if (list.get(0) instanceof Copy) {
                        filter = criteriaBuilder.not(root.in(list));
                    }
                    filterPredicates.add(filter);
                }
            allFiltersPredicate = criteriaBuilder.and(filterPredicates.toArray(new Predicate[0]));
            } 
            query.where(criteriaBuilder.greaterThanOrEqualTo(root.get("copyID"), firstCopyIndex),
                        criteriaBuilder.equal(root.get("availability"), Availability.AVAILABLE),
                        criteriaBuilder.or(criteriaBuilder.notEqual(root.get("purchasePrice"), 0),
                                            criteriaBuilder.notEqual(root.get("rentPrice"), 0)),
                                            allFiltersPredicate).distinct(true);
            return entityManager.createQuery(query).setMaxResults(pageLength).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllGameNames() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(String.class);
            var root = query.from(Game.class);
        
            query.select(root.get("title"));
        
            return entityManager.createQuery(query).getResultList(); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllConsoleNames() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(String.class);
            var root = query.from(Console.class);
        
            query.select(root.get("consoleName"));
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllDeveloperNames() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(String.class);
            var root = query.from(Developer.class);
        
            query.select(root.get("developerName"));
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllGenreNames() {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(String.class);
            var root = query.from(Genre.class);
        
            query.select(root.get("genreName"));
        
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    

    public List<Game> searchGames(String searchInput, int pageLength) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Game.class);
            var root = query.from(Game.class);

            query.where(criteriaBuilder.like(root.get("title"), "%" + searchInput + "%"));

            return entityManager.createQuery(query).setMaxResults(pageLength).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
