package org.csystem.servicelayer;

public class ServiceException extends RuntimeException {	
	private static final long serialVersionUID = 1L;

	public ServiceException(String msg)
	{
		super(msg);
	}
	
	public ServiceException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public boolean isCausePresent()
	{
		return getCause() != null;
	}
	
	public String getMessage()
	{
		Throwable cause = getCause();
		
		return String.format("Message:%s%s", super.getMessage(), 
				cause == null ? "" : ", Cause Message:" + cause.getMessage());
	}
}
