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


public class PassChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientService patientService = null;
	private DoctorService doctorService = null;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		patientService = webApplicationContext.getBean(PatientService.class);
		doctorService = webApplicationContext.getBean(DoctorService.class);
	}

    public PassChangeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		int id = Integer.valueOf(request.getParameter("id"));
		String oldPass = request.getParameter("old_pass");
		String newPass = request.getParameter("new_pass");
		
		switch (type) {
		case "patient":
			JSONObject result = patientService.updatePassword(id, oldPass, newPass);
			response.getWriter().write(result.toString());
			break;
		case "doctor":
			JSONObject result2 = doctorService.updatePassword(id, oldPass, newPass);
			response.getWriter().write(result2.toString());
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
