package br.uaijug.tutorial.rest.domain;

import java.io.Serializable;

import org.neo4j.rest.graphdb.entity.RestNode;

public class Palestra implements Serializable{

	private static final long serialVersionUID = -8665210973261435532L;
	private String id;
	private String titulo;
	private String descricao;
	private String data;
	private String subLocal;
	private String hrInicio;
	private String hrFim;
	
	public Palestra (){
		super();
	}
	
	public Palestra(RestNode restNode) {
		this.id = restNode.getProperty("id").toString();
		this.titulo =  restNode.getProperty("titulo").toString();
		this.data = restNode.getProperty("data").toString();
		this.subLocal = restNode.getProperty("sublocal").toString();
		this.hrInicio = restNode.getProperty("hrinicio").toString();
		this.hrFim = restNode.getProperty("hrfim").toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSubLocal() {
		return subLocal;
	}
	public void setSubLocal(String subLocal) {
		this.subLocal = subLocal;
	}
	public String getHrInicio() {
		return hrInicio;
	}
	public void setHrInicio(String hrInicio) {
		this.hrInicio = hrInicio;
	}
	public String getHrFim() {
		return hrFim;
	}
	public void setHrFim(String hrFim) {
		this.hrFim = hrFim;
	}
	
	
}
