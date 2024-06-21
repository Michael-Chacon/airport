package org.vuelosGlobales.salesAgent.customer.domain;

public class Customer {
    private int id;
    private String name;
    private int age;
    private int idDocument;

    public Customer() {
    }

    public Customer(int id, String name, int age, int idDocument) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.idDocument = idDocument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }
}
