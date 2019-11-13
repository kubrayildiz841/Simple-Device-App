package org.csystem.deviceapp.service;

import java.util.Optional;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.deviceapp.repository.IDeviceRepository;
import org.csystem.repository.RepositoryException;
import org.csystem.servicelayer.ServiceException;

public class DeviceService {
	private final IDeviceRepository m_repository;
	
	public DeviceService(IDeviceRepository repository) //dependency injection
	{
		m_repository = repository;
	}
	
	public DeviceInfo save(DeviceInfo di)
	{
		try {
			return m_repository.save(di);
		}
		catch (RepositoryException ex) {
			throw new ServiceException("exception in Service.save", ex.getCause());
		}
	}
	
	public Iterable<? extends DeviceInfo> findAll()
	{
		try {			
			return m_repository.findAll();
		}
		catch (RepositoryException ex) {
			throw new ServiceException("exception in Service.findAll", ex.getCause());
		}		
	}	
	
	public Optional<DeviceInfo> findById(int id)
	{
		try {
			return m_repository.findById(id);
		}
		catch (RepositoryException ex) {
			throw new ServiceException("exception in Service.findById", ex.getCause());
		}
	}
	
	public Iterable<DeviceInfo> findByName(String name)
	{
		try {
			return m_repository.findByName(name);
		}
		catch (RepositoryException ex) {
			throw new ServiceException("exception in Service.findByName", ex.getCause());
		}
	}
	
	public Iterable<DeviceInfo> findByNameContains(String name)
	{
		try {			
			return m_repository.findByNameContains(name);
		}
		catch (RepositoryException ex) {
			throw new ServiceException("exception in Service.findByNameContains", ex.getCause());
		}
	}
}

