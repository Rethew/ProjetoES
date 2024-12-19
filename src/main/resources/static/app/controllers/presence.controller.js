(function() {
    'use strict';

    angular.module('presenceApp')
        .controller('PresenceController', ['presenceService', '$scope', function(presenceService, $scope) {
            var vm = this;

            vm.newPresence = {
                name: '',
                date: new Date(), // or default to current date/time
                status: 'Present' // example field if needed
            };

            vm.presences = [];
            vm.loading = false;
            vm.error = null;
            vm.success = null;

            vm.init = function() {
                vm.loading = true;
                presenceService.getAll().then(function(data) {
                    vm.presences = data;
                    vm.loading = false;
                }, function(err) {
                    vm.error = "Failed to load presences.";
                    vm.loading = false;
                });
            };

            vm.registerPresence = function() {
                vm.error = null;
                vm.success = null;

                if(!vm.newPresence.name) {
                    vm.error = "Name is required.";
                    return;
                }

                // Additional validation logic can be added here

                presenceService.save(vm.newPresence).then(function(savedPresence) {
                    vm.success = "Presence registered successfully.";
                    vm.presences.push(savedPresence);
                    vm.resetForm();
                }, function(err) {
                    vm.error = "Error registering presence.";
                });
            };

            vm.resetForm = function() {
                vm.newPresence = {
                    name: '',
                    date: new Date(),
                    status: 'Present'
                };
                $scope.presenceForm.$setPristine();
                $scope.presenceForm.$setUntouched();
            };

            vm.init();
        }]);
})();
