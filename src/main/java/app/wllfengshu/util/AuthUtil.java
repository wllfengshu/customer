package app.wllfengshu.util;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

public enum AuthUtil {
	/**
	 * 权限认证
	 */
	instance;
	
	public String getCurrentUserBySessionId(String sessionId){
		String user = getUsersBySession(sessionId);
		return user;
	}
	
	public String getCurrentUserIdBySessionId(String sessionId){
		String user = getCurrentUserBySessionId(sessionId);
		JSONObject userJson = null;
		String user_id=null;
		if(null!=user && !"".equals(user)){
			userJson = JSONObject.fromObject(user);
			user_id = userJson.getString("id");
		}
		return user_id;
	}
	
	private String getUsersBySession(String sessionId){
		long beginTime=System.currentTimeMillis();
		String security_base_url="http://127.0.0.1:9001/";
		String url = security_base_url+"security/user";
		Response res=null;
		try {
			res = Request.Get(url).setHeader("sessionId", sessionId).execute();
			HttpResponse response = res.returnResponse();
			String responseStr= null;
			if(response.getEntity()!=null){
				responseStr = IOUtils.toString(response.getEntity().getContent(),"utf-8");
			}
			long endTime=System.currentTimeMillis();
			int statusCode = response.getStatusLine().getStatusCode();
			String logStr="{\"statusCode\":"+statusCode+",\"useTime\":\""+(endTime-beginTime)+"ms\",\"url\":\""+url+"\",\"serviceResponseStr\":"+responseStr+"}";
			LogUtils.trace(this, "########## AuthUtil.getAgentByNo,%s ##########",logStr);
			if(statusCode>300){
				throw new SecurityException(logStr);
			}
			String user=new Gson().fromJson(responseStr, String.class);
			return user;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
	
