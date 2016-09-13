var UserFormController = function($scope) {
    $scope.firstName='';
    $scope.lastname='';
    $scope.contacts = [];
    $scope.addContact = function () {
        $scope.contacts.push({})
    }

    $scope.showData = function () {
        alert($scope.firstName + ' ' + $scope.lastname);
    }
};

angular.module('userApp').component('userForm', {
    templateUrl: 'js/user-form.template.html',
    controller: UserFormController
})
;