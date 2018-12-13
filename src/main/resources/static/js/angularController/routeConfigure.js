//路由设置
//注意路径加载 是当前页面的相对位置
var actionApp = angular.module('actionApp', ['ngRoute']);
actionApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/servInfo', {
            controller: 'serveInfoController',
            templateUrl: '../views/mainInfoPages/serveInfo.html',
        })
        .when('/regEdit', {
            controller: 'regEditController',
            templateUrl: '../views/reg/regEdit.html',
        });

}]);
//控制器 控制预约模块
actionApp.controller('serveInfoController', ['$rootScope', '$scope', '$http',
    function ($rootScope, $scope, $http) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('页面加载完成 serveInfoController');
        });
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
        //预约服务
        $scope.orderServe = (function (serverId) {
            // 获取页面数据
            $scope.data = {
                serveInfoId: serverId,
                parentId: 1,
                dateTime: "2018-07-11"
            };
            //ajax提交数据
            $http({
                url: 'saveParentUseServe',
                method: 'POST',
                data: $scope.data
            }).then({
                function() {
                    alert("请求成功");
                }
            }).catch(function (reason) {
                alert(reason)
            });
        })
        // 删除服务信息
        $scope.deleteServeInfoItem = (function (serverId) {
            $scope.data={
                id:serverId
            };
            $http({
                url: 'deleteServeInfoById',
                method: 'Get',
                data:      $scope.data,//作为消息体参数
                params:{id:serverId}//为url的参数
            }).then(function (value) {
                alert("删除成功");
                $rootScope.serveInfoList=value.data;
            }, function (reason) {
            }).catch(function (reason) {
            });
        });
        //addServeInfo PopWindow
        // $scope.addServeInfo =(function () {
        //
        //     var myPopup=  $ionicPopup.show({
        //
        //         templateUrl:"../views/addInfo/addServeInfo.html",
        //         title:'新增服务信息',
        //         scope:$scope,
        //         buttons: [
        //             { text: 'Cancel' },
        //             {
        //                 text: '<b>Save</b>',
        //                 type: 'button-positive',
        //                 onTap: function(e) {
        //                     if (!$scope.serveAddInfo) {
        //                         //不允许用户关闭，除非他键入wifi密码
        //                         console.log(serveAddInfo);
        //                         e.preventDefault();
        //                     } else {
        //                         return $scope.serveAddInfo;
        //                     }
        //                 }
        //             },
        //         ]
        //     });
        //
        // });
        $scope.addServeInfo=(function () {
            alert('tanchu');
        });
    }]);

//用户信息修改
actionApp.controller('regEditController', ['$rootScope', '$scope', '$http',
    function ($rootScope, $scope, $http) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('页面加载完成');
        });
        //获取父母用户信息，用以修改
        $http({
            method: 'GET',
            url: "getParentInfoForEdit"
        }).then(function (responseData) {
            console.log(responseData);
            $scope.parentInfo = responseData.data;
        }).catch(function (reason) {
            alert(reason)
        });
        //保存父母信息
        $scope.saveParentInfo = (function (parentId) {
            $http({
                url: 'saveParentInfo',
                method: "POST",
                data: $scope.parentInfo
            }).then(function (data) {
                alert("保存成功");
            }, function (rep) {
                alert(rep.data);
            }).catch(function (reason) {
                alert(reason.data);
            });
        });
    }]);

