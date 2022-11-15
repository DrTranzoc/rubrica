package com.rubrica.service.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.rubrica.controller.Rubrica;
import com.rubrica.data_repo.Persona;

/**
 * Servlet che inoltra alla pagina di editing, vuota o con campi pre esistenti se si intende modificare
 * @author Alessio Tranzocchi
 */
@WebServlet("/editor.jsp")
public class getEditorContatti extends HttpServlet {
	private static final long serialVersionUID = 4834L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getEditorContatti() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("id") != null) {
			try {
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				Persona contattoRichiesto = Rubrica.getContatto(id);
				
				session.setAttribute("idContatto",id);
				session.setAttribute("contatto",contattoRichiesto);
				
				response.getWriter().write(getServletContext().getContextPath()+"/editor.jsp");
				return;
			}
			catch(NumberFormatException nfe) {
				response.getWriter().write("-1");
				System.err.println(nfe);
			}
		}
		else {

			if(session.getAttribute("idContatto") != null) {
				request.setAttribute("idContatto",session.getAttribute("idContatto"));
				request.setAttribute("contatto",session.getAttribute("contatto"));
				
				session.removeAttribute("idContatto");
				session.removeAttribute("contatto");
			}
			response.getWriter().write("1");
			getServletContext().getRequestDispatcher("/editor_page.jsp").forward(request, response);
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
