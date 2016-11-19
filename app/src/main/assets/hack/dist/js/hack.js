$(function(){
  var masterKey = 'nQQC7mWauJvWKSzImNg4tmNf03M=';
  var deviceIds = [4069466, 4069464, 4069463, 4069462];
  var sensorIds = [4069458, 4069457, 4069456];
  var sensors = [
                  {
                      "errno": 0,
                      "data": {
                          "protocol": "MQTT",
                          "other": {
                              "version": "1.0"
                          },
                          "create_time": "2016-11-17 22:03:45",
                          "keys": [
                              {
                                  "title": "hongwai",
                                  "key": "wis7d=3o9P78Hx72dWpFA4Zcgn8="
                              }
                          ],
                          "online": false,
                          "id": "4069458",
                          "auth_info": "1237",
                          "title": "红外传感器",
                          "desc": "热感",
                          "tags": [
                              "SENSOR"
                          ]
                      },
                      "error": "succ"
                  },
                  {
                    "errno": 0,
                    "data": {
                        "other": {
                            "version": "1.0"
                        },
                        "protocol": "MQTT",
                        "create_time": "2016-11-17 22:03:01",
                        "keys": [
                            {
                                "title": "wendutoken",
                                "key": "eCvU2ym9GJHN7Gnvs7Hf9XkJf2Y="
                            }
                        ],
                        "online": false,
                        "auth_info": "1236",
                        "id": "4069457",
                        "datastreams": [
                            {
                                "create_time": "2016-11-17 22:25:25",
                                "id": "humitity",
                                "uuid": "f6047182-5719-4fe0-b585-963e03a0aaa8"
                            },
                            {
                                "create_time": "2016-11-17 22:25:25",
                                "id": "temperature",
                                "uuid": "896f390f-5bcc-458b-a0ff-5f449cd063a1"
                            }
                        ],
                        "title": "温度传感器",
                        "desc": "温度",
                        "tags": [
                            "SENSOR"
                        ]
                    },
                    "error": "succ"
                  },
                  {
                    "errno": 0,
                    "data": {
                        "protocol": "MQTT",
                        "other": {
                            "version": "1.0"
                        },
                        "create_time": "2016-11-17 22:02:44",
                        "online": false,
                        "id": "4069456",
                        "auth_info": "1235",
                        "title": "声音传感器",
                        "desc": "声音",
                        "tags": [
                            "SENSOR"
                        ]
                    },
                    "error": "succ"
                  }
                ];
  var devices = [
                  {
                      "errno": 0,
                      "data": {
                          "other": {
                              "version": "1.0"
                          },
                          "protocol": "MQTT",
                          "create_time": "2016-11-17 22:07:00",
                          "online": false,
                          "auth_info": "1242",
                          "id": "4069466",
                          "title": "风扇",
                          "desc": "风扇",
                          "tags": [
                              "DEVICE"
                          ]
                      },
                      "error": "succ"
                  },
                  {
                      "errno": 0,
                      "data": {
                          "other": {
                              "version": "1.0"
                          },
                          "protocol": "MQTT",
                          "create_time": "2016-11-17 22:06:15",
                          "keys": [
                              {
                                  "title": "light",
                                  "key": "Fotw0j110jFSsL7PqdXu7vC5Gpc="
                              }
                          ],
                          "online": false,
                          "auth_info": "1240",
                          "id": "4069464",
                          "datastreams": [
                              {
                                  "create_time": "2016-11-18 14:12:04",
                                  "id": "switch",
                                  "uuid": "c42633ac-145b-5f69-a539-7fdb63b1c4ec"
                              },
                              {
                                  "create_time": "2016-11-17 23:46:27",
                                  "id": "Light_intensity",
                                  "uuid": "35782de9-2f08-4232-bdca-33ec63f90260"
                              }
                          ],
                          "title": "Light",
                          "desc": "智能灯",
                          "tags": [
                              "DEVICE"
                          ]
                      },
                      "error": "succ"
                  },
                  {
                      "errno": 0,
                      "data": {
                          "protocol": "MQTT",
                          "other": {
                              "version": "1.0"
                          },
                          "create_time": "2016-11-17 22:06:10",
                          "online": false,
                          "id": "4069463",
                          "auth_info": "1239",
                          "datastreams": [
                              {
                                  "create_time": "2016-11-18 14:12:20",
                                  "id": "switch",
                                  "uuid": "79c3145f-c6d3-5463-a8fe-a4d494fdcfb4"
                              }
                          ],
                          "title": "Light",
                          "desc": "智能灯",
                          "tags": [
                              "DEVICE"
                          ]
                      },
                      "error": "succ"
                  },
                  {
                      "errno": 0,
                      "data": {
                          "protocol": "MQTT",
                          "other": {
                              "version": "1.0"
                          },
                          "create_time": "2016-11-17 22:06:05",
                          "online": false,
                          "id": "4069462",
                          "auth_info": "1238",
                          "datastreams": [
                              {
                                  "create_time": "2016-11-18 14:12:44",
                                  "id": "switch",
                                  "uuid": "e03aa06d-6773-51c5-aa16-9e75fa8167bf"
                              }
                          ],
                          "title": "Light",
                          "desc": "智能灯",
                          "tags": [
                              "DEVICE"
                          ]
                      },
                      "error": "succ"
                  }
                ];

  var ruleJson = {
    'rulename':'',
    'condition':{
      'device_id':'',
      'device_function':'',
      'type':'',
      'value':''
    },
    'action':{
      'device_id':'',
      'value':''
    }
  };

  $("[name='device-switch']").bootstrapSwitch();
  function switchEvent(){
    $("[name='device-switch']").on('switchChange.bootstrapSwitch', function (e, state) {
      if(state){
              ruleJson.action.value = 1;
        }else{
          ruleJson.action.value = 0;
      }
    });
  }
  switchEvent();
  $('input[type="radio"].flat-red').iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue'
  });

  $('input[type="radio"]').click(function(){
    ruleJson.rule.type = $("input[type='radio']:checked").val();
  });

  var bodyHeight = document.body.clientHeight;
  var htmlHeight = window.screen.height;
  var divHeight = htmlHeight - bodyHeight;
  $('body').append("<div id='back' style='height:" + divHeight + "px; width:100%'></div>");
  $('body').delegate('#back', 'click', function(){
    $('.content-wrapper:not(.isdisplay)').addClass('isdisplay');
    $('#add-device').removeClass('isdisplay');
  });

  function appendDeviceItem(deviceInfo){
    if (deviceInfo.data.tags[0] == 'DEVICE') {
      type = 'device';
    }else if(deviceInfo.data.tags[0] == 'SENSOR'){
      type = 'sensor';
    }
    var li = '<li class="' + type + '" name="' + deviceInfo.data.id + '"><a href="#">' + deviceInfo.data.title + '</a></li>';
    if (deviceInfo.data.tags[0] == 'DEVICE') {
      $('#device-list').append(li);
    }else if(deviceInfo.data.tags[0] == 'SENSOR'){
      $('#sensor-list').append(li);
    }
  }

  $('#add-rule').click(function(){
    $('#add-device').addClass('isdisplay');
    $('#choose-sensor').removeClass('isdisplay');
    $('#sensor-list').empty();
    // for (var i = 0; i < sensors.length; i++) {
    //   var li = '<li class="sensor" name="' + sensors[i].data.id + '"><a href="#">' + sensors[i].data.title + '</a></li>';
    //   $('#sensor-list').append(li);
    // }
    for (var i = 0; i < sensorIds.length; i++) {
      window.GetAndroid.queryDevices(sensorIds[i]);
    }
    ruleJson.rulename = $('#rulename').val();
  });
  $('#add-action').click(function(){
    $('#add-device').addClass('isdisplay');
    $('#choose-device').removeClass('isdisplay');
    $('#device-list').empty();
    // for (var i = 0; i < devices.length; i++) {
    //   var li = '<li class="device" name="' + devices[i].data.id + '"><a href="#">' + devices[i].data.title + '</a></li>';
    //   $('#device-list').append(li);
    // }
    for (var i = 0; i < deviceIds.length; i++) {
      window.GetAndroid.queryDevices(deviceIds[i]);
    }
    ruleJson.rulename = $('#rulename').val();
  });
  $('#sensor-list').delegate('.sensor', 'click', function(e){
    $('#choose-sensor').addClass('isdisplay');
    $('#set-rule').removeClass('isdisplay');
    ruleJson.condition.device_id = $(e.target).parent().attr('name');
  });
  $('#device-list').delegate('.device', 'click', function(e){
    var name = $(e.target).text();
    var id  = $(e.target).parent().attr('name');
    $('.operate-device').find('label').text(name).attr('name', id);
    $('#choose-device').addClass('isdisplay');
    $('#no-off-device').removeClass('isdisplay');
    ruleJson.action.device_id = $(e.target).parent().attr('name');
  });

  $('#ruleok').click(function(){
    ruleJson.condition.value = $('#threshold').val();
  });
});