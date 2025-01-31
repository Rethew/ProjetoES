(function() {
    'use strict';

    angular.module('presenceApp')
        .factory('unidadeCurricularService', ['$http', '$q', function($http, $q) {

            var baseUrl = '/api/unidadecurricular';

            return {
                getAll: function() {
                    return $http.get(baseUrl).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                },

                save: function(disciplina) {
                    return $http.post(baseUrl, disciplina).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                },

                delete: function(id) {
                    return $http.delete(baseUrl + '/' + id).then(
                        function(response) { return response.data; },
                        function(error) { return $q.reject(error); }
                    );
                }
            };
        }]);
})();