app.service('ParticipanteService', function($http , EndPointsService) {
    
    $http.defaults.headers.common['Authorization'] = 'Basic QURNSU46QURNSU4=';

    this.buscarTodosRestaurantes = function() {

        return $http.get(EndPointsService.urlRestaurantesBuscarTodosRestaurantes());
    }

    this.buscarCardapioRestaurantesPorTurnoEId = function (idRestaurante , turno){

        return $http.get(EndPointsService.urlRestaurantesBuscarCardapioPorRU(idRestaurante , turno));
    }

    this.cadastrarParticipante = function (participante){

    	/*var string = "http://localhost:8080/participante/adicionarParticipante/"+
    	participante.cpf+"/"+participante.email+"/"+participante.telefone+"/"+participante.nome+"";*/

    	return $http.get("http://10.248.19.17:8080/participante/buscarDadosCertificado/1/1");

    	/*$.get("http://10.248.19.17:8080/participante/buscarDadosCertificado/1/1", function(data, status){

	        var result = data;    // Object {Month: "August", Year: "2015", Online: "true", Days: Array[31]}
	        
  		});*/
    }
  
})