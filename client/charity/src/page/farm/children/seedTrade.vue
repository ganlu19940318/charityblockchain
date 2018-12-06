<template>
    <div>
        <div class="trade_box">
            <el-form ref="form" :model="form" label-width="140px">
                <div>稀有种子交易</div><br><br>
                <el-form-item label="种子地址">
                    <el-input v-model.lazy="seedpoeid"></el-input>
                </el-form-item>
                <el-form-item label="对方用户的地址">
                    <el-input v-model.lazy="topoeid"></el-input>
                </el-form-item>
                <el-form-item label="用户的地址">
                    <el-input v-model.lazy="mypoeid"></el-input>
                </el-form-item>
                <el-form-item label="用户私钥1">
                    <el-input v-model.lazy="mywalletid"></el-input>
                </el-form-item>
                <el-form-item label="用户私钥2">
                    <el-input v-model.lazy="mypk64"></el-input>
                </el-form-item>
               
                    <el-button type="primary" @click="submitTrade">确认交易</el-button>
                    <el-button @click="toFarm">返回农场</el-button>
                
            </el-form>
        </div>
        <loading v-if="showloading"></loading>
    </div>			
</template>

<script>
import loading from 'src/components/common/loading';
import {seedTrade} from "src/service/getData";

	export default {
        data(){
            return{
                topoeid:null,//对方用户的地址
                seedpoeid:null,//种子ID
                mypk64:null,//
                mypoeid:null,//我的地址
                mywalletid:null,//
                seedTradeInfo:null,//交易请求后返回信息
                showloading:false,//是否显示loading
              
            }
        },
        components:{
            loading
        },
		methods: {
            //稀有种子交易
            async submitTrade(){
                this.showloading = true;
                this.seedTradeInfo = await seedTrade(this.mypk64,this.mypoeid,this.mywalletid,this.seedpoeid,window.localStorage.getItem('token'),this.topoeid);
                this.showloading = false;
                if(parseInt(this.seedTradeInfo.code)===0){
                    this.$alert('种子交易成功！', '操作成功', {
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
                    this.$alert(this.seedTradeInfo.msg, '操作失败', {
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
            //返回农场
            toFarm(){
                this.$router.go(-1);
            }
		}
	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.trade_box{
    width: 550px;
    text-align: center;
    margin: 50px auto;
    padding: 30px 50px 30px 30px;
    border: 2px solid gray;
}
</style>