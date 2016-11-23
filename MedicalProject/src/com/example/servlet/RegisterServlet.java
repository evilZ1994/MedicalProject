package com.example.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Patient;

/**
 * Servlet implementation class Register
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String infoStr = request.getParameter("info");
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
					
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
