package com.mcit.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mcit.webapp.entity.Employee;
import com.mcit.webapp.entity.Payroll;
import com.mcit.webapp.entity.Project;
import com.mcit.webapp.util.HibernateSessionUtil;

/**
 * Servlet implementation class AddEmployeewithPayroll
 */
@WebServlet("/add-employee-with-project")
public class AddEmployeewithProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployeewithProject() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher("index.html").include(request, response);
		request.getRequestDispatcher("add-employee-with-project.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		
		// fetch data from request
		//personal information
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		double salary =  Double.parseDouble(request.getParameter("salary"));
		String dept = request.getParameter("dept");
		
		//project info
		String P1name = request.getParameter("project1-name");
		String P1no = request.getParameter("project1-no");
		
		String P2name = request.getParameter("project2-name");
		String P2no = request.getParameter("project2-no");
		
		//build hibernate session
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
			
			// 2. create a session
			Session session = factory.openSession();
			
			// 3. begin transaction
			Transaction tx = session.beginTransaction();
			
			// list of project
			Set<Project> projects = new HashSet<>();
			Project p1 = new Project(P1no, P1name);
			Project p2 = new Project(P2no, P2name);
			projects.add(p1);
			projects.add(p2);
			
			Employee employee = new Employee(firstName, lastName, salary, dept);
			
			//add payroll & projects
			employee.setProjects(projects);
			
			// 5. save product
			session.save(employee);
			
			// 6. commit transaction
			tx.commit();
	
			out.print("<h3 style='color:green'> Employee is created with payroll & projects successfully !<h3>");
			// 3. close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Create Employee failed ! <h3>" + e);
		}
	}

}
