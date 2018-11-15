let serverAdd = "http://www.tencent12580.cn/";
let storage=window.localStorage;

//机构主页排行榜
function groupTop(){
    $.ajax({
        url:serverAdd+"rankitem",
        type:"post",
        dataType:"json",    

        success:function(res){
            let bodyStr = "";
            for(let i =0;i<res.result.length;i++){
                bodyStr += "<tr><td>"+res.result[i].address+"</td><td>"+res.result[i].name+
                "</td><td>"+res.result[i].money+"</td><td>"+res.result[i].info+
                "</td><td>"+res.result[i].state+"</td><td>";
                if(res.result[i].donateaddress != null)
                {
                    
                     bodyStr+=res.result[i].donateaddress+";";
                    
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
    let name = $("#name").val();
    $.ajax({
        url:serverAdd+"create",
        type:"post",
        dataType:"json",
        data: {
            "info": infoGroup,
            "money": moneyGroup,
            "username": storage.username,
            "password": storage.password, 
            "name": name          
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
    //let resAdd = $("#resAdd").val();
    //let resConfirm = $("#resConfirm").val();
    $.ajax({
        url:serverAdd+"confirm",
        type:"post",
        dataType:"json",
        data: {
            "poeid": storage.poeid,
            "address": storage.address,
            "pk64": storage.pk64,
            "useraddress":$("#donateAdd").val(),      
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