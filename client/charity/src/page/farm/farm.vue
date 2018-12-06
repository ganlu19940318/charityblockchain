<template>
    <div>
        <head-top></head-top>
        <div class="titlefarm" style="text-align: center;font-size: 30px;">慈善农场</div>
        <section class="farm_container">
            <div class="farm_box">
                <div class="land_item" v-for="(item, index) in farmInfo.data" :key="index" @click="chooseLand(index,item.tag,item.mmsec)">
                    <div v-if="parseInt(item.tag)===1" 
                    :class="{
                        land_status1 : item.itemid==='3GFQHAP4' && item.mmsec>0,
                        land_status2 : item.itemid==='3GFQHAP4' && item.mmsec<=0,
                        land_status3 : item.itemid==='3RWHBC3R' && item.mmsec>0,
                        land_status4 : item.itemid==='3RWHBC3R' && item.mmsec<=0,
                        hide:noitems
                    }">
                    
                        <span class="msgSec" v-if="item.mmsec>=0">植物生长中</span>
                        <span class="msgSec" v-else>果实已成熟，点击收取</span>
                    </div>
                    <!-- <img v-if="parseInt(item.tag)===0" src="../../../static/img/blankLand.png"> -->
                </div>
            </div>
        </section>
        
        <section class="margintop40">
            <div class="btn_container">
                <button @click="mySeed">我的种子</button>
                <button @click="myFertilizer">我的化肥</button>
                <button @click="myFruit">我的果实</button>
                <button @click="myCard">我的慈善名片</button>
            </div>
            <div class="btn_container">
                <button @click="dailyTask">日常活跃任务</button>
                <button @click="toGetSeed">稀有种子领取</button>
                <button @click="toFruitMerge">已有果实加工</button>
                <button @click="toSeedTrade">稀有种子交易</button>
                
            </div>
        </section>
        <my-item v-if="itemShow" @closeItem="closeItem" 
            :itemTitle="itemTitle" 
            :farmItemType="farmItemType" 
            :farmItemInfo="farmItemInfo.data">
        </my-item>
        <my-card v-if="cardShow" 
            :myCardInfo="myCardInfo" 
            @goFarm="cardShow = false" 
            @toShare="toShare">
        </my-card>
        <land-item v-if="landitemShow" 
            :itemUsing="itemUsing"
            :farmItemInfo="farmItemInfo" 
            @useItem="useItem" 
            @closeLandItem="landitemShow=false">
        </land-item>
        <fruit-merge v-if="showFruitMerge" @closeFruit="showFruitMerge=false"></fruit-merge>
        <loading v-if="showloading"></loading>
    </div>
</template>
<script>
import headTop from '../../components/header/head';
import loading from 'src/components/common/loading'
import myItem from 'src/page/farm/children/myItems';
import myCard from 'src/page/farm/children/myCard';
import fruitMerge from 'src/page/farm/children/fruitMerge';

// import getSeed from 'src/page/farm/children/getSeed';
import landItem from 'src/page/farm/children/itemsForLand';
import {
        getLand,
        getFarmItem,
        getMyCard,
        useitemServer,
        getHaverstServer,
        getSeedAnd
    } from "src/service/getData";

export default {
    data(){
        return{
            showloading:false,
            itemShow:false,//
            itemTitle:null,//我的物品对应题头
            cardShow:false,//是否显示慈善名片
            myCardInfo:null,//查看慈善名片返回结果
            showFruitMerge:false,
            farmInfo:null,//查看土地状态返回结果
            farmItemInfo:null,//查看我的物品返回结果
            farmItemType:null,//
            landitemShow:false,//
            landIndex:null,//土地编号
            userItemInfo:null,//使用物品返回结果
            noitems:false,//土地上是否有作物
            getHaverstInfo:null,//收获作物返回结果
            shareInfo:null,//分享名片返回结果
            itemUsing:false,
            
        }
    },
    components:{
        headTop,
        loading,
        myItem,
        myCard,
        fruitMerge,
        landItem
    },
    //初始化数据
    async beforeMount(){
        let token = window.localStorage.getItem('token');
        if(!token){
            this.$router.push('login');
        }
        else{
            this.farmInfo = await getLand(token);
            this.farmItemInfo = await getFarmItem(token);
        }
       
    },
    methods:{
        async mySeed(){
            this.showloading = true;
            this.farmItemInfo = await getFarmItem(window.localStorage.getItem('token'));
            this.showloading = false;
            this.farmItemType = "2";
            this.itemShow = true;
            this.itemTitle = "种子";
        },
        async myFertilizer(){
            this.showloading = true;
            this.farmItemInfo = await getFarmItem(window.localStorage.getItem('token'));
            this.showloading = false;
            this.farmItemType = "3";
            this.itemShow = true;
            this.itemTitle = "化肥";
        },
        async myFruit(){
            this.showloading = true;
            this.farmItemInfo = await getFarmItem(window.localStorage.getItem('token'));
            this.showloading = false;
            this.farmItemType = "1";
            this.itemShow = true;
            this.itemTitle = "果实";
        },
        closeItem(){
            this.itemShow = false;
        },
        //日常任务按钮
        dailyTask(){
            this.$router.push('rank');
        },
        //领取稀有种子
        toGetSeed(){
            this.$router.push('getSeed');
        },
        //果实加工
        toFruitMerge(){
            this.showFruitMerge =true;
        },
        //稀有种子交易
        toSeedTrade(){
            this.$router.push('seedTrade');
        },
        //点击土地
        async chooseLand(index,tag,mmsec){
            this.landIndex = index+1;
            if(parseInt(tag)===1 && mmsec<=0){
                this.landitemShow=false;
                this.getHaverstInfo = await getHaverstServer(this.landIndex,window.localStorage.getItem('token'));
                if(this.getHaverstInfo.code===0){
                    this.noitems = true;

                    this.$alert('成功收取一个果实！快去我的果实查看吧', '收取成功', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$message({
                            type: 'info',
                            message: `action: ${ action }`
                            });
                        }
                    });

                    
                }
                else{
                    this.noitems = false;
                    this.$alert(this.getHaverstInfo.msg, '操作失败', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$message({
                            type: 'info',
                            message: `action: ${ action }`
                            });
                        }
                    });
                    
                }
            }
            else{    
                this.landitemShow=true;
            } 
        },
        //在土地上使用物品
        async useItem(itemid){
            this.itemUsing = true;
            this.userItemInfo = await useitemServer(...itemid, this.landIndex, window.localStorage.getItem('token'));
            this.itemUsing = false;
            if(this.userItemInfo.code===0){
                
                this.$alert('物品使用成功！返回农场查看吧', '使用成功', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                        type: 'info',
                        message: `action: ${ action }`
                        });
                    }
                });
                

                this.landitemShow = false;
                this.farmInfo = await getLand(window.localStorage.getItem('token'));
            }
            else{
                this.$alert(this.userItemInfo.msg, '使用失败', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                        type: 'info',
                        message: `action: ${ action }`
                        });
                    }
                });
                
            }
        },
        //查看我的名片
        async myCard(){
            this.showloading = true;
            this.myCardInfo = await getMyCard(window.localStorage.getItem('token'));
            this.showloading = false;
            this.cardShow = true;
        },
        //分享获得种子
        async toShare(){
            
            this.$alert('http://www.charitychain.wang', '分享以下链接到社交软件获取一个种子吧！', {
                confirmButtonText: '确定',
                callback: action => {
                    this.$message({
                    type: 'info',
                    message: `action: ${ action }`
                    });
                }
            });

            
            this.shareInfo = await getSeedAnd(window.localStorage.getItem('token'),'1');
            if(this.shareInfo.code===0){

                this.$alert('成功获得一个种子，快去我的种子查看吧！', '获取成功', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                        type: 'info',
                        message: `action: ${ action }`
                        });
                    }
                });
                
            }
            else{
                this.$alert(this.shareInfo.msg, '获取失败', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                        type: 'info',
                        message: `action: ${ action }`
                        });
                    }
                });
                
                
            }
        }
    }
}
</script>

<style lang="scss" scoped>

.farm_container{
    margin: 0 40px;
    margin: 0px 40px;
    padding: 180px 0 10px 0;
    background-size: contain;
    background-position: center center;
    background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/new_beijing.png');
}
.farm_box{
    display: flex;
    flex-wrap: wrap;
    background-size:contain;
    
    /*background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/new_beijing.png');*/
    background-repeat: no-repeat;
    background-position: center center;
    .land_item{
        text-align: center;
        cursor: pointer;
        flex: 0 0 25%;
        height:80px;
        background-size:contain;
        background-image: url('../../../static/img/blankLand.png');
        background-repeat: no-repeat;
        background-position: center center;
    }
    //普通成熟前
    .land_status1{
        height:70px;
        background-size:contain;
        background-repeat: no-repeat;
        background-position: center center;
        background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/3GFQHAP41');
    }
    //普通成熟后
    .land_status2{
        height:70px;
        background-size:contain;
        background-repeat: no-repeat;
        background-position: center center;
        background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/3GFQHAP42');
    }
    //稀有成熟前
    .land_status3{
        height:70px;
        background-size:contain;
        background-repeat: no-repeat;
        background-position: center center;
        background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/3RWHBC3R1');
    }
    //稀有成熟后
    .land_status4{
        height:70px;
        background-size:contain;
        background-repeat: no-repeat;
        background-position: center center;
        background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/3RWHBC3R2');
    }

}
.titlefarm{
    text-align: center;
    font-size: 30px;
    margin-top: -80px;
    margin-bottom: 10px;
}
.margintop40{
    margin-top:40px;
}
.msgSec{
    font-size: 13px;
    background-color: #3598A9;
    color: #fff;
    padding: 3px;
    border-radius: 6px;
}
.btn_container{
    height: 50px;
    display: flex;
    justify-content: space-around;
    padding: 0 40px;
    width: 100%;
    button{
        height: 34px;
        width: 100px;
        border-radius: 5px;
        cursor: pointer;
        background-color: #3598A9;
        color: #fff;
    }
    button:hover{
        background-color: #3598db;
    }
}
</style>
