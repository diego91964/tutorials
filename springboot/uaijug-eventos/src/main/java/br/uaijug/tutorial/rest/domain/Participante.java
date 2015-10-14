package br.uaijug.tutorial.rest.domain;

import java.io.Serializable;

import org.neo4j.rest.graphdb.entity.RestNode;

public class Participante implements Serializable{

	private static final long serialVersionUID = 6857187872324730968L;
	private String id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	
	
	public Participante (){
		super();
	}
	
	public Participante(RestNode restNode) {
		this.id = restNode.getProperty("id").toString();
		this.nome =  restNode.getProperty("nome").toString();
		this.email = restNode.getProperty("email").toString();
		this.cpf = restNode.getProperty("cpf").toString();
		this.telefone = restNode.getProperty("telefone").toString();
	}
	
	
	
	public Participante(String nome, String email, String cpf, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
