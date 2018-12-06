<template>
	<section id="partbox">
		<el-container>
			<el-header>
				<head-top></head-top>
			</el-header>
			<el-main class="margintop80 ganluindex">
				<h3 class="ganlucenter" style="color: white;">百川链——基于区块链的慈善社交游戏平台</h3><br>
				<h3 class="ganlucenter" style="color: white;">资讯</h3><br><br>
				<br><br>

				<el-row :gutter="20">
					<el-col :span="10" :offset="3">
						<div class="grid-content bg-purple">
							<el-card class="box-card">
								<div slot="header" class="clearfix">
									<span style="font-size: medium;">99公益日,支持你的爱豆,推一把,一起爱!</span>
									
									<el-button style="float: right; padding: 3px 0; font-size: small " type="text"><a href="http://view.inews.qq.com/a/CRI2018090505246200" target="_blank">原文地址</a></el-button>
									
								</div>
								
								<img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/1111.bmp" />
							<br><br>
								<div  style="font-size: small;">每个人的微小助推,汇聚在一起可以形成改变世界的力量.99公益日,王俊凯,王源,易烊千玺等明星邀你推一把,从身边的小事做起,积累善意,传递爱心.合抱之木,生于毫木.九层之台,起于累土.让我们一起为爱推推推推推一把!</div>
								
							</el-card>
						</div>
					</el-col>
					<el-col :span="10">
						<div class="grid-content bg-purple">
							<el-card class="box-card">
								<div slot="header" class="clearfix">
									<span style="font-size: medium;">父亲带脑瘫儿看世界,跑第36场马拉松</span>
									<el-button style="float: right; padding: 3px 0; font-size: small" type="text" ><a href="https://gongyi.qq.com/a/20181127/004619.htm" target="_blank">原文地址</a></el-button>
								</div>
								<img src="https://charity-1257055754.cos.ap-beijing.myqcloud.com/222.bmp" />
								<br><br>
								<div  style="font-size: small;">带着脑瘫儿子奔跑在马拉松路上,罗书坚也想通过这样的方式,鼓励跟他一样的家庭.</div>
								
							</el-card>
						</div>
					</el-col>
				</el-row>

			</el-main>
		</el-container>
	</section>
</template>

<script>
	import { mapState, mapActions } from 'vuex'
	import headTop from '../../components/header/head'
	import 'src/plugins/swiper.min.js'
	import 'src/style/swiper.min.css'

	export default {
		data() {
			return {

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
		computed: mapState([
			'count'
		]),
		methods: {
			...mapActions([
				'increment', 'decrement',
			]),

		}

	}
</script>

<style>
	.ganluindex{
		z-index: 3;
	}
	.ganlucenter {
		text-align: center;
	}
	
	.title_head {
		font-size: 26px;
		text-align: center;
		margin: 20px 0;
	}
	
	.text {
		font-size: 14px;
	}
	
	.item {
		margin-bottom: 18px;
	}
	
	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}
	
	.clearfix:after {
		clear: both
	}
	
	.box-card {
		text-align: center;
		width: 80%;
		height: 350px;
	}
</style>