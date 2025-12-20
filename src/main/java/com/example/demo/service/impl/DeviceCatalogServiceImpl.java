package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {
    
    private final DeviceCatalogItemRepository repo;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository repo){
        this.repo=repo;
    }
    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item){
        return repo.save(item);
        
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id,Boolean active){
        DeviceCatalogItem item = repo.findById(id).orElseThrow();
        item.setActive(active);
        return repo.save(item);
    }

    @Override
    public List<DeviceCatalogItem> getAllItems(){
        return repo.findAll();
    }
}
