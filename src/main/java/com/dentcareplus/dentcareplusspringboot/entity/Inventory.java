package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {
    @Id
    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    @Column(name = "inventory_id")
    private Long inventoryId; // Primary Key

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "current_quantity", nullable = false)
    private Integer currentQuantity;

    @Column(name = "status_level", nullable = false)
    private String statusLevel; // e.g., In Stock, Low Stock, Out of Stock

    @Column(name = "restock_level", nullable = false)
    private Integer restockLevel; // Minimum quantity before triggering restock

    @Column(name = "purchase_date")
    private LocalDate purchaseDate; // Date of purchase

    @Column(name = "expiry_date")
    private LocalDate expiryDate; // Expiry date of the item

    @Column(name = "unit_cost", nullable = false)
    @Min(value = 0, message = "Unit cost cannot be negative")
    private Double unitCost; // Cost per unit

    @Column(name = "total_cost", nullable = false)
    @Min(value = 0, message = "Total cost cannot be negative")
    private Double totalCost; // Total cost (quantity * unitCost)

    @Column(name = "last_updated")
    private LocalDate lastUpdated = LocalDate.now(); // Last update date of inventory

    // Lifecycle callback to calculate total cost and set current quantity before persisting or updating the entity
    @PrePersist
    @PreUpdate
    private void calculateTotalCost() {
        if (unitCost != null && quantity != null) {
            this.totalCost = unitCost * quantity;
        }
        if (this.currentQuantity == null) {
            this.currentQuantity = this.quantity; // Set currentQuantity to the same as quantity during creation
        }
    }

    // Method to decrease the quantity and update currentQuantity accordingly
    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
            this.currentQuantity = this.quantity; // Sync currentQuantity with quantity
        } else {
            throw new IllegalArgumentException("Quantity cannot be less than 0.");
        }
    }
}