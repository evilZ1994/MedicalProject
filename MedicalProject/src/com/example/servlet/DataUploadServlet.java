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


public class DataUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataService dataService = null;
       
    public DataUploadServlet() {
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
		System.out.println("DataUpload:");
		String data = request.getParameter("data");
		System.out.println("data: "+data);
		PrintWriter writer = response.getWriter();
		try {
			JSONArray dataArray = new JSONArray(data);
			boolean isSuccess = dataService.insertBatchData(dataArray);
			JSONObject result = new JSONObject();
			result.put("status", isSuccess?"success":"fail");
			writer.write(result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
