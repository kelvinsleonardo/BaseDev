(function() {
    'use strict';

	// Declarando modulo com as dependencias
	var app = angular.module("app", ['ngRoute', 'usuarios']);

	// Definindo cabeçalho JSON
	app.run(function($http) {
        $http.defaults.headers.get = {'Content-Type': 'application/json'};

    });


	// Declarando rotas do sistema
	app.config(function($routeProvider, $httpProvider) {
	    $routeProvider

	      	// Rota da tela home
            .when('/', {
                templateUrl : 'inicio.html',
                controller : 'AppController',
                resolve: {
                    setMenu: function($rootScope) {
                        $rootScope.menu = "home";
                    }
                }
            })
            // Tela gestao usuarios
            .when('/gestao_usuarios', {
                templateUrl : 'gestaoUsuarios.html',
                controller : 'GestaoUsuarioController',
                resolve: {
                    setMenu: function($rootScope) {
                        $rootScope.menu = "usuarios";
                    }
                }
            });

        // Declarando interceptador para autenticacao login     	
        $httpProvider.interceptors.push("httpInterceptor");
	});

	// Interceptador para verificar se usuario esta logado.
	app.factory("httpInterceptor", function ($q, $window) {
        return {
            "response": function (response) {
                var respostaCabecalho = response.headers();
                console.log(respostaCabecalho);
                //verifica se o usuario esta deslogado
                if (respostaCabecalho["content-type"] 
                	&& respostaCabecalho["content-type"].indexOf("text/html") !== -1
                    && response.data
                    && response.data.indexOf('<meta name="unauthorized" content="true">') !== -1) {
                    // Recarregando pagina para exibir login.
                    $window.location.reload();
                    return $q.reject(response);
                }

                return response;
            }
        };
    });

	// Controlador App
    app.controller("AppController", function($http, $scope, $rootScope, $location) {
        $scope.exibirUsuarios = function() {
            $location.path('buscar_usuario');
        };

    });

})();