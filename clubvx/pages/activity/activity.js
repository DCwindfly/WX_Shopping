const app = getApp() // 取app.js
// 油条，以后就复制粘贴
const util= require('../../utils/util.js')

Page({
    data: { // 页面的全局变量。写的变量都可以被wxml使用{{}}取出来
        api: app.globalData.api,
        item: {},
        myid: 0,// 编译时，从缓存中取
        aid: 0, // 提交给java
    },
    onLoad: function (options) { // 页面的主函数
        this.data.myid = wx.getStorageSync('myid')
        // 在主函数中，把缓存的 myid 取出来赋值给 myid 变量
        this.data.item = wx.getStorageSync('activity')
        this.data.aid = this.data.item.id // 把活动信息中的主键id赋值给aid
        this.setData(this.data) // 把item数据更新到页面中
    },
    join() {
        util.http('/vx/join', this.data, resp=>{
            if (resp.code == 1) {
                util.alert('报名成功')
                this.data.item.myid = wx.getStorageSync('myid')
                this.setData(this.data)
            } else {
                util.alert(resp.msg)
            }
        })
    }
})