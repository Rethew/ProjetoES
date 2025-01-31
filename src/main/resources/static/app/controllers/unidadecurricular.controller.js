(function() {
    'use strict';

    angular.module('presenceApp')
        .controller('UnidadeCurricularController', ['unidadeCurricularService', '$scope', function(unidadeCurricularService, $scope) {
            var vm = this;

            vm.newDisciplina = {
                nome: '',
                docenteId: ''
            };

            vm.disciplinas = [];
            vm.loading = false;
            vm.error = null;
            vm.success = null;

            vm.init = function() {
                vm.loading = true;
                unidadeCurricularService.getAll().then(function(data) {
                    vm.disciplinas = data;
                    vm.loading = false;
                }, function(err) {
                    vm.error = "Failed to load disciplines.";
                    vm.loading = false;
                });
            };

            vm.registerDisciplina = function() {
                vm.error = null;
                vm.success = null;

                if(!vm.newDisciplina.nome || !vm.newDisciplina.docenteId) {
                    vm.error = "Both name and docente ID are required.";
                    return;
                }

                unidadeCurricularService.save(vm.newDisciplina).then(function(savedDisciplina) {
                    vm.success = "Discipline registered successfully.";
                    vm.disciplinas.push(savedDisciplina);
                    vm.resetForm();
                }, function(err) {
                    vm.error = "Error registering discipline.";
                });
            };

            vm.resetForm = function() {
                vm.newDisciplina = {
                    nome: '',
                    docenteId: ''
                };
                $scope.disciplinaForm.$setPristine();
                $scope.disciplinaForm.$setUntouched();
            };

            vm.init();
        }]);
})();