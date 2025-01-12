(function() {
    'use strict';

    angular.module('presenceApp')
        .factory('presenceService', ['$http', '$q', function($http, $q) {

            var baseUrl = '/api/presencas';

            return {
                getAll: function() {
                    return $http.get(baseUrl).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                },

                save: function(presence) {
                    return $http.post(baseUrl, presence).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                }
            };
        }]);
})();
