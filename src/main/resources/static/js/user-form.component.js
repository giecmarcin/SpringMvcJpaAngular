var UserFormController = function($scope) {
    $scope.contacts = [];
    $scope.addContact = function () {
        $scope.contacts.push({})
    }
};

angular.module('userApp').component('userForm', {
    templateUrl: 'js/user-form.template.html',
    controller: UserFormController
})
;