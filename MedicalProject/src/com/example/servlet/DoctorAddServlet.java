package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class DoctorAddServlet
 */
public class DoctorAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DoctorService doctorService;
    private PatientService patientService;

    @Override
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		doctorService = webApplicationContext.getBean(DoctorService.class);
		patientService = webApplicationContext.getBean(PatientService.class);
	}

	public DoctorAddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		PrintWriter writer = response.getWriter();
		JSONObject result = new JSONObject();
		switch (type) {
		case "search":
			String doctorUsername = request.getParameter("username");
			result  = doctorService.searchDoctor(doctorUsername);
			writer.write(result.toString());
			break;
		case "add":
			int id = Integer.valueOf(request.getParameter("id"));
			int doctor_id = Integer.valueOf(request.getParameter("doctor_id"));
			result = patientService.addDoctor(doctor_id, id);
			writer.write(result.toString());
			break;
		default:
			break;
		}
		System.out.println(result.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
