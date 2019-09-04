// pages/meetingdetail/meetingdetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    mid:'123',
    //saved in meeting
    meetingname:'123',
    topic:'123',
    time:'123',
    place:'231',
    state:0,
    files:'123.txt',
    content:'123ajfajjakjfdasjfklasdfdasklfjkasfjasdfciwafaksljcfclndja',

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app = getApp();
    this.setData({
      email: app.globalData.email
    })/*
    wx.request({
      url: 'https://localhost:443/ComplexMeeting',
      data:{
        mid:this.data.mid
      },
      method:"GET",
      header:{
        'content-type':'application/json'
      },
      success: function(res){
        console.log(res.data);
        this.setData({
          meetingname:res.data.meeting.name,
          topic:res.data.meeting.topic,
          time:res.data.meeting.time,
          place:res.data.meeting.place,
          state:res.data.meeting.state,
          files:res.data.meeting.fileUrl,
          content:res.data.meeting.content
        });
      }
    });*/
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

  }
})