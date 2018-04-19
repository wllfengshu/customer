package app.wllfengshu.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import app.wllfengshu.entity.Customer;

@Repository
public interface CustomerDao {
	public Customer getCustomers(@Param("username")String username,@Param("password")String password);

	public String addCustomer();

	public String getCustomer();

	public String updateCustomer();

	public String deleteCustomer();
	
}
