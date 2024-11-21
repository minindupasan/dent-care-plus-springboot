package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Inventory;
import com.dentcareplus.dentcareplusspringboot.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // POST request to create a new inventory item
    @PostMapping("/create")
    public ResponseEntity<Inventory> createInventoryItem(@RequestBody Inventory inventory) {
        Inventory newInventory = inventoryService.createInventoryItem(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInventory); // Return 201 Created with the new inventory item
    }

    // GET request to retrieve all inventory items
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventoryItems() {
        List<Inventory> inventoryItems = inventoryService.getAllInventoryItems();

        if (inventoryItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content if no inventory items found
        }

        return ResponseEntity.ok(inventoryItems); // Return 200 OK with the list of inventory items
    }

    // GET request to retrieve a specific inventory item by ID
    @GetMapping("/{inventoryId}")
    public ResponseEntity<Inventory> getInventoryItemById(@PathVariable Long inventoryId) {
        Inventory inventory = inventoryService.getInventoryItemById(inventoryId);

        if (inventory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if the inventory item doesn't exist
        }

        return ResponseEntity.ok(inventory); // Return 200 OK with the inventory item
    }

    // PUT request to update an inventory item
    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<Inventory> updateInventoryItem(@PathVariable Long inventoryId, @RequestBody Inventory inventoryDetails) {
        Inventory updatedInventory = inventoryService.updateInventoryItem(inventoryId, inventoryDetails);

        if (updatedInventory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if the inventory item doesn't exist
        }

        return ResponseEntity.ok(updatedInventory); // Return 200 OK with the updated inventory item
    }

    // DELETE request to remove an inventory item
    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long inventoryId) {
        Inventory inventory = inventoryService.getInventoryItemById(inventoryId);

        if (inventory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if the inventory item doesn't exist
        }

        boolean isDeleted = inventoryService.deleteInventoryItem(inventoryId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content if successfully deleted
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 if something went wrong during deletion
    }
}