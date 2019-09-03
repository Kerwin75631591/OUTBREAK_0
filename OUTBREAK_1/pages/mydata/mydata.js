Page({

  /**
   * 页面的初始数据
   */
  data: {
    // onPullDownRefresh: function () {
    //   wx.stopPullDownRefresh()
    // },
    myinfo: null,
    name: '',
    phoneNum: '',
    duties: '',
    address: '',
    email: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //var stu = wx.getStorageSync('student');
    //this.setData({ myinfo: stu });

    var that = this;
    // 发出请求
    wx.request({
      url: 'http://localhost:443/UserData',
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)//打印到控制台
        // 获得来自后台的变量值
        var name = res.data.name;
        var phoneNum = res.data.phoneNum;
        var duties = res.data.duties;
        var address = res.data.address;
        var email = res.data.email;
        if (list == null) {
          var toastText = '数据获取失败';
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          // 将后台数据传至data中
          that.setData({
            name: name,
            phoneNum: phoneNum,
            duties: duties,
            address: address,
            email: email
          })
        }
      }
    })
  },

  exit: function (e) {
    wx.showModal({
      title: '提示',
      content: '是否确认退出',
      success: function (res) {
        if (res.confirm) {
          // console.log('用户点击确定')
          wx.removeStorageSync('student');
          //页面跳转
          wx.redirectTo({
            url: '../log/log',
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  /**
   * 重置密码函数
   */
  resetpwd: function (e) {
    var no = this.data.myinfo.no;
    wx.navigateTo({
      url: '../password/password?no=' + no,
    })
  },

  setemail: function (e) {
    var no = this.data.myinfo.no;
    wx.navigateTo({
      url: '../email/email?no=' + no,
    })
  }
})
