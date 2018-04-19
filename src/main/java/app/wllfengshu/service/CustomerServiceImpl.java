package app.wllfengshu.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.wllfengshu.dao.CustomerDao;
import app.wllfengshu.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public String getCustomers(String username,String password) {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		System.out.println("service");
		Customer customer = customerDao.getCustomers(username,password);
		responseMap.put("data", customer);
		responseMap.put("count", 1);
		responseMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		return gson.toJson(responseMap);
	}
	@Override
	public String addCustomer() {
		customerDao.addCustomer();
		return null;
	}
	@Override
	public String getCustomer() {
		customerDao.getCustomer();
		return null;
	}
	@Override
	public String updateCustomer() {
		customerDao.updateCustomer();
		return null;
	}
	@Override
	public String deleteCustomer() {
		customerDao.deleteCustomer();
		return null;
	}


}
