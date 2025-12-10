package com.studentManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String age = request.getParameter("age");
	        String address = request.getParameter("address");

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement("INSERT INTO students(name,email,age,address) VALUES(?,?,?,?)")) {

	            ps.setString(1, name);
	            ps.setString(2, email);
	            ps.setInt(3, (age==null || age.isEmpty())?0:Integer.parseInt(age));
	            ps.setString(4, address);
	            ps.executeUpdate();

	            response.sendRedirect("viewStudents.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error: " + e.getMessage());
	        }
}
}
