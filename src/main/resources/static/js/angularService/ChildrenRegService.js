var childrenRegService = angular.module("childrenRegService", []);

childrenRegService.service('saveChildrenRegInfo', function ($http, $window, $q) {


    this.saveChildrenRegInfoMethod = (function (childreninfo) {
        //保存注册信息
        $http({
            method: "POST",
            url: "../../index/reg/saveChildrenForEidt",
            data: childreninfo
        }).then(function (responseData) {
            console.log(responseData.data);
            alert("注册成功");
            $window.location.href = "../views/login.html";

        }).catch(function (reasion) {
            alert(reasion);
        });


    });



    this.getAllParent = (function () {

        var defer = $q.defer();
        $http({
            method: "GET",
            url: "../../index/reg/getAllParetnInfoReg"
        }).then(function (response) {
            defer.resolve(response.data);
        }).catch(function (reason) {
            defer.reject(data);
        });
        return defer.promise;

    });


})