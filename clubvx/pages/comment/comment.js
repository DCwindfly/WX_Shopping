// pages/comment/comment.js

const app = getApp()
const util = require('../../utils/util.js')

Page({

    data: {
        api: app.globalData.api,
        pid: 0,
        uid: 0,
        comment:{},//帖子
        comments:[],//评论列表
        detail:''

    },
onLoad(options) {
    this.data.comment=wx.getStorageSync('comment')
    util.http('/vx/comments?pid='+this.data.comment.id,resp=>{
    // util.alert(resp.comments)
    this.data.comments=resp.comments
    this.setData(this.data)
    })
},
onShow() {
    this.onLoad()
},
onPullDownRefresh() {
    util.http('/vx/comments?pid='+this.data.comment.id,resp=>{
        this.data.comments=resp.comments
        util.stopPullSetData(this)
    })
},
onInput(e) { // 赋值
    this.data.detail = e.detail.value
},
csubmit(){
    this.data.pid=this.data.comment.id
    this.data.uid=wx.getStorageSync('myid')
    if(this.data.detail == ''){
        util.alert("不能输入空")
      }else{
        util.http('/vx/addcomments', this.data, resp=>{
          util.alert('发布成功')
          this.data.detail=''//数据清空
          this.onLoad()
          this.setData({ inputValue:null })
      })
    
    }
}
})
