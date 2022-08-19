const util = require("../../utils/util")

// 获取应用实例
const app = getApp()
Page({
  data: {
    longitude: 120.123986, //地图界面中心的经度
    latitude: 36.004251, //地图界面中心的纬度
    markers: []
  },

  onLoad: function () {
    var that = this;
    util.http('/vx/map?myid='+'1', resp=>{
      this.data.myid = wx.getStorageSync('myid')
      this.data.markers = resp.map
      this.setData(this.data) // 固定写法，就可以把变化的数据更新到页面中
    })
  },

  onReady: function () {

  },

  /**
   * 地图放大缩小事件触发
   * @param {*} e 
   */
  bindregionchange(e) {
    console.log('=bindregiοnchange=', e)
  },

  /**
   * 点击地图事件，有经纬度信息返回
   * @param {*} e 
   */
  bindtapMap(e) {
    console.log('=bindtapMap=', e)
  }
  
})
