package com.cantarino.customer.customer.models;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    private long id;
    private String name;
    private String address;

    public Customer() { }
    public Customer(long id , String name ,  String address ) {
        this.id  = id;
        this.address = address;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name" , nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address" , nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Customer [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ']';
    }
}
