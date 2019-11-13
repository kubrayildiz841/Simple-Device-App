package org.csystem.repository;

public class RepositoryException extends RuntimeException {	
	private static final long serialVersionUID = 1L;

	public RepositoryException(String msg)
	{
		super(msg);
	}
	
	public RepositoryException(String msg, Throwable cause)
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
