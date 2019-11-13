package org.csystem.deviceapp.test.repository;

import java.util.Scanner;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.deviceapp.repository.DeviceRepository;
import org.csystem.repository.RepositoryException;

public class FindByNameApp {
	public static void main(String [] args)
	{		
		try (Scanner kb = new Scanner(System.in)) {
			for (;;) {
				DeviceRepository repo = DeviceRepository.INSTANCE;
				
				System.out.println("All Devices:");
				for (DeviceInfo di : repo.findAll())
					System.out.println(di);
				
				System.out.print("Name:");
				String name = kb.nextLine();
				
				if (name.equals("quit"))
					break;				
				
				for (DeviceInfo di : repo.findByName(name))
					System.out.println(di);
			}		
			
		}
		catch (RepositoryException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
