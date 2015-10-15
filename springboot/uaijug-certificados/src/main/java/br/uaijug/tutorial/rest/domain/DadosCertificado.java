package br.uaijug.tutorial.rest.domain;

public class DadosCertificado {

	private String nomeParticipante;
    private String nomeEvento;
    private String diasEvento;
    private String nomeInstituicao;
    private String totalHoras;
    private String nomeCidade;
    
    
	public DadosCertificado(String nomeParticipante, String nomeEvento, String diasEvento, String nomeInstituicao,
			String totalHoras, String nomeCidade) {
		super();
		this.nomeParticipante = nomeParticipante;
		this.nomeEvento = nomeEvento;
		this.diasEvento = diasEvento;
		this.nomeInstituicao = nomeInstituicao;
		this.totalHoras = totalHoras;
		this.nomeCidade = nomeCidade;
	}
	
	public DadosCertificado () {
		super();
	}
	
	
	public String getNomeParticipante() {
		return nomeParticipante;
	}
	public void setNomeParticipante(String nomeParticipante) {
		this.nomeParticipante = nomeParticipante;
	}
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public String getDiasEvento() {
		return diasEvento;
	}
	public void setDiasEvento(String diasEvento) {
		this.diasEvento = diasEvento;
	}
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}
	public String getTotalHoras() {
		return totalHoras;
	}
	public void setTotalHoras(String totalHoras) {
		this.totalHoras = totalHoras;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

}
