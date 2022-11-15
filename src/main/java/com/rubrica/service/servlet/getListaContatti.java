package com.rubrica.service.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.rubrica.controller.Rubrica;

/**
 * Ritorna la lista contatti caricata sul server
 * @author Alessio Tranzocchi
 */
@WebServlet("/lista.jsp")
public class getListaContatti extends HttpServlet {
	private static final long serialVersionUID = 4834L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getListaContatti() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("connectedDB") == null || (Boolean)session.getAttribute("connectedDB") != true) {
			getServletContext().getRequestDispatcher("/index_page.jsp").forward(request, response);
		}
		else {
			//Carica la lista contatti dal server, usata per creare la pagina dinamica
			request.setAttribute("contatti", Rubrica.getRubrica());
			getServletContext().getRequestDispatcher("/lista_contatti.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
