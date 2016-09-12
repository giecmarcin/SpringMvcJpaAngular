'use strict';

App.controller('UserController', ['$scope', 'User', function($scope, User) {
    var self = this;
    self.user= new User();

    self.users=[];

    self.fetchAllUsers = function(){
        self.users = User.query();
    };
}]);