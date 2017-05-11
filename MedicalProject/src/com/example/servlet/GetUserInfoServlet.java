package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.bean.Doctor;
import com.example.bean.Patient;
import com.example.service.DoctorService;
import com.example.service.PatientService;

/**
 * Servlet implementation class GetUserInfoServlet
 */
public class GetUserInfoServlet extends HttpServlet {
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

    public GetUserInfoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		int id = Integer.valueOf(request.getParameter("id"));
		switch (type) {
		case "patient":
			Patient patient = patientService.getPatientById(id);
			JSONObject patientResult = new JSONObject();
			try {
				patientResult.put("username", patient.getUsername())
						.put("name", patient.getName())
						.put("sex", patient.getSex())
						.put("age", patient.getAge());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			response.getWriter().write(patientResult.toString());
			break;
		case "doctor":
			Doctor doctor = doctorService.getDoctorById(id);
			JSONObject doctorResult = new JSONObject();
			try {
				doctorResult.put("username", doctor.getUsername())
						.put("name", doctor.getName())
						.put("sex", doctor.getSex())
						.put("hospital", doctor.getHospital())
						.put("department", doctor.getDepartment());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			response.getWriter().write(doctorResult.toString());
			break;
		default:
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
