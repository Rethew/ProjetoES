(function() {
    'use strict';

    angular.module('presenceApp')
        .factory('aulasService', ['$http', '$q', function($http, $q) {
            var baseUrl = '/api/aulas';

            return {
                getAll: function() {
                    return $http.get(baseUrl).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                },

                createAula: function(aula) {
                    return $http.post(baseUrl, aula).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                },

                updateAula: function(id, aula) {
                    return $http.put(baseUrl + '/' + id, aula).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                },

                deleteAula: function(id) {
                    return $http.delete(baseUrl + '/' + id).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                }
            };
        }]);
})();
