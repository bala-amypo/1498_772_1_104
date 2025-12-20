package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceCatalogServiceImpl
        implements DeviceCatalogService {

    @Autowired
    private DeviceCatalogItemRepository deviceRepository;

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {

        if (deviceRepository.findByDeviceCode(item.getDeviceCode()).isPresent()) {
            throw new BadRequestException("DeviceCode already exists");
        }

        if (item.getMaxAllowedPerEmployee() == null
                || item.getMaxAllowedPerEmployee() <= 0) {
            throw new BadRequestException("maxAllowedPerEmployee");
        }

        return deviceRepository.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, boolean active) {

        DeviceCatalogItem item = getItemById(id);
        item.setActive(active);
        return deviceRepository.save(item);
    }

    @Override
    public DeviceCatalogItem getItemById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found"));
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return deviceRepository.findAll();
    }

    @Override
    public void deleteItem(Long id) {

        DeviceCatalogItem item = getItemById(id);
        deviceRepository.delete(item);
    }
}
