// index.js
// 获取应用实例
const app = getApp() // 取app.js
// 油条，以后就复制粘贴
const util= require('../../utils/util.js')
Page({
  data: {
    api: app.globalData.api,
    goods: [], // 空数组，数据来源于java
    myid: 0, // 初始化0，在主函数中获取myid
  }, 

  onShow() {
    this.onLoad()
  },
  onLoad() {
    this.data.myid = wx.getStorageSync('myid')
    util.http('/vx/bought?myid='+this.data.myid, resp=>{
      this.data.goods = resp.goods
      this.setData(this.data) // 固定写法，就可以把变化的数据更新到页面中
    })
  },
})
