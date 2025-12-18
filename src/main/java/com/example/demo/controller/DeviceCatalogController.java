package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;

@RestController
@RequestMapping("/devices")
public class DeviceCatalogController {

    private final DeviceCatalogService service;

    public DeviceCatalogController(DeviceCatalogService service) {
        this.service = service;
    }

    @PostMapping
    public DeviceCatalogItem create(@RequestBody DeviceCatalogItem item) {
        return service.createItem(item);
    }

    @GetMapping
    public List<DeviceCatalogItem> getAll() {
        return service.getAllItems();
    }

    @PutMapping("/{id}")
    public DeviceCatalogItem updateStatus(@PathVariable Long id,@RequestParam Boolean active) {
        return service.updateActiveStatus(id, active);
    }
}