package org.csystem.deviceapp.test.service;

import java.util.Scanner;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.deviceapp.repository.DeviceRepository;
import org.csystem.deviceapp.service.DeviceService;

public class SaveTestApp {
	
	public static void displayDevices(Iterable<? extends DeviceInfo> devices)
	{
		for (DeviceInfo di : devices)
			System.out.println(di);
	}
	
	public static void main(String [] args)
	{
		try (Scanner kb = new Scanner(System.in)) {
			DeviceService service = new DeviceService(DeviceRepository.INSTANCE);
			
			for (;;) {
				displayDevices(service.findAll());
				System.out.print("Name:");
				String name = kb.nextLine();
				if (name.equals("quit"))
					break;		
				
				System.out.print("Ip Address:");
				String ipAddress = kb.nextLine();
				
				DeviceInfo di = new DeviceInfo(name, ipAddress);
				
				service.save(di);
				
				System.out.println("Device Information:");
				System.out.println(di);
			}
		}
		catch (Throwable ex) {
			System.out.println(ex.getMessage());
		}
	}
}
