package org.csystem.deviceapp.jsp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import org.csystem.deviceapp.entity.DeviceInfo;
import org.csystem.deviceapp.repository.DeviceRepository;
import org.csystem.deviceapp.service.DeviceService;
import org.csystem.servicelayer.ServiceException;

public class DeviceAppJSP {	
	private static void printDeviceDetails(DeviceInfo di, JspWriter out) throws IOException
	{
		out.println("<h3>Device Information</h3>");			
		out.println(String.format("<h4>Id:%s</h4>", di.getId()));
		out.println(String.format("<h4>Name:%s</h4>", di.getName()));
		out.println(String.format("<h4>IpAddress:%s</h4>", di.getIpAddress()));
	}
	
	private DeviceAppJSP() {}
	
	
	
	public static void printDevicesAsTable(JspWriter out, Iterable<? extends DeviceInfo> devices) throws IOException
	{
		try {			
			for (DeviceInfo di : devices) {
				out.println("<tr>");
				out.println(String.format("<td>%d</td>", di.getId()));				
				out.println(String.format("<td>%s</td>", di.getName()));
				out.println(String.format("<td>%s</td>", di.getIpAddress()));
				out.println("</tr>");
			}			
		}
		catch (ServiceException ex) {
			out.println("Error in page:" + ex.getMessage());
		}				
	}
	
	public static void saveDeviceOperations(HttpServletRequest request, JspWriter out) throws IOException
	{
		try {
			if (request.getMethod().equals("POST")) {
				String name = request.getParameter("name");
				String ipAddress = request.getParameter("ip_address");
				
				//validation
				DeviceService service = new DeviceService(DeviceRepository.INSTANCE);				 
				DeviceInfo di = new DeviceInfo(name.trim(), ipAddress.trim());				
				
				service.save(di);
				printDeviceDetails(di, out);
			}
			else 
				out.println("Access forbidden");
		}
		catch (ServiceException ex) {
			out.println("Error in page:" + ex.getMessage());
		}		
	}
}
