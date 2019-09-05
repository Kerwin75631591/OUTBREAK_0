// pages/qrcode/qr.js
var wxbarcode = require('../../utils/qrcodeCreator.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'383250208@qq.com',
    mid:123456
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wxbarcode.qrcode('qrcode',this.data.email+' '+this.data.mid,400,400);
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