package org.example.entity;

import java.util.Objects;

public class Employee {
    // Test reflection: alan adları tam olarak aşağıdaki gibi ve private olmalı
    private Long id;
    private String firstname;
    private String lastname;

    public Employee(Long id, String firstname, String lastname) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Testler int ile de çağırıyor
    public Employee(int id, String firstname, String lastname) {
        this((long) id, firstname, lastname);
    }

    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname()  { return lastname; }

    // Eşitlik id’ye göre
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee that = (Employee) o;
        return id.equals(that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }

    @Override public String toString() {
        return "Employee{id=" + id + ", firstName='" + firstname + "', lastName='" + lastname + "'}";
    }
}
