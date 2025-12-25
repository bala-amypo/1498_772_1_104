package com.example.demo.service;

import com.example.demo.model.DeviceCatalogItem;

import java.util.List;
import java.util.Optional;

public interface DeviceCatalogService {

    DeviceCatalogItem createDevice(DeviceCatalogItem device);

    DeviceCatalogItem updateDevice(Long id, DeviceCatalogItem device);

    void deleteDevice(Long id);

    Optional<DeviceCatalogItem> getDeviceById(Long id);

    List<DeviceCatalogItem> getAllDevices();
}
