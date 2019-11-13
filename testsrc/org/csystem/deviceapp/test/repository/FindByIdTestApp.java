package org.csystem.deviceapp.test.repository;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.deviceapp.repository.DeviceRepository;
import org.csystem.repository.RepositoryException;

import java.util.Optional;
import java.util.Scanner;

public class FindByIdTestApp {
	public static void main(String [] args)
	{		
		try (Scanner kb = new Scanner(System.in)) {
			for (;;) {
				DeviceRepository repo = DeviceRepository.INSTANCE;
				
				System.out.println("All Devices:");
				for (DeviceInfo di : repo.findAll())
					System.out.println(di);
				
				System.out.print("Id:");
				int id = Integer.parseInt(kb.nextLine());
				
				if (id == 0)
					break;
				
				Optional<DeviceInfo> opDev = repo.findById(id);
				
				if (opDev.isPresent())				
					System.out.printf("Device Information:%s%n", opDev.get());
				else
					System.out.println("Not found");				
			}		
			
		}
		catch (RepositoryException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
