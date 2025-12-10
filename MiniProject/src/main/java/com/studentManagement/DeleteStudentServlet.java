package com.studentManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("id");
	        if (id == null) {
	            response.sendRedirect("viewStudents.jsp");
	            return;
	        }
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?")) {
	            ps.setInt(1, Integer.parseInt(id));
	            ps.executeUpdate();
	            response.sendRedirect("viewStudents.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error: " + e.getMessage());
	        }
	    }
}
	
