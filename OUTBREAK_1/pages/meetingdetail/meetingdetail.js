// pages/meetingdetail/meetingdetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    mid:'123',
    //saved in data
    num:0,//number of invited people
    //saved in data.meeting
    meetingname:'123',
    topic:'123',
    time:'123',
    place:'231',
    state:0,
    files:'123.txt',
    content:'123ajfajjakjfdasjfklasdfdasklfjkasfjasdfciwafaksljcfclndja',
    //saved in data.peoplei(0----n-1)
    //name saved in data.people.name,TOF saved in data.people.TOF
    people:[{name:'Harry',TOF:1},{name:'Hermoine',TOF:1}]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app = getApp();
    this.setData({
      email: app.globalData.email
    });
    //this.refreshData();
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

  //functions
  refreshData:function(){
    wx.request({
      url: 'http://localhost:443/ComplexMeeting',
      data:{
        mid:this.data.mid
      },
      method:'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function(res){
        console.log(res.data);
        this.setData({
          num:res.data.number,
          meetingname:res.data.meeting.name,
          topic:res.data.meeting.topic,
          time:res.data.meeting.time,
          place:res.data.meeting.place,
          state:res.data.meeting.state,
          files:res.data.meeting.fileUrl,
          content:res.data.meeting.content,
          people:[]
        });
        var name='';
        var TOF=0;
        for(var i=0;i<this.data.num;i++){
          //res.data.peoplei...
        }
      }
    })
  },

  //testing functions
  printPeople: function(){
    console.log(this.data.people);
  },
  resetPeople:function(){
    this.data.people=[];
    this.data.people.push({name:'Ronn',TOF:1});
    this.data.people.push({name:'George',TOF:0});
    console.log(this.data.people);
  }
})