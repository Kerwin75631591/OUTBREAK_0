// pages/reg/reg.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email: '',
    password: '',
    name: '',
    isregister: false
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
   * 获取用户邮箱
   */
  RegisterEmail: function (re) {
    this.setData({
      emial: re.detail.value
    })
  },

  /**
   * 获取用户名称
   */
  RegisterName: function (rn) {
    this.setData({
      name: rn.detail.value
    })
  },

  /**
   * 获取用户密码
   */
  RegisterPassword: function (rp) {
    this.setData({
      password: rp.detail.value
    })
  },

  /**
   * 用户点击注册按钮完成注册
   */
  RegsterBtn: function () {
    var that = this;
    // 发出请求
    wx.request({
      url: 'http://localhost:443/Register',
      data: {
        email: that.data.email,
        name: that.data.name,
        password: that.data.password
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)//打印到控制台
        // 获得来自后台的变量值
        var isregister = res.data.isregister;
        // 将后台数据传至data中
        that.setData({
          isregister: isregister
        })
        // 如果邮箱与密码匹配，登录成功
        if (isregister == true) {
          wx.navigateTo({
            url: '/pages/home/home',
            success: function (res) { },
            fail: function (res) { },
            complete: function (res) { },
          })
        }
      }
    })
  },

  /**
   * 用户点击退出按钮退出注册
   */
  CancelBtn: function () {
    wx.navigateTo({
      url: '/pages/log/log',
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  }
})