package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")

public class DeviceCatalogController {

    private final DeviceCatalogService service;

    public DeviceCatalogController(DeviceCatalogService service) {
        this.service = service;
    }

    @Operation(summary = "Create device catalog item")
    @PostMapping
    public ResponseEntity<DeviceCatalogItem> create(
            @RequestBody DeviceCatalogItem item) {
        return ResponseEntity.ok(service.createItem(item));
    }

    @Operation(summary = "Get all device items")
    @GetMapping
    public ResponseEntity<List<DeviceCatalogItem>> getAll() {
        return ResponseEntity.ok(service.getAllItems());
    }

    @Operation(summary = "Get device by id")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Update device active status")
    @PutMapping("/{id}/active")
    public ResponseEntity<DeviceCatalogItem> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return ResponseEntity.ok(service.updateActiveStatus(id, active));
    }
}
