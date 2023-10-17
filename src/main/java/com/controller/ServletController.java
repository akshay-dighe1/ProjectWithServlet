package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletController")
public class ServletController extends HttpServlet {



	String url = "jdbc:mysql://localhost:3306/StudentManagement";
	Connection con;
	Statement stmt;
	String query;
	int r;

	@Override
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
		String name = request.getParameter("name");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		long mobile = Long.parseLong(request.getParameter("mobile"));
		response.setContentType("index.jsp");

		try {
			stmt = con.createStatement();
			query = "insert into Students values('" + id + "','" + name + "','" + lname + "','" + email + "','" + pass
					+ "','" + mobile + "')";
			r = stmt.executeUpdate(query);
			if (r > 0) {
				PrintWriter w = response.getWriter();
				w.println("Data inserted...!");
			} else {
				PrintWriter w = response.getWriter();
				w.println("Something wrong here...!");
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
