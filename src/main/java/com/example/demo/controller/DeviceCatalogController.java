package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device Catalog Endpoints")
public class DeviceCatalogController {

    private final DeviceCatalogService deviceService;

    // ✅ Constructor Injection
    public DeviceCatalogController(DeviceCatalogService deviceService) {
        this.deviceService = deviceService;
    }

    @Operation(summary = "Create a new device catalog item")
    @PostMapping
    public ResponseEntity<DeviceCatalogItem> createDevice(
            @RequestBody DeviceCatalogItem device) {

        DeviceCatalogItem created = deviceService.createDevice(device);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all device catalog items")
    @GetMapping
    public ResponseEntity<List<DeviceCatalogItem>> getAllDevices() {
        List<DeviceCatalogItem> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @Operation(summary = "Get device catalog item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> getDeviceById(@PathVariable Long id) {
        DeviceCatalogItem device = deviceService.getDeviceById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + id));
        return ResponseEntity.ok(device);
    }

    @Operation(summary = "Update a device catalog item")
    @PutMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> updateDevice(
            @PathVariable Long id,
            @RequestBody DeviceCatalogItem device) {

        DeviceCatalogItem updated = deviceService.updateDevice(id, device);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a device catalog item")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update active status of a device catalog item")
    @PutMapping("/{id}/active")
    public ResponseEntity<DeviceCatalogItem> updateActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        DeviceCatalogItem device = deviceService.getDeviceById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + id));
        device.setActive(active);
        DeviceCatalogItem updated = deviceService.updateDevice(id, device);
        return ResponseEntity.ok(updated);
    }
}
