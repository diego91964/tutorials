app.controller('ParticipanteController' ,function($scope, ParticipanteService, RestaurantesService, EnvService, ionPlatform , $stateParams) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});
  
  $scope.restaurantesList = null;
  $scope.restauranteSelecionado = null;
  $scope.participante = {};

  ionPlatform.ready.then(function (device) {


      $scope.piechart = [
        {label: "one", value: 12.2, color: "red"}, 
        {label: "two", value: 45, color: "#00ff00"},
        {label: "three", value: 10, color: "rgb(0, 0, 255)"}
      ];

      $scope.diasSemana = ["Segunda-Feira", "Terça-Feira" , "Quarta-Feira" ,"Quinta-Feira" , "Sexta-Feira"];

      $scope.myActiveSlide = 1;

      $scope.data = {
        isLoading: false
      };

      $scope.restaurantesList =  ["Santa Monica", "Umuarama" , "Pontal" ,"Patos de Minas" , "Gloria"];/*RestaurantesService.buscarTodosRestaurantes()
          .success(function(result) {
              $scope.restaurantesList = result;
            })
          .error(function (data, status) {
              console.log("Erro ao buscar restaurantes." + data + " " + status);
          }); */
   });


    $scope.cadastrar = function(){
        ParticipanteService.cadastrarParticipante( $scope.participante);
    };

 $scope.setRestaurante = function (restaurante) {
      $scope.restauranteSelecionado = restaurante;
      var turno = EnvService.buscarTurnoRestaurante();
      

      RestaurantesService.buscarCardapioRestaurantesPorTurnoEId(restaurante.idLocalRu , turno)
            .success(function(result) {
              $scope.restauranteSelecionado.cardapio = result;
            })
          .error(function (data, status) {
              console.log("Erro ao buscar cardapio." + data + " " + status);
      });

  }


});