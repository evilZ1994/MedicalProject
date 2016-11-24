package com.example.servlet;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.bean.Patient;
import com.example.mapper.PatientMapper;
import com.example.utils.SqlSessionFactoryUtil;


/**
 * Servlet implementation class testServlet
 */
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
		/*Reader reader = Resources.getResourceAsReader("Configuration.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		Patient patient = session.selectOne("com.example.mapper.PatientMapper.selectPatientById", 1);
		System.out.println("name:"+patient.getUsername());
		
		System.out.println("test success!");*/
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		SqlSessionFactory factory = new SqlSessionFactoryUtil().getSqlSessionFactory();
		SqlSession session = factory.openSession();
		PatientMapper patientMapper = session.getMapper(PatientMapper.class);
		Patient patient = patientMapper.selectPatientById(id);
		System.out.println(patient.getUsername()+":"+patient.getPassword());
	}

}
