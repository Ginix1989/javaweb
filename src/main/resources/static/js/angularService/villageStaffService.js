var mysubC = angular.module('actionApp');
mysubC.service('myService',[function () {

    return {
        getData:function () {
            alert('sssss');
        }
    }
}]);