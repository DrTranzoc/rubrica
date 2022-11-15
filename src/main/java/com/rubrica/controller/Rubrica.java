package com.rubrica.controller;

import com.rubrica.data_repo.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Rubrica {
	
	private static Map<Integer,Persona> rubrica;
	
	public Rubrica() {
		
		//Carica la rubrica
		if(rubrica == null) {
			rubrica = new LinkedHashMap<Integer,Persona>();
			loadFromDB();
		}
		
	}
	
	/**
	 * Funzione eseguita allo start up del server, carica in memoria tutti i contatti persistenti dal db
	 */
	public static void loadFromDB() {
		
		if(!DBManager.esisteDB())
			return;
		
		try {
			ResultSet contatti = DBManager.getContatti().orElseThrow();
			
			while(contatti.next()){
				
				//Ottieni i contatti dal database
				Integer id = Integer.parseInt(contatti.getString("id_contatto"));
				String nome = contatti.getString("nome");
				String cognome = contatti.getString("cognome");
				String indirizzo = contatti.getString("indirizzo");
				String telefono = contatti.getString("telefono");
				Integer eta = Integer.parseInt(contatti.getString("eta"));

				Persona contatto = new Persona(id,nome,cognome,indirizzo,telefono,eta);
				Rubrica.aggiungiContatto(contatto,true);
				
			}
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return La rubrica
	 */
	public static List<Persona> getRubrica(){
		return rubrica.values()
                .stream()
                .collect(Collectors.toList());
	}
	
	
	public static Persona getContatto(Integer id) {
		return rubrica.get(id);
	}
	
	
	/*
	 * Aggiungi contatto alla rubrica sul server, e conseguentemente anche sul DB
	 */
	public static Boolean aggiungiContatto(Persona contatto,Boolean serverOnly){
		
		try {
			
			if(serverOnly == true) {
				rubrica.put(contatto.getId(), contatto);
			}else
			{
				if(DBManager.aggiungiContatto(contatto))
					Rubrica.loadFromDB();
				else
					return false;
			}
			
		}
		catch(NullPointerException np) {
			System.err.println(np.toString());
			return false;
		}
		catch(IllegalArgumentException ia) {
			System.err.println(ia.toString());
			return false;
		}
		
		//Aggiungi il contatto anche dal db
		return true;
	}
	
	/*
	 * Rimuovi contatto dalla rubrica sul server, e conseguentemente dal DB
	 */
	public static Boolean rimuoviContatto(Integer Id){
		try {
			if(DBManager.eliminaContatto(Id))
				rubrica.remove(Id);
			else
				return false;
		}
		catch(NullPointerException np) {
			System.err.println(np.toString());
			return false;
		}
		//Rimuovi il contatto anche dal db
		return true;
	}
	
	/*
	 * Aggiorna il contatto sul server, e conseguentemente nel DB
	 */
	public static Boolean modificaContatto(Integer Id,Persona nuovoContatto){
		try {
			if(DBManager.modificaContatto(nuovoContatto))
				rubrica.replace(Id, nuovoContatto);
			else
				return false;
		}
		catch(NullPointerException np) {
			System.err.println(np.toString());
			return false;
		}
		//Aggiorna il contatto anche nel db
		return true;
	}

	
	

}
