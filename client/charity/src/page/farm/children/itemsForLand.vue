<template>
    <div class="landitem_container">
        <div class="landitem_text_container">
            <i class="iconfont close_icon" @click="closeLandItem"></i>
            <h3>可使用的物品</h3>
            <ul class="items_box">
                <li v-for="(item ,index) in farmItemInfo.data" :key="index"  @click="useItem(item.itemid)">
                    <div class="itemimg_box" :class="{
                        seed_img: item.itemid==='3GFQHAP4',
                        rareSeed_img: item.itemid==='3RWHBC3R',
                        fertilizer_img: item.itemid==='RS55S9SR',
                        fruit_img: item.itemid==='L26OPEQC',
                        rareFruit_img: item.itemid==='V0YARWB4'
                    }"></div>
                    <p class="item_number">数量:{{item.number}}</p>   
                </li>    
            </ul>
            <p v-if="itemUsing" class="hint_msg">物品使用中，请稍等...</p>
        </div>
    </div>
</template>
<script>
export default {
    props:['farmItemInfo','itemUsing'],
    methods:{
        useItem(itemid){
            this.$emit('useItem',[itemid]);
        },
        closeLandItem(){
            this.$emit('closeLandItem');
        }
    }
}
</script>

<style lang="scss" scoped>
.landitem_container{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 200;
    background-color: rgba(220,220,220,0.5);
    .landitem_text_container{
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -140px;
        margin-left: -300px;
        width: 600px;
        animation: tipMove .4s ;
        background-color: rgb(255, 255, 233);
        border: 1px;
        border-radius: 5px;
        padding: 20px 10px 30px 10px;
        h3{
            text-align: center;
            margin-bottom: 15px;
        }
        .item_number{
            font-size: 19px;
        }
        .hint_msg{
            color: red;
            font-size: 18px;
            padding-left: 10px;
        }
    }
}
.items_box{  
    display: flex;
    justify-content: center;
    li{
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100px;
        cursor: pointer;
        .itemimg_box{
            width: 100px;
            height: 100px;
        }      
    }
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
.seed_img{
    background-size: contain;
    background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/3GFQHAP4');
    background-position:center center;
    background-repeat: no-repeat;
}
.rareSeed_img{
    background-size: contain;
    background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/3RWHBC3R');
    background-position:center center;
    background-repeat: no-repeat;
}
.fertilizer_img{
    background-size: contain;
    background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/RS55S9SR');
    background-position:center center;
    background-repeat: no-repeat;
}
.fruit_img{
    background-size: contain;
    background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/L26OPEQC');
    background-position:center center;
    background-repeat: no-repeat;
}
.rareFruit_img{
    background-size: contain;
    background-image: url('https://charity-1257055754.cos.ap-beijing.myqcloud.com/V0YARWB4');
    background-position:center center;
    background-repeat: no-repeat;
}
</style>
