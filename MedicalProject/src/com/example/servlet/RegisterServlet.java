package com.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Patient;
import com.example.mapper.PatientMapper;
import com.example.utils.SqlSessionFactoryUtil;

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
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line;
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		String infoStr = buffer.toString();
		System.out.println("info:"+infoStr);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryUtil().getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();
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
					
					PatientMapper patientMapper = session.getMapper(PatientMapper.class);
					patientMapper.register(patient);
					session.commit();
					PrintWriter writer = response.getWriter();
					writer.write("OK");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
