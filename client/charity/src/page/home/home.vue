<template>
	<div id="partbox">
		<el-container>
			<el-header>
				<head-top></head-top>
			</el-header>
			<el-main class="margintop80">
				<section class="margin20">
					<div class="swiper-container">
						<h3 class="ganlucenter" style="color: white;">百川链——基于区块链的慈善社交游戏平台</h3><br>
						<div class="swiper-wrapper" style="text-align: center;">
							<div class="swiper-slide img_box" v-lazy:background-image="homeImg1">
							</div>
							<div class="swiper-slide img_box" v-lazy:background-image="homeImg2">
							</div>
							<div class="swiper-slide img_box" v-lazy:background-image="homeImg3">
							</div>
						</div>
						<div class="swiper-pagination"></div>
						<div class="swiper-button-prev"></div>
						<div class="swiper-button-next"></div>
					</div>
				</section>
				
				
				<el-row class="z-indexganlu">
					<el-col :span="10" :offset="7" class="style1">
						<h3>项目介绍</h3>
						<p>CharityChain——charity blockchain based on Arxan Baas</p>
						<p align="left" style="text-align: justify;">Nowadays，blockchain，as a famous newly technology,has been widely used to solve many problems about trust and data security.</p>
						<p align="left"  style="text-align: justify;">As for charity business,there has been many people trying to use Blockchain to track the Donations.In their case,they think the biggest problem in charity business ，which does harm to the people doing charity，is that people can't make sure whether their money has been used in correct way,or given to the poor guys.</p>
						<p align="left"  style="text-align: justify;">Obviously,we can't agree with it.Ensuring the money being used correctly and properly can only be the duty of government.Any one who want to manage the charity organizations above the goverment,will easily be the one do harm to the charity business.</p>
						<p align="left"  style="text-align: justify;">So when it comes to Blockchain,we think it as a Baas for the people to make the data worthy.For the people doing charity,their behavior uusally means totally devotion,and can't make any financial returns.If anyone can make it available that every money you donate will increase your image，there will be certainly more rich people and company turn in the charity.</p>
						<p align="left"  style="text-align: justify;">To build it, we began our try to make CharityChain to record every charity behavior,and make it useful to the people’s public image.Everyone using our CharityChain can build his personly social name card,which faithfully records his donation and generosity.</p>
						<p align="left"  style="text-align: justify;">To learn more about our design and achievement in CharityChain,contact Dr Wang at 125801169@qq.com</p>
					</el-col>
				</el-row>
			</el-main>
		</el-container>
	</div>
</template>
<script>
	//import { mapState, mapActions } from 'vuex'
	import headTop from '../../components/header/head'
	import 'src/plugins/swiper.min.js'
	import 'src/style/swiper.min.css'

	export default {
		data() {
			return {
				homeImg1: 'https://charity-1257055754.cos.ap-beijing.myqcloud.com/21ee17bf90d7c144cd1cbb4a28e5decc.jpg',
				homeImg2: 'https://charity-1257055754.cos.ap-beijing.myqcloud.com/95J8R24UOKD6_IMG_5194_600.JPG',
				homeImg3: 'https://charity-1257055754.cos.ap-beijing.myqcloud.com/NK4178K3H3G7_IMG_5193_600.JPG'
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
		components: {
			headTop
		}

	}
</script>

<style lang="scss" scoped>
	.partner_sectionpartner_section {
		border: 1px solid #eee;
		margin: 0 40px;
		background-color: linen;
	}
	h3 {
		color: white;
		text-align: center;
	}
	.style1 {
		height: 1200px;
		p{
			color: white;
			margin-top: 10px;
		}
	}
	.z-indexganlu{
		z-index: 3;
	}
	.swiper-container {
		width: 800px;
		height: 300px;
	}
	
	.img_box {
		background-size: contain;
		background-position: center center;
		background-repeat: no-repeat;
	}
	
	.homeimg_box {
		height: 100px;
		background-size: 80%;
		background-repeat: no-repeat;
		background-position: center center;
	}
	
	.homeimg_container {
		height: 220px;
		background-size: 80%;
		background-repeat: no-repeat;
		background-position: center center;
	}
	.title_head {
		font-size: 26px;
		text-align: center;
		margin: 20px 0;
	}
	.margin20{
		margin-bottom: 20px;
	}
</style>