(function() {
    'use strict';

    angular.module('presenceApp')
        .controller('AulasController', ['$routeParams', 'aulasService', '$scope', function($routeParams, aulasService, $scope) {
            var vm = this;

            vm.aulas = [];
            vm.newAula = {};
            vm.loading = true;
            vm.error = null;
            vm.success = null;

            vm.init = function() {
                vm.loading = true;
                aulasService.getAll().then(function(data) {
                    vm.aulas = data;
                    vm.loading = false;
                }, function(err) {
                    vm.error = "Failed to load disciplines.";
                    vm.loading = false;
                });
            };

            vm.createAula = function() {
                vm.error = null;
                vm.success = null;

                // Validate required fields
                if (!vm.newAula.data || !vm.newAula.hora || !vm.newAula.duracao || !vm.newAula.estado) {
                    vm.error = "All fields are required.";
                    return;
                }

                // Format `data` to `yyyy-MM-dd`
                const formattedData = new Date(vm.newAula.data).toISOString().split('T')[0]; // yyyy-MM-dd

                // Format `hora` to `HH:mm:ss`
                let formattedHora = vm.newAula.hora;
                if (formattedHora.length === 5) {
                    formattedHora += ":00"; // Add seconds if not present
                }

                // Prepare the object to send to the backend
                const formattedAula = {
                    data: formattedData, // Formatted as `yyyy-MM-dd`
                    hora: formattedHora, // Formatted as `HH:mm:ss`
                    duracao: vm.newAula.duracao,
                    estado: vm.newAula.estado
                };

                // Log the payload for debugging
                console.log("Payload being sent to backend:", formattedAula);

                // Send the formatted data to the backend
                aulasService.createAula(formattedAula).then(function(savedAula) {
                    vm.success = "Aula created successfully.";
                    vm.aulas.push(savedAula); // Add the saved Aula to the list
                    vm.newAula = {}; // Reset the form
                }, function(err) {
                    vm.error = "Failed to create Aula.";
                });
            };


            vm.deleteAula = function(id) {
                if (confirm("Are you sure you want to delete this Aula?")) {
                    aulasService.deleteAula(id).then(function() {
                        vm.success = "Aula deleted successfully.";
                        vm.aulas = vm.aulas.filter(function(aula) {
                            return aula.id !== id;
                        });
                    }, function(err) {
                        vm.error = "Failed to delete aula.";
                    });
                }
            };

            vm.init();
        }]);
})();
