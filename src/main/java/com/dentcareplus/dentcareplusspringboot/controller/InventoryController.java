package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.InventoryDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Inventory;
import com.dentcareplus.dentcareplusspringboot.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // POST request to create a new inventory item
    @PostMapping("/create")
    public ResponseEntity<InventoryDTO> createInventoryItem(@RequestBody Inventory inventory) {
        InventoryDTO newInventory = inventoryService.createInventoryItem(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInventory);
    }

    // GET request to retrieve all inventory items
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventoryItems() {
        List<InventoryDTO> inventoryItems = inventoryService.getAllInventoryItems();

        if (inventoryItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(inventoryItems);
    }

    // GET request to retrieve a specific inventory item by ID
    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryDTO> getInventoryItemById(@PathVariable Long inventoryId) {
        InventoryDTO inventory = inventoryService.getInventoryItemById(inventoryId);
        return ResponseEntity.ok(inventory);
    }

    // PUT request to update an inventory item
    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<InventoryDTO> updateInventoryItem(@PathVariable Long inventoryId, @RequestBody Inventory inventoryDetails) {
        InventoryDTO updatedInventory = inventoryService.updateInventoryItem(inventoryId, inventoryDetails);
        return ResponseEntity.ok(updatedInventory);
    }

    // DELETE request to delete an inventory item by ID
    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long inventoryId) {
        inventoryService.deleteInventoryItem(inventoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}