
angular.module('userApp').component('userList', {
    templateUrl: 'js/user-list.template.html',
    controller: ['User',
        function UserListController(User) {
            this.users = User.query();
        }
    ]
})
;
