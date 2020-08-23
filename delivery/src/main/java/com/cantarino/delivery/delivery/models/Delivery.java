package com.cantarino.delivery.delivery.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id" , nullable = false)
    private Long order_id;

    @Column(name = "customer_id" , nullable = false)
    private Long customer_id;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "fee" , nullable = false)
    private Long fee;

    public Delivery() {
    }

    public Delivery(Long id , Long order_id , Long customer_id ,  String address , Long fee) {
        this.id = id;
        this.address = address;
        this.fee = fee;
        this.order_id = order_id;
        this.customer_id = customer_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery delivery = (Delivery) o;

        return Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", address='" + address + '\'' +
                ", fee=" + fee +
                '}';
    }
}
