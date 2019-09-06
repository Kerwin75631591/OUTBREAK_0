// pages/meetingdetail/meetingdetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    mid:0,
    //saved in data
    num:0,//number of invited people
  
    meetingname:'',
    topic:'',
    time:'',
    place:'',
    state:0,
    files:null,
    content:'',
    //saved in data.peoplei(0----n-1)
    //name saved in data.people.name,TOF saved in data.people.TOF
    people:'',
    TOF:0
    //people:[{name:'Harry',TOF:1,email:'Harry@owl.com'},{name:'Sirius',TOF:0,email:'Sirius@owl.com'}]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app = getApp();
    this.setData({
      email: app.globalData.email,
      mid:options.mid
    });
    var that=this;
    wx.request({
      url: 'http://www.outbreak.xyz:443/ComplexMeeting',
      data:{
        mid:that.data.mid
      },
      method:"GET",
      header: {
        'content-type': "applicaton/json"
      },
      success: function(res){
        /*test code        
        console.log(res.data);
        console.log(res.data.number);
        console.log(res.data.meeting.name);
        console.log(res.data.meeting.topic);
        console.log(res.data.meeting.time);
        console.log(res.data.meeting.place);
        console.log(res.data.meeting.state);
        console.log(res.data.meeting.fileUrl);
        console.log(res.data.meeting.content);
        console.log(res.data.list);*/
        
        that.setData({
          num:res.data.number,
          meetingname:res.data.meeting.name,
          topic:res.data.meeting.topic,
          time:res.data.meeting.time,
          place:res.data.meeting.place,
          state:res.data.meeting.state,
          files:res.data.meeting.fileUrl,
          content:res.data.meeting.content,
          people:res.data.list
        });
        for(var i=0;i<num;i++){
          if(that.data.email==people[i].email){
            that.data.TOF=people[i].TOF;
            break;
          }
        }
        console.log(that.data);
      }
    });
    //console.log(this.data);
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

  //functions
  download: function(){
    wx.downloadFile({
      url:this.data.files,
      success: function(res){
        console.log('success');
      }
    });
  },
  setTOF:function(){
    var that=this;
    wx.showModal({
      title: '确认参加会议',
      content: '确认后将无法更改，您是否确认参加会议',
      success:function(res){
        if(res.confirm){
          wx.request({
            url: 'http://www.outbreak.xyz:443/setTOF',
            data:{
              email:that.data.email,
              mid:that.data.mid
            },
            method:'GET',
            header: {
              'content-type': 'application/json'
            },
            success:function(res){
              var judge=res.data.judge;
              if(judge){
                wx.showModal({
                  title: '确认参加会议',
                  content: '确认成功',
                })
              }else{
                wx.showModal({
                  title: '确认参加会议',
                  content: '确认失败',
                })
              }
            }
          })
        }
      }
    })
  },
  showQr:function(){
    var that=this;
    wx.navigateTo({
      url: '/pages/qrcode/qr?email='+that.data.email+'&mid='+that.data.mid,
    });
  },

  //testing functions
  printPeople: function(){
    console.log(this.data.people);
  },
  resetPeople:function(){
    this.data.people=[];
    this.data.people.push({name:'Ronn',TOF:'参加',email:'Ronn@owl.com'});
    this.data.people.push({name:'George',TOF:'未确定',email:'George@owl.com'});
    console.log(this.data.people);
  }
})