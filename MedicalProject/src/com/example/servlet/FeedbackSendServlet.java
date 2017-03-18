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

import com.example.service.DataService;
import com.example.service.FeedbackService;

public class FeedbackSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FeedbackService feedbackService;
       
	@Override
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		feedbackService = webApplicationContext.getBean(FeedbackService.class);
		super.init();
	}
	
    public FeedbackSendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String params = new String(request.getParameter("params").getBytes("iso-8859-1"), "utf-8");
		try {
			JSONObject jsonObject = new JSONObject(params);
			feedbackService.addFeedback(jsonObject, response.getWriter());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
