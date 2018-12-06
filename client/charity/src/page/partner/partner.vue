<template>
	<div id="partbox">
		<el-container>
			<el-header>
				<head-top></head-top>
			</el-header>
			<el-main class="margintop80 ganluindex">
				<h3 class="ganlucenter" style="color: white;">百川链——基于区块链的慈善社交游戏平台</h3><br>
				<h3 class="ganlucenter" style="color: white;">合作伙伴</h3><br><br>
				<el-row :gutter="20">
					<el-col :span="2" :offset="7"><div class="ganlu-part1"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/arx.png"  width="100%"></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/qinghua.png"  width="100%" ></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/gangzhongwen.png" width="100%"></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/dlut.jpg" width="100%"></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/hust.jpg" width="100%"></div></el-col>
				</el-row>
<br><br>
				<el-row :gutter="20">
					<el-col :span="2" :offset="7"><div class="ganlu-part2"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/research.jpg" width="100%"></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/community.jpg" width="100%"></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/babi.jpg" width="100%"></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/hf.jpg" width="100%"></div></el-col>
					<el-col :span="2"><div class="ganlu-part"><img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/kk.jpg" width="100%"></div></el-col>
				</el-row>	
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import { mapState, mapActions } from 'vuex'
	import headTop from '../../components/header/head'
	import 'src/plugins/swiper.min.js'
	import 'src/style/swiper.min.css'

	export default {
		data() {
			return {
				partnerimg1: imgUrl+'/arx.png',

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
		},
		
		methods: {
			

		}

	}
</script>

<style>
.ganluindex{
	z-index: 3;
}
	.ganlucenter{
		color: white;
		text-align: center;
	}
	.title_head {
		font-size: 26px;
		text-align: center;
		margin: 20px 0;
	}
	
	
</style>