package app.wllfengshu.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.wllfengshu.service.CustomerService;

@Controller
@Path("/customer")
public class CustomerRest {
	@Autowired
	private CustomerService customerService ;
    
	/**
	 * @title 查询所有顾客信息
	 * @param request
	 * @param response
	 * @return
	 */
    @GET
    public Response getCustomers(@QueryParam("username")String username,@QueryParam("password")String password) {
		System.out.println("***rest"+username+password);
		String responseStr = customerService.getCustomers(username,password);
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 添加顾客信息
     * @param request
     * @param response
     * @return
     */
    @POST
    public Response addCustomer() {
		System.out.println("***rest");	
		String responseStr = customerService.addCustomer();
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 查询顾客详情
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("/{customer_id}/")
    public Response getCustomer(@Context HttpServletRequest request,@Context HttpServletResponse response) {
		System.out.println("***rest");	
		String responseStr = customerService.getCustomer();
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 修改顾客信息
     * @param request
     * @param response
     * @return
     */
    @PUT
    @Path("/{customer_id}/")
    public Response updateCustomer(@Context HttpServletRequest request,@Context HttpServletResponse response) {
		System.out.println("***rest");	
		String responseStr = customerService.updateCustomer();
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 删除顾客信息
     * @param request
     * @param response
     * @return
     */
    @DELETE
    @Path("/{customer_id}/")
    public Response deleteCustomer(@Context HttpServletRequest request,@Context HttpServletResponse response) {
		System.out.println("***rest");	
		String responseStr = customerService.deleteCustomer();
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
}
