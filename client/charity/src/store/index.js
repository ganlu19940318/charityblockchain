import Vue from 'vue'
import Vuex from 'vuex'
import actions from './action'
import mutations from './mutations'

Vue.use(Vuex)
const state = {
    count:0
};
export default new Vuex.Store({
	state,
	actions,
	mutations
})