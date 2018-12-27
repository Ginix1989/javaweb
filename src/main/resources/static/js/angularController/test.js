
//小区子女信息控制请
actionApp.controller('regChildrenInfoController', ['$rootScope', '$scope', '$http', '$uibModal',
    function ($rootScope, $scope, $http, $uibModal) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('regChildrenInfoController页面加载完成');
        });

        //获取子女信息列表
        $http({
            method: "GET",
            url: 'getAllParetnInfo'
        }).then(function (responseData) {
            console.log(responseData.data);
            $scope.childrenInfoList = responseData.data;
        }).catch(function (reason) {
            alert(reason);
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