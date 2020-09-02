package com.huseby.banking;

import com.huseby.banking.entites.*;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "banking";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {


        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // read the existing entries and write to console
        Query q = em.createQuery("select t from Person t");
        List<Person> personList = q.getResultList();
        for (Person person : personList) {
            System.out.println(person);
        }
        System.out.println("Size: " + personList.size());

        // create new todo
        em.getTransaction().begin();

        Address address = new Address();
        address.setStreet("Gatenavn");
        address.setNumber(7);

        Person person = new Person();
        person.setName("Oddmund");

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        person.setAddresses(addresses);

        List<Person> residents = new ArrayList<>();
        residents.add(person);
        address.setResidents(residents);

        Bank bank = new Bank();
        bank.setName("DNB");

        Pincode pincode = new Pincode();
        pincode.setCount(10);
        pincode.setPincode("1234");

        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(1000);
        creditCard.setLimit(10000);
        creditCard.setNumber(12345678);
        creditCard.setPincode(pincode);
        creditCard.setBank(bank);

        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(creditCard);
        bank.setCreditCards(creditCards);

        em.persist(creditCard);
        em.persist(person);
        em.persist(bank);
        em.persist(address);
        em.persist(pincode);

        em.getTransaction().commit();

        em.close();
    }
}