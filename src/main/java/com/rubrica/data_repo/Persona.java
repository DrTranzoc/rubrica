package com.rubrica.data_repo;


/*
 * @author Alessio Tranzocchi
 */
public class Persona {
	

	private Integer id;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private Integer eta;
	
	
	public Persona(Integer id, String nome, String cognome, String indirizzo, String telefono, Integer eta) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.eta = eta;
	}


	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}


	public String getTelefono() {
		return telefono;
	}


	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getEta() {
		return eta;
	}
	
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo
				+ ", telefono=" + telefono + ", eta=" + eta + "]";
	}
		
}
