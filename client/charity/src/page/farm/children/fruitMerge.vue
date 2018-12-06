<template>
	<div class="fruit_box">
		<el-card class="fruit_text_box">
			<el-row >
				<el-col :span="8">
					<div>物品名称</div>
				</el-col>
				<el-col :span="8">
					<div>所需果实数量</div>
				</el-col>
				<el-col :span="8">
					<div>
						操作
					</div>
				</el-col>
			</el-row>
			<br>
			<el-row >
				<el-col :span="8">
					<div>优惠券</div>
				</el-col>
				<el-col :span="8">
					<div>10</div>
				</el-col>
				<el-col :span="8">
					<div>
						<el-button type="primary" size="small">领取</el-button>
					</div>
				</el-col>
			</el-row>
            <el-row class="margin10">
                <el-button type="primary" size="small" @click="closeFruit">返回农场</el-button>
            </el-row>        
		</el-card>
	</div>
</template>

<script>
export default {
    methods:{
        closeFruit(){
            this.$emit('closeFruit');
        }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.fruit_box{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 200;
    background-color: rgba(220,220,220,0.5);
}
	.fruit_text_box {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -150px;
        margin-left: -300px;
        animation: tipMove .4s ;
		width: 600px;	
		text-align: center;
		font-size: 15px;
	}
    .margin10{
        margin-top: 10px;
    }
</style>