package org.csystem.deviceapp.repository;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.repository.IRepository;

public interface IDeviceRepository extends IRepository<DeviceInfo, Integer> {
	Iterable<DeviceInfo> findByName(String name);
	Iterable<DeviceInfo> findByNameContains(String name);
}
