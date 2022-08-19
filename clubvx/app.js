// app.js 在js中{}代表对象
App({ // App({ onLaunch() {} }) 调用函数
  onLaunch() { // onLaunch: function () {  } --> onLaunch() {  }
    // 整个小程序的主函数
    // 展示本地存储能力
    // wx是一个系统对象 wx.getStorageSync('变量名') 获取本地缓存
    // 数据存储：
    //  1.网络存储，将小程序数据通过http请求发给java，java接收数据存入数据库中
    //   密码，发布的活动，用户信息，商品信息...都要保存在java的Mysql数据库中
    //  2.本地存储，将数据直接存入到App软件内部
    //   一般存储相对重要的数据，存登录用户的用户id
    //  问题：小程序卸载、微信卸载、我们清缓存，都会把本地存储数据清掉，数据丢失
    // var定义变量，const定义常量，常量特点：只能被赋值一次，一次赋值终身不改
    const logs = wx.getStorageSync('logs') || [] // 在js中，[]代表空数组
    // 数组.unshift()  在数组的开头添加新元素
    logs.unshift(Date.now()) // setStorageSync存  get取
    wx.setStorageSync('logs', logs)
    // 不应该在小程序主函数中获取微信登录信息
    // 登录，微信登录，可以通过微信登录获取当前微信用户的唯一id，叫openid
    // wx.login({
      // success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId

      // }
    // })
  },
  globalData: { // 整个小程序全局变量
    userInfo: null,
    api: 'http://localhost:8080', // 电脑ip地址:8080
  }
})
