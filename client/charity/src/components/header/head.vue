<template>
	<section class="head_box centerganlu">

		<el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
			<el-row :gutter="20">
				<el-col :span="5" :offset="1">
					<div class="grid-content bg-purple">
						<el-menu-item index="0"><i class="iconfont logo_icon"></i>百川汇海，链者无疆</el-menu-item>
					</div>
				</el-col>
				<el-col :span="3">
					<div class="grid-content bg-purple">
						<el-menu-item index="1">
							<router-link to="/home" class="ganlucolor">首页</router-link>
						</el-menu-item>
					</div>
				</el-col>
				<el-col :span="3">
					<div class="grid-content bg-purple">
						<el-menu-item index="2">
							<router-link to="/info" class="ganlucolor">资讯</router-link>
						</el-menu-item>
					</div>
				</el-col>
				<el-col :span="3">
					<div class="grid-content bg-purple">
						<el-menu-item index="3">
							<router-link to="/rank" class="ganlucolor">排行榜</router-link>
						</el-menu-item>
					</div>
				</el-col>
				<el-col :span="3">
					<div class="grid-content bg-purple">
						<el-menu-item index="4">
							<router-link to="/login" class="ganlucolor">登录</router-link>
						</el-menu-item>
					</div>
				</el-col>
				<el-col :span="3">
					<div class="grid-content bg-purple">
						<el-menu-item index="5">
							<router-link to="/partner" class="ganlucolor">合作伙伴</router-link>
						</el-menu-item>
					</div>
				</el-col>
				<el-col :span="3">
					<div class="grid-content bg-purple">
						<el-menu-item index="6">
							<router-link to="/about" class="ganlucolor">关于我们</router-link>
						</el-menu-item>
					</div>
				</el-col>
			</el-row>
		</el-menu>
		<h1 class="title_head">.</h1>
	</section>
</template>

<script>
	export default {

		watch: {
			'$route' (to, from) {
				console.log("jjjj");
				console.log(to.path);
				// react to route changes...
			}
		},
		beforeRouteUpdate(to, from, next) {
			// react to route changes...
			// don't forget to call next()
			console.log("bng");
			next();
		}
	}
</script>

<style lang="scss" scoped>
	@import '../../style/mixin';
	.head_container {
		position: sticky;
		top: 0;
		display: flex;
		justify-content: space-between;
		height: 45px;
		padding: 0 40px;
		align-items: center;
		background-color: #eee;
		box-shadow: 0 0 1px rgba(0, 0, 0, 0.25);
		.disflex {
			display: flex;
		}
		
		.heada_current {
			border-bottom: 3px solid #4283b9;
		}
	}
	.centerganlu{
		text-align: center;
	}
	.logo_icon {
			font-size: 26px;
			color: #ffe;
		}
	.logo_icon:before {
		content: "\e89d";
	}
	.ganlucolor {
		color: white;
	}
	
	.title_head {
		font-size: 26px;
		text-align: center;
		margin: 25px 0 45px;
		color: white;
	}
	
	.gan {
		margin-bottom: -970px;
	}
	
	.head_box{
		position: sticky;
		top: 0;
		z-index: 2;
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
	
	.image {
		width: 80%;
		display: block;
	}
</style>