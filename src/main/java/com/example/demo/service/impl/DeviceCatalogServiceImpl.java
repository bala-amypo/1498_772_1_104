package com.example.demo.service.impl;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {

    private final DeviceCatalogItemRepository deviceRepo;

    @Autowired
    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    @Override
    public DeviceCatalogItem createDevice(DeviceCatalogItem device) {
        if (deviceRepo.existsByDeviceCode(device.getDeviceCode())) {
            throw new IllegalArgumentException("Device code already exists: " + device.getDeviceCode());
        }
        return deviceRepo.save(device);
    }

    @Override
    public DeviceCatalogItem updateDevice(Long id, DeviceCatalogItem device) {
        DeviceCatalogItem existing = deviceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + id));

        existing.setDeviceCode(device.getDeviceCode());
        existing.setDeviceType(device.getDeviceType());
        existing.setModel(device.getModel());
        existing.setMaxAllowedPerEmployee(device.getMaxAllowedPerEmployee());
        existing.setActive(device.getActive());

        return deviceRepo.save(existing);
    }

    @Override
    public void deleteDevice(Long id) {
        if (!deviceRepo.existsById(id)) {
            throw new IllegalArgumentException("Device not found with id: " + id);
        }
        deviceRepo.deleteById(id);
    }

    @Override
    public Optional<DeviceCatalogItem> getDeviceById(Long id) {
        return deviceRepo.findById(id);
    }

    @Override
    public List<DeviceCatalogItem> getAllDevices() {
        return deviceRepo.findAll();
    }
}
