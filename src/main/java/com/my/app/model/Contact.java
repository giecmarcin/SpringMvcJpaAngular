package com.my.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Marcin on 01.09.2016.
 */

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private long id;
    private String type;
    private String value;


    public Contact() {
    }

    public Contact(long id, String type, String value, int idPerson) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public Contact(String type, String value, int idPerson) {
        this.type = type;
        this.value = value;
    }

    public Contact(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (type != null ? !type.equals(contact.type) : contact.type != null) return false;
        return value != null ? value.equals(contact.value) : contact.value == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
