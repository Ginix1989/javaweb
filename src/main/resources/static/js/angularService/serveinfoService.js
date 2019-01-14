var serveinfoService =angular.module('serveinfoService',[]);
serveinfoService.service('getAllServeinfo',function ($http) {
    // 动态请求服务信息内容
    $http.get("getAllServeInfo")
        .then(function (responsedata) {
            console.log(responsedata);
            $rootScope.serveInfoList = responsedata.data;
        })
        .catch(function (response) {
                console.log(response);
            }
        );
    
})