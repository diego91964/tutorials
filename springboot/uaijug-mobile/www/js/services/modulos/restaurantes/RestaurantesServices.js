app.service('RestaurantesService', function($http , EndPointsService) {
    
    $http.defaults.headers.common['Authorization'] = 'Basic Y2xpZW50OjEyMzQ1Ng==';

    this.buscarTodosRestaurantes = function() {

        return $http.get(EndPointsService.urlRestaurantesBuscarTodosRestaurantes());
    }

    this.buscarCardapioRestaurantesPorTurnoEId = function (idRestaurante , turno){

        return $http.get(EndPointsService.urlRestaurantesBuscarCardapioPorRU(idRestaurante , turno));
    }
  
})