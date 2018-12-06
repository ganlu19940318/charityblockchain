import App from '../App'

const home = r => require.ensure([], () => r(require('../page/home/home')), 'home');
const login = r => require.ensure([], () => r(require('../page/login/login')), 'login');
const register = r => require.ensure([], () => r(require('../page/register/register')), 'register');
const farm = r => require.ensure([], () => r(require('../page/farm/farm')), 'farm');
const seedTrade = r => require.ensure([], () => r(require('../page/farm/children/seedTrade')), 'seedTrade');
const getSeed = r => require.ensure([], () => r(require('../page/farm/children/getSeed')), 'getSeed');

const rank = r => require.ensure([], () => r(require('../page/rank/rank')), 'rank');
const about = r => require.ensure([], () => r(require('../page/about/about')), 'about');
const partner = r => require.ensure([], () => r(require('../page/partner/partner')), 'partner');
const info = r => require.ensure([], () => r(require('../page/info/info')), 'info');

const test = r => require.ensure([], () => r(require('../page/test/test')), 'test');


export default [{
    path: '/',
    component: App,
    children: [
         //地址为空时跳转home页面
         {
            path: '',
            redirect: '/home'
        },
        //首页
        {
            path: '/home',
            component: home
        },
        //登录页面
        {
            path: '/login',
            component: login
        },
        //注册
        {
            path: '/register',
            component: register
        },
        //农场页面
        {
            path: '/farm',
            component: farm,
            children:[
               ]
        },
        //领取稀有种子
        {
            path: '/getSeed',
            component: getSeed
        },
        //稀有种子交易
        {
            path: '/seedTrade',
            component: seedTrade
        },
        //排行榜页面
        {
            path: '/rank',
            component: rank
        },
         //关于我们
        {
            path: '/about',
            component: about
        },
        //合作伙伴
        {
            path: '/partner',
            component: partner
        },
        //资讯
        {
            path: '/info',
            component: info
        },
        //test
        {
            path: '/test',
            component: test
        }
   
    ]
}]