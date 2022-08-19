// pages/login/login.js // 基本上每一个页面js都需要app.js和油条工具
const app = getApp() // 导入app.js文件
const util= require('../../utils/util.js')

Page({ // 小程序语句，我推荐末尾不加 ;
    data: { // login页面的全局变量，对象里面变量值末尾要记得加 ,
        username: '', // 末尾加逗号
        password: '',
    }, // 之前登录过一次，就会把myid存入到小程序内部，此时再编译就无需再次登录
    onLoad: function (options) { // 登录页面的主函数
        if (wx.getStorageSync('myid')) {
            wx.switchTab({
              url: '../../pages/index/index',
            })
        }
    },
    onInput(e) { // 自己定义的函数，e 叫事件参数
        // e.detail.value 则为获取输入框中的内容
        // 对应法则：data-name="值" 对应 e.currentTarget.dataset.name
        //          data-item="值" 对应 e.currentTarget.dataset.item
        // util.alert(e.detail.value) // 语句末尾可以加 ; 也可以不加
        // util.alert(e.currentTarget.dataset.name)
        this.data[e.currentTarget.dataset.name] = e.detail.value
        // 在哪个框中写，就会把写的内容直接赋值给对应框name(username,password)
        // 在用户名框中写，this.data['username'] = 写的内容
        // 在密码框中写，  this.data['password'] = 写的内容
        // this.data['username'] 等价于 this.data.username = 写的内容
        // this.data['password'] 等价于 this.data.password = 写的内容
    },
    login() { // http是一种网络协议，处在《计算机网络》中 应用层 协议
        // util.alert(this.data.username + ',' + this.data.password)
        // util.http('地址', 带参, resp=>{ 函数体 }) // 第三个参数叫回调函数
        // 小程序-->http请求Java数据接口-->Java去CURD-->Java最后把结果返给小程序
        //   不能不写前半段/vx/login
        util.http('/vx/login', this.data, resp=>{
            if (resp.code == 1) { // 把java给小程序的myid直接存入到本地缓存中
                wx.setStorageSync('myid', resp.myid)
                wx.switchTab({ // “切换”到主页面，因为主页面有底部栏 首页 我的
                  url: '../../pages/index/index',
                })
            } else {
                util.alert(resp.msg)
            }
        })
    }
})