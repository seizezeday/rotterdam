    $(document).ready(function(){
//        $(".hide_tabs").hide();  // Спрятать все табы пока водитель не введет настройки
                        
        //Получение значений текущего дня недели и запись на таб время
                        $.ajax({  
                        type: "POST",
                        url: "api/home",
                        datatype: "json",
                        contentType: "application/json; charset=utf-8",
                        statusCode: {
                        200: function (data) {
                        $(".username").append(data.firstname);
                        $("#userdate").append(data.date);   
//                        $(".time_year option").append(data.currentYear);   
                        $(".time_date").eq(0).append(data.weekList[0]);   
                        $(".time_date").eq(1).append(data.weekList[1]);   
                        $(".time_date").eq(2).append(data.weekList[2]);   
                        $(".time_date").eq(3).append(data.weekList[3]);   
                        $(".time_date").eq(4).append(data.weekList[4]);   
                        $(".time_date").eq(5).append(data.weekList[5]);   
                        $(".time_date").eq(6).append(data.weekList[6]);
//                        $(".time_mount :nth-child("+data.currentMonth+")").attr("selected", "selected");
//                        $(".time_week :nth-child("+data.currentWeekNumber+")").attr("selected", "selected");
            }     
                        }
                    });
        // Редирект на index.html после нажатия кнопки logout
        $('#logout').click(function(){
             $.ajax({
              type: "POST",
              url: "api/logout",
              contentType: "application/json; charset=utf-8",
              dataType: "json",
              statusCode: {
                  200: function () {
                      location.href='index.html';
                  }
              }
          });
        });
        $(".time_monday_type_day").change(type_day_monday);
        
            function type_day_monday(){
          if($(".time_monday_type_day").val() == 2){
                $(".add_row_monday button").hide();
                $(".add_row_monday button").attr("disabled","disabled");
                $(".time_monday_start").attr("disabled","disabled");
                $(".time_monday_start").val('00:00')
                $(".time_monday_end").attr("disabled","disabled");
                $(".time_monday_end").val('00:00')
                $(".time_monday_rest").attr("disabled","disabled");
                $(".time_monday_rest").val('00:00')
              } 
            else {
                $(".add_row_monday button").show();
                $(".add_row_monday button").removeAttr("disabled","disabled");
                $(".time_monday_start").removeAttr("disabled","disabled");
//                $(".time_monday_start").val('')
                $(".time_monday_end").removeAttr("disabled","disabled");
//                $(".time_monday_end").val('')
                $(".time_monday_rest").removeAttr("disabled","disabled");
//                $(".time_monday_rest").val('') 
              }
            };   
        $(".time_tuesday_type_day").change(type_day_tuesday);
            function type_day_tuesday(){
          if($(".time_tuesday_type_day").val() == 2){
                $(".add_row_tuesday button").hide();
                $(".add_row_tuesday button").attr("disabled","disabled");
                $(".time_tuesday_start").attr("disabled","disabled");
                $(".time_tuesday_start").val('00:00')
                $(".time_tuesday_end").attr("disabled","disabled");
                $(".time_tuesday_end").val('00:00')
                $(".time_tuesday_rest").attr("disabled","disabled");
                $(".time_tuesday_rest").val('00:00')
              } 
            else {
                $(".add_row_tuesday button").show();
                $(".add_row_tuesday button").removeAttr("disabled","disabled");
                $(".time_tuesday_start").removeAttr("disabled","disabled");
//                $(".time_tuesday_start").val('')
                $(".time_tuesday_end").removeAttr("disabled","disabled");
//                $(".time_tuesday_end").val('')
                $(".time_tuesday_rest").removeAttr("disabled","disabled");
//                $(".time_tuesday_rest").val('') 
              }
            };      
        $(".time_wednesday_type_day").change(type_day_wednesday);
            function type_day_wednesday(){
          if($(".time_wednesday_type_day").val() == 2){
                $(".add_row_wednesday button").hide();
                $(".add_row_wednesday button").attr("disabled","disabled");
                $(".time_wednesday_start").attr("disabled","disabled");
                $(".time_wednesday_start").val('00:00')
                $(".time_wednesday_end").attr("disabled","disabled");
                $(".time_wednesday_end").val('00:00')
                $(".time_wednesday_rest").attr("disabled","disabled");
                $(".time_wednesday_rest").val('00:00')
              } 
            else {
                $(".add_row_wednesday button").show();
                $(".add_row_wednesday button").removeAttr("disabled","disabled");
                $(".time_wednesday_start").removeAttr("disabled","disabled");
//                $(".time_wednesday_start").val('')
                $(".time_wednesday_end").removeAttr("disabled","disabled");
//                $(".time_wednesday_end").val('')
                $(".time_wednesday_rest").removeAttr("disabled","disabled");
//                $(".time_wednesday_rest").val('') 
              }
            };   
        $(".time_thursday_type_day").change(type_day_thursday)
            function type_day_thursday(){
          if($(".time_thursday_type_day").val() == 2){
                $(".add_row_thursday button").hide();
                $(".add_row_thursday button").attr("disabled","disabled");
                $(".time_thursday_start").attr("disabled","disabled");
                $(".time_thursday_start").val('00:00')
                $(".time_thursday_end").attr("disabled","disabled");
                $(".time_thursday_end").val('00:00')
                $(".time_thursday_rest").attr("disabled","disabled");
                $(".time_thursday_rest").val('00:00')
              } 
            else {
                $(".add_row_thursday button").show();
                $(".add_row_thursday button").removeAttr("disabled","disabled");
                $(".time_thursday_start").removeAttr("disabled","disabled");
//                $(".time_thursday_start").val('')
                $(".time_thursday_end").removeAttr("disabled","disabled");
//                $(".time_thursday_end").val('')
                $(".time_thursday_rest").removeAttr("disabled","disabled");
//                $(".time_thursday_rest").val('') 
              }
            };  
        
        $(".time_friday_type_day").change(type_day_friday)
            function type_day_friday(){
          if($(".time_friday_type_day").val() == 2){
                $(".add_row_friday button").hide();
                $(".add_row_friday button").attr("disabled","disabled");
                $(".time_friday_start").attr("disabled","disabled");
                $(".time_friday_start").val('00:00')
                $(".time_friday_end").attr("disabled","disabled");
                $(".time_friday_end").val('00:00')
                $(".time_friday_rest").attr("disabled","disabled");
                $(".time_friday_rest").val('00:00')
              } 
            else {
                $(".add_row_friday button").show();
                $(".add_row_friday button").removeAttr("disabled","disabled");
                $(".time_friday_start").removeAttr("disabled","disabled");
//                $(".time_friday_start").val('')
                $(".time_friday_end").removeAttr("disabled","disabled");
//                $(".time_friday_end").val('')
                $(".time_friday_rest").removeAttr("disabled","disabled");
//                $(".time_friday_rest").val('') 
              }
            }; 
        $(".time_saturday_type_day").change(type_day_saturday);
            function type_day_saturday(){
          if($(".time_saturday_type_day").val() == 2){
                $(".add_row_saturday button").hide();
                $(".add_row_saturday button").attr("disabled","disabled");
                $(".time_saturday_start").attr("disabled","disabled");
                $(".time_saturday_start").val('00:00')
                $(".time_saturday_end").attr("disabled","disabled");
                $(".time_saturday_end").val('00:00')
                $(".time_saturday_rest").attr("disabled","disabled");
                $(".time_saturday_rest").val('00:00')
              } 
            else {
                $(".add_row_saturday button").show();
                $(".add_row_saturday button").removeAttr("disabled","disabled");
                $(".time_saturday_start").removeAttr("disabled","disabled");
//                $(".time_saturday_start").val('')
                $(".time_saturday_end").removeAttr("disabled","disabled");
//                $(".time_saturday_end").val('')
                $(".time_saturday_rest").removeAttr("disabled","disabled");
//                $(".time_saturday_rest").val('') 
              }
            };       
        $(".time_sunday_type_day").change(type_day_sunday);
            function type_day_sunday(){
          if($(".time_sunday_type_day").val() == 2){
                $(".add_row_sunday button").hide();
                $(".add_row_sunday button").attr("disabled","disabled");
                $(".time_sunday_start").attr("disabled","disabled");
                $(".time_sunday_start").val('00:00')
                $(".time_sunday_end").attr("disabled","disabled");
                $(".time_sunday_end").val('00:00')
                $(".time_sunday_rest").attr("disabled","disabled");
                $(".time_sunday_rest").val('00:00')
              } 
            else {
                $(".add_row_sunday button").show();
                $(".add_row_sunday button").removeAttr("disabled","disabled");
                $(".time_sunday_start").removeAttr("disabled","disabled");
//                $(".time_sunday_start").val('')
                $(".time_sunday_end").removeAttr("disabled","disabled");
//                $(".time_sunday_end").val('')
                $(".time_sunday_rest").removeAttr("disabled","disabled");
//                $(".time_sunday_rest").val('') 
              }
            };
        
//        $(".time_monday_type_day option:nth-child(1):selected").change({
//            $(".add_row_monday button").attr("disabled","disabled");
//        })
//        $(".time_monday_type_day option:nth-child(2):selected").change({
//                 $(".add_row_monday button").removeAttr("disabled","disabled");
//        }
        
//        $(".time_monday_type_day").change(function(){
////          $(".add_row_monday button").removeAttr("disabled","disabled");
//          var tape_day_moyday = $(".time_monday_type_day option:selected").text()
//           if (tape_day_moyday = 'Weekend'){
//            $(".add_row_monday button").attr("disabled","disabled");
//
////          $('.add_row_monday button').prop('disabled', !(checkval == '1' || checkval == '2'));
////          if (tape_day_moyday = 'Weekend'){
////          $(".add_row_monday button").attr("disabled","disabled");
////          }
//            else {
////                if (tape_day_moyday = 'Work'){
//             $(".add_row_monday button").removeAttr("disabled","disabled");
//            };
//            }; 
//        });

        
        $('.add_row_monday').bind('click',function(){
         var time_day_add = tryOne("monday");
         $(this).parent().after(time_day_add);
         $('.time_tab_del').bind('click',function(){
         $($(this).parents().get(2)).remove();
         });
         });

        $('.add_row_tuesday').click(function(){
            var time_day_add = tryOne("tuesday");
            $(this).parent().after(time_day_add);
            $('.time_tab_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });

        $('.add_row_wednesday').click(function(){
            var time_day_add = tryOne("wednesday");
            $(this).parent().after(time_day_add);
            $('.time_tab_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_thursday').click(function(){
            var time_day_add = tryOne("thursday");
            $(this).parent().after(time_day_add);
            $('.time_tab_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_friday').click(function(){
            var time_day_add = tryOne("friday");
            $(this).parent().after(time_day_add);
            $('.time_tab_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_saturday').click(function(){
            var time_day_add = tryOne("saturday");
            $(this).parent().after(time_day_add);
            $('.time_tab_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_sunday').click(function(){
            var time_day_add = tryOne("sunday");
            $(this).parent().after(time_day_add);
            $('.time_tab_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });

    function tryOne(data) {
        var time_day_add = '<div class="time_tab_row">' +
            '<div class="col-md-4 margin_bottom_10 ">' +
            '<div class="col-md-4 font_size_18 time_date"></div>' +
            '<div class="col-md-4 font_size_18 time_day"></div>' +
            '<div class="col-md-4">' +
            '<input type="text" class="form-control time_' + data +'_start" placeholder="Start"></div></div>' +
            '<div class="col-md-4 margin_bottom_10"><div class="col-md-6"><input type="text" class="form-control time_' + data +'_end" placeholder="End"></div>' +
            '<div class="col-md-6"><input type="text" class="form-control time_' + data +'_rest" placeholder="Rest" number-mask=""></div>' +
            '</div>'+
            '<div class="col-md-4 margin_bottom_10">'+
//            '<div class="col-md-4">' +
//            '<select class="form-control time_' + data +'_ride_type">' +
//            '<option>1</option>' +
//            '<option>2</option>' +
//            '</select>'+
//            '</div>' +
            '<div class="col-md-4"><button type="button" class="btn btn-danger btn-block time_tab_del">Delete</button></div></div></div>'
        return time_day_add;
    };   
//        function tryOne(data) {
////        var n = 1;    
//        var time_day_add = '<div class="time_tab_row">' +
//            '<div class="col-md-4 margin_bottom_10 ">' +
//            '<div class="col-md-4 font_size_18 time_date"></div>' +
//            '<div class="col-md-4 font_size_18 time_day"></div>' +
//            '<div class="col-md-4 form-group">'+
//            '<div>'+
//            '<input type="text" class="form-control" size="5" ng-model="selectedTimeAsNumberStart" data-time-format="HH:mm" data-time-type="number" data-autoclose="1" name="time_start_monday" placeholder="Start" bs-timepicker>'+
//      '</div>'+      
//       '</div>'+
//        '<div class="col-md-4 form-group">'+
//      '<div class="col-md-6">'+
//       '<div >'+
//        '<input type="text" class="form-control time_' + data +'_end" size="5" ng-model="selectedTimeAsNumberTuesdayEnd" data-time-format="HH:mm" data-time-type="number" data-autoclose="1" name="time2" placeholder="End" bs-timepicker>'+
//      '</div>'+
//      '</div>'+
//        '<div class="col-md-6"><input type="text" class="form-control time_tuesday_rest" placeholder="Rest"></div>'+
//       '</div>'+
//            '<div class="col-md-6"><input type="text" class="form-control time_' + data +'_rest" placeholder="Rest"></div>' +
//            '</div><div class="col-md-4 margin_bottom_10"><div class="col-md-4">' +
//            '<select class="form-control time_' + data +'_ride_type">' +
//            '<option>1</option>' +
//            '<option>2</option>' +
//            '</select></div>' +
//            '<div class="col-md-4 "><button type="button" class="btn btn-danger btn-block time_tab_del">Delete</button></div>'+
//        '</div>'+
//        '</div>'
//        return time_day_add;
//    };
// получения новых дат 
        $('#date_submit').click(function date_submit(){
                var selected_date  = {
                currentDate: $("#time_week_date").val()
            };
             $('.time_date').empty();
            time_date(selected_date);
            readSettings();
             }); 
            function time_date(data) {
            $.ajax({
                type: "POST",
                url: "api/week",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function (data) {
                        $(".time_date").empty();
                        $(".time_date").eq(0).append(data.weekList[0]);
                        $(".time_date").eq(1).append(data.weekList[1]);
                        $(".time_date").eq(2).append(data.weekList[2]);
                        $(".time_date").eq(3).append(data.weekList[3]);
                        $(".time_date").eq(4).append(data.weekList[4]);
                        $(".time_date").eq(5).append(data.weekList[5]);
                        $(".time_date").eq(6).append(data.weekList[6]);
                    }
                }
            });
        };
        //        Time tab end
        //            compensation time start
            $("#compensation_time").keyup(function compensation_hide_btn(){
           var compensation=$("#compensation_time").val();
     if (compensation !=="") {
        $("#compensation_btn").removeAttr("disabled");
    }
        else {
                $("#compensation_btn").attr("disabled","disabled");
        }                  
                         });
        
               $('#compensation_btn').click(function compensation(){
                var compensation = {
                    use_time_for_time: $('#compensation_time').val()   
                };

                  compensation_json(compensation);
               });
            function compensation_json(data) {
            $.ajax({
                type: "POST",
                url: "api/usetimefortime",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function () {
                        alert("Success...");
                    }
                }
            });
        };
        //            compensation time end
        $("#payment_time_field").keyup(function payment_hide_btn(){
           var payment = $("#payment_time_field").val();
           var paymant_avaliable = $('#payment_time_avaliable').text();     
     if (payment !=="" && payment <= paymant_avaliable) {
        $("#payment_time_btn").removeAttr("disabled");
    }
        else {
                $("#payment_time_btn").attr("disabled","disabled");
        }                  
                         });
   $('#payment_time_btn').click(function payment_time(){
                var paymenttime = {
                    avl_time_for_pay: $('#payment_time_field').val()   
                };
                  alert(paymenttime.avl_time_for_pay);
                  payment_json(avl_time_for_pay);
               });
            function payment_json(data) {
            $.ajax({
                type: "POST",
                url: "api/paymenttime",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function () {
                        alert("Success...");
                    }
                }
            });
        };
//        Time tab start
        $('#date_save').click(function date_save(){
            var s_date  = {
                Monday :    date_to_save("monday", 0),
                Tuesday :   date_to_save("tuesday", date_to_save("monday", 0).length-1),
                Wednesday : date_to_save("wednesday", date_to_save("monday", 0).length + date_to_save("tuesday", 0).length - 2),
                Thursday :  date_to_save("thursday", date_to_save("monday", 0).length + date_to_save("tuesday", 0).length + date_to_save("wednesday", 0).length - 3),
                Friday :    date_to_save("friday", date_to_save("monday", 0).length + date_to_save("tuesday", 0).length + date_to_save("wednesday", 0).length + date_to_save("thursday", 0).length - 4),
                Saturday :  date_to_save("saturday", date_to_save("monday", 0).length + date_to_save("tuesday", 0).length + date_to_save("wednesday", 0).length + date_to_save("thursday", 0).length + date_to_save("friday", 0).length - 5),
                Sunday :    date_to_save("sunday", date_to_save("monday", 0).length + date_to_save("tuesday", 0).length + date_to_save("wednesday", 0).length + date_to_save("thursday", 0).length + date_to_save("friday", 0).length + date_to_save("saturday", 0).length - 6)

            };
            time_save(s_date);
        });
        function time_save(data) {
            $.ajax({
                type: "POST",
                url: "api/time",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function (data) {
                        alert("good")
                        $('#total_mon_fri').text(data.totalTime)
                        $('#total_time_monday').text(data.days.Monday)
                        $('#total_time_tuesday').text(data.days.Tuesday)
                        $('#total_time_wednesday').text(data.days.Wednesday)
                        $('#total_time_thursday').text(data.days.Thursday)
                        $('#total_time_friday').text(data.days.Friday)
                        $('#total_time_saturday').text(data.days.Saturday)
                        $('#total_saturday').text(data.days.Saturday)
                        $('#total_time_sunday').text(data.days.Sunday)
                        $('#total_sunday').text(data.days.Sunday)
                        $('#total_over_hovers').text(data.overTime)
                    }
                }
            });

            //loadTimeTab();

        }

        loadTimeTab();

        function loadTimeTab(){
            $.ajax({
                type: "POST",
                url: "api/timeTab",
                data: JSON.stringify({currentDate: $("#time_week_date").val()}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function (data) {
//                       alert('json ok');
                        //start
                       $(".time_monday_start").val(data.days.Monday.workHours[0].startWorkingTime);
                       $(".time_tuesday_start").val(data.days.Tuesday.workHours[0].startWorkingTime);
                    $(".time_wednesday_start").val(data.days.Wednesday.workHours[0].startWorkingTime);
                       $(".time_thursday_start").val(data.days.Thursday.workHours[0].startWorkingTime);
                       $(".time_friday_start").val(data.days.Friday.workHours[0].startWorkingTime);
                       $(".time_saturday_start").val(data.days.Saturday.workHours[0].startWorkingTime);
                       $(".time_sunday_start").val(data.days.Sunday.workHours[0].startWorkingTime);
                        //end
                       $(".time_monday_end").val(data.days.Monday.workHours[0].endWorkingTime);
                       $(".time_tuesday_end").val(data.days.Tuesday.workHours[0].endWorkingTime);
                       $(".time_wednesday_end").val(data.days.Wednesday.workHours[0].endWorkingTime);
                       $(".time_thursday_end").val(data.days.Thursday.workHours[0].endWorkingTime);
                       $(".time_friday_end").val(data.days.Friday.workHours[0].endWorkingTime);
                       $(".time_saturday_end").val(data.days.Saturday.workHours[0].endWorkingTime);
                       $(".time_sunday_end").val(data.days.Sunday.workHours[0].endWorkingTime);
                        //rest
                       $(".time_monday_rest").val(data.days.Monday.workHours[0].restTime);
                       $(".time_tuesday_rest").val(data.days.Tuesday.workHours[0].restTime);
                       $(".time_wednesday_rest").val(data.days.Wednesday.workHours[0].restTime);
                       $(".time_thursday_rest").val(data.days.Thursday.workHours[0].restTime);
                       $(".time_friday_rest").val(data.days.Friday.workHours[0].restTime);
                       $(".time_saturday_rest").val(data.days.Saturday.workHours[0].restTime);
                       $(".time_sunday_rest").val(data.days.Sunday.workHours[0].restTime);
                        //dayTape
                       $(".time_monday_type_day").val(data.days.Monday.workHours[0].dayType);
                       $(".time_tuesday_type_day").val(data.days.Tuesday.workHours[0].dayType);
                       $(".time_wednesday_type_day").val(data.days.Wednesday.workHours[0].dayType);
                       $(".time_thursday_type_day").val(data.days.Thursday.workHours[0].dayType);
                       $(".time_friday_type_day").val(data.days.Friday.workHours[0].dayType);
                       $(".time_saturday_type_day").val(data.days.Saturday.workHours[0].dayType);
                       $(".time_sunday_type_day").val(data.days.Sunday.workHours[0].dayType); 
                       type_day_monday();
                       type_day_tuesday();
                       type_day_wednesday();
                       type_day_thursday();
                       type_day_friday();
                       type_day_saturday();
                       type_day_sunday();
                       
                    }
                }
            });
        }

        function date_to_save(day, number){
            var arr = [];
            arr[arr.length] ={
                date : $(".time_date").eq(Number(number)).text()
            };
            $(".time_"+ day +"_start").each(function(data){
                arr[arr.length] ={
                    startWorkingTime :  $(".time_"+ day +"_start").eq(data).val(),
                    endWorkingTime :    $(".time_"+ day +"_end").eq(data).val(),
                    restTime :          $(".time_"+ day +"_rest").eq(data).val(),
                    dayType :           $(".time_"+ day +"_type_day").val()
                };
            });
            return arr;
        }
        //        Time tab end
        //            Settings tab start 

        $('#setting_save').click(function save_btn(){
//            var monday_hours = $("#settings_monday").val();
//            var tuesday_hours = $("#settings_tuesday").val();
//            var wednesday_hours = $("#settings_wednesday").val();
//            var thursday_hours = $("#settings_thursday").val();
//            var friday_hours = $("#settings_friday").val();
//            var saturday_hours = $("#settings_saturday").val();
//            var sunday_hours = $("#settings_sunday").val();          
//            alert(monday_hours);
            var selected_settings  = {
                currentDate: $("#time_week_date").val(),
            monday_hours : $("#settings_monday").val(), 
            tuesday_hours : $("#settings_tuesday").val(), 
            wednesday_hours : $("#settings_wednesday").val(), 
            thursday_hours : $("#settings_thursday").val(), 
            friday_hours : $("#settings_friday").val(), 
            saturday_hours : $("#settings_saturday").val(), 
            sunday_hours : $("#settings_sunday").val(),                 
           	suterday_compensation:  $("#suterday_compensation").is(':checked'),
            show_compensation:  $("#show_compensation").is(':checked'),
           	allow_suterday_compensation:  $("#allow_suterday_compensation").is(':checked'),
//            select1: $("#select1 option:selected").text(),
//            select2: $("#select2 option:selected").text(),
//            select3: $("#select3 option:selected").text(),
//            check3:  $("#check3").is(':checked'),
//            check4:  $("#check4").is(':checked'),
//            select4: $("#select4 option:selected").text()
        };
//            alert(selected_settings.monday_hours);
       settings(selected_settings);
         }); 
        function settings(data) {
        $.ajax({
            type: "POST",
            url: "api/settings",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
                200: function () {
                    $(".hide_tabs").css("display","block");
                    alert("Success...");
                }
            }
        });
    };
                    $("#settings_monday,#settings_tuesday,#settings_wednesday,#settings_thursday,#settings_friday,#settings_saturday,#settings_sunday").keyup(settings_day_hours);
                    $("#settings_monday,#settings_tuesday,#settings_wednesday,#settings_thursday,#settings_friday,#settings_saturday,#settings_sunday").focusout(settings_day_hours)
    $("#show_compensation").click(settings_compensation);                    
                    function settings_day_hours(){
//                        alert('ok')
                    var monday_hours = $("#settings_monday").val();
                    var tuesday_hours = $("#settings_tuesday").val();
                    var wednesday_hours = $("#settings_wednesday").val();
                    var thursday_hours = $("#settings_thursday").val();
                    var friday_hours = $("#settings_friday").val();
                    var saturday_hours = $("#settings_saturday").val();
                    var sunday_hours = $("#settings_sunday").val();
                    var show_compensation = $("#show_compensation").is(':checked');
                    var allow_suterday_compensation = $("#allow_suterday_compensation").is(':checked');
                    if (monday_hours !=="" && tuesday_hours !=="" && wednesday_hours !=="" && thursday_hours !=="" && friday_hours  !=="") {
                    //$(".hide_tabs").css("display","block"); // Показать табы если введены поля времени
; // Показать табы если введены поля времени
                    $('#setting_save').removeAttr("disabled","disabled")
                    //$('#setting_save').click();
                    }
                        else {
                    $(".hide_tabs").css("display","none");; 
                    $('#setting_save').attr("disabled","disabled");
                    }
                    };
        function settings_compensation(){
          if($("#show_compensation").is(':checked')){
              $("#compensation_tab").css("display","block");
          } else {
              $("#compensation_tab").css("display","none");
          }
        }
        //            Settings tab end 

       //            Tab time multitrip start 
        $('#multitrip_save').click(function multitrip(day){
        var multitripDateStart = $('#time_week_date_modal1').val();
        var multitripTimeStart = $('#time_start_modal').val();  
        var multitripDateEnd = $('#time_week_date_modal2').val();
        var multitripTimeEnd = $('#time_end_modal').val(); 
            
        $('#time_week_date_modal1').val('');
        $('#time_start_modal').val('');  
        $('#time_week_date_modal2').val('');
        $('#time_end_modal').val('');
            
        $('#modal_close').click();  
            
        $( ".time_tab div:contains("+multitripDateStart+")").children(3).addClass('modal_date1_start');
        $( ".modal_date1_start:nth-child(3) input").val(multitripTimeStart);   
        $( ".modal_date1_start:nth-child(3) input.ng-pristine").attr('disabled','disabled');
            
        $( ".time_tab div:contains("+multitripDateStart+")").next().addClass('modal_parent_date1_end');         $( ".modal_parent_date1_end:nth-child(2) input.ng-pristine").val('00:00');   
        $( ".modal_parent_date1_end:nth-child(2) input.ng-pristine").attr('disabled','disabled');  

        $( ".time_tab div:contains("+multitripDateEnd+")").children(3).addClass('modal_date2_start');
        $( ".modal_date2_start:nth-child(3) input").val("00:00");
        $( ".modal_date2_start:nth-child(3) input").attr('disabled','disabled');
            
        $( ".time_tab div:contains("+multitripDateEnd+")").next().addClass('modal_parent_date2_end');    
        $( ".modal_parent_date2_end:nth-child(2) input.ng-pristine").val(multitripTimeEnd);   
        $( ".modal_parent_date2_end:nth-child(2) input.ng-pristine").attr('disabled','disabled');
      
        $( ".time_tab div:contains("+multitripDateEnd+")").children(3).removeClass('modal_date1_start');
        $( ".time_tab div:contains("+multitripDateEnd+")").children(3).removeClass('modal_date1_end');           $( ".time_tab div:contains("+multitripDateEnd+")").children(3).removeClass('modal_date2_start');
        $( ".time_tab div:contains("+multitripDateEnd+")").children(3).removeClass('modal_date2_end');
       //            Tab time multitrip end 

    });
        $('.time_monday_start,.time_monday_end,.time_monday_rest').keyup(function(){
//                $(".time_monday_type_day").change(function(){

        var monday_start_str =  $(".time_monday_start").val();
        var monday_start = parseFloat(monday_start_str);
        var monday_end_str =  $(".time_monday_end").val();
        var monday_end = parseFloat(monday_end_str);
        var monday_rest_str =  $(".time_monday_rest").val();
        var monday_rest = parseFloat(monday_rest_str);
        var total_time_monday = monday_end - monday_start - monday_rest;
        console.log(total_time_monday)
        $('#total_time_monday').empty()
        $('#total_time_monday').append(total_time_monday)
    });


        function readSettings() {
            //setting settings tab
            $.ajax({
                type: "POST",
                url: "api/settings/get",
                datatype: "json",
                data: JSON.stringify({currentDate: $("#time_week_date").val()}),
                contentType: "application/json; charset=utf-8",
                statusCode: {
                    200: function (data) {
                        $("#settings_monday").val(data.monday_hours);
                        $("#settings_tuesday").val(data.tuesday_hours);
                        $("#settings_wednesday").val(data.wednesday_hours);
                        $("#settings_thursday").val(data.thursday_hours);
                        $("#settings_friday").val(data.friday_hours);
                        $("#settings_saturday").val(data.saturday_hours);
                        $("#settings_sunday").val(data.sunday_hours);
                        $("#start_period").text(data.startDate);
                        $("#end_period").text(data.endDate);
                        settings_compensation();
                        if (data.monday_hours !=="" && data.tuesday_hours !=="" && data.wednesday_hours !=="" && data.thursday_hours !=="" &&
                            data.friday_hours  !=="" && data.saturday_hours  !=="" && data.sunday_hours !=="" &&
                            data.monday_hours !==null && data.tuesday_hours !==null && data.wednesday_hours !==null && data.thursday_hours !==null &&
                            data.friday_hours  !==null && data.saturday_hours  !==null && data.sunday_hours !==null) {
                            $(".hide_tabs").css("display", "block");
                            $('#setting_save').removeAttr("disabled","disabled")
                        }else {
                            $(".hide_tabs").css("display","none");;
                            $('#setting_save').attr("disabled","disabled");
                        }
                    }
                }
            });
        }

        readSettings();

    });