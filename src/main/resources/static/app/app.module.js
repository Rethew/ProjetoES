(function() {
    'use strict';

    angular.module('presenceApp', ['ngRoute'])
        .config(['$locationProvider', function($locationProvider) {
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
        }]);
})();
