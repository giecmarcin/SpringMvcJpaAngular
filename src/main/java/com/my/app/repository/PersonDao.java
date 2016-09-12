package com.my.app.repository;

import com.my.app.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mgiec on 9/6/2016.
 */
@Repository
public class PersonDao {

    /**
     * The persistence context is the collection of all the managed objects of an EntityManager.
     */
    /**
     *
     transient — this instance is not, and never was, attached to a Session; this instance has no corresponding rows in the database; it’s usually just a new object that you have created to save to the database;
     persistent — this instance is associated with a unique Session object; upon flushing the Session to the database, this entity is guaranteed to have a corresponding consistent record in the database;
     detached — this instance was once attached to a Session (in a persistent state), but now it’s not; an instance enters this state if you evict it from the context, clear or close the Session, or put the instance through serialization/deserialization process.

     Persitence context - The generation of INSERT statements will occur only upon commiting the transaction, flushing or closing the session.


     * Metoda persist:
     * The persist method is intended for adding a new entity instance to the persistence context,
     * i.e. transitioning an instance from transient to persistent state
     * The specification for the persist method allows the implementation to issue statements for generating id on commit or flush,
     * and the id is not guaranteed to be non-null after calling this method, so you should not rely upon it
     * You may call this method on an already persistent instance, and nothing happens.
     * But if you try to persist a detached instance, the implementation is bound to throw an exception.

     merge
     The main intention of the merge method is to update a persistent entity instance with
     new field values from a detached entity instance.

     Person person = new Person();
     person.setName("John");
     session.save(person);
     session.evict(person);
     person.setName("Mary");
     Person mergedPerson = (Person) session.merge(person);

     Note that the merge method returns an object — it is the mergedPerson object that was loaded into persistence context and updated,
     not the person object that you passed as an argument. Those are two different objects, and the person object usually needs to be
     discarded (anyway, don’t count on it being attached to persistence context).
     As with persist method, the merge method is specified by JSR-220 to have certain semantics that you can rely upon:
     if the entity is detached, it is copied upon an existing persistent entity;
     if the entity is transient, it is copied upon a newly created persistent entity;
     this operation cascades for all relations with cascade=MERGE or cascade=ALL mapping;
     if the entity is persistent, then this method call does not have effect on it (but the cascading still takes place).
     */


    /*
     Update -  As with persist and save, the update method is an “original” Hibernate method that was present long before the merge method was added.
     Its semantics differs in several key points:
        * it acts upon passed object (its return type is void); the update method transitions the passed object from detached to persistent state;
        * this method throws an exception if you pass it a transient entity.
     In the following example we save the object, then evict (detach) it from the context, then change its name and call update.
     Notice that we don’t put the result of the update operation in a separate variable, because the update takes place on the person object itself.
      Basically we’re reattaching the existing entity instance to the persistence context — something the JPA specification does not allow us to do.
     */

    /*
        Dodanie flush() wymusza wrzucenie zmian do bazy, ale jeszce nie commituje transakcji.
        Od tej chwili zmiany są widoczne dla innych transakcji odczytujących dane z poziomem izolacji read uncommitted.
        Jeżeli po flush() nastąpi rollback, to wszystkie zmiany zostaną wycofane.
     */

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void savePerson(Person person){
        entityManager.persist(person);
        entityManager.flush();
    }


    public Person findById(long id){
        return entityManager.find(Person.class, id);
    }

    public Person findbyIdWithContacts(long id){
        TypedQuery query = entityManager.createQuery("select distinct  p from Person p left join fetch p.contacts where p.id=:id", Person.class);
        query.setParameter("id", id);
        List<Person> people = query.getResultList();
        if(people.isEmpty()){
            return null;
        }else{
            return people.get(0);
        }

    }
    public List<Person> findByNameAndLastname(String firstname, String lastname){
        TypedQuery query = entityManager.createQuery("select p from Person p left join fetch p.contacts where p.firstName=:firstname and p.lastname=:lastname",Person.class);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        List<Person> people = query.getResultList();
        return people;
    }

    public List<Person> findAll(){
        TypedQuery query = entityManager.createQuery("select distinct p from Person p left join fetch p.contacts", Person.class);
        return  query.getResultList();
    }

    @Transactional
    public Person mergePerson(Person person){
        Person p = entityManager.merge(person);
        return p;
    }
}
