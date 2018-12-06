<template>
    <div class="register_container">
        <section class="register_text_container">
            <h3 class="title_register">注册成功！请妥善保存您的ID和私钥！</h3>
            <ul class="ft18">
                <li>
                    您的用户ID为：{{userID}}
                </li>
                <li>
                    您的私钥1为：{{userpsw1}}
                </li>
                <li class="breakword">
                    您的私钥2为：{{userpsw2}}
                </li>
            </ul>
            <br>
            <div class="ok_btn" @click="closeAlert">OK</div>
        </section>
    </div>
</template>
<script>
export default {
    data(){
        return{

        }
    },
    props:['userID','userpsw1','userpsw2'],
    methods:{
        closeAlert(){
            this.$emit('closeAlert');
        }
    }
}
</script>
<style lang="scss" scoped>
@keyframes tipMove{
    0%   { transform: scale(1) }
    35%  { transform: scale(.8) }
    70%  { transform: scale(1.1) }
    100% { transform: scale(1) }
}
.ft18{
    font-size: 18px;
}
.breakword{
    word-break: break-all;
}
.register_container{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 200;
    background-color: rgba(220,220,220,0.5);
}
.register_text_container{
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -150px;
    margin-left: -250px;
    width: 500px;
    animation: tipMove .4s ;
    background-color: linen;
    border: 1px;
    border-radius: 5px;
    padding: 10px;
    .title_register{
        text-align: center;
    }
    ul>li{
        margin-top: 6px;
    }
    li{
    	font-size: 10px;
    }
    .ok_btn{
        width:100px;
        height: 30px;
        text-align: center;
        margin: 0 auto;
        border-radius: 5px;
        cursor: pointer;
        background-color: #3598A9;
        color: #fff;
    }
    .ok_btn:hover{
        background-color: #3598db;
    }
}


</style>


