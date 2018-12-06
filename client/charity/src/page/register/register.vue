<template>
    <div id="partbox">
    	<el-container>
			<el-header>
				<head-top></head-top>
			</el-header>
			<el-main class="margintop80 ganluindex">
				<h3 class="ganlucenter" style="color: white;">百川链——基于区块链的慈善社交游戏平台</h3><br>
			<section class="main_section">		
            <div class="disflex">
                <i class="iconfont register_icon"></i><h3 style="color: white">用户注册</h3>
            </div>
            <form>
                <section>
                    <label>登录用名</label>
                    <input type="text" placeholder="请输入登录用户名" v-model="loginName">     
                </section>
                <section>
                    <label>登录密码</label>
                    <input type="password" placeholder="密码,8-16大小写字母加数字" v-model="password">     
                </section>
                <section>
                    <label>真实姓名</label>
                    <input type="text" placeholder="请输入真实姓名" v-model="userName">     
                </section>
                <section>
                    <label>身份证号</label>
                    <input type="text" placeholder="请输入身份证号" maxlength="18" v-model="idCard">
                </section>
                <section>
                    <label>联系方式</label>
                    <input type="tel" placeholder="请输入常用手机号" maxlength="11" v-model="phoneNum">
                </section>
                <section>
                    <label>用户类型</label>
                    <select v-model="userType">
                        <option value="4">个人用户</option>
                        <option value="3">企业用户</option>
                    </select>
                </section>
                <div class="info_div">
                    <label>用户简介</label>
                    <textarea placeholder="请输入您的简介" class="info_text" v-model="userInfo"></textarea>
                </div>  
            </form>
            <router-link to="/login" class="link_a">已有账号，立即登录</router-link>
            <p v-if="noticeShow" class="notice_container">{{noticeMsg}}</p>
            <div @click="toRegister" class="register_btn">注册</div>
        </section>
        <register-alert v-if="registerInfoShow" 
        :userID="registerInfo.data.poeid" 
        :userpsw1="registerInfo.data.walletid" 
        :userpsw2="registerInfo.data.pk64"
        @closeAlert="registerInfoShow=false"></register-alert>
        <alert-comp v-if="alertShow" :alertText="alertText" @closeTip="closeTip"></alert-comp>
        <loading v-if="showloading"></loading>
			</el-main>
		</el-container>	
			
			
        
    </div> 
</template>

<script>
import headTop from '../../components/header/head'
import alertComp from '../../components/common/alertComp'
import loading from 'src/components/common/loading'
import registerAlert from 'src/page/register/children/registerInfo';
import {register} from "src/service/getData";

export default {
    data(){
        return{
            loginName:null,//登录用户名
            password:null,//登录密码
            userName:null,//用户姓名
            idCard:null,//身份证号
            phoneNum:null,//手机号码
            userType:null,//用户类型
            userInfo:null,//用户简介
            noticeShow:false,//是否显示提示信息
            noticeMsg:null,//提示信息
            alertShow:false,//是否显示弹窗组件
            alertText:null,//弹框信息
            registerInfo:null,//注册返回数据
            registerInfoShow:false,//是否显示注册成功提示
            showloading:false
        }
    },
    mounted() {
			particlesJS.load('partbox','../../../static/particles.json');
			// this.$nextTick(()=>{
			//初始化swiper
			let mySwiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				navigation: {
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
				loop: true,
				speed: 2000,
				autoplay: {
					delay: 2000
				}
			});
			//});

		},
    components:{
        headTop,
        alertComp,
        registerAlert,
        loading
    },
    computed: {
        //判断手机号码
        rightPhoneNum: function (){
            return /^1\d{10}$/gi.test(this.phoneNum);
        },
        //判断身份证号码
        rightCardID: function(){
            return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(this.idCard);
        },
        //判断密码
        rightPassword: function(){
            return /[a-z0-9A-Z]{8,16}/.test(this.password);
        }
    },
    methods:{
        async toRegister(){
            if(!this.loginName){
                this.noticeShow = true;
                this.noticeMsg = "请输入登录用户名！";
                return;
            }
            else if(!this.password){
                this.noticeShow = true;
                this.noticeMsg = "请输入登录密码！";
                return;
            }
            else if(!this.rightPassword){
                this.noticeShow = true;
                this.noticeMsg = "密码格式不符合规范！";
                return;
            }
            else if(!this.userName){
                this.noticeShow = true;
                this.noticeMsg = "请输入用户姓名！";
                return;
            }
            else if(!this.idCard){
                this.noticeShow = true;
                this.noticeMsg = "请输入身份证号！";
                return;
            }
            else if(!this.rightCardID){
                this.noticeShow = true;
                this.noticeMsg = "身份证号不正确！";
                return;
            }
            else if(!this.phoneNum){
                this.noticeShow = true;
                this.noticeMsg = "请输入手机号码！";
                return;
            }
            else if(!this.rightPhoneNum){
                this.noticeShow = true;
                this.noticeMsg = "手机号码不正确！";
                return;
            }
            else if(!this.userInfo){
                this.noticeShow = true;
                this.noticeMsg = "请输入用户简介！";
                return;
            }
            //向服务器发起注册请求
            this.showloading = true;
            this.registerInfo = await register(
                this.userName, 
                this.idCard, 
                this.phoneNum, 
                this.userType,
                this.userInfo,
                this.loginName,
                this.password
            );
            this.showloading = false;
            console.log(this.registerInfo);
            if(parseInt(this.registerInfo.code) === 0){
                this.noticeShow = false;
                this.registerInfoShow = true;
               // this.alertText = "注册成功！";
            }
            
        },
        closeTip(){
            this.alertShow = false;
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../../style/mixin';
.ganluindex{
	z-index: 3;
}
.ganlucenter{
    	text-align: center;
    }
label{
	color: white;
}
.disflex{
    display: flex;
    align-items: center;
    margin-bottom: 8px;
}
.register_icon{
    font-size: 22px;
    margin-right: 5px;
    color: #3598db;
}
.register_icon:before{
    content: "\e6ad";
}
.main_section{
    @include cl;
    form{
        section{
            height: 45px;
            line-height: 45px;
            input,select{
                height: 30px;
                width: 300px;
                font-size: 16px;
                padding-left: 5px;
            }  
        }
    }
    .info_text{
        height: 70px;
        width: 300px;
        padding: 5px;
        margin-left: 5px;
        font-size: 16px;
    }
    .notice_container{
        color: red;
        font-size: 16px;
    }
}
.info_div{
    display: flex;
    margin-top: 10px;
}
.link_a{
    font-size: 16px;
    color: white;
}
.link_a:hover{
    color: #4283b9;
}
.register_btn{
    height: 40px;
    margin-top: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #3598db;
    color: #fff;
    border-radius: 2px;
    cursor: pointer;
}

.title_head {
		font-size: 26px;
		text-align: center;
		margin: 20px 0;
	}
</style>

