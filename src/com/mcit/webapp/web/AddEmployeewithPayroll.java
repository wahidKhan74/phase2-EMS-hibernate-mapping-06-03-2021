package com.mcit.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.mcit.webapp.util.HibernateSessionUtil;

/**
 * Servlet implementation class AddEmployeewithPayroll
 */
@WebServlet("/add-employee-with-payroll")
public class AddEmployeewithPayroll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployeewithPayroll() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher("index.html").include(request, response);
		request.getRequestDispatcher("add-employee-with-payroll.html").include(request, response);
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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		double salary =  Double.parseDouble(request.getParameter("salary"));
		String dept = request.getParameter("dept");
		
		double grossAmount = Double.parseDouble(request.getParameter("gross"));
		double netAmount = Double.parseDouble(request.getParameter("net"));
		double tax = Double.parseDouble(request.getParameter("tax"));
		
		//build hibernate session
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
			
			// 2. create a session
			Session session = factory.openSession();
			
			// 3. begin transaction
			Transaction tx = session.beginTransaction();
			
			// 4. create persistence object / add product
			Payroll payroll = new Payroll(grossAmount, netAmount, tax);
			Employee employee = new Employee(firstName, lastName, salary, dept, payroll);
			
			// 5. save product
			session.save(employee);
			
			// 6. commit transaction
			tx.commit();
	
			out.print("<h3 style='color:green'> Employee is createdm with payroll successfully !<h3>");
			// 3. close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Create Employee failed ! <h3>" + e);
		}
	}

}
