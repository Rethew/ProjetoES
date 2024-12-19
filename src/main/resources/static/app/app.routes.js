(function() {
    'use strict';

    angular.module('presenceApp')
        .config(['$routeProvider', function($routeProvider) {
            $routeProvider
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
