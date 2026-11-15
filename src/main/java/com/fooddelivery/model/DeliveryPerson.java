package com.fooddelivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "delivery_persons")
public class DeliveryPerson {

    @Id
    private Long deliveryPersonId;

    @NotBlank
    private String name;

    @NotNull
    private Long contactNo;

    public DeliveryPerson() {}

    public DeliveryPerson(Long deliveryPersonId, String name, Long contactNo) {
        this.deliveryPersonId = deliveryPersonId;
        this.name = name;
        this.contactNo = contactNo;
    }

    public Long getDeliveryPersonId() { return deliveryPersonId; }
    public void setDeliveryPersonId(Long deliveryPersonId) { this.deliveryPersonId = deliveryPersonId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getContactNo() { return contactNo; }
    public void setContactNo(Long contactNo) { this.contactNo = contactNo; }

    @Override
    public String toString() {
        return "DeliveryPerson{" +
                "deliveryPersonId=" + deliveryPersonId +
                ", name='" + name + '\'' +
                ", contactNo=" + contactNo +
                '}';
    }
}
