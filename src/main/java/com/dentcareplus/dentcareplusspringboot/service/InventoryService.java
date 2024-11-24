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

    // Update an existing inventory item
    public InventoryDTO updateInventoryItem(Long inventoryId, Inventory inventoryDetails) {
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

        Inventory updatedInventory = inventoryRepository.save(inventory);
        return mapToDTO(updatedInventory);
    }

    // Delete an inventory item by ID
    public void deleteInventoryItem(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item with ID " + inventoryId + " not found."));
        inventoryRepository.delete(inventory);
    }

    // Helper method to map Inventory entity to InventoryDTO
    private InventoryDTO mapToDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setInventoryId(inventory.getInventoryId());
        dto.setItemName(inventory.getItemName());
        dto.setQuantity(inventory.getQuantity());
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