function addAlert() {
    $(".append_alert").append(
            '<div class="alert alert-success alert-dismissable">'+
            '<button type="button" class="close" ' +
            'data-dismiss="alert" aria-hidden="true">' +
            '&times;' +
            '</button>' +
            'Saved' +
            '</div>');
    $(".alert").show();
    $(".alert").fadeTo(2000, 500).slideUp(500, function(){
        $(".alert").alert('close');
    });
//                        window.setTimeout(function() { $(".alert").alert('close'); }, 2000);
//                        $(".close").click()
};


function preloadFunc()
{
    $.ajax({
        type: "POST",
        url: "api/ok",
        datatype: "json",
        contentType: "application/json; charset=utf-8",
        statusCode: {
            403 : function(){
                window.location.href = "/index.html";
            }
        },
        async:   false
    });
}
window.onload = preloadFunc();

$(document).ready(function(){
//        $(".hide_tabs").hide();  // Спрятать все табы пока водитель не введет настройки
        //Получение значений текущего дня недели и запись на таб время
        var week_days = ["monday", "tuesday","wednesday","thursday","friday","saturday","sunday"]; 
        var week_days_upercase = ["Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"]; 
        var pdf_report = [];
//        alert (week_days[0]);
//        $.each(week_days,function(){
////            alert(this);
//        });
          clockPicker();

                        $.ajax({
                        type: "POST",
                        url: "api/home",
                        datatype: "json",
                        contentType: "application/json; charset=utf-8",
                        statusCode: {
                        200: function (data) {
                        $(".username").append(data.firstname);
                        $("#userdate").append(data.date);
                        $("#registrationNumber").append(data.regNum);
                        $(".time_date").eq(0).append(data.weekList[0]);
                        $(".time_date").eq(1).append(data.weekList[1]);   
                        $(".time_date").eq(2).append(data.weekList[2]);   
                        $(".time_date").eq(3).append(data.weekList[3]);   
                        $(".time_date").eq(4).append(data.weekList[4]);   
                        $(".time_date").eq(5).append(data.weekList[5]);   
                        $(".time_date").eq(6).append(data.weekList[6]);
                        compensation_json();
                        overview_date();
                        }
//                            ,
//                            403 : function(){
//                                window.location.href = "/index.html";
//                            }
                        }
                    });

//        payment_json();
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
    function type_day(){
    $.each(week_days,function(){
//                alert(this);
             if($(".time_"+this+"_type_day").val() != 1){
                $(".time_tab_"+this+"_del").click();
                $(".add_row_"+this+" button").hide();
                $(".time_"+this+"_start").attr("disabled","disabled");
                $(".time_"+this+"_start").val('00:00')
                $(".time_"+this+"_end").attr("disabled","disabled");
                $(".time_"+this+"_end").val('00:00')
                $(".time_"+this+"_rest").attr("disabled","disabled");
                $(".time_"+this+"_rest").val('00:00')
              } 
            else {
                $(".add_row_"+this+" button").show();
                $(".time_"+this+"_start").removeAttr("disabled","disabled");
//                $(".time_"+this+"_start").val('')
                $(".time_"+this+"_end").removeAttr("disabled","disabled");
//                $(".time_"+this+"_end").val('')
                $(".time_"+this+"_rest").removeAttr("disabled","disabled");
//                $(".time_"+this+"_rest").val('')
              } 
});  
    };       
                $(".time_"+week_days[0]+"_type_day, .time_"+week_days[1]+"_type_day, .time_"+week_days[2]+"_type_day, .time_"+week_days[3]+"_type_day, .time_"+week_days[4]+"_type_day, .time_"+week_days[5]+"_type_day, .time_"+week_days[6]+"_type_day").change(type_day);
        
        $('.add_row_monday').bind('click',function(){
         var time_day_add = tryOne("monday");
         $(this).parent().after(time_day_add);
         clockPicker();    
         $('.time_tab_monday_del').bind('click',function(){
         $($(this).parents().get(2)).remove();
         });
         });

        $('.add_row_tuesday').click(function(){
            var time_day_add = tryOne("tuesday");
            $(this).parent().after(time_day_add);
            clockPicker();    
            $('.time_tab_tuesday_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });

        $('.add_row_wednesday').click(function(){
            var time_day_add = tryOne("wednesday");
            $(this).parent().after(time_day_add);
            clockPicker();    
            $('.time_tab_wednesday_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_thursday').click(function(){
            var time_day_add = tryOne("thursday");
            $(this).parent().after(time_day_add);
            clockPicker();
            $('.time_tab_thursday_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_friday').click(function(){
            var time_day_add = tryOne("friday");
            $(this).parent().after(time_day_add);
            clockPicker();
            $('.time_tab_friday_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_saturday').click(function(){
            var time_day_add = tryOne("saturday");
            $(this).parent().after(time_day_add);
            clockPicker();
            $('.time_tab_saturday_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });
        $('.add_row_sunday').click(function(){
            var time_day_add = tryOne("sunday");
            $(this).parent().after(time_day_add);
            clockPicker();
            $('.time_tab_sunday_del').bind('click',function(){
                $($(this).parents().get(2)).remove();
            });
        });

    function tryOne(data) {
        var time_day_add = '<div class="time_tab_row">' +
            '<div class="col-md-4 margin_bottom_10 ">' +
            '<div class="col-md-4 col-md-offset-8">' +
            '<input type="text" class="form-control time_' + data +'_start clockpicker" placeholder="Start"></div></div>' +
            '<div class="col-md-4 margin_bottom_10"><div class="col-md-6"><input type="text" class="form-control time_' + data +'_end clockpicker" placeholder="End"></div>' +
            '<div class="col-md-6"><input type="text" class="form-control time_' + data +'_rest" placeholder="Rest" number-mask=""></div>' +
            '</div>'+
            '<div class="col-md-4 margin_bottom_10">'+
            '<div class="col-md-4"><button type="button" class="btn btn-danger btn-block time_tab_'+ data +'_del">Delete</button></div></div></div>'
        return time_day_add;
    };   
        

// получения новых дат 
        $('#date_submit').click(function date_submit(){
                var selected_date  = {
                currentDate: $("#time_week_date").val()
            };
             $('.time_date').empty();
            time_date(selected_date);
            readSettings();
            loadTimeTab();
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
            function compensation_json(){
            $.ajax({
                type: "POST",
                url: "api/timeFor",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function (data) {
//                        alert("Success...");
                        $('#compensation_time_avaliable').text(data.timeForTime);
                        $('#payment_time_avaliable').text(data.overTime);
                    }
                }
            });
        };
        //            compensation time end
        $("#payment_time_field").keyup(function payment_hide_btn(){
           var payment = parseInt($("#payment_time_field").val());
           var paymant_avaliable = parseInt($('#payment_time_avaliable').text());
     if (payment !=="" && payment <= paymant_avaliable) {
        $("#payment_time_btn").removeAttr("disabled");
    }
        else {
                $("#payment_time_btn").attr("disabled","disabled");
        }                  
                         });
   $('#payment_time_btn').click(function payment_time(){
                var paymenttime = {
                    overTime: $('#payment_time_field').val()
                };
                  //alert(paymenttime.avl_time_for_pay);
                  payment_json(paymenttime);
               });
            function payment_json(data) {
            $.ajax({
                type: "POST",
                url: "api/timeFor/setOverTime",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function () {
                        compensation_json();
                        //alert("Success...");
                        $('#payment_time_field').val('');
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
                        addAlert();
                        $('#total_mon_fri').text(data.totalTime);
                        $('#total_time_monday').text(data.days.Monday);
                        $('#total_time_tuesday').text(data.days.Tuesday);
                        $('#total_time_wednesday').text(data.days.Wednesday);
                        $('#total_time_thursday').text(data.days.Thursday);
                        $('#total_time_friday').text(data.days.Friday);
                        $('#total_time_saturday').text(data.days.Saturday);
                        $('#total_saturday').text(data.days.Saturday);
                        $('#total_time_sunday').text(data.days.Sunday);
                        $('#total_sunday').text(data.days.Sunday);
                        $('#total_over_hours').text(data.overTime);
                        loadTimeTab();
                        compensation_json();
                    }
                }
            });

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
                $(".time_monday_start").val('');
                $(".time_monday_end").val('');
                $(".time_monday_rest").val('');

                $(".time_tuesday_start").val('');
                $(".time_tuesday_end").val('');
                $(".time_tuesday_rest").val('');

                $(".time_wednesday_start").val('');
                $(".time_wednesday_end").val('');
                $(".time_wednesday_rest").val('');

                $(".time_thursday_start").val('');
                $(".time_thursday_end").val('');
                $(".time_thursday_rest").val('');

                $(".time_friday_start").val('');
                $(".time_friday_end").val('');
                $(".time_friday_rest").val('');

                $(".time_saturday_start").val('');
                $(".time_saturday_end").val('');
                $(".time_saturday_rest").val('');

                $(".time_sunday_start").val('');
                $(".time_sunday_end").val('');
                $(".time_sunday_rest").val('');
                type_day();

                if(data.active){
                    $('#date_save').removeAttr("disabled","disabled");

                    $(".time_monday_start").removeAttr("disabled","disabled");
                    $(".time_monday_end").removeAttr("disabled","disabled");
                    $(".time_monday_rest").removeAttr("disabled","disabled");
                    $(".time_monday_type_day").removeAttr("disabled","disabled");
                    $(".add_row_monday button").css("display","block");

                    $(".time_tuesday_start").removeAttr("disabled","disabled");
                    $(".time_tuesday_end").removeAttr("disabled","disabled");
                    $(".time_tuesday_rest").removeAttr("disabled","disabled");
                    $(".time_tuesday_type_day").removeAttr("disabled","disabled");
                    $(".add_row_tuesday button").css("display","block");

                    $(".time_wednesday_start").removeAttr("disabled","disabled");
                    $(".time_wednesday_end").removeAttr("disabled","disabled");
                    $(".time_wednesday_rest").removeAttr("disabled","disabled");
                    $(".time_wednesday_type_day").removeAttr("disabled","disabled");
                    $(".add_row_wednesday button").css("display","block");


                    $(".time_thursday_start").removeAttr("disabled","disabled");
                    $(".time_thursday_end").removeAttr("disabled","disabled");
                    $(".time_thursday_rest").removeAttr("disabled","disabled");
                    $(".time_thursday_type_day").removeAttr("disabled","disabled");
                    $(".add_row_thursday button").css("display","block");


                    $(".time_friday_start").removeAttr("disabled","disabled");
                    $(".time_friday_end").removeAttr("disabled","disabled");
                    $(".time_friday_rest").removeAttr("disabled","disabled");
                    $(".time_friday_type_day").removeAttr("disabled","disabled");
                    $(".add_row_friday button").css("display","block");


                    $(".time_saturday_start").removeAttr("disabled","disabled");
                    $(".time_saturday_end").removeAttr("disabled","disabled");
                    $(".time_saturday_rest").removeAttr("disabled","disabled");
                    $(".time_saturday_type_day").removeAttr("disabled","disabled");
                    $(".add_row_saturday button").css("display","block");


                    $(".time_sunday_start").removeAttr("disabled","disabled");
                    $(".time_sunday_end").removeAttr("disabled","disabled");
                    $(".time_sunday_rest").removeAttr("disabled","disabled");
                    $(".time_sunday_type_day").removeAttr("disabled","disabled");
                    $(".add_row_sunday button").css("display","block");


                }else {
                    $('#date_save').attr("disabled","disabled");

                    $(".time_monday_start").attr("disabled","disabled");
                    $(".time_monday_end").attr("disabled","disabled");
                    $(".time_monday_rest").attr("disabled","disabled");
                    $(".time_monday_type_day").attr("disabled","disabled");
                    $(".add_row_monday button").css("display","none");



                    $(".time_tuesday_start").attr("disabled","disabled");
                    $(".time_tuesday_end").attr("disabled","disabled");
                    $(".time_tuesday_rest").attr("disabled","disabled");
                    $(".time_tuesday_type_day").attr("disabled","disabled");
                    $(".add_row_tuesday button").css("display","none");


                    $(".time_wednesday_start").attr("disabled","disabled");
                    $(".time_wednesday_end").attr("disabled","disabled");
                    $(".time_wednesday_rest").attr("disabled","disabled");
                    $(".time_wednesday_type_day").attr("disabled","disabled");
                    $(".add_row_wednesday button").css("display","none");


                    $(".time_thursday_start").attr("disabled","disabled");
                    $(".time_thursday_end").attr("disabled","disabled");
                    $(".time_thursday_rest").attr("disabled","disabled");
                    $(".time_thursday_type_day").attr("disabled","disabled");
                    $(".add_row_thursday button").css("display","none");


                    $(".time_friday_start").attr("disabled","disabled");
                    $(".time_friday_end").attr("disabled","disabled");
                    $(".time_friday_rest").attr("disabled","disabled");
                    $(".time_friday_type_day").attr("disabled","disabled");
                    $(".add_row_friday button").css("display","none");


                    $(".time_saturday_start").attr("disabled","disabled");
                    $(".time_saturday_end").attr("disabled","disabled");
                    $(".time_saturday_rest").attr("disabled","disabled");
                    $(".time_saturday_type_day").attr("disabled","disabled");
                    $(".add_row_saturday button").css("display","none");

                    $(".time_sunday_start").attr("disabled","disabled");
                    $(".time_sunday_end").attr("disabled","disabled");
                    $(".time_sunday_rest").attr("disabled","disabled");
                    $(".time_sunday_type_day").attr("disabled","disabled");
                    $(".add_row_sunday button").css("display","none");

                }
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
                $("#total_mon_fri").text(data.totalTime.totalTime);
                $("#total_over_hours").text(data.totalTime.overTime);
                $('#total_time_monday').text(data.totalTime.days.Monday)
                $('#total_time_tuesday').text(data.totalTime.days.Tuesday)
                $('#total_time_wednesday').text(data.totalTime.days.Wednesday)
                $('#total_time_thursday').text(data.totalTime.days.Thursday)
                $('#total_time_friday').text(data.totalTime.days.Friday)
                $('#total_time_saturday').text(data.totalTime.days.Saturday)
                $('#total_time_sunday').text(data.totalTime.days.Sunday)
                $('#total_sunday').text(data.totalTime.days.Sunday)

                $('#total_saturday').text(data.totalTime.days.Saturday)

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
            var selected_settings  = {
                currentDate: $("#time_week_date").val(),
            monday_hours : $("#settings_monday").val(), 
            tuesday_hours : $("#settings_tuesday").val(), 
            wednesday_hours : $("#settings_wednesday").val(), 
            thursday_hours : $("#settings_thursday").val(), 
            friday_hours : $("#settings_friday").val(), 
//            saturday_hours : $("#settings_saturday").val(), 
//            sunday_hours : $("#settings_sunday").val(),                 
           	saturday_compensation:  $("#suterday_compensation").is(':checked'),
//            show_compensation:  $("#show_compensation").is(':checked'),
            allow_saturday_compensation:  $("#allow_saturday_compensation").is(':checked')
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
                    var show_compensation = $('#show_compensation').bootstrapSwitch('state');
                    var allow_saturday_compensation = $("#allow_saturday_compensation").is(':checked');
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
          if($('#show_compensation').bootstrapSwitch('state')){
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
                        //$("#settings_saturday").val(data.saturday_hours);
                        //$("#settings_sunday").val(data.sunday_hours);
                        $("#start_period").text(data.startDate);
                        $("#end_period").text(data.endDate);
//                        $("#show_compensation").prop('checked', data.show_compensation);
                        $('#show_compensation').bootstrapSwitch('state',data.show_compensation);

                        $("#allow_saturday_compensation").bootstrapSwitch('state', data.allow_saturday_compensation);
                        settings_compensation();
                        if (data.monday_hours !=="" && data.tuesday_hours !=="" && data.wednesday_hours !=="" && data.thursday_hours !=="" &&
                            data.friday_hours  !=="" &&
                            //data.saturday_hours  !=="" && data.sunday_hours !=="" &&
                            data.monday_hours !==null && data.tuesday_hours !==null && data.wednesday_hours !==null && data.thursday_hours !==null &&
                            data.friday_hours  !==null
                            //&& data.saturday_hours  !==null && data.sunday_hours !==null
                            ) {
                            $(".hide_tabs").css("display", "block");
                            $('#setting_save').removeAttr("disabled","disabled")
                        }else {
                            $(".hide_tabs").css("display","none");
                            $('#setting_save').attr("disabled","disabled");
                        }
                    }
                }
            });
        }

        readSettings();
    function clockPicker(){
        var input = $('.clockpicker').clockpicker({
        placement: 'bottom',
        align: 'left',
        autoclose: true,
        'default': 'now'
    });   
    }    
         $('#overview_submit').click(overview_date);
             function overview_date(){
            $.ajax({
            type: "POST",
            url: "api/overView/getDetail",
            data: JSON.stringify({currentDate: $("#overview_calendar").val()}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
                200: function (data) {
//                    alert("Success...");
                    $("#overview_total").text(data.total);
                    $("#overview_time_to_pay").text(data.overTime);
                    $("#overview_total_100").text(data.total100);
                    $("#overview_total_130").text(data.total130);
                    $("#overview_total_150").text(data.total150);
                    $("#overview_total_200").text(data.total200);
                    for(var i = 0; i < data.weekDates.length; i++)
                        $("#overview_week_select :nth-child(" + (i+1) +")")
                            .text(data.weekDates[i].start + " - " + data.weekDates[i].end);

                    $("#download_pdf_btn").removeAttr("disabled","disabled");
                }
            }
        });  
        };

//    $("#download_pdf_btn").removeAttr("disabled","disabled");
    $('#download_pdf_btn').click(function download_pdf(){
//        var testData = '{"date":"26.01.2015","usedWeeks":["1", "2", "3", "4"]}';
            $.ajax({
            type: "POST",
            url: "api/overView/getPdf",
            data: JSON.stringify({
                date: $("#overview_calendar").val(),
                usedWeeks: $('#overview_week_select').val()
            }),
//                data : testData,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
                200: function (data) {
//                    alert("Success...");
//                    data.weekOverViews.push(data.weekOverViews[1]);
//                    data.weeks.push(data.weeks[1]);
                    generatefromjson(pdf_report, data);
                }
            }
        });  
        });
    function generatefromjson(data, jsonData) {
        var fontSize = 12;
        var xOffset = 50;
        var doc = new jsPDF('p', 'pt', 'a4', true);
        doc.setFont("times", "normal");
        doc.setFontSize(fontSize);
        doc.text(10 + xOffset, 20, "Name: " + jsonData.user.Name);
        doc.text(10 + xOffset, 35, "Surname: " + jsonData.user.LastName);
        doc.text(10 + xOffset, 50, "Period: " + jsonData.startEnd.start + " - " + jsonData.startEnd.end);
        doc.text(10 + xOffset, 65, "Driver ID: " + jsonData.user.regNum);
        doc.text(10 + xOffset, 80, "Time-for-Time: " + jsonData.timeForTime);
        doc.text(10 + xOffset, 95, "Scheduled Hours: " + jsonData.scheduledHours);

        var heightDefault = 125, height = heightDefault;
        var pageHeight = 500;

        if(jsonData.weekOverViews.length == 0)
            doc.text(50, 60 + height, 'No data selected');
        else{
            for (var weekI = 0; weekI < jsonData.weekOverViews.length; weekI++) {
                var weekOverView = jsonData.weekOverViews[weekI];

                height = drawTotalTable(doc, data, weekOverView, height, xOffset, false);

                var week = jsonData.weeks[weekI];

                height = drawWeekTable(doc, data, week, height, xOffset);

                var declaration = jsonData.declarations[weekI];

                height = drawDeclarationsTable(doc, data, declaration, height, xOffset);

                if (height >= pageHeight) {
                    doc.addPage();
                    height = heightDefault;
                }
            }
            if(jsonData.weekOverViews.length > 1){
                var totalData = [];
                totalData.detail = jsonData.totalPeriodDetail;
                height = drawTotalTable(doc, data, totalData, height, xOffset, true);
            }
        }



        doc.text(346 + xOffset, height + 20, 'Report created: ' + jsonData.date);
        doc.save("some-file.pdf");
    }

    function drawTotalTable(doc, data, week, height, xOff, isTotal){
        data = [];
        var weekDetail = week.detail;

        var jsonDetailAdapter = {"total" : "Total hours","total100" : "100% hours",
            "total130" : "130% hours", "total150" : "150% hours", "total200" : "200% hours"};

        for (var detail in weekDetail) {

            if(detail in jsonDetailAdapter)
                data.push({
                    "Time": jsonDetailAdapter[detail],
                    "Value": weekDetail[detail]
                });
        }

        if(data.length != 0) {
            if (!isTotal) {
                var startEnd = week.startEnd;
                doc.text(20 + xOff, height, 'Week #' + week.weekNum + ': ' + startEnd.start + " - " + startEnd.end);
            } else {
                doc.text(20 + xOff, height, 'Total in the selected weeks');
            }
            doc.drawTable(data, {
                xstart: 10 + xOff,
                ystart: 10,
                tablestart: 10 + height,
                marginright: 50,
                xOffset: 10,
                yOffset: 10,
                pagesplit: true
            }) ;
            height += 200;
        }
        return height;
    }

    function drawWeekTable(doc, data, week, height, xOff){
        data = [];
        for (var dayI in week.days) {

            var day = week.days[dayI];

            for (var workHourI = 0; workHourI < day.workHours.length; workHourI++) {

                var workHour = day.workHours[workHourI];

                data.push({
                    "Week Day": dayI,
                    "Date": day.date,
                    "Time start": workHour.startWorkingTime,
                    "Time end": workHour.endWorkingTime,
                    "Rest": workHour.restTime,
                    "Total time": day.total
                });
            }
        }

        if(data.length != 0){
            height +=doc.drawTable(data, {
                xstart: 10 + xOff,
                ystart: 10,
                tablestart: 10 + height,
                marginright: 50,
                xOffset: 10,
                yOffset: 10,
                pagesplit: true
            }) ;
            //height += 200;
        }
        return height;
    }

    function drawDeclarationsTable(doc, data, declaraion, height, xOff){
        data = [];
        var decs = declaraion.declarations;
        for (var dec in declaraion.declarations) {
               data.push({
                    "Type": decs[dec].costType,
                    "Date": "Will be date",
                    "Price": decs[dec].price
                });
        }

        if(data.length != 0){
            doc.drawTable(data, {
                xstart: 10 + xOff,
                ystart: 10,
                tablestart: 10 + height,
                marginright: 50,
                xOffset: 10,
                yOffset: 10,
                pagesplit: true
            }) ;
            height += 200;
        }
        return height;
    }
});