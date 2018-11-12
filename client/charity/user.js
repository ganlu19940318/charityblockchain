let serverAdd = "http://www.tencent12580.cn/";
let storage=window.localStorage;

let btnGroup =document.getElementById("btn-group");
let btnUser =document.getElementById("btn-user");
let userCon = document.getElementById("user-con");
let groupCon = document.getElementById("group-con");

//机构与用户菜单切换
btnGroup.onclick=function(){
    btnGroup.className = "menu-item menu-active";
    btnUser.className = "menu-item";
    userCon.style.display = "none";
    groupCon.style.display="block";
}
btnUser.onclick=function(){
    btnGroup.className = "menu-item";
    btnUser.className = "menu-item menu-active";
    userCon.style.display = "block";
    groupCon.style.display="none";
}

//机构主页排行榜
function groupTop(){
    $.ajax({
        url:serverAdd+"query",
        type:"post",
        dataType:"json",    
        success:function(res){
            let bodyStr ="";
            for(let i =0;i<res.result.length;i++){
                bodyStr += "<tr><td>"+res.result[i].address+"</td><td>"+res.result[i].name+
                "</td><td>"+res.result[i].money+"</td><td>"+res.result[i].info+
                "</td><td>"+res.result[i].state+"</td><td>";
                for(let j=0;j<res.result[i].donateaddress.length;j++){
                    bodyStr+=res.result[i].donateaddress[j].address+";";
                }
                bodyStr += "</td></tr>";
            }
           $("#groupTable").find("tbody").empty().html(bodyStr);
           //$("#groupTable").find("tbody").find("tr").addClass("eclips-text");
        }
    });
}
groupTop();

//创建慈善项目
function createCharity(){
    
    let moneyGroup = $("#moneyGroup").val();
    let infoGroup = $("#infoGroup").val();
    $.ajax({
        url:serverAdd+"create",
        type:"post",
        dataType:"json",
        data: {
            "info": infoGroup,
            "money": moneyGroup,
            "username": storage.username,
            "password": storage.password           
        },
        beforeSend:function(){
            $("#loadingCss").show();
        }, 
        success:function(res){
            $("#loadingCss").hide();
            if(res.result_code==0){
                alert("申请成功！");
            }
            else{
                alert("申请失败！");
            }  
        },
        complete:function(){
            $("#loadingCss").hide();
        },
        errror:function(){
            alert("申请失败！");
        }

    });
}

//确认慈善项目
function confirmMoney(){
    $.ajax({
        url:serverAdd+"confirm",
        type:"post",
        dataType:"json",
        data: {
            "poeid": storage.poeid,
            "address": storage.address,
            "pk64": storage.pk64,
            "useraddress":$("#donateAdd").val(),
            "money": $("#sureMoney").val()           
        },
        beforeSend:function(){
            $("#loadingCss").show();
        }, 
        success:function(res){
            if(res.result_code==0){
                alert("确认成功！");
            }
            else{
                alert("确认失败！");
            }           
        },
        complete:function(){
            $("#loadingCss").hide();
        },
        errror:function(){
            alert("确认失败！");
        }

    });
}


//用户主页排行榜
function userTop(){
    $.ajax({
        url:serverAdd+"rank",
        type:"post",
        dataType:"json",    
        success:function(res){
            let bodyStr ="";
            let arrUser = res.result.cardResps;
            for(let i =0;i<arrUser.length;i++){
                bodyStr += "<tr><td>"+arrUser[i].poeid+"</td><td>"+arrUser[i].name+
                "</td><td>"+arrUser[i].money+"</td><td>"+arrUser[i].info+"</td></tr>";
            }
            $("#userTable").find("tbody").empty().html(bodyStr);
        }
    });
}
userTop();

//用户名片
function userCard(){
    $.ajax({
        url:serverAdd+"card",
        type:"post",
        dataType:"json", 
        data:{
            "address": storage.poeid
        },
        success:function(res){
            $("#userName").empty().html(res.result.name);
            $("#userAdd").empty().html(res.result.poeid);
            $("#userMoney").empty().html(res.result.money);
            $("#userInfo").empty().html(res.result.info);
            $("#userGroup").empty().html(res.result.charity);
        }
    });
}
userCard();

//用户转让交易记录
function userTransfer(){
    $.ajax({
        url:serverAdd+"trade",
        type:"post",
        dataType:"json",
        data:{
            "poeid": storage.poeid,
            "address": storage.address,
            "pk64": storage.pk64,
            "topoeid": $("#poeidOther").val(),
            "charitypoeid": $("#poeidCharity").val()
        },
        beforeSend:function(){
            $("#loadingCss").show();
        }, 
        success:function(res){
            $("#loadingCss").hide();
            if(res.result_code==0){
                alert("转让成功！");
            }
            else{
                alert("转让失败！");
            } 
        },
        complete:function(){
            $("#loadingCss").hide();
        },
        errror:function(){
            alert("转让失败！");
        }
    });
}