package br.uaijug.tutorial.service.palestra;

import java.util.List;

import br.uaijug.tutorial.rest.domain.Palestra;

public interface PalestraService {
	
	public List<Palestra> buscarPalestras(String idEvento);
	public List<String> buscarDatasPalestrasPorEventos (String idEvento);

}
