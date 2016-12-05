package com.example.servlet;

import java.io.IOException;
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

import com.example.bean.Doctor;
import com.example.service.DoctorService;
import com.example.service.PatientService;
import com.example.service.UtilGetService;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class UtilGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PatientService patientService = null;
	private DoctorService doctorService = null;
	private UtilGetService utilGetService = null;

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		patientService = webApplicationContext.getBean(PatientService.class);
		doctorService = webApplicationContext.getBean(DoctorService.class);
		utilGetService = webApplicationContext.getBean(UtilGetService.class);
	}   
	
    public UtilGetServlet() {
        super();
    }

    /**
     * get方式，参数  operation=... & params=json
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UtilGetServlet running...");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		String operation = new String(request.getParameter("operation").getBytes("iso-8859-1"),"utf-8");
		System.out.println("operation: "+operation);
		JSONObject params;
		try {
			params = new JSONObject(new String(request.getParameter("params").getBytes("iso-8859-1"), "utf-8"));
			JSONObject result = new JSONObject();
			
			switch (operation) {
			case "hasAddDoctor":
				utilGetService.hasAddDoctor(params, result, patientService, writer);
				System.out.println("UtilGetServlet done.");
				break;
			case "searchDoctor":
				utilGetService.searchDoctor(params, result, doctorService, writer);
				System.out.println("UtilGetServlet done.");
				break;
			case "addDoctor":
				utilGetService.addDoctor(params, result, patientService, writer);
			case "getFeedback":
				utilGetService.getFeedback(params, result, writer);
				break;
			case "updateFeedback":
				utilGetService.updateFeedback(params);
				break;
			default:
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
