package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Read")
public class Read extends HttpServlet {

	String url = "jdbc:mysql://localhost:3306/StudentManagement";
	Connection con;
	Statement stmt;
	String query;
	int r;

	public void init() throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "Akshay@123");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		
		response.setContentType("update.html");

		try {
			Statement stmt=con.createStatement();
			query = "select * from Students where id='"+id+"'";
			

			ResultSet rs=stmt.executeQuery(query);
			
					if (rs.next()) {
						PrintWriter w = response.getWriter();
						w.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
						
						
					}
					else {
						PrintWriter w = response.getWriter();
						w.println("Data not found");
					}

		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

	}

	public void distroy() throws SQLException {
		con.close();
	}

}
