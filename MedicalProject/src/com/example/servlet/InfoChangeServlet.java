package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.service.DoctorService;
import com.example.service.PatientService;

public class InfoChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DoctorService doctorService = null;
	private PatientService patientService = null;
       
	
	
    @Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		doctorService = webApplicationContext.getBean(DoctorService.class);
		patientService = webApplicationContext.getBean(PatientService.class);
	}

	public InfoChangeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		int id = Integer.valueOf(request.getParameter("id"));
		String tag = request.getParameter("tag");
		String content = request.getParameter("content");
		
		if (type.equals("doctor")) {
			JSONObject result = doctorService.updateInfo(id, tag, content);
			response.getWriter().write(result.toString());
		} else if (type.equals("patient")) {
			JSONObject result = patientService.updateInfo(id, tag, content);
			response.getWriter().write(result.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
