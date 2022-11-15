package com.rubrica.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rubrica.data_repo.Persona;

/**
 * Classe che gestisce le connessioni con i DB
 * @author Alessio Tranzocchi
 */
public class DBManager{
	
	private static DB db;
	
	public DBManager(String url) {
		if(db == null)
			db = new DB(url);
	}
	
	/**
	 * @param url : Indirizzo del db a cui connettersi
	 * @return se la connessione è stata creata con successo
	 */
	public static Boolean connectToDb() {
		return db.creaConnessione();
	}
	
	/**
	 * @param url : Indirizzo del db a cui riconnettersi
	 * @return se la connessione è stata ricreata con successo
	 */
	public static Boolean changeConnection(String url) {
		db.cambiaURL(url);
		return connectToDb();
	}
	
	
	/**
	 * 
	 * @param contatto : I dati del contatto da aggiungere
	 * @return Se l'operazione ha avuto successo
	 */
	public static Boolean aggiungiContatto(Persona contatto) {
		String nome = contatto.getNome();
		String cognome = contatto.getCognome();
		String indirizzo = contatto.getIndirizzo();
		String telefono = contatto.getTelefono();
		Integer eta = contatto.getEta();
	
		
		String query = "INSERT INTO `rubrica`.`lista_contatti` (`nome`, `cognome`, `telefono`, `indirizzo`, `eta`) "+
				"VALUES ('"+nome+"', '"+cognome+"', '"+telefono+"', '"+indirizzo+"', '"+eta+"');";
		
		return db.exDMLQuery(query, 0);
	}
	
	/**
	 * 
	 * @param contatto : I dati del contatto da modificare
	 * @return Se l'operazione ha avuto successo
	 */
	public static Boolean modificaContatto(Persona contatto) {
		String nome = contatto.getNome();
		String cognome = contatto.getCognome();
		String indirizzo = contatto.getIndirizzo();
		String telefono = contatto.getTelefono();
		Integer eta = contatto.getEta();
		Integer id = contatto.getId();
		
		String query = "UPDATE `rubrica`.`lista_contatti` "
				+ "SET `nome` = '"+nome+"', `cognome` = '"+cognome+"', `telefono` = '"+telefono+"', `indirizzo` = '"+indirizzo+"', `eta` = '"+eta+"' "
				+ "WHERE (`id_contatto` = '"+id+"');";
		
		return db.exDMLQuery(query, 0);
	}
	
	/**
	 * 
	 * @param contatto : L'id del contatto da eliminare
	 * @return Se l'operazione ha avuto successo
	 */
	public static Boolean eliminaContatto(Integer id) {
	
		String query = "DELETE FROM `rubrica`.`lista_contatti` WHERE id_contatto = "+id;
		return db.exDMLQuery(query, 0);
	}
	
	/**
	 * @return L'Optional della lista dei contatti
	 */
	public static Optional<ResultSet> getContatti() {
		String query = "SELECT * FROM `rubrica`.`lista_contatti`";
		return Optional.of(db.exDQLQuery(query));
	}
	
	
	public static Boolean esisteDB() {
		return db!=null;
	}
	
	
	/**
	 * Classe che rappresenta il db
	 */
	private class DB {

		private Connection dbConnection;
		private Statement dbStmt;
		private String dbUrl;

		private DB(String url){
			this.dbUrl = url;
		}
		
		/**
		 * 
		 * @param url : Indirizzo del DB a cui il server si deve connettere
		 * @return Ritorna se la connessione ha avuto successo
		 */
		private Boolean creaConnessione() {
			
			if(dbUrl == null || dbUrl == "")
				return false;
			
			try {
				
				if(dbConnection != null && !dbConnection.isClosed())
					dbConnection.close();
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbConnection = DriverManager.getConnection(dbUrl);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * @return ritorna se lo statement è stato creato correttamente
		 */
		private Boolean creaStatement() {
			if(dbConnection == null) {
				return false;
			}
			try {
				dbStmt = dbConnection.createStatement();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * Controlla se 'qry' modifica almeno 'rowsToAffect' righe
		 * @param qry
		 * @param rowsToAffect : Numero di righe che dovrebbero essere raggiunte
		 */
		private Boolean exDMLQuery(String qry,Integer rowsToAffect) {

			try {
				
				if(dbStmt == null || dbStmt.isClosed())
					creaStatement();
				
				//Esegui la query e controlla se modifica almeno 'rowsToAffect' righe riga
				boolean check = dbStmt.executeUpdate(qry) > 0;
				
				//Chiudi lo stato
				dbStmt.close();
				return check;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * @param qry La query da eseguire
		 * @return il risultato della query
		 */
		private ResultSet exDQLQuery(String qry) {

			ResultSet result = null;
			try {
				
				if(dbStmt == null || dbStmt.isClosed())
					creaStatement();
				
				//Esegui la query
				result = dbStmt.executeQuery(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * @param newUrl : La nuova url del database
		 */
		private void cambiaURL(String newUrl) {
			this.dbUrl = newUrl;
		}
		
	}
}