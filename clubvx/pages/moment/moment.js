// pages/moment/moment.js

const app = getApp()
const util = require('../../utils/util.js')

Page({

    data: {
        api: app.globalData.api,
        postings: [],
        item: {},
        myid: 0,
        clicks: 0,
        uid: 0,
        detail: '',
    },
    onLoad(options) {
        this.data.myid = wx.getStorageSync('myid')
        util.http('/vx/postings', resp=>{
             //util.alert(resp.postings)
            
            this.data.postings = resp.postings
            this.setData(this.data)
        })
    },
    onPullDownRefresh() {
        util.http('/vx/postings', resp=>{
            this.data.postings = resp.postings
            util.stopPullSetData(this)
        })
    },
    onShow() {
        this.onLoad()
    },
    comment(e) {
        // 打开活动详情页
        // 把点击的活动item直接存入到小程序中，并定名为activity
        //增加点击量
        wx.setStorageSync('comment', e.currentTarget.dataset.item) 
        util.http('/vx/clicks?id='+e.currentTarget.dataset.item.id,resp=>{})
        util.redirect('comment')
    },
csubmit(){
    this.data.uid=wx.getStorageSync('myid')
    if(this.data.detail==''){
        util.alert("不能输入空")
      }else{
        util.http('/vx/addpostings', this.data, resp=>{
          util.alert('发布成功')
          this.data.detail=''
          this.onLoad()
          this.setData({ inputValue:null })
      })
    }
},
onInput(e) { // 赋值
    this.data.detail = e.detail.value
    }
})