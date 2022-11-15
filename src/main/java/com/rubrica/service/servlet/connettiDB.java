package com.rubrica.service.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.rubrica.controller.DBManager;
import com.rubrica.controller.Rubrica;

/**
 * Servlet che gestisce la connessione (o riconnessione) al db
 * @author Alessio Tranzocchi
 */
@WebServlet("/connettiDB")
public class connettiDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public connettiDB() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			
			
			Integer porta = -1;
			String host = request.getParameter("host");
			porta = Integer.parseInt(request.getParameter("porta"));
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			
			if(host == null || host.isBlank())
			{
				response.getWriter().write("-1");
				return;
			}
			
			String url = "jdbc:mysql://address=(host="+host+")(port="+porta+")(user="+user+")(password="+password+")/rubrica";
	
			session.removeAttribute("connectedDB");
			
			DBManager.changeConnection(url);
			if(DBManager.connectToDb() == false) {
				response.getWriter().write("-1");
				return;
			}
			
			if(session != null)
				session.setAttribute("connectedDB", true);
			
			Rubrica.loadFromDB();
			response.getWriter().write(getServletContext().getContextPath()+"/lista.jsp");
			return;
		}catch(NumberFormatException ex1) {
			response.getWriter().write("-1");
			ex1.printStackTrace();
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
