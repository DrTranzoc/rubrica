package com.rubrica.service.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.rubrica.controller.DBManager;
import com.rubrica.controller.Rubrica;

/**
 * Ritorna l'index
 * @author Alessio Tranzocchi
 */
public class getIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getIndex() {
        super();
        
        //Chiamata allo start-up (definito in web.xml)
        new Rubrica();
        new DBManager("");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String cambiaDB = request.getParameter("cambiaDB");
		
		if(session == null || session.getAttribute("connectedDB") == null || (Boolean)session.getAttribute("connectedDB") != true) {
			getServletContext().getRequestDispatcher("/index_page.jsp").forward(request, response);
		}
		else {
			if(cambiaDB == null)
				getServletContext().getRequestDispatcher("/lista.jsp").forward(request, response);
			else {
				response.getWriter().write(getServletContext().getContextPath()+"/index_page.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
