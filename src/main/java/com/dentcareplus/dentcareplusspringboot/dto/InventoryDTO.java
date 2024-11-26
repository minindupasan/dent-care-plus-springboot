package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InventoryDTO {

    private Long inventoryId;
    private String itemName;
    private int quantity;
    private int currentQuantity;
    private String statusLevel;
    private int restockLevel;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private BigDecimal unitCost;
    private BigDecimal totalCost;
    private LocalDate lastUpdated;
}