package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Inventory;
import com.dentcareplus.dentcareplusspringboot.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Create a new inventory item
    public Inventory createInventoryItem(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // Get all inventory items
    public List<Inventory> getAllInventoryItems() {
        List<Inventory> inventoryItems = inventoryRepository.findAll();
        if (inventoryItems.isEmpty()) {
            throw new IllegalStateException("No inventory items found.");
        }
        return inventoryItems;
    }

    // Get inventory item by ID
    public Inventory getInventoryItemById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));
    }

    // Update an existing inventory item
    public Inventory updateInventoryItem(Long inventoryId, Inventory inventoryDetails) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));

        inventory.setItemName(inventoryDetails.getItemName());
        inventory.setQuantity(inventoryDetails.getQuantity());
        inventory.setStatusLevel(inventoryDetails.getStatusLevel());
        inventory.setRestockLevel(inventoryDetails.getRestockLevel());
        inventory.setPurchaseDate(inventoryDetails.getPurchaseDate());
        inventory.setExpiryDate(inventoryDetails.getExpiryDate());
        inventory.setUnitCost(inventoryDetails.getUnitCost());
        inventory.setTotalCost(inventoryDetails.getTotalCost());
        inventory.setLastUpdated(inventoryDetails.getLastUpdated());

        return inventoryRepository.save(inventory);
    }

    // Delete an inventory item by ID
    public boolean deleteInventoryItem(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));
        inventoryRepository.delete(inventory);
        return true;
    }
}