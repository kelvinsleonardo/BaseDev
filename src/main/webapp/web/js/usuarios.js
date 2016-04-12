(function() {
    'use strict';

    var app = angular.module("usuarios", []);

    app.controller('GestaoUsuarioController', function($http, $scope, $rootScope, $routeParams, $location){

        $scope.pagina = 1;

        $scope.buscarUsuarios = function(){
            buscarUsuarios()
        }

        buscarUsuarios();

        function buscarUsuarios(){
            console.log("Buscando");
            $http.get("usuarios/buscar/"+$scope.pagina, {data: null}).success(function(data) {
                console.log(data);
                $scope.usuarios = data.usuarios;
                $scope.temProximaPagina = data.temProximaPagina;
                $scope.numRegistros = data.numRegistros;
            });
        }
    });

})();