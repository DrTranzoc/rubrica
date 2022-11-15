package com.rubrica.service.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.rubrica.controller.Rubrica;
import com.rubrica.data_repo.Persona;

/**
 * Servlet che gestisce l'aggiunta o modifica di un contatto nella rubrica
 * @author Alessio Tranzocchi
 */
@WebServlet("/gestisciContatto")
public class gestisciContatto extends HttpServlet {
	private static final long serialVersionUID = 574819L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestisciContatto() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Parsing parametri della richiesta
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String indirizzo = request.getParameter("indirizzo");
		String telefono = request.getParameter("telefono");
		Integer eta = Integer.parseInt(request.getParameter("eta"));
		Integer reqType = Integer.parseInt(request.getParameter("reqType"));
		
		
		//Check dei parametri
		if(reqType == null || reqType < 0 || nome == null || cognome == null || indirizzo == null || telefono == null || eta == null) {
			response.getWriter().write("-1");
			return;
		}
		//Ulteriore check dei parametri
		else if(reqType == null || reqType < 0 || nome.isBlank()|| cognome.isBlank() || indirizzo.isBlank() || telefono.isBlank()) {
				response.getWriter().write("-1");
				return;
		}
		else {
			switch(reqType) {
				//Aggiungi un contatto
				case 0:{
					Persona contatto = new Persona(0,nome,cognome,indirizzo,telefono,eta);
					Rubrica.aggiungiContatto(contatto,false);
					
				}break;
				//Modifica un contatto
				case 1:{
					Integer id = Integer.parseInt(request.getParameter("id"));
					Persona contatto = new Persona(id,nome,cognome,indirizzo,telefono,eta);
					Rubrica.modificaContatto(id, contatto);
				}break;
			}
			
			response.getWriter().write(getServletContext().getContextPath()+"/lista.jsp");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
