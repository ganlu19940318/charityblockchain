<template>
    <div class="item_container">
        <section class="item_text_container">
            <i class="iconfont close_icon" @click="closeItem"></i>
            <h3 class="title_item">我的{{itemTitle}}</h3>
            <ul id="itemUl">
                <li v-for="(item, index) in farmItemInfo" :key="index" v-if="item.type === farmItemType">
                    <div class="item_specific">
                        <div>
                            <i class="iconfont" 
                            :class="{
                                seed_icon: parseInt(item.type)===2,
                                fruit_icon: parseInt(item.type)===1,
                                fertilize_icon: parseInt(item.type)===3,
                            }"></i>
                            {{item.itemname}}
                            </div>
                        <div>数量：{{item.number}}</div>
                    </div>
                </li>
            </ul>
            <p v-if="!haveItem">暂时没有任何{{itemTitle}}哦</p>
        </section>
    </div>
</template>
<script>
export default {
    data(){
        return{
            haveItem:false//
        }
    },
    props:['itemTitle','farmItemType','farmItemInfo'],
    mounted(){
        //console.log(document.getElementById("itemUl").childNodes);
        if(document.getElementById("itemUl").childNodes){
            this.haveItem = true;
        }
        else{
            this.haveItem = false;
        }
    },
    methods:{
        closeItem(){
            this.$emit('closeItem');
        }
    }
}
</script>
<style lang="scss" scoped>

.item_container{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 200;
    background-color: rgba(220,220,220,0.5);

}
.item_text_container{
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -150px;
    margin-left: -250px;
    width: 500px;
    animation: tipMove .4s ;
    background-color: #ffe;
    border: 1px;
    border-radius: 5px;
    padding: 20px 10px 30px 10px;
    .title_item{
        text-align: center;
    }
}
.item_specific{
    display: flex;
    justify-content: space-around;
}
.seed_icon,.fruit_icon,.fertilize_icon{
    font-size:25px;
    color: #3598db;
}
.seed_icon:before{
    content: "\e62c";
}
.fruit_icon:before{
    content: "\e671";
}
.fertilize_icon:before{
    content: "\e69e";
}
.close_icon{
    cursor: pointer;
    position: absolute;
    top:17px;
    right: 22px;
    z-index: 220;
    color: #887;
    font-size: 30px;
}
.close_icon:before{
    content: "\e606";
}
</style>

