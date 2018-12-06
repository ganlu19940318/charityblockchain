<template>
	<div id="partbox">
		<el-container>
			<el-header>
				<head-top></head-top>
			</el-header>
			<el-main class="margintop80 z-indexganlu">
				<h3 class="ganlucenter" style="color: white;">百川链——基于区块链的慈善社交游戏平台</h3><br>
				<h3 class="ganlucenter" style="color: white;">团队介绍</h3><br><br>
				<el-row>
					<el-col :span="16" :offset="4" class="style1">
						<el-row :gutter="20">
							<el-col :span="6">
								<el-card :body-style="{ padding: '0px' }">
									<div class="ganlu-height">
										<img v-lazy='photoLC'>
										<div style="padding: 14px;">
											<p class="text_word">刘冲</p>
											<br>
											<p class="text_word">清华大学法学硕士</p>
											<p class="text_word">YOUNG区块链青年社区联合创始人</p>
										</div>
									</div>
								</el-card>
							</el-col>
							<el-col :span="6">
								<el-card :body-style="{ padding: '0px' }">
									<div class="ganlu-height">
										<img v-lazy='photoDFJ'>
										<div style="padding: 14px;">
											<p class="text_word">丁凤姣</p>
											<br>
											<p class="text_word">香港中文大学金融学硕士</p>
											<p class="text_word">世界500强企业资深PM</p>
										</div>
									</div>
								</el-card>
							</el-col>
							<el-col :span="6">
								<el-card :body-style="{ padding: '0px' }">
									<div class="ganlu-height">
										<img v-lazy='photoWTY'>
										<div style="padding: 14px;">
											<p class="text_word">王腾宇</p>
											<br>
											<p class="text_word">大连理工大学软件工程硕士</p>
											<p class="text_word">YOUNG区块链研究院院长</p>
											<p class="text_word">Hyperledger国际化工作组成员</p>
										</div>
									</div>
								</el-card>
							</el-col>
							<el-col :span="6">
								<el-card :body-style="{ padding: '0px' }">
									<div class="ganlu-height">
										<img v-lazy='photoGL'>
										<div style="padding: 14px;">
											<p class="text_word">甘露</p>
											<br>
											<p class="text_word">华中科技大学计算机硕士</p>
											<p class="text_word">2018 IEEE Cybermatics Congress on Blockchain以学生一作身份发表论文</p>
										</div>
									</div>
								</el-card>
							</el-col>
						</el-row>
					</el-col>

				</el-row>
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import headTop from '../../components/header/head'
	import { getProjectRank, getUserRank, getSeedAnd } from "src/service/getData";

	export default {
		data() {
			return {
				photoLC: 'https://charity-1257055754.cos.ap-beijing.myqcloud.com/01.bmp',
				photoGL: 'https://charity-1257055754.cos.ap-beijing.myqcloud.com/04.bmp',
				photoWTY: 'https://charity-1257055754.cos.ap-beijing.myqcloud.com/03.bmp',
				photoDFJ: 'https://charity-1257055754.cos.ap-beijing.myqcloud.com/02.bmp'
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
.title_head {
		font-size: 26px;
		text-align: center;
		margin: 20px 0;
	}
	.ganlu-height{
		height: 400px;
		text-align: center;
	}
	.gan {
		margin-bottom: -970px;
	}
	
	.ganlucenter{
		text-align: center;
	}
	.z-indexganlu{
		z-index: 3;
	}
	.back-white {
		background-color: rgba(0, 0, 0, 0);
	}
	
	.wzt-center {
		background-color: rgba(0, 0, 0, 0);
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.el-carousel__item h3 {
		color: #475669;
		font-size: 14px;
		opacity: 0.75;
		line-height: 150px;
		margin: 0;
	}
	
	.el-carousel__item:nth-child(2n) {
		background-color: rgba(0, 0, 0, 0);
	}
	
	.el-carousel__item:nth-child(2n+1) {
		background-color: rgba(0, 0, 0, 0);
	}
	
	.style1 {
		height: 900px;
	}
	
	.text_word {
		text-align: center;
	}
</style>