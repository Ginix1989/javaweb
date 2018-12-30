//路由设置
//注意路径加载 是当前页面的相对位置
var actionApp = angular.module('actionApp', ['ngRoute', 'ui.bootstrap']);
actionApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/servInfo', {//获取服务信息
            controller: 'serveInfoController',
            templateUrl: '../views/mainInfoPages/serveInfo.html',
        })
        .when('/regEdit', {//注册信息
            controller: 'regEditController',
            templateUrl: '../views/reg/regEdit.html',
        })
        .when('/regVillageStaff', {//小区员工路由 负责小区员工CRUD
            controller: 'regVillageStaffController',
            templateUrl: '../views/reg/regVillageStaff.html',
        })
        .when('/regVillageAdminStaff', {//小区管理员路由 负责小区管理员CRUD
            controller: 'regVillageAdminStaffController',
            templateUrl: '../views/reg/regVillageAdminStaff.html',
        })
        .when('/regParent', {//父母信息管理路由 负责父母信息CRUD
            controller: 'regParentInfoController',
            templateUrl: '../views/reg/regParentInfo.html',
        })
        .when('/regChildren', {//子女信息管理路由 负责子女信息CRUD
            controller: 'regChildrenInfoController',
            templateUrl: '../views/reg/regChildrenInfo.html',
        })
        .when('/gradeServe',{
            controller:'gradeServeController',
            templateUrl:'../views/gradeInfo/gradeServeInfo.html'
        })
    ;
}]);
//控制器 控制预约模块
actionApp.controller('serveInfoController', ['$rootScope', '$scope', '$http', '$uibModal', '$log',
    function ($rootScope, $scope, $http, $uibModal, $log) {
        $scope.$on('$viewContentLoaded', function () {

            // 动态请求服务信息内容
            $http.get("getParentAuthor")
                .then(function (responsedata) {
                    console.log(responsedata.data);
                    $scope.parentAuthor = responsedata.data;
                    //$rootScope.serveInfoList = responsedata.data;
                })
                .catch(function (response) {
                        console.log(response);
                    }
                );

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
            //预约服务信息
            serveData={
                serveInfoId:serverId,
                parentId:"",
                grade:0,
                note:""
            };

            var ordermodalInstance = $uibModal.open({
                templateUrl: '../views/addInfo/addOrderServeInfo.html',
                controller: orderModalInstanceCtrl,
                size: 'lg',
                backdrop: 'true',
                //  windowClass: {},
                // resolve: {
                //     addVillageStaffInfo: null
                // }
            });
            // 模态窗口打开之后执行的函数
            ordermodalInstance.opened.then(function () {

                console.log('modal is opened');
                console.log();
            });
            ordermodalInstance.result.then(function (result) {
                console.log('returnValue:');
                console.log(result);
                //$scope.addVillageStaffInfo = result;
               // console.log($scope.addVillageStaffInfo);
               //  renturnValue and  saveValeu to villagestaff
                serveData['orderdateTime']=result;
                $http({
                    method: 'POST',
                    url: 'saveParentOrderInfo',

                    data: serveData,//作为消息体参数
                }).then(function (value) {
                    alert("保存成功");
                  //  $scope.villageStaffList = value.data;
                }, function (reason) {
                }).catch(function (reason) {
                });
            }, function (reason) {
                console.log(reason);// 点击空白区域，总会输出backdrop
                // click，点击取消，则会暑促cancel
                console.log('Modal dismissed at: ' + new Date());
            });







            // // 获取页面数据
            // $scope.data = {
            //     serveInfoId: serverId,
            //     parentId: 1,
            //     dateTime: "2018-07-11"
            // };
            // //ajax提交数据
            // $http({
            //     url: 'saveParentUseServe',
            //     method: 'POST',
            //     data: $scope.data
            // }).then({
            //     function() {
            //         alert("请求成功");
            //     }
            // }).catch(function (reason) {
            //     alert(reason)
            // });
        });
        // 删除服务信息
        $scope.deleteServeInfoItem = (function (serverId) {
            $scope.data = {
                id: serverId
            };
            $http({
                url: 'deleteServeInfoById',
                method: 'Get',
                data: $scope.data,//作为消息体参数
                params: {id: serverId}//为url的参数
            }).then(function (value) {
                alert("删除成功");
                $rootScope.serveInfoList = value.data;
            }, function (reason) {
            }).catch(function (reason) {
            });
        });

        $scope.items = ['item1', 'item2', 'item3'];
        //弹出新增窗口
        $scope.addServeInfo = function () {

            $scope.serveAddInfo = null;
            var modalInstance = $uibModal.open({
                templateUrl: '../views/addInfo/addServeInfo.html',
                controller: ModalInstanceCtrl,
                size: 'lg',
                backdrop: 'true',
                //  windowClass: {},
                resolve: {
                    items: function () {
                        return $scope.items;
                    }
                }
            });
            // 模态窗口打开之后执行的函数
            modalInstance.opened.then(function () {
                console.log('modal is opened');
            });
            modalInstance.result.then(function (result) {
                console.log('returnValue:');
                console.log(result);

                //renturnValue and  saveValeu to ServeinfoTable
                $http({
                    url: 'saveServeInfo',
                    method: 'Get',
                    //data: $scope.data,//作为消息体参数
                    params: {serveInfo: result}//为url的参数
                }).then(function (value) {
                    alert("保存成功");
                    $rootScope.serveInfoList = value.data;
                }, function (reason) {
                }).catch(function (reason) {
                });
            }, function (reason) {
                console.log(reason);// 点击空白区域，总会输出backdrop
                // click，点击取消，则会暑促cancel
                $log.info('Modal dismissed at: ' + new Date());
            });
        }
    }]);

//弹出框控制器
var ModalInstanceCtrl = function ($scope, $uibModalInstance) {
    $scope.ok = function () {
        console.log($scope.serveAddInfo);
        $uibModalInstance.close($scope.serveAddInfo);////关闭并返回当前选项
    };
    $scope.cancel = function () {
        console.log("取消");
        $uibModalInstance.dismiss('cancel');
    };
};


//预约服务弹出框控制器
var orderModalInstanceCtrl = function ($scope, $uibModalInstance) {
    $scope.ok = function () {
        console.log($scope.serveTimeInfo);
        $uibModalInstance.close($scope.serveTimeInfo);////关闭并返回当前选项
    };
    $scope.cancel = function () {
        console.log("取消");
        $uibModalInstance.dismiss('cancel');
    };
};

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

//小区员工控制器
actionApp.controller('regVillageStaffController', ['$rootScope', '$scope', '$http', '$uibModal',
    function ($rootScope, $scope, $http, $uibModal) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('regVillageStaffController页面加载完成');
        });
        // myService.getData();
        //获取员工信息
        $http({
            method: "GET",
            url: "getAllvillageStaff"
        }).then(function (responseData) {
            console.log(responseData);
            $scope.villageStaffList = responseData.data;
        }).catch(function (reason) {
            alert(reason);
        });
        //删除员工信息
        $scope.deleteVillageStaff = (function (villageStaffid) {
            $http({
                method: "GET",
                url: "deleteVillageStaffById",
                params: {villageStaffId: villageStaffid}//为url的参数
            }).then(function (responseData) {
                alert("删除成功");
                $scope.villageStaffList = responseData.data;
            }).catch(function (reason) {
                alert(reason)
            });

        });
        //新增员工信息弹出框
        $scope.addVillageStaff = (function () {

            var addVillagemodalInstance = $uibModal.open({
                templateUrl: '../views/addInfo/addVillagestaff.html',
                controller: addVillageModalInstanceCtrl,
                size: 'lg',
                backdrop: 'true',
                //  windowClass: {},
                resolve: {
                    addVillageStaffInfo: null
                }
            });
            // 模态窗口打开之后执行的函数
            addVillagemodalInstance.opened.then(function () {

                console.log('modal is opened');
                console.log();
            });
            addVillagemodalInstance.result.then(function (result) {
                console.log('returnValue:');
                console.log(result);
                $scope.addVillageStaffInfo = result;
                console.log($scope.addVillageStaffInfo);
                //renturnValue and  saveValeu to villagestaff
                $http({
                    method: 'POST',
                    url: 'saveVillageStaffInfo',

                    data: result,//作为消息体参数
                }).then(function (value) {
                    alert("保存成功");
                    $scope.villageStaffList = value.data;
                }, function (reason) {
                }).catch(function (reason) {
                });
            }, function (reason) {
                console.log(reason);// 点击空白区域，总会输出backdrop
                // click，点击取消，则会暑促cancel
                console.log('Modal dismissed at: ' + new Date());
            });

        });
        //修改员工信息
        $scope.editVillageStaff = (function (village) {

            var addVillagemodalInstance = $uibModal.open({
                templateUrl: '../views/addInfo/addVillagestaff.html',
                controller: addVillageModalInstanceCtrl,
                size: 'lg',
                backdrop: 'true',
                //  windowClass: {},
                resolve: {
                    addVillageStaffInfo: function () {
                        return village;
                    }
                }
            });
            // 模态窗口打开之后执行的函数
            addVillagemodalInstance.opened.then(function () {

                console.log('modal is opened');
                console.log();
            });
            addVillagemodalInstance.result.then(function (result) {
                console.log('returnValue:');
                console.log(result);
                $scope.addVillageStaffInfo = result;
                console.log($scope.addVillageStaffInfo);
                //renturnValue and  saveValeu to villagestaff
                $http({
                    method: 'POST',
                    url: 'saveVillageStaffInfo',

                    data: result,//作为消息体参数
                }).then(function (value) {
                    alert("保存成功");
                    $scope.villageStaffList = value.data;
                }, function (reason) {
                }).catch(function (reason) {
                });
            }, function (reason) {
                console.log(reason);// 点击空白区域，总会输出backdrop
                // click，点击取消，则会暑促cancel
                console.log('Modal dismissed at: ' + new Date());
            });


        });


    }
]);


//新增员工信息弹出框控制器
var addVillageModalInstanceCtrl = function ($scope, $uibModalInstance, addVillageStaffInfo) {

    if (addVillageStaffInfo != undefined & addVillageStaffInfo != null) {

        $scope.addVillageStaffInfo = addVillageStaffInfo;
    }
    $scope.ok = function () {
        console.log($scope.addVillageStaffInfo);
        $uibModalInstance.close($scope.addVillageStaffInfo);////关闭并返回当前选项
    };
    $scope.cancel = function () {
        console.log("取消");
        $uibModalInstance.dismiss('cancel');
    };
};


// //小区管理员控制器
actionApp.controller('regVillageAdminStaffController', ['$rootScope', '$scope', '$http', '$uibModal',
    function ($rootScope, $scope, $http, $uibModal) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('regVillageStaffController页面加载完成');
        });
        // myService.getData();
        //获取员工信息
        $http({
            method: "GET",
            url: "getAllvillageAdminStaff"
        }).then(function (responseData) {
            console.log(responseData);
            $scope.villageAdminStaffList = responseData.data;
        }).catch(function (reason) {
            alert(reason);
        });
        //删除员工信息
        $scope.deleteVillageAdminStaff = (function (villageAdminStaffid) {
            $http({
                method: "GET",
                url: "deleteVillageAdminStaffById",
                params: {villageAdminStaffId: villageAdminStaffid}//为url的参数
            }).then(function (responseData) {
                alert("删除成功");
                $scope.villageAdminStaffList = responseData.data;
            }).catch(function (reason) {
                alert(reason)
            });

        });
        //新增员工信息弹出框
        $scope.addVillageAdminStaff = (function () {

            var addVillagemodalInstance = $uibModal.open({
                templateUrl: '../views/addInfo/addVillageadminstaff.html',
                controller: addVillageAdminModalInstanceCtrl,
                size: 'lg',
                backdrop: 'true',
                //  windowClass: {},
                resolve: {
                    villageAdminStaff: null
                }
            });
            // 模态窗口打开之后执行的函数
            addVillagemodalInstance.opened.then(function () {

                console.log('modal is opened');
                console.log();
            });
            addVillagemodalInstance.result.then(function (result) {
                console.log('returnValue:');
                console.log(result);
                //$scope.villageAdminStaff = result;
                //console.log($scope.villageAdminStaff);
                //renturnValue and  saveValeu to villagestaff
                $http({
                    method: 'POST',
                    url: 'saveVillageAdminStaffInfo',

                    data: result,//作为消息体参数
                }).then(function (value) {
                    $scope.villageAdminStaffList = value.data;
                    alert("保存成功");

                }, function (reason) {
                }).catch(function (reason) {
                });
            }, function (reason) {
                console.log(reason);// 点击空白区域，总会输出backdrop
                // click，点击取消，则会暑促cancel
                console.log('Modal dismissed at: ' + new Date());
            });

        });

        // $scope.editVillageAdminStaff=(function()
        // {
        //     alert('ss');
        // });
        // $scope.ddd=function () {
        //     alert('sss');
        // }
        //修改员工信息
        $scope.editVillageAdminStaff = (function (village) {

            var addVillagemodalInstance = $uibModal.open({
                templateUrl: '../views/addInfo/addVillageadminstaff.html',
                controller: addVillageAdminModalInstanceCtrl,
                size: 'lg',
                backdrop: 'true',
                //  windowClass: {},
                resolve: {
                    villageAdminStaff: function () {
                        return village;
                    }
                }
            });
            // 模态窗口打开之后执行的函数
            addVillagemodalInstance.opened.then(function () {

                console.log('modal is opened');
                console.log();
            });
            addVillagemodalInstance.result.then(function (result) {
                console.log('returnValue:');
                console.log(result);
                // $scope.villageAdminStaffList = result;
                // console.log($scope.villageAdminStaffList);
                //renturnValue and  saveValeu to villagestaff
                $http({
                    method: 'POST',
                    url: 'saveVillageAdminStaffInfo',

                    data: result,//作为消息体参数
                }).then(function (value) {
                    $scope.villageStaffList = value.data;
                    alert("保存成功");

                }, function (reason) {
                }).catch(function (reason) {
                });
            }, function (reason) {
                console.log(reason);// 点击空白区域，总会输出backdrop
                // click，点击取消，则会暑促cancel
                console.log('Modal dismissed at: ' + new Date());
            });


        });


    }
]);

//新增管理员信息弹出框控制器
var addVillageAdminModalInstanceCtrl = function ($scope, $uibModalInstance, villageAdminStaff) {

    if (villageAdminStaff != undefined & villageAdminStaff != null) {

        $scope.villageAdminStaff = villageAdminStaff;
    }
    $scope.ok = function () {
        console.log($scope.villageAdminStaff);
        $uibModalInstance.close($scope.villageAdminStaff);////关闭并返回当前选项
    };
    $scope.cancel = function () {
        console.log("取消");
        $uibModalInstance.dismiss('cancel');
    };
};

//小区父母信息控制器
actionApp.controller('regParentInfoController', ['$rootScope', '$scope', '$http', '$uibModal',
    function ($rootScope, $scope, $http, $uibModal) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('regParentInfoController页面加载完成');
        });

        //获取父母信息列表
        $http({
            method: "GET",
            url: 'getAllParetnInfo'
        }).then(function (responseData) {
            console.log(responseData.data);
            $scope.parentInfoList = responseData.data;
        }).catch(function (reason) {
            alert(reason);
        });

        //删除父母信息
        $scope.deleteParentInfo = (function (parentInfoid) {
            $http({
                method: "GET",
                url: "deleteParentInfoById",
                params: {parentId: parentInfoid}
            }).then(function (responseData) {
                console.log(responseData.data);
                alert('删除成功');
                $scope.parentInfoList = responseData.data;
            }).catch(function (reason) {
                alert(reason);
            });
        });

        //修改审核状态
        $scope.isAccess=(function (parentInfo) {

            parentInfo['isAccess']=1;



            $http({

                method: 'POST',
                url: 'saveParentInfoEntity',
                data: parentInfo
            }).then(function (value) {
                $scope.parentInfoList = value.data;
                alert('保存成功');
            }).catch(function (reason) {
                alert(reason);
            });
            
        });
        
        //新增父母信息弹出框
        $scope.addparentInfo = (function () {

            var addParentInfoModalInstance = $uibModal.open(
                {
                    templateUrl: '../views/addInfo/addParentInfo.html',
                    controller: addParentInfoModalInstanceCtrl,
                    size: 'lg',
                    backdrop: 'true',
                    resolve: {
                        parentInfoModal: null
                    }
                }
            );


            //打开对话框后
            addParentInfoModalInstance.opened.then(function () {
                console.log('open ParentInfo Modal');
            });
            addParentInfoModalInstance.result.then(function (resultData) {
                console.log(resultData);
                $http({

                    method: 'POST',
                    url: 'saveParentInfoEntity',
                    data: resultData
                }).then(function (value) {
                    $scope.parentInfoList = value.data;
                    alert('保存成功');
                }).catch(function (reason) {
                    alert(reason);
                });
            }, function (reason) {
                console.log(reason);//点击空白区域 和取消
                console.log('Modal dismissed at' + new Date());
            })
        });

        //修改父母信息
        $scope.editParentInfo = (function (parentInfo) {

            var addParentInfoModalInstance = $uibModal.open(
                {
                    templateUrl: '../views/addInfo/addParentInfo.html',
                    controller: addParentInfoModalInstanceCtrl,
                    size: 'lg',
                    backdrop: 'true',
                    resolve: {
                        parentInfoModal: parentInfo
                    }
                }
            );
            //打开对话框后
            addParentInfoModalInstance.opened.then(function () {
                console.log('open ParentInfo Modal');
            });
            addParentInfoModalInstance.result.then(function (resultData) {
                console.log(resultData);

                $http({

                    method: 'POST',
                    url: 'saveParentInfoEntity',
                    data: resultData
                }).then(function (value) {
                    $scope.parentInfoList = value.data;
                    alert('保存成功');
                }).catch(function (reason) {
                    alert(reason);
                });

            }, function (reason) {
                console.log(reason);//点击空白区域 和取消
                console.log('Modal dismissed at' + new Date());
            });

        })
    }
]);

//新增父母控制器弹出框

var addParentInfoModalInstanceCtrl = function ($scope, $uibModalInstance, parentInfoModal) {

    if (parentInfoModal != undefined & parentInfoModal != null) {
        $scope.parentInfoModal = parentInfoModal;
    }

    $scope.ok = function () {
        console.log($scope.parentInfoModal);
        $uibModalInstance.close($scope.parentInfoModal);////关闭并返回当前选项
    }
    $scope.cancel = function () {
        console.log("取消");
        $uibModalInstance.dismiss('cancel');
    };

}


//小区子女信息控制器
actionApp.controller('regChildrenInfoController', ['$rootScope', '$scope', '$http', '$uibModal',
    function ($rootScope, $scope, $http, $uibModal) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('regChildrenInfoController页面加载完成');
        });

        //获取子女信息列表
        $http({
            method: "GET",
            url: 'getAllChildrenInfo'
        }).then(function (responseData) {
            console.log(responseData.data);
            $scope.childrenInfoList = responseData.data;
        }).catch(function (reason) {
            alert(reason);
        });


        //修改审核状态
        $scope.isAccess=(function (childrenInfo) {

            childrenInfo['isAccess']=1;



            $http({

                method: 'POST',
                url: 'saveChildrenInfoEntity',
                data: childrenInfo
            }).then(function (value) {
                $scope.childrenInfoList = value.data;
                alert('审核成功');
            }).catch(function (reason) {
                alert(reason);
            });

        });

        //删除子女信息
        $scope.deleteChildrenInfo = (function (childrenInfoid) {
            $http({
                method: "GET",
                url: "deleteChildrenInfoById",
                params: {childrenId: childrenInfoid}
            }).then(function (responseData) {
                console.log(responseData.data);
                alert('删除成功');
                $scope.childrenInfoList = responseData.data;
            }).catch(function (reason) {
                alert(reason);
            });
        });

        //新增子女信息弹出框
        $scope.addchildrenInfo = (function () {

            var addChildrenInfoModalInstance = $uibModal.open(
                {
                    templateUrl: '../views/addInfo/addChildrenInfo.html',
                    controller: addChildrenInfoModalInstanceCtrl,
                    size: 'lg',
                    backdrop: 'true',
                    resolve: {
                        childrenInfoModal: null
                    }
                }
            );


            //打开对话框后
            addChildrenInfoModalInstance.opened.then(function () {
                console.log('open ChildrenInfo Modal');
            });
            addChildrenInfoModalInstance.result.then(function (resultData) {
                console.log(resultData);
                $http({

                    method: 'POST',
                    url: 'saveChildrenInfoEntity',
                    data: resultData
                }).then(function (value) {
                    $scope.childrenInfoList = value.data;
                    alert('保存成功');
                }).catch(function (reason) {
                    alert(reason);
                });
            }, function (reason) {
                console.log(reason);//点击空白区域 和取消
                console.log('Modal dismissed at' + new Date());
            })
        });

        //修改子女信息
        $scope.editChildrenInfo = (function (childrenInfo) {

            var addChildrenInfoModalInstance = $uibModal.open(
                {
                    templateUrl: '../views/addInfo/addChildrenInfo.html',
                    controller: addChildrenInfoModalInstanceCtrl,
                    size: 'lg',
                    backdrop: 'true',
                    resolve: {
                        childrenInfoModal: childrenInfo
                    }
                }
            );
            //打开对话框后
            addChildrenInfoModalInstance.opened.then(function () {
                console.log('open ChildrenInfo Modal');
            });
            addChildrenInfoModalInstance.result.then(function (resultData) {
                console.log(resultData);

                $http({

                    method: 'POST',
                    url: 'saveChildrenInfoEntity',
                    data: resultData
                }).then(function (value) {
                    $scope.childrenInfoList = value.data;
                    alert('保存成功');
                }).catch(function (reason) {
                    alert(reason);
                });

            }, function (reason) {
                console.log(reason);//点击空白区域 和取消
                console.log('Modal dismissed at' + new Date());
            });

        })
    }
]);

//新增子女控制器弹出框

var addChildrenInfoModalInstanceCtrl = function ($scope, $uibModalInstance, childrenInfoModal) {

    if (childrenInfoModal != undefined & childrenInfoModal != null) {
        $scope.childrenInfoModal = childrenInfoModal;
    }

    $scope.ok = function () {
        console.log($scope.childrenInfoModal);
        $uibModalInstance.close($scope.childrenInfoModal);////关闭并返回当前选项
    }
    $scope.cancel = function () {
        console.log("取消");
        $uibModalInstance.dismiss('cancel');
    };

}


//控制器 预约信息评分模块
actionApp.controller('gradeServeController', ['$rootScope', '$scope', '$http', '$uibModal', '$log',
    function ($rootScope, $scope, $http, $uibModal, $log) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('页面加载完成 gradeServeController');
        });
        // 动态请求指定老人服务信息内容
        $http({
            method: 'GET',
            url: 'getParentUseServeInfo',
            params: {parentId: 1}
        }).then(function (responsedata) {
                console.log(responsedata);
                $rootScope.parentUseServeInfoList = responsedata.data;
            })
            .catch(function (response) {
                    console.log(response);
                }
            );

        //评分弹出框
        $scope.gradeServeInfo=(function (serveInfo) {

            var parentUseServeModalInstance = $uibModal.open(
                {
                    templateUrl: '../views/addInfo/addGradeServeInfo.html',
                    controller: parentUseServeModalInstanceCtrl,
                    size: 'lg',
                    backdrop: 'true',
                    resolve: {
                        parentUseServeInfoModal: serveInfo
                    }
                }
            );


            //打开对话框后
            parentUseServeModalInstance.opened.then(function () {
                console.log('open ChildrenInfo Modal');
            });
            parentUseServeModalInstance.result.then(function (resultData) {
                console.log(resultData);
                $http({

                    method: 'POST',
                    url: 'saveParentUseServeGrade',
                    data: resultData
                }).then(function (value) {
                    $scope.parentUseServeInfoList = value.data;
                    alert('评分成功');
                }).catch(function (reason) {
                    alert(reason);
                });
            }, function (reason) {
                console.log(reason);//点击空白区域 和取消
                console.log('Modal dismissed at' + new Date());
            })


        });

    }]);

//弹出框控制器
var parentUseServeModalInstanceCtrl = function ($scope, $uibModalInstance,parentUseServeInfoModal) {

    if (parentUseServeInfoModal != undefined & parentUseServeInfoModal != null) {
        $scope.parentUseServeInfoModal = parentUseServeInfoModal;
    }

    $scope.ok = function () {
        console.log($scope.parentUseServeInfoModal);
        $uibModalInstance.close($scope.parentUseServeInfoModal);////关闭并返回当前选项
    };
    $scope.cancel = function () {
        console.log("取消");
        $uibModalInstance.dismiss('cancel');
    };
};
