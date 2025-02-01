(function() {
    'use strict';

    angular.module('presenceApp')
        .config(['$routeProvider', function($routeProvider) {
            $routeProvider
                .when('/uc', {
                    templateUrl: 'views/uc.html',
                    controller: 'UnidadeCurricularController',
                    controllerAs: 'uc'
                })

                .when('/aulas', {
                    templateUrl: 'views/aulas.html',
                    controller: 'AulasController',
                    controllerAs: 'aulas',
                })

                .when('/presence', {
                    templateUrl: 'views/presence.html',
                    controller: 'PresenceController',
                    controllerAs: 'pc'
                })

                .otherwise({
                    redirectTo: '/presence'
                });
        }]);
})();
