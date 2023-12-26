package be.kuleuven.VGHF.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

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
    
    public List<User> getAllUsers() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(User.class);
        var root = query.from(User.class);
    
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

    // public void saveNewConsole(Console console) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.persist(console);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         if (entityManager.getTransaction().isActive()) {
    //             entityManager.getTransaction().rollback();
    //         }
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void saveNewCopy(Copy copy) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.persist(copy);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         if (entityManager.getTransaction().isActive()) {
    //             entityManager.getTransaction().rollback();
    //         }
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void saveNewUser(User user) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.persist(user);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         if (entityManager.getTransaction().isActive()) {
    //             entityManager.getTransaction().rollback();
    //         }
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void saveNewDeveloper(Developer developer) {
    //     try {
    //         entityManager.persist(developer);
    //     } catch (Exception exception) {
    //         if (entityManager.getTransaction().isActive()) {
    //             entityManager.getTransaction().rollback();
    //         }
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void saveNewGame(Game game) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.persist(game);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         if (entityManager.getTransaction().isActive()) {
    //             entityManager.getTransaction().rollback();
    //         }
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void saveNewGenre(Genre genre) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.persist(genre);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         if (entityManager.getTransaction().isActive()) {
    //             entityManager.getTransaction().rollback();
    //         }
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void saveNewMonetaryTransaction(MonetaryTransaction monetaryTransaction) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.persist(monetaryTransaction);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         if (entityManager.getTransaction().isActive()) {
    //             entityManager.getTransaction().rollback();
    //         }
    //         exception.printStackTrace();
    //     }
    // }
    
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

    // public void updateConsole(Console console) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.merge(console);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void updateCopy(Copy copy) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.merge(copy);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void updateUser(User user) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.merge(user);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void updateDeveloper(Developer developer) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.merge(developer);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void updateGame(Game game) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.merge(game);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void updateGenre(Genre genre) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.merge(genre);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    // }
    
    // public void updateMonetaryTransaction(MonetaryTransaction monetaryTransaction) {
    //     try {
    //         entityManager.getTransaction().begin();
    //         entityManager.merge(monetaryTransaction);
    //         entityManager.getTransaction().commit();
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    // }

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

    public List<Copy> getPageOfCopies(int firstCopyIndex, int pageLength) {
        try {
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Copy.class);
            var root = query.from(Copy.class);
            query.where(criteriaBuilder.greaterThanOrEqualTo(root.get("copyID"), firstCopyIndex),
                        criteriaBuilder.equal(root.get("availability"), Availability.AVAILABLE),
                        criteriaBuilder.or(criteriaBuilder.notEqual(root.get("purchasePrice"), 0),
                                            criteriaBuilder.notEqual(root.get("rentPrice"), 0)));
            return entityManager.createQuery(query).setMaxResults(pageLength).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
