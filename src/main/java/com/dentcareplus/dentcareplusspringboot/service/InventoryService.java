package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.InventoryDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Inventory;
import com.dentcareplus.dentcareplusspringboot.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Create a new inventory item
    public InventoryDTO createInventoryItem(Inventory inventory) {
        inventory.setCurrentQuantity(inventory.getQuantity()); // Initialize currentQuantity with the total quantity
        Inventory savedInventory = inventoryRepository.save(inventory);
        return mapToDTO(savedInventory);
    }

    // Get all inventory items
    public List<InventoryDTO> getAllInventoryItems() {
        return inventoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get inventory item by ID
    public InventoryDTO getInventoryItemById(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));
        return mapToDTO(inventory);
    }

    // Update an existing inventory item (except for quantity, which should remain as the original stocked amount)
    public InventoryDTO updateInventoryItem(Long inventoryId, Inventory inventoryDetails) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));

        inventory.setItemName(inventoryDetails.getItemName());
        inventory.setRestockLevel(inventoryDetails.getRestockLevel());
        inventory.setPurchaseDate(inventoryDetails.getPurchaseDate());
        inventory.setExpiryDate(inventoryDetails.getExpiryDate());
        inventory.setUnitCost(inventoryDetails.getUnitCost());
        inventory.setTotalCost(inventoryDetails.getTotalCost());
        inventory.setLastUpdated(inventoryDetails.getLastUpdated());

        // Important: Quantity remains unchanged, only currentQuantity can change
        if (inventoryDetails.getCurrentQuantity() != null) {
            inventory.setCurrentQuantity(inventoryDetails.getCurrentQuantity());
        }

        Inventory updatedInventory = inventoryRepository.save(inventory);
        return mapToDTO(updatedInventory);
    }

    // Delete an inventory item by ID
    public void deleteInventoryItem(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));
        inventoryRepository.delete(inventory);
    }

    // Decrease the currentQuantity of an inventory item by 1
    public InventoryDTO decreaseQuantity(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));

        if (inventory.getCurrentQuantity() <= 0) {
            throw new IllegalStateException("Current quantity is already zero or less.");
        }

        inventory.setCurrentQuantity(inventory.getCurrentQuantity() - 1); // Decrease currentQuantity

        // Update the status level based on the new currentQuantity
        updateStatusLevel(inventory);

        Inventory updatedInventory = inventoryRepository.save(inventory);
        return mapToDTO(updatedInventory);
    }

    // Helper method to update the stock status level based on currentQuantity
    private void updateStatusLevel(Inventory inventory) {
        if (inventory.getCurrentQuantity() <= 0) {
            inventory.setStatusLevel("Out of Stock");
        } else if (inventory.getCurrentQuantity() <= inventory.getRestockLevel()) {
            inventory.setStatusLevel("Low Stock");
        } else {
            inventory.setStatusLevel("In Stock");
        }
    }

    // Helper method to map Inventory entity to InventoryDTO
    private InventoryDTO mapToDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setInventoryId(inventory.getInventoryId());
        dto.setItemName(inventory.getItemName());
        dto.setQuantity(inventory.getQuantity()); // The total original stocked quantity
        dto.setCurrentQuantity(inventory.getCurrentQuantity()); // The current quantity that is available
        dto.setStatusLevel(inventory.getStatusLevel());
        dto.setRestockLevel(inventory.getRestockLevel());
        dto.setPurchaseDate(inventory.getPurchaseDate());
        dto.setExpiryDate(inventory.getExpiryDate());
        dto.setUnitCost(BigDecimal.valueOf(inventory.getUnitCost()));
        dto.setTotalCost(BigDecimal.valueOf(inventory.getTotalCost()));
        dto.setLastUpdated(inventory.getLastUpdated());
        return dto;
    }
}