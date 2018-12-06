<template>
    <div class="card_container">
        <section class="card_text_container">
            <h3>慈善名片</h3>         
            <section class="margin10">
                <div class="disflex card_item">
                    <div>姓名:{{myCardInfo.data.name}}</div><div>慈善总额:{{myCardInfo.data.money}}</div>
                </div>
                <div class="card_item">电话:{{myCardInfo.data.tel}}</div>
                <div class="card_item">地址:{{myCardInfo.data.poeid}} </div>
                <div class="card_item">
                    简介:{{myCardInfo.data.info}}
                </div>
                <section class="ft16">
                    慈善记录:
                    <ul>
                        <li v-for="(item,index) in myCardInfo.data.recordlist" :key="index">
                            <div class="disflex">
                                <div>名称:{{item.name}}</div>
                                <div>金额:{{item.money}}</div>
                            </div>
                            <div>地址:{{item.poeid}}</div>
                        </li>
                    </ul>
                    
                </section>
            </section>
            <!-- <p v-if="linkShow">分享以下链接到社交软件获取一个种子吧！</p> -->
           
            <section class="btn_container">
            	<div class="border_right" @click="goFarm">返回我的农场</div>
            	<div @click="toShare">分享获得种子</div>
            </section>
        </section>
    </div>
</template>
<script>


export default {
    // data(){
    //     return{
    //         linkShow:false
    //     }
    // },
    props:['myCardInfo'],
    methods:{
        goFarm(){
            this.$emit('goFarm');
        },
        toShare(){
            //this.linkShow = true;
            this.$emit('toShare');
        }
    }
}
</script>
<style lang="scss" scoped>

.card_container{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 200;
    background-color: rgba(220,220,220,0.5);
}
.card_text_container{	
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -250px;
    margin-left: -250px;
    width: 500px;
    animation: tipMove .4s ;
    background-color: #ffe;
    border-radius: 10px;
    border:1px solid #ddd;
    padding: 10px 10px 5px 10px;
    h3{
        text-align: center;
    }
    .margin10{
        margin: 10px;
    }
    .disflex{
        display: flex;
        div{
            flex: 1;
        }
    }
    ul>li{
        border-bottom: 1px solid #ccc;
    }
    .card_item{
        font-size: 17px;
        height: 26px;
    }
    .ft16{
        font-size: 16px;
        background-color: #efefef;
    }
}
.btn_container{
    display: flex;
    border-top: 2px solid #aaa;
    color: #0076ff;
    .border_right{
        border-right: 2px solid #aaa;
    }
    div{
        color: #0076ff;
        cursor: pointer;
        flex: 1;
        text-align: center;
        padding: 5px 0;
    }
}
</style>
