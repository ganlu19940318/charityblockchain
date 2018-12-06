<template>
    <div id="partbox">
    	<el-container>
			<el-header>
				<head-top></head-top>
			</el-header>
			<el-main class="margintop80 z-indexganlu">
				<h3 class="ganlucenter" style="color: white;">百川链——基于区块链的慈善社交游戏平台</h3><br>
			 <section class="main_section">		
            <div class="disflex">
                <i class="iconfont login_icon" ></i><h3 style="color: white;">用户登录</h3>
            </div>
            <form>
                <section class="input_box">
                    <label><i class="iconfont username_icon"></i></label>
                    <input type="text" placeholder="用户名" v-model.lazy="userName"> 
                </section>
                <section>
                    <label><i class="iconfont password_icon"></i></label>
                    <input type="password" placeholder="密码" maxlength="16" v-model="password">
                </section>    
            </form>
            <p v-if="noticeShow" class="notice_p">{{noticeMsg}}</p>
            <router-link to="/register" class="link_a">没有账号？去注册</router-link>
            <div @click="toLogin" class="login_button">登录</div>
        </section> 
        <loading v-if="showloading"></loading> 
			</el-main>
		</el-container>
    </div>
</template>
<script>
import headTop from '../../components/header/head'
import loading from 'src/components/common/loading'
import alertComp from '../../components/common/alertComp'
import {login} from "src/service/getData";

export default {
    data(){
        return{
            showloading:false,       
            userName:null,//用户名
            password:null,//密码
            loginInfo:null,//登录返回信息
            noticeShow:false,//是否显示提示段落
            noticeMsg:null//提示信息
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
        loading,
        alertComp
    },
    methods:{
        async toLogin(){
            if(!this.userName){
                this.noticeShow = true;
                this.noticeMsg = "请输入用户名";
                return;
            }
            else if(!this.password){
                this.noticeShow = true;
                this.noticeMsg = "请输入密码";
                return;
            }
            //发起登录
            this.showloading = true;
            this.loginInfo = await login(this.userName,this.password);
             this.showloading = false;
            if(parseInt(this.loginInfo.code)===0){
               // window.localStorage.setItem('token', token)
                window.localStorage.setItem('token', this.loginInfo.data.token);
                //个人用户登录进入农场
                if(parseInt(this.loginInfo.data.type)===4){
                    this.$router.push('farm');
                }
                else{//企业用户登录后进入排行榜
                    this.$router.push('rank');
                }
                 
            }
            else{
                this.noticeShow = true;
                this.noticeMsg = this.loginInfo.msg;
                return;
            }

        }
    }
}
</script>
<style lang="scss" scoped>
@import '../../style/mixin';

.main_section{
    @include cl;
    .disflex{
        display: flex;
        align-items: center;
        margin-bottom: 8px;
    }
    .login_icon{
        font-size: 22px;
        margin-right: 5px;
        color: #3598db;
    }
    .login_icon:before{
        content: "\e614";
    }
    .ganlucenter{
    	text-align: center;
    }
    form{
        background-color:#fff;
        opacity: 0.9;
        border-radius: 3px;
        text-align: center; 
        .username_icon:before{
           content: "\e7bd";
           color: #ccc;
        }
        .password_icon:before{
           content: "\e622";
           color: #ccc;
        }
        .input_box{
            border-bottom: 1px solid #e3e4e5;
        }
        section{
            height: 45px;
            line-height: 45px;
            display: flex;
           
            align-items: center;
            label{
                margin: 0 5px;
            }
            input,select{
                height: 30px;
                width: 300px;
                font-size: 16px;
                padding-left: 5px;
                border: none;
                outline: none;
            } 
        }
    }
    .notice_p{
        color: red;
        font-size: 16px;
    }
}
.link_a{
    font-size: 16px;
    color: white;
}
.link_a:hover{
    color: #4283b9;
}
.login_button{
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
	.z-indexganlu{
		z-index: 3;
	}
</style>

