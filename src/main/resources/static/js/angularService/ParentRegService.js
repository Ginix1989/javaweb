var parentRegService= angular.module("parentRegService",[]);

parentRegService.service('saveParentRegInfo',function ($http,$window) {

    
    this.saveParentRegInfoMethod=(function (parentInfo) {
        //保存注册信息
        $http({
            method:"POST",
            url:"../../index/reg/saveParentInfoNewRegParent",
            data: parentInfo
        }).then(function(responseData){
            console.log(responseData.data);
            alert("注册成功");
            $window.location.href="../views/login.html";


        }).catch(function(reasion){
            alert(reasion);
        });


    });


})