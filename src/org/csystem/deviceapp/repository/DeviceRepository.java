package org.csystem.deviceapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.repository.RepositoryException;
 
public enum DeviceRepository implements IDeviceRepository {
	INSTANCE;
	private static final String URL = "jdbc:postgresql://localhost:5432/devicesappdb";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "csd1993";	
	
	
	private <S extends DeviceInfo> S saveProc(Connection con, S d) throws SQLException
	{
		String sqlCmd = "insert into devices (name, ip_address) values (?, ?)";
		
		try (PreparedStatement stmt = con.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS)) {
			con.setAutoCommit(false); // Transaction başlatıyoruz
			stmt.setString(1, d.getName()); // Dikkat ? ile belirtilen parametrelerin indeksi 1(bir) den başlar
			stmt.setString(2, d.getIpAddress());
			
			int count = stmt.executeUpdate();
			
			if (count != 0) { // Dikkat insert için gerek olmasa da gösterdik 
				ResultSet rs = stmt.getGeneratedKeys();
				
				rs.next();
				
				d.setId(rs.getInt(1));												
				con.commit();
			}
			else 
				con.rollback();
			
			return d;
		}	
		catch (Throwable ex) {
			con.rollback();
			throw ex;
		}
	}
	
	private DeviceInfo getDevice(ResultSet rs) throws SQLException
	{
		return new DeviceInfo(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
	
	@Override
	public <S extends DeviceInfo> S save(S d)
	{		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			return saveProc(con, d);
		}
		catch (Throwable ex) {
			throw new RepositoryException("exception in save", ex);
		}
	}

	@Override
	public Iterable<? extends DeviceInfo> findAll()
	{
		String sqlCmd = "select * from devices order by name";
		
		List<DeviceInfo> devices = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = con.createStatement()) {
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while (rs.next())
				devices.add(getDevice(rs));					
			
			return devices;
		}	
		catch (Throwable ex) {
			throw new RepositoryException("exception in findAll", ex);
		}						
	}

	@Override
	public Optional<DeviceInfo> findById(Integer id)
	{
		String sqlCmd = "select * from devices where device_id=?";
		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
				PreparedStatement stmt = con.prepareStatement(sqlCmd)) {
			stmt.setInt(1, id);		
			
			ResultSet rs = stmt.executeQuery();
			
			return rs.next() ? Optional.of(getDevice(rs)) : Optional.empty();
		}	
		catch (Throwable ex) {
			throw new RepositoryException("exception in findById", ex);
		}			
	}
	
	@Override
	public Iterable<DeviceInfo> findByName(String name)
	{
		String sqlCmd = "select * from devices where name=?";
		List<DeviceInfo> devices = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
				PreparedStatement stmt = con.prepareStatement(sqlCmd)) {
			stmt.setString(1, name);		
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
				devices.add(getDevice(rs));
			
			return devices;
		}	
		catch (Throwable ex) {
			throw new RepositoryException("exception in findByName", ex);
		}			
	}
	
	@Override
	public Iterable<DeviceInfo> findByNameContains(String name)
	{
		String sqlCmd = "select * from devices where name like ?";
		List<DeviceInfo> devices = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
				PreparedStatement stmt = con.prepareStatement(sqlCmd)) {
			stmt.setString(1, "%" + name + "%");		
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
				devices.add(getDevice(rs));
			
			return devices;
		}	
		catch (Throwable ex) {
			throw new RepositoryException("exception in findByNameContains", ex);
		}			
	}

	@Override
	public void delete(DeviceInfo entity)
	{
		throw new UnsupportedOperationException("delete not supported for device");
	}

	@Override
	public boolean existsById(Integer id)
	{
		throw new UnsupportedOperationException("existById not supported for device");
	}

	@Override
	public long count()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
			
}









