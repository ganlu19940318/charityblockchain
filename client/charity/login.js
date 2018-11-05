//window.onload
let serverAdd = "http://www.tencent12580.cn/";
let storage=window.localStorage;

$(function(){
    $("#userName").focus();
 
});
//用户和密码icon焦点动画
$("#userName").focus(function(){
    document.getElementById("userLabel").className = "label-active";
    $("#pswLabel").removeClass();
    $("#surePswLabel").removeClass();
});
$("#psword").focus(function(){
    document.getElementById("pswLabel").className = "label-active";
    $("#userLabel").removeClass();
    $("#surePswLabel").removeClass();
});
$("#surPsw").focus(function(){
    document.getElementById("surePswLabel").className = "label-active";
    $("#userLabel").removeClass();
    $("#pswLabel").removeClass();
});

//登录
function login(){
    let userName = $("#userName").val();
    let password = $("#psword").val();
    if(userName===""){
        $("#errMsg").empty().html("用户名不能为空");
        $("#userName").focus();
        document.getElementById("userLabel").className = "label-error";
        return false;
    }
    else if(password===""){
        $("#errMsg").empty().html("密码不能为空");
        $("#psword").focus();
        document.getElementById("pswLabel").className = "label-error";
        return false;
    }

    // console.log("userName",userName);
    // console.log("password",password);
     
    $.ajax({
        url:serverAdd+"login",
        type:"post",
        dataType:"JSON",
        data:{
            "username":userName,
            "password":password
        },
        success:function(res){
            if(res.result_code==0){
                location.href="charity.html";
                let address = res.result.address;
                let pk64 = res.result.pk64;
                let poeid = res.result.poeid;
                storage.setItem("address",address);
                storage.setItem("pk64",pk64);
                storage.setItem("poeid",poeid);
                storage.setItem("username",userName);
                storage.setItem("password",password);
            }
            else{
                $("#errMsg").empty().html("登录不成功！请确认用户名和密码正确。");
            }
        },
        complete:function(){
            $("#loadingCss").hide();
        },
        error:function(){
            $("#errMsg").empty().html("登录不成功！");
        }
    });  
}

//注册
function regist(){
    let userName = $("#userName").val();
    let password = $("#psword").val();
    let name = $("#name").val();
    let type = $("#typeSelect").val();
    let pswAgain = $("#surPsw").val();
    const pswReg = /[a-z0-9A-Z]{8,16}/;
    if(userName===""){
        $("#errMsg").empty().html("用户名不能为空");
        $("#userName").focus();
        document.getElementById("userLabel").className = "label-error";
        return false;
    }
    else if(password===""){
        $("#errMsg").empty().html("密码不能为空");
        $("#psword").focus();
        document.getElementById("pswLabel").className = "label-error";
        return false;
    }
    else if(password!==pswAgain){
        $("#errMsg").empty().html("两次密码输入不一致");
        $("#psword").focus();
        document.getElementById("pswLabel").className = "label-error";
        return false;
    }
    else if(name===""){
        $("#errMsg").empty().html("真实姓名或项目名不能为空");
        $("#name").focus();
        //document.getElementById("pswLabel").className = "label-error";
        return false;

    }
    else{
        if(!pswReg.test(password)){
            $("#errMsg").empty().html("密码由8-16位大小写字母和数字组合，且必须同时包含这三种字符");
            document.getElementById("pswLabel").className = "label-error";
            $("#psword").focus();
            return false;
        }
        else {
            const regNum = /[0-9]/;
            const regaz = /[a-z]/;
            const regAZ = /[A-Z]/;
            let pswCount = 0;
            if(regNum.test(password)){
                pswCount++;
            }
            if(regaz.test(password)){
                pswCount++;
            }
            if(regAZ.test(password)){
                pswCount++;
            }
            if(pswCount<3){
                $("#errMsg").empty().html("密码由8-16位大小写字母和数字组合，且必须同时包含这三种字符");
                document.getElementById("pswLabel").className = "label-error";
                $("#psword").focus();
                return false;
            }  
        }
    }  

    $.ajax({
        url:serverAdd+"register",
        type:"post",
        dataType:"JSON",
        data:{
            "username":userName,
            "password":password,
            "type":type,
            "name":name
        },
        beforeSend:function(){
            $("#loadingCss").show();
        },
        success:function(res){
            if(res.result_code==0){
                $("#errMsg").empty().html("注册成功，开始登陆吧！");
                $("#toLogin").click();
                let address = res.result.address;
                let pk64 = res.result.pk64;
                let poeid = res.result.poeid;
                //保存到cookie
                // let cookieStr = "username="+userName+";password="+password+";address="+address+";pk64="+pk64+";poeid="+poeid;
                // let d = new Date();
                // d.setTime(d.getTime() + (30*24*60*60*1000));
                // let expires = "expires="+d.toUTCString();
                // document.cookie = cookieStr+";"+expires;

                storage.setItem("address",address);
                storage.setItem("pk64",pk64);
                storage.setItem("poeid",poeid);
                storage.setItem("username",userName);
                storage.setItem("password",password);
            }
            else{
                $("#errMsg").empty().html("注册失败!");
            }
           
        },
        complete:function(){
            $("#loadingCss").hide();
        },
        error:function(){
            $("#errMsg").empty().html("注册失败!");
        }
    });    
}
//显示注册
function showRegist(){
    $("#pswAgain").show();
    $("#toLogin").show();
    $("#toRegist").hide();
    $("#loginDiv").hide();
    $("#registDiv").show();
    $("#inputName").show();
    $("#typeSelect").show();
    $("#userName").focus();
}
//返回登录
function showLogin(){
    $("#pswAgain").hide();
    $("#toLogin").hide();
    $("#toRegist").show();
    $("#loginDiv").show();
    $("#registDiv").hide();
    $("#inputName").hide();
    $("#typeSelect").hide();
    $("#userName").focus();
}

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i=0; i<ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}

