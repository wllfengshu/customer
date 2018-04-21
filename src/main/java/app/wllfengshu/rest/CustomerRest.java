package app.wllfengshu.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.wllfengshu.exception.NotAcceptableException;
import app.wllfengshu.service.CustomerService;
import app.wllfengshu.util.LogUtils;

@Controller
@Path("/customer")
public class CustomerRest {
	
	@Autowired
	private CustomerService customerService ;
    
	/**
	 * @title 查询所有顾客信息
	 * @param sessionId
	 * @param request
	 * @param response
	 * @return
	 */
    @GET
    public Response getCustomers(@HeaderParam(value="sessionId") String sessionId,
    		@HeaderParam(value="user_id") String user_id,
    		@Context HttpServletRequest request,@Context HttpServletResponse response) {
		String responseStr = null;
		try{
			responseStr=customerService.getCustomers(sessionId,user_id);
		}catch (NotAcceptableException e) {
			System.out.println(e);
			return Response.serverError().entity("{\"message\":\""+e.getMessage()+"\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(406).build();
		}catch (Exception e) {
			System.out.println(e);
			LogUtils.error(this, e, "# CustomerRest-getCustomers,has exception #");
			return Response.serverError().entity("{\"message\":\"has exception\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(500).build();
		}
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 添加顾客信息
     * @param customer 顾客信息数据
     * @param sessionId
     * @param request
     * @param response
     * @return
     */
    @POST
    public Response addCustomer(String customer,
    		@HeaderParam(value="sessionId") String sessionId,
    		@HeaderParam(value="user_id") String user_id,
    		@Context HttpServletRequest request,@Context HttpServletResponse response) {
		String responseStr = null;
		try{
			responseStr=customerService.addCustomer(customer,sessionId,user_id);
		}catch (NotAcceptableException e) {
			System.out.println(e);
			return Response.serverError().entity("{\"message\":\""+e.getMessage()+"\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(406).build();
		}catch (Exception e) {
			System.out.println(e);
			LogUtils.error(this, e, "# CustomerRest-addCustomer,has exception #");
			return Response.serverError().entity("{\"message\":\"has exception\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(500).build();
		}
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 查询顾客详情
     * @param customer_id 顾客ID
     * @param sessionId
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("/{customer_id}/")
    public Response getCustomer(@PathParam("customer_id")String customer_id,
    		@HeaderParam(value="sessionId") String sessionId,
    		@HeaderParam(value="user_id") String user_id,
    		@Context HttpServletRequest request,@Context HttpServletResponse response) {
		String responseStr = null;
		try{
			responseStr=customerService.getCustomer(customer_id,sessionId,user_id);
		}catch (NotAcceptableException e) {
			System.out.println(e);
			return Response.serverError().entity("{\"message\":\""+e.getMessage()+"\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(406).build();
		}catch (Exception e) {
			System.out.println(e);
			LogUtils.error(this, e, "# CustomerRest-getCustomer,has exception #");
			return Response.serverError().entity("{\"message\":\"has exception\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(500).build();
		}
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 修改顾客信息
     * @param customer 顾客信息数据
     * @param sessionId
     * @param request
     * @param response
     * @return
     */
    @PUT
    @Path("/{customer_id}/")
    public Response updateCustomer(String customer,
    		@HeaderParam(value="sessionId") String sessionId,
    		@HeaderParam(value="user_id") String user_id,
    		@Context HttpServletRequest request,@Context HttpServletResponse response) {
		String responseStr = null;
		try{
			responseStr=customerService.updateCustomer(customer,sessionId,user_id);//前端的customer中必须包含customer_id
		}catch (NotAcceptableException e) {
			System.out.println(e);
			return Response.serverError().entity("{\"message\":\""+e.getMessage()+"\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(406).build();
		}catch (Exception e) {
			System.out.println(e);
			LogUtils.error(this, e, "# CustomerRest-updateCustomer,has exception #");
			return Response.serverError().entity("{\"message\":\"has exception\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(500).build();
		}
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
    /**
     * @title 删除顾客信息
     * @param customer_id 顾客ID
     * @param sessionId
     * @param request
     * @param response
     * @return
     */
    @DELETE
    @Path("/{customer_id}/")
    public Response deleteCustomer(@PathParam("customer_id")String customer_id,
    		@HeaderParam(value="sessionId") String sessionId,
    		@HeaderParam(value="user_id") String user_id,
    		@Context HttpServletRequest request,@Context HttpServletResponse response) {
		String responseStr = null;
		try{
			responseStr=customerService.deleteCustomer(customer_id,sessionId,user_id);
		}catch (NotAcceptableException e) {
			System.out.println(e);
			return Response.serverError().entity("{\"message\":\""+e.getMessage()+"\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(406).build();
		}catch (Exception e) {
			System.out.println(e);
			LogUtils.error(this, e, "# CustomerRest-deleteCustomer,has exception #");
			return Response.serverError().entity("{\"message\":\"has exception\",\"timestamp\":\""+System.currentTimeMillis()+"\"}").status(500).build();
		}
		return Response.ok(responseStr, MediaType.APPLICATION_JSON)
        		.status(200).build();
    }
    
}
