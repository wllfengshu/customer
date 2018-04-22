package app.wllfengshu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.wllfengshu.dao.CustomerDao;
import app.wllfengshu.entity.Customer;
import app.wllfengshu.exception.NotAcceptableException;
import app.wllfengshu.util.AuthUtil;
import net.sf.json.JSONObject;

@Service
public class CustomerServiceImpl implements CustomerService {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public String getCustomers(String sessionId,String user_id) throws NotAcceptableException {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		AuthUtil.instance.checkUserInfo(sessionId, user_id);
		List<Customer> customers = customerDao.getCustomers(user_id);
		responseMap.put("data", customers);
		responseMap.put("count", customers.size());
		responseMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		return gson.toJson(responseMap);
	}
	
	@Override
	public String addCustomer(String customer,String sessionId,String user_id) throws NotAcceptableException {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		String customer_id = UUID.randomUUID().toString();
		AuthUtil.instance.checkUserInfo(sessionId, user_id);
		JSONObject customerJson = null;
		Customer customerTemp=null;
		try{
			customerJson=JSONObject.fromObject(customer);
			customerTemp=(Customer) JSONObject.toBean(customerJson,Customer.class);
			customerTemp.setId(customer_id);
			customerTemp.setUser_id(user_id);
		}catch(Exception e){
			throw new NotAcceptableException("数据格式错误");
		}
		customerDao.addCustomer(customerTemp);
		responseMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		return gson.toJson(responseMap);
	}
	
	@Override
	public String getCustomer(String customer_id,String sessionId,String user_id) throws NotAcceptableException {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		AuthUtil.instance.checkUserInfo(sessionId, user_id);
		Customer customer = customerDao.getCustomer(customer_id);
		responseMap.put("data", customer);
		responseMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		return gson.toJson(responseMap);
	}
	
	@Override
	public String updateCustomer(String customer,String sessionId,String user_id) throws NotAcceptableException {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		AuthUtil.instance.checkUserInfo(sessionId, user_id);
		JSONObject customerJson = null;
		Customer customerTemp=null;
		try{
			customerJson=JSONObject.fromObject(customer);
			customerTemp=(Customer) JSONObject.toBean(customerJson,Customer.class);
		}catch(Exception e){
			throw new NotAcceptableException("数据格式错误");
		}
		customerDao.updateCustomer(customerTemp);
		responseMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		return gson.toJson(responseMap);
	}
	
	@Override
	public String deleteCustomer(String customer_id,String sessionId,String user_id) throws NotAcceptableException {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		AuthUtil.instance.checkUserInfo(sessionId, user_id);
		customerDao.deleteCustomer(customer_id);
		responseMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		return gson.toJson(responseMap);
	}

}
