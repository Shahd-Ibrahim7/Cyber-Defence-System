package com.ecdtms.service;

import com.ecdtms.repository.DeviceRepository;

import java.sql.SQLException;
import java.util.List;

public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService() {
        this.deviceRepository = new DeviceRepository();
    }

    public List<String> getAllDeviceIds() throws SQLException {
        return deviceRepository.getAllDeviceIds();
    }
}

