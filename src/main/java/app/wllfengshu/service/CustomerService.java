package app.wllfengshu.service;

import app.wllfengshu.exception.NotAcceptableException;

public interface CustomerService {
	
	public String getCustomers(String sessionId,String user_id) throws NotAcceptableException;

	public String addCustomer(String customer,String sessionId,String user_id) throws NotAcceptableException;

	public String getCustomer(String customer_id,String sessionId,String user_id) throws NotAcceptableException;

	public String updateCustomer(String customer,String sessionId,String user_id) throws NotAcceptableException;

	public String deleteCustomer(String customer_id,String sessionId,String user_id) throws NotAcceptableException;
	
}
