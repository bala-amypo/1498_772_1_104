package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device Catalog Endpoints")
public class DeviceCatalogController {

    @Autowired
    private DeviceCatalogService deviceService;

    @Operation(summary = "Create a new device catalog item")
    @PostMapping
    public ResponseEntity<DeviceCatalogItem> createItem(
            @Valid @RequestBody DeviceCatalogItem item
    ) {
        return new ResponseEntity<>(
                deviceService.createItem(item),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Get all device catalog items")
    @GetMapping
    public ResponseEntity<List<DeviceCatalogItem>> getAllItems() {
        return ResponseEntity.ok(deviceService.getAllItems());
    }

    @Operation(summary = "Get device catalog item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> getItemById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(deviceService.getItemById(id));
    }

    @Operation(summary = "Update device active status")
    @PutMapping("/{id}/active")
    public ResponseEntity<DeviceCatalogItem> updateActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return ResponseEntity.ok(
                deviceService.updateActiveStatus(id, active)
        );
    }

    @Operation(summary = "Delete device catalog item")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        deviceService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
