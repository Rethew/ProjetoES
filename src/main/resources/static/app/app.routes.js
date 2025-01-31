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
                .otherwise({
                    redirectTo: '/uc'
                });
        }]);
})();
