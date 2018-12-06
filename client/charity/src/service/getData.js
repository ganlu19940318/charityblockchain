import fetch from '../config/fetch'


/**
 * 注册
 */
export const register = (name,idCard,tel,type,info,username,password) => fetch('/user/register', {
    name: name,
    idCard: idCard,
    tel: tel,
    type: type,
    info: info,
    username: username,
    password: password

}, 'POST');


/**
 * 登录
 */
export const login = (username,password) => fetch('/user/login', {
    username: username,
    password: password
}, 'POST');


/**
 * 查看慈善名片
 */
export const getMyCard = (token) => fetch('/user/card', {
    token
}, 'POST');



/**
 * 获取农场土地信息
 */
export const getLand = (token) => fetch('/farm/land', {
    token
}, 'POST');



/**
 * 查看背包所有物品（种子/化肥/果实）
 */
export const getFarmItem = (token) => fetch('/farm/item', {
    token
}, 'POST');



/**
 * 查看慈善项目排行榜
 */

export const getProjectRank = () => fetch('/rank/project', {
    
}, 'POST');



/**
 * 查看用户排行榜（企业/个人）
 */

export const getUserRank = (type) => fetch('/rank/user', {
    type
}, 'POST');



/**
 * 获取种子或者化肥
 */

export const getSeedAnd = (token,method) => fetch('/farm/seed', {
    token,
    method
}, 'POST');



/**
 * 获取稀有种子
 */

export const getRareSeed = (seedpk64,seedpoeid,seedwalletid,token,userpoeid) => fetch('/farm/rare', {
    seedpk64,
    seedpoeid,
    seedwalletid,
    token,
    userpoeid
}, 'POST');


/**
 * 在农田上使用物品
 */

export const useitemServer = (itemid,number,token) => fetch('/farm/useitem', {
    itemid,
    number,
    token
}, 'POST');



/**
 * 收获果实
 */

export const getHaverstServer = (number,token) => fetch('/farm/harvest', {
    number,
    token
}, 'POST');



/**
 * 果实加工
 */

export const fruitMerge = (token) => fetch('/farm/merge', {
    token
}, 'POST');


/**
 * 稀有种子交易
 */

export const seedTrade = (mypk64,mypoeid,mywalletid,seedpoeid,token,topoeid) => fetch('/farm/trade', {
    mypk64,
    mypoeid,
    mywalletid,
    seedpoeid,
    token,
    topoeid
}, 'POST');


