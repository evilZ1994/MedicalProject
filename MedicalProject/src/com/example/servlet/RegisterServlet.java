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
import com.example.service.DoctorService;
import com.example.service.PatientService;

/**
 * Servlet implementation class Register
 */
public class RegisterServlet extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		//返回结果
		PrintWriter writer = response.getWriter();
		JSONObject resultJson = new JSONObject();
		try {
			//判断是医生还是患者注册
			if (type.equals("patient")) {
				resultJson = patientService.patientRegister(new JSONObject(request.getParameter("user")));
				System.out.println(resultJson.toString());
				writer.write(resultJson.toString());
			} else if (type.equals("doctor")) {
				resultJson = doctorService.doctorRegister(new JSONObject(request.getParameter("user")));
				writer.write(resultJson.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
