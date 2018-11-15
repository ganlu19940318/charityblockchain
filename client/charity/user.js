let serverAdd = "http://www.tencent12580.cn/";
let storage=window.localStorage;


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
                "</td><td>"+arrUser[i].info+"</td><td>"+arrUser[i].money+"</td></tr>";
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