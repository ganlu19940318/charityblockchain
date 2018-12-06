<template>
    <div >
        <section class="seed_text_container">
            <h3>领取稀有种子</h3>
             <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用户ID">
                    <el-input placeholder="请输入用户的哈希码" v-model.lazy="userpoeid"></el-input> 
                </el-form-item>
                <el-form-item label="种子ID">
                    <el-input placeholder="请输入稀有种子的哈希码" v-model.lazy="seedpoeid"></el-input>
                </el-form-item>
                <el-form-item label="私钥1">
                    <el-input placeholder="请输入稀有种子的address" v-model.lazy="seedwalletid"></el-input>
                </el-form-item>
                <el-form-item label="私钥2">
                    <el-input placeholder="请输入稀有种子的pk64" v-model.lazy="seedpk64"></el-input>
                </el-form-item>                       
            </el-form>
            <p v-if="showSeedSuccess" class="msg_p">领取成功！</p>
            <p v-else class="msg_p">{{getSeedMsg}}</p>
            <el-button type="primary" @click="getSeed">确认领取</el-button>
            <el-button @click="toFarm">返回农场</el-button>   
        </section>
        <loading v-if="showloading"></loading>
    </div>
</template>

<script>
import loading from 'src/components/common/loading';
import {getRareSeed} from "src/service/getData";

export default {
    data(){
        return{
            userpoeid:null,//用户ID
            seedpoeid:null,//种子ID
            seedwalletid:null,//
            seedpk64:null,//
            rareSeedInfo:null,//
            showloading:false,//
            showSeedSuccess:false,//
            getSeedMsg:null
           
        }
    },
    components:{
        loading
    },
    methods:{
        toFarm(){
            this.$router.go(-1);
        },
        //领取稀有种子
        async getSeed(seedprams){
            this.showloading = true;
            this.rareSeedInfo = await getRareSeed(this.seedpk64,this.seedpoeid,this.seedwalletid,window.localStorage.getItem('token'),this.userpoeid);
            this.showloading = false;
            if(this.rareSeedInfo.code ===0){
                this.showSeedSuccess = true;
            }
            else{
                this.showSeedSuccess = false;
                this.getSeedMsg = this.rareSeedInfo.msg;
            } 
        }
        
    }
}
</script>

<style lang="scss" scoped>
@import '../../../style/mixin';


.seed_text_container{
    margin: 80px auto;
    width: 500px;
    border: 2px solid #aaa;
    border-radius: 5px;
    padding: 60px;
    text-align: center;
    h3{
        text-align: center;
        margin-bottom: 15px;
    }
    .msg_p{
        color: red;
        font-size: 18px;
        text-align: left;
        margin-bottom: 10px;
    }
    
}
</style>

