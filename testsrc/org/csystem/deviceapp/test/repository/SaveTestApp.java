package org.csystem.deviceapp.test.repository;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.deviceapp.repository.DeviceRepository;
import org.csystem.repository.RepositoryException;

import java.util.Scanner;

public class SaveTestApp {
	public static void displayDevices(Iterable<? extends DeviceInfo> devices)
	{
		System.out.println("All Devices:");
		for (DeviceInfo di : devices)
			System.out.println(di);		
	}
	
	public static void main(String [] args)
	{		
		try (Scanner kb = new Scanner(System.in)) {
			for (;;) {
				DeviceRepository repo = DeviceRepository.INSTANCE;
				
				displayDevices(repo.findAll());
				
				System.out.print("Device Name:");
				String name = kb.nextLine();
				
				if (name.equals("quit"))
					break;
				
				System.out.print("IP Address:");
				String ipAddress = kb.nextLine();
				
				DeviceInfo di = new DeviceInfo(name, ipAddress);
				
				repo.save(di);
				
				System.out.printf("Device Information:%s%n", di);				
			}			
			
		}
		catch (RepositoryException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
}
