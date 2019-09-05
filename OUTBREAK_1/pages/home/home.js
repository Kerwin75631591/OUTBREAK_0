// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    Number: 0,
    list: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app = getApp();
    this.setData({
      email: app.globalData.email
    })
    
    var that = this;
    wx.request({
      url: 'http://localhost:443/SimpleMeeting',
      data: {
        email: that.data.email
      },
      method: 'GET',
      header: {
        'content-type': "applicaton/json"
      },
      success: function (res) {
        console.log(res.data); // 将后台得到的数据打印到控制台
        var list = res.data.list;
        if(list != null) {
          that.setData({
            Number: res.data.Number,
            list: res.data.list
          })
        } else {
          // 此处用处理该用户无有关会议的情况
        }
      }
    })
    console.log(that.data);
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
    this.onLoad();
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
    this.onLoad();
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
   * 用户点击详细信息进入该会议详细信息界面
   */
  // 跳转到详情页
  jumpToDetail: function (v) {
    console.log(v.currentTarget.id);
    wx.navigateTo({
      url: '/pages/meetingdetail/meetingdetail?mid=' + v.currentTarget.id
    })
  },
})