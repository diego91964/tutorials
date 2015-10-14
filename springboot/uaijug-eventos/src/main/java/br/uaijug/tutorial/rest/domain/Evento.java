package br.uaijug.tutorial.rest.domain;

import java.io.Serializable;

import org.neo4j.rest.graphdb.entity.RestNode;

public class Evento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8025919543821727398L;
	
	private String id;
	private String nome;
	private String local;
	private String cidade;
	private String totalHoras;
	private String data;
	
	public Evento (){
		super();
	}
	
	public Evento(RestNode restNode) {
		this.id = restNode.getProperty("id").toString();
		this.nome =  restNode.getProperty("nome").toString();
		this.local = restNode.getProperty("local").toString();
		this.cidade = restNode.getProperty("cidade").toString();
		this.totalHoras = restNode.getProperty("totalhoras").toString();
		this.data = restNode.getProperty("data").toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(String totalHoras) {
		this.totalHoras = totalHoras;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	

}
