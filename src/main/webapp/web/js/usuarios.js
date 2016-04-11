(function() {
    'use strict';

    var app = angular.module("usuarios", []);

    app.controller('GestaoUsuarioController', function($http, $scope, $rootScope, $routeParams, $location){

        console.log("Controlador Usuarios");


        $http.get("usuarios/buscar", {data: null}).success(function(data) {
            $scope.usuarios = data.usuarios;
        });
    });

})();