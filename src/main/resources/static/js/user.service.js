/**
 * Created by mgiec on 9/12/2016.
 */
angular.
module('userApp').
factory('User', ['$resource',
    function($resource) {
        return $resource('http://localhost:8080/rest/person/all.json', {}, {
            query: {
                method: 'GET',
                // params: {phoneId: 'phones'},
                isArray: true
            }
        });
    }
]);