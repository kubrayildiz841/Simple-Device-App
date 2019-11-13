<%@page import="org.csystem.deviceapp.jsp.DeviceAppJSP"%>
<%@page import="org.csystem.deviceapp.entity.DeviceInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.csystem.deviceapp.repository.DeviceRepository"%>
<%@page import="org.csystem.deviceapp.service.DeviceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<div><input type="text" name="name"/></div>
		<div><input type="submit" value="Find"/></div>
	</form>
	
	<%
		if (request.getMethod().equals("POST")) {
			String name = request.getParameter("name");
			//validation
			DeviceService service = new DeviceService(DeviceRepository.INSTANCE);			
			Iterable<DeviceInfo> devices = service.findByNameContains(name.trim());			
	%>
	<h3>All Devices</h3>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Ip Adress</th>
		</tr>	
		<%
			DeviceAppJSP.printDevicesAsTable(out, devices);
		%>	
	</table>	
	<%
		}
	%>
</body>
</html>