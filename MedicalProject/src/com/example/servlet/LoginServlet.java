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

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.service.DoctorService;
import com.example.service.PatientService;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientService patientService = null;
	private DoctorService doctorService = null;

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		patientService = webApplicationContext.getBean(PatientService.class);
		doctorService = webApplicationContext.getBean(DoctorService.class);
	}   

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String infoStr = buffer.toString();
		try {
			JSONObject info = new JSONObject(infoStr);
			if (info.getString("type").equals("patient")) {
				JSONObject content = info.getJSONObject("content");
				JSONObject result = patientService.patientLogin(content);
				if (result!=null) {
					writer.write(result.toString());
				}else {
					writer.write(result.put("status", "Fail").put("Error", "登录失败，请稍后重试").toString());
				}
			} else if (info.getString("type").equals("doctor")){
				JSONObject content = info.getJSONObject("content");
				JSONObject result = doctorService.doctorLogin(content);
				if (result!=null) {
					writer.write(result.toString());
				}else {
					writer.write(result.put("status", "Fail").put("Error", "登录失败，请稍后重试").toString());
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
