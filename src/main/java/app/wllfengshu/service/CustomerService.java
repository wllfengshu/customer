package app.wllfengshu.service;

public interface CustomerService {
	
	public String getCustomers(String username,String password);

	public String addCustomer();

	public String getCustomer();

	public String updateCustomer();

	public String deleteCustomer();
	
}
