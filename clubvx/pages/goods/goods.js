const app = getApp() // 取app.js
// 油条，以后就复制粘贴
const util= require('../../utils/util.js')

Page({
    data: { // 页面的全局变量。写的变量都可以被wxml使用{{}}取出来
        Goods_Num: 0,
        api: app.globalData.api,
        item: {},
        cid: 0,// 编译时，从缓存中取
        gid: 0, // 提交给java
        amount: 0
    },
    onLoad: function (options) { // 页面的主函数
        this.data.item = wx.getStorageSync('goods')
        this.data.cid = wx.getStorageSync('myid')
        this.data.gid = this.data.item.id
        this.setData(this.data) // 把item数据更新到页面中
        let num=this.data.Goods_Num
        num++
        this.setData({
        Goods_Num:num
    })
    },
    addNum: function() {
      let num=this.data.Goods_Num
      if(num<this.data.item.amount){
        num++
      }
      else{
        wx.showToast({
          title: '不能再增加了哟！',
          icon:'none',
          duration:1500
        })
      }
      this.setData({
        Goods_Num:num
      })
    },
    //数字减1
    minusNum: function() {
      let num=this.data.Goods_Num
      if(num>=2){
        num--
      }
      else{
        wx.showToast({
          title: '不能再减少了哟！',
          icon:'none',
          duration:1500
        })
      }
      this.setData({
        Goods_Num:num
      })
    },
    buy() {
      let num=this.data.Goods_Num
        this.data.amount = num
        util.http('/vx/buy', this.data, resp=>{
            if (resp.code == 1) {
                util.alert('购买成功')
                this.data.item.cid = wx.getStorageSync('myid')
                this.setData(this.data)
                wx.redirectTo({
                  url: '../shopping/shopping',
                })
            } else {
                util.alert(resp.msg)
            }
        })
    }
})