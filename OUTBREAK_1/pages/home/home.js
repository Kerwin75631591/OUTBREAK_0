// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    Number: 0,
    list: '',
    userList: null,
    hiddenAssessmentModal: false,
    A1: null,
    A2: null,
    A3: null,
    A4: null,
    A5: null,
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
      url: 'http://www.outbreak.xyz:443/SimpleMeeting',
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
    
    wx.request({
      url: 'http://www.outbreak.xyz:443/searchAssessment',
      data: {
        email: that.data.email
      },
      method: 'GET',
      header: {
        'content-type': "applicaton/json"
      },
      success: function (res) {
        var temp = res.data.list;
        if (temp != null) {
          that.setData({
            userList: res.data.list
          })
        } else {
          // 此处用处理该用户不需要进行会议评估的情况
        }
      }
    })
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

  /**
  * 动态改变A1.value
  */
  bindinput_Q1: function (e) {
    this.setData({
      A1: e.detail.value
    })
    if (this.A1 > 10 || this.A1 < 0) {
      wx.showToast({
        title: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A1: null
      })
    }
  },

  /**
  * 动态改变A2.value
  */
  bindinput_Q2: function (e) {
    this.setData({
      A2: e.detail.value
    })
    if(this.A2 > 10 || this.A2 < 0){
      wx.showToast({
        title: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A2: null
      })
    }
  },

  /**
  * 动态改变A3.value
  */
  bindinput_Q3: function (e) {
    this.setData({
      A3: e.detail.value
    })
    if (this.A3 > 10 || this.A3 < 0) {
      wx.showToast({
        title: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A3: null
      })
    }
  },

  /**
  * 动态改变A4.value
  */
  bindinput_Q4: function (e) {
    this.setData({
      A4: e.detail.value
    })
    if (this.A4 > 10 || this.A4 < 0) {
      wx.showToast({
        title: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A4: null
      })
    }
  },

  /**
  * 动态改变A5.value
  */
  bindinput_Q5: function (e) {
    this.setData({
      A5: e.detail.value
    })
    if (this.A5 > 10 || this.A5 < 0) {
      wx.showToast({
        title: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A5: null
      })
    }
  },

  /**
  * 会议评估框的取消函数
  */
  assessmentCancel: function(){
    wx.showToast({
      title: '请您尽快完成会议评估',
      duration: 1000,
      mask: true,
    });
    this.setData({
      hiddenAssessmentModal: true,
    })
  },

  /**
  * 会议评估框的确认函数
  */
  resetConfirm: function (e) {
    wx.request({
      url: 'http://localhost:443/Assessment',
      data: {
        email: this.data.email,
        mid: e,
        A1: this.data.A1,
        A2: this.data.A2,
        A3: this.data.A3,
        A4: this.data.A4,
        A5: this.data.A5
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        wx.showLoading({
          title: '正在提交会议评估表，请稍等！',
          duration: 500,
          mask: true
        })
      }
    });
    wx.redirectTo({
      url: '/pages/home/home',
    });
  },

})