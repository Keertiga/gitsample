package com.paypal.charity;


import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



@Path("/charity")
public class Charity {
	
	@POST
	@Path("/CreateProducts")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkProduct(String data,@Context HttpServletRequest servletRequest,@Context HttpServletResponse servletResponse) throws IOException{
           System.out.println(data);
		   return null;
	}
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String checkAuth(String data,@Context HttpServletRequest servletRequest,@Context HttpServletResponse servletResponse) throws IOException{
		JSONObject obj=new JSONObject();
		String msg="default";
		String[] formValues=new String[2];
		int itr=0;
		String[] parts=data.split("&");
		  for(int i=0;i<parts.length;i++){
			  String p[]=parts[i].split("=");
			  formValues[itr++]=p[1];
		  }
		CheckAuth auth=new CheckAuth(formValues[0],formValues[1]);
		int response=auth.checkauth();
		switch(response){
		   case 0:
			  //servletResponse.sendRedirect(servletRequest.getHeader("Referer"));
			  msg="Invalid user Name";
		      break;
		   case 1:
			   servletResponse.sendRedirect("../admin.html");
			   break;
			  
		   case 2:
			   servletResponse.sendRedirect("../charity.html");
			   break;
			
		   case 3:
			   msg="UserName and Password does not match";
			   break;
		}
		
		obj.put("Message", msg);
		
		System.out.println(obj);
	
	    return obj.toJSONString();
		   
	}
	
	
	@Path("/SelectedProducts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getProducts(){
		JSONObject obj = new JSONObject();
		obj.put("Product", "p1");
		obj.put("selected", "true");


		JSONArray ja = new JSONArray();
		ja.add(obj);
		
		JSONObject obj1 = new JSONObject();
		obj1.put("Product", "p2");
		obj1.put("selected", "false");
		
		ja.add(obj1);
		
		JSONObject obj2 = new JSONObject();
		obj2.put("Product", "p3");
		obj2.put("selected", "true");

		ja.add(obj2);
		
		JSONObject mainObj = new JSONObject();
		mainObj.put("Products", ja);
	    return mainObj.toJSONString();
	}
	
	
	

	
	@Path("/Products")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String showProducts(){
		JSONObject obj = new JSONObject();
		obj.put("Product", "p1");
		obj.put("Price", "100");
		obj.put("metrics", "kg");

		JSONArray ja = new JSONArray();
		ja.add(obj);
		
		JSONObject obj1 = new JSONObject();
		obj1.put("Product", "p2");
		obj1.put("Price", "200");
		obj1.put("metrics", "litre");
		
		ja.add(obj1);
		
		JSONObject obj2 = new JSONObject();
		obj2.put("Product", "p3");
		obj2.put("Price", "300");
		obj2.put("metrics", "piece");

		ja.add(obj2);
		
		JSONObject mainObj = new JSONObject();
		mainObj.put("Products", ja);
	    return mainObj.toJSONString();
	}
	

	
	
	
	
	/*public String showProducts(){
		String[] result={"product1","product2","product3"};
		Gson gson=new Gson();
		String resultJson=gson.toJson(result);
		return resultJson;
	}*/
	

} 	 