// index.js
// 获取应用实例
const app = getApp() // 取app.js
// 油条，以后就复制粘贴
const util= require('../../utils/util.js')

Page({
    data: {
        api: app.globalData.api,
        activity: [],
        myid: 0,// 发给java
        aid: 0, // 活动id，发给java
    },
    onLoad: function (options) {
        this.data.myid = wx.getStorageSync('myid')
        util.http('/vx/join?myid='+this.data.myid, resp=>{
            this.data.activity = resp.activity
            this.setData(this.data) // 固定写法，将数据更新到页面中
        })
    },
    unjoin(e) {
        this.data.aid = e.currentTarget.dataset.aid
        util.http('/vx/unjoin', this.data, resp=>{
            util.alert('取消报名成功')
            this.onLoad() // 重新调用主函数，查询最新的数据
        })
    }
})