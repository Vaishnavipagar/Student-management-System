<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.studentManagement.DBConnection" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Students</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div class="container">
    <h2>All Students</h2>
    <div class="center"><a href="addStudent.html" class="btn">Add New Student</a></div>
    <br>
    <table>
      <tr><th>ID</th><th>Name</th><th>Email</th><th>Age</th><th>Address</th><th>Action</th></tr>
      <%
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM students");
            rs = ps.executeQuery();
            while (rs.next()) {
      %>
      <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getInt("age") %></td>
        <td><%= rs.getString("address") %></td>
        <td>
          <a class="delete" href="DeleteStudentServlet?id=<%=rs.getInt("id")%>" onclick="return confirm('Delete this record?');">Delete</a>
        </td>
      </tr>
      <%
            }
        } catch(Exception e) {
            out.println("<tr><td colspan='6'>Error: " + e.getMessage() + "</td></tr>");
        } finally {
            try{ if(rs!=null) rs.close(); } catch(Exception e){}
            try{ if(ps!=null) ps.close(); } catch(Exception e){}
            try{ if(con!=null) con.close(); } catch(Exception e){}
        }
      %>
    </table>
    <p class="center"><a href="index.html">Home</a></p>
  </div>
</body>
</html>
