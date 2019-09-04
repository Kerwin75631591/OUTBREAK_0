// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    password:'',
    judge:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 获取邮箱
   */
  LoginEmail: function (lm) {
    this.setData({
      email: lm.detail.value
    })
  },

  /**
   * 获取密码
   */
  LoginPassword: function (lp) {
    this.setData({
      password: lp.detail.value
    })
  },

  /**
   * 用户点击登录按钮进入小程序
   */
  LoginBtn: function() {
    var that = this;
    // 发出请求
    wx.request({
      url: 'http://localhost:443/Login',
      data: {
        email: that.data.email,
        password: that.data.password
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)//打印到控制台
        // 获得来自后台的变量值
        var judge = res.data.judge;
        // 将后台数据传至data中
        that.setData({
          judge: judge
        })
        // 如果邮箱与密码匹配，登录成功
        if (judge == true) {
          wx.reLaunch({
            url: '/pages/home/home',
          })
        } 
      }
    })
  },

  /**
   * 用户点击忘记密码可以对密码进行重置
   */
  FogetPwdBtn: function() {
    wx.navigateTo({
      url: '../pwforget/pwforget',
    })
  },

  /**
  *用户点击注册按钮进入注册页面
  */
  RegisterBtn: function () {
    wx.navigateTo({
      url: '/pages/reg/reg',
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  }

})