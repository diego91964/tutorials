package br.uaijug.tutorial.service.evento;

import java.util.List;

import br.uaijug.tutorial.rest.domain.Evento;

public interface EventoService {
	
	public List<Evento> buscarEventos();
	
	public Evento buscarEventoPorId(String idEvento);

}
