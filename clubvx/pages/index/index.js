// index.js
// 获取应用实例
const app = getApp() // 取app.js
// 油条，以后就复制粘贴
const util= require('../../utils/util.js')
Page({
  data: {
    api: app.globalData.api,
    activity: [], // 空数组，数据来源于java
    myid: 0, // 初始化0，在主函数中获取myid
    hot: [], // 热门活动数组
  }, // 下拉刷新，比较好玩
  onPullDownRefresh() {
    util.http('/vx/activity?myid='+this.data.myid, resp=>{
      this.data.activity = resp.activity
      this.data.hot = resp.hot
      util.stopPullSetData(this) // 把变化的数据更新到页面的同时，结束三个点
    })
  },
  // 时机：又回到首页，就说明，再一次看见了index画面
  onShow() {
    this.onLoad()
  },
  onLoad() {
    this.data.myid = wx.getStorageSync('myid')
    util.http('/vx/activity?myid='+this.data.myid, resp=>{
      this.data.hot = resp.hot
      this.data.activity = resp.activity // activity列表中就会多出1列myid
      this.setData(this.data) // 固定写法，就可以把变化的数据更新到页面中
    })
  },
  activity(e) { // e 很关键，带参 data-item=""
    // 把点击的活动item直接存入到小程序中，并定名为activity
    wx.setStorageSync('activity', e.currentTarget.dataset.item)
    // 打开活动详情页，封装了一个重定向，其实就是打开新页面
    util.redirect('activity')
  }
})
