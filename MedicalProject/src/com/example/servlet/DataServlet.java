package com.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.service.DataService;


public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataService dataService = null;
       
    public DataServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	ServletContext servletContext = this.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		dataService = webApplicationContext.getBean(DataService.class);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		String operation = new String(request.getParameter("operation").getBytes("iso-8859-1"),"utf-8");
		System.out.println("operation: "+operation);
		JSONObject params;
		
		try{
			params = new JSONObject(new String(request.getParameter("params").getBytes("iso-8859-1"), "utf-8"));
			JSONObject result = new JSONObject();
			switch (operation) {
				case "getData":
					dataService.getData(params, result, writer);
					break;
				case "updateData":
					dataService.updateData(params, result, writer);
					break;
				default:
					break;
			}
		} catch (Exception e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line;
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		reader.close();
		PrintWriter writer = response.getWriter();
		
		System.out.println("insertBatchData: "+buffer.toString());
		
		try {
			JSONArray jsonArray = new JSONArray(buffer.toString());
			
			boolean isSuccess = dataService.insertBatchData(jsonArray);
			JSONObject result = new JSONObject();
			result.put("result", isSuccess?"Success":"Fail");
			writer.write(result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
