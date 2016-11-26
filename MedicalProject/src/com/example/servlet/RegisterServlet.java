package com.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.bean.Patient;
import com.example.service.PatientService;

/**
 * Servlet implementation class Register
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PatientService patientService = null;

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		patientService = webApplicationContext.getBean(PatientService.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line;
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		reader.close();
		
		String infoStr = buffer.toString();
		System.out.println("info:"+infoStr);
	
		try {
			if (infoStr!=null) {
				JSONObject info = new JSONObject(infoStr);
				String type = info.getString("type");
				if (type.equals("patient")) {
					JSONObject content = info.getJSONObject("content");
					String username = content.getString("username");
					String name = content.getString("name");
					String password = content.getString("password");
					Date create_time = new Date();
					Date update_time = new Date();
					Patient patient = new Patient();
					patient.setUsername(username);
					patient.setName(name);
					patient.setPassword(password);
					patient.setCreate_time(create_time);
					patient.setUpdate_time(update_time);
					
					boolean registerSuccess = patientService.patientRegister(patient);
					PrintWriter writer = response.getWriter();
					writer.write(registerSuccess ? "Ok" : "Fail");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
