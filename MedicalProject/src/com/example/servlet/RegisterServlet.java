package com.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		PrintWriter writer = response.getWriter();
		//返回结果
		JSONObject resultJson = new JSONObject();
		try {
			if (infoStr!=null) {
				JSONObject info = new JSONObject(infoStr);
				String type = info.getString("type");
				//判断是医生还是患者注册
				if (type.equals("patient")) {
					resultJson = patientService.patientRegister(info.getJSONObject("content"));
					writer.write(resultJson.toString());
				} else if (type.equals("doctor")) {
					
					
					
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
