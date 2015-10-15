app.service('EndPointsService', function(EnvService) {
  // Might use a resource here that returns a JSON array

    var urls = {
      "url_base_comunica"     : "http://www.comunica.ufu.br/m-ufu/",
      "url_base_mobile"       : "http://www.mobile.ufu.br/m-ufu/",
      "url_base_prefeitura"   : "http://www.portal.prefeitura.ufu.br/m-ufu/",
      "url_base_ufu"          : "http://www.ufu.br/m-ufu/",
      "url_base_sustentavel"  : "http://www.sustentavel.ufu.br/",
      "url_base_intercampi"   : "http://www.sustentavel.ufu.br/intercampi/",
      "url_base_ru"           : "http://www.ru.ufu.br/json/",
      /*"url_base_sistemas"     : "https://www.sistemas.ufu.br/mobile"*/
      "url_base_sistemas"     : "http://localhost:8080/mobile-client"
    };

    var appEndPoints = {
      "ru" : "/ru" , 
      "almoco" : "/almoco",
      "jantar" : "/jantar"
    };

  
    this.urlBaseComunica = function() {
      return urls["url_base_sistemas"];
    };

    this.urlBaseMobile = function() {
      return urls["url_base_sistemas"];
    };
    
    this.urlBasePrefeitura = function() {
      return urls["url_base_sistemas"];
    };
    
    this.urlBaseSustentavel = function() {
      return urls["url_base_sistemas"];
    };
    
    this.urlBaseIntercampi = function() {
      return urls["url_base_sistemas"];
    };
    
    this.urlBaseSistemas = function() {
      return urls["url_base_sistemas"];
    };
    
    this.urlRestaurantesBuscarTodosRestaurantes = function() {
      return urls["url_base_sistemas"] + appEndPoints["ru"] + "/buscarTodosRestaurantes";
    };
    
    this.urlRestaurantesBuscarPercentualLotacaoEstimadaPorRestaurante = function (){
      return urls["url_base_sistemas"] + appEndPoints["ru"] + "/buscarPercentualLotacaoEstimadaPorRestaurante"
    };
   
   this.urlRestaurantesBuscarCardapioPorRU = function (idRestaurante , turno){
      return urls["url_base_ru"] + EnvService.nomeRestaurantePorIdRestaurante(idRestaurante) + turno;
    };
  
});