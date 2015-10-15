app.service('EnvService', function() {
  // Might use a resource here that returns a JSON array
    
    var ambiente = 'TREINO';

    var listaRU = {
        'PRODUCAO'  : 
            { '22' : "santa-monica/",
              '21' : "umuarama/",
              '23' : "ituiutaba/"},
               
        'TREINO':   
            { '42' : "umuarama/",
              '43' : "santa-monica/",
              '23' : "ituiutaba/"}
    };
   

  

    this.ambiente = function() {
      return ambiente;
    };

    this.nomeRestaurantePorIdRestaurante = function(idRU) {
      if(idRU && ambiente == 'PRODUCAO'){
        return listaRU.PRODUCAO[''+idRU+''];
      }else if (idRU && ambiente == 'TREINO'){
          return listaRU.TREINO[''+idRU+''];
      }
    };

    this.buscarTurnoRestaurante = function() {
        var hoje = new Date();
        var horas = hoje.getHours();

        if(horas<15){
          return "almoco";
        }
        else{
          return "jantar";
        }
    }
  
});