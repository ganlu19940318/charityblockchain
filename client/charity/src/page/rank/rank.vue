<template>
	<section>
		<el-container>
			<el-header>
				<head-top></head-top>
			</el-header>
			<el-main class="margintop80 ganluindex">
				<section class="rank_container">
					<h3 class="ganlucenter" style="color: black;">百川链——基于区块链的慈善社交游戏平台</h3><br>
					<nav class="menu">
						<div class="menu_item" :class="{menu_active:isProgram}" @click="chooseProgram">慈善项目排行</div>
						<div class="menu_item" :class="{menu_active:isCompany}" @click="chooseCompany">企业用户排行</div>
						<div class="menu_item" :class="{menu_active:isPerson}" @click="choosePerson">个人用户排行</div>
					</nav>
					<div class="table_container">
						<section v-if="isProgram">
							<table>
								<thead>
									<th>慈善序号</th>
									<th>项目名称</th>
									<th>项目金额</th>
									<th>项目地址</th>
									<th>捐助状态</th>
									<th>捐助者地址</th>
									<th>简介</th>
								</thead>
								<tbody>
									<tr v-for="(item,index) in rankProjectInfo.data" :key="index">
										<td>{{index+1}}</td>
										<td>{{item.name}}</td>
										<td>{{item.money}}</td>
										<td>{{item.poeid}}</td>
										<td>{{item.status}}</td>
										<td>{{item.owner}}</td>
										<td>{{item.info}}</td>
									</tr>
								</tbody>
							</table>
						</section>
						<section v-if="isCompany">
							<table>
								<thead>
									<th>序号</th>
									<th>姓名</th>
									<th>地址</th>
									<th>捐助总额</th>
									<th>简介</th>
								</thead>
								<tbody>
									<tr v-for="(item,index) in rankCompanyInfo.data" :key="index">
										<td>{{index+1}}</td>
										<td>{{item.name}}</td>
										<td>{{item.poeid}}</td>
										<td>{{item.money}}</td>
										<td>{{item.info}}</td>
									</tr>
								</tbody>
							</table>
						</section>
						<section v-if="isPerson">
							<table>
								<thead>
									<th>序号</th>
									<th>姓名</th>
									<th>地址</th>
									<th>捐助总额</th>
									<th>简介</th>
								</thead>
								<tbody>
									<tr v-for="(item,index) in rankPersonInfo.data" :key="index">
										<td>{{index+1}}</td>
										<td>{{item.name}}</td>
										<td>{{item.poeid}}</td>
										<td>{{item.money}}</td>
										<td>{{item.info}}</td>
									</tr>
								</tbody>
							</table>
						</section>
					</div>
				</section>
				<div class="foot_nav">
					<button @click="toFarm">返回农场</button>
					<button @click="buyCharity">购买慈善</button>
					<button @click="getFertilizer">领取化肥</button>
				</div>
				<loading v-if="showloading"></loading>
			</el-main>
		</el-container>

	</section>
</template>

<script>
	import headTop from '../../components/header/head'
	import loading from 'src/components/common/loading'
	import { getProjectRank, getUserRank, getSeedAnd } from "src/service/getData";

	export default {
		data() {
			return {
				showloading: true,
				isProgram: true, //是否显示慈善项目排行
				isCompany: false, //是否显示企业用户排行
				isPerson: false, //是否显示个人用户排行
				rankProjectInfo: null, //
				rankPersonInfo: null, //
				rankCompanyInfo: null, //
				seedMethod: null, //
				getSeedInfo: null //

			}
		},
		components: {
			headTop,
			loading
		},
		beforeCreat() {
			this.showloading = true;
		},
		async beforeMount() {
			this.rankProjectInfo = await getProjectRank();

		},

		mounted() {

			this.showloading = false;

		},
		methods: {
			//慈善项目排行
			async chooseProgram() {
				this.isProgram = true;
				this.isCompany = false;
				this.isPerson = false;
				//this.rankInfo = await getProjectRank();
			},
			//企业用户排行
			async chooseCompany() {
				this.isProgram = false;
				this.isCompany = true;
				this.isPerson = false;
				if(!this.rankCompanyInfo) {
					this.rankCompanyInfo = await getUserRank("1");
				}

			},
			//个人用户排行
			async choosePerson() {
				this.isProgram = false;
				this.isCompany = false;
				this.isPerson = true;
				if(!this.rankPersonInfo) {
					this.rankPersonInfo = await getUserRank("2");
				}

			},
			toFarm() {
				this.$router.push('farm');
			},
			buyCharity() {
				this.$router.push('farm');
			},
			async getFertilizer() {
				if(this.isProgram) {
					this.seedMethod = "4";
				} else if(this.isCompany) {
					this.seedMethod = "2";
				} else if(this.isPerson) {
					this.seedMethod = "3";
				}
				this.showloading = true;
				this.getSeedInfo = await getSeedAnd(window.localStorage.getItem('token'), this.seedMethod);
				this.showloading = false;
				//console.log(this.getSeedInfo);
				if(parseInt(this.getSeedInfo.code) === 0) {
					this.$alert("领到一份化肥，返回农场查看吧", '领取成功', {
						confirmButtonText: '确定',
						callback: action => {
							this.$message({
								type: 'info',
								message: `action: ${ action }`
							});
						}
					});

				} else {
					//aleat弹框
					this.$alert(this.getSeedInfo.msg, '领取失败', {
						confirmButtonText: '确定',
						callback: action => {
							this.$message({
								type: 'info',
								message: `action: ${ action }`
							});
						}
					});

				}
			}
		}

	}
</script>

<style lang="scss" scoped>
	.rank_container {
		margin: 20px 40px;
	}
	.ganlucenter{
		text-align: center;
	}
	.menu {
		display: flex;
		font-size: 20px;
		.menu_item {
			height: 40px;
			line-height: 40px;
			text-align: center;
			cursor: pointer;
			flex: 1;
			border-bottom: 1px solid #3598db;
			color: #3598db;
		}
		.menu_active {
			background-color: #3598db;
			color: #fff;
			border-radius: 6px 6px 0 0;
			border-bottom: none;
		}
	}
	
	.table_container {
		padding: 20px;
	}
	
	.foot_nav {
		position: fixed;
		bottom: 100px;
		display: flex;
		justify-content: space-around;
		padding: 0 40px;
		width: 100%;
		button {
			height: 34px;
			width: 100px;
			border-radius: 5px;
			cursor: pointer;
			background-color: #3598A9;
			color: #fff;
		}
		button:hover {
			background-color: #3598db;
		}
	}
	
	.title_head {
		font-size: 26px;
		text-align: center;
		margin: 20px 0;
	}
</style>