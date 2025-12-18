package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DeviceCatalogItem;

public interface DeviceCatalogService {
    
    DeviceCatalogItem createItem(DeviceCatalogItem item);
    DeviceCatalogItem updateActiveStatus(Long id,Boolean active);
    List<DeviceCatalogItem> getAllItems();
    
}
