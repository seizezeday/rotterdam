    $(document).ready(function(){
                        $.ajax({
                        type: "POST",
                        url: "api/home",
                        datatype: "json",
                        contentType: "application/json; charset=utf-8",
                        statusCode: {
                        200: function (data) {
                        $(".username").append(data.firstname);
                        $("#userdate").append(data.date);   
                        $(".time_year option").append(data.currentYear);   
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
//                    var time_mounts = { January: 1, February: 2, March: 3, April: 4, May: 5, June: 6, July: 7, August: 8, September: 9, October: 10, November: 11, December: 12};
////        var obj = { one: 1, two: 2, three: 3, four: 4, five: 5 };
//            $.each( time_mounts, function( i, val ) {
////                    $( "#" + i ).append( document.createTextNode( " - " + val ) );
////                alert('Значение ключа '+i+' : '+val);
//                for (i = 0; i < games.length; i++) {  
//
//                }
//                });
//                        var mounts = data.currentMonth;    
//                        $(time_mounts).each(function(key, val) {
//                        alert('Значение ключа '+key+' : '+val[]);
//                    });
        
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

        /*$('.add_row').click(function(){
            var time_day_add ='<div class="time_tab_row">' +
                '<div class="col-md-4 margin_bottom_10 ">' +
                '<div class="col-md-4 font_size_18 time_date"></div>' +
                '<div class="col-md-4 font_size_18 time_day"></div>' +
                '<div class="col-md-4">' +
                '<input type="text" class="form-control time_monday_start" placeholder="Start"></div></div>' +
                '<div class="col-md-4 margin_bottom_10"><div class="col-md-6"><input type="text" class="form-control time_monday_end" placeholder="End"></div>' +
                '<div class="col-md-6"><input type="text" class="form-control time_monday_rest" placeholder="Rest"></div>' +
                '</div><div class="col-md-4 margin_bottom_10"><div class="col-md-4">' +
                '<select class="form-control time_monday_ride_type">' +
                '<option>1</option>' +
                '<option>2</option>' +
                '</select></div>' +
                '<div class="col-md-4 "><button type="button" class="btn btn-danger btn-block time_tab_del">Delete</button></div></div></div>'
            $(this).parent().after(time_day_add);
        $('.time_tab_del').bind('click',function(){
            $($(this).parents().get(2)).remove();
        });
        });*/

        $('.add_row_monday').click(function(){
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
//            '<input type="text" class="form-control" size="5" ng-com.rotterdam.model="selectedTimeAsNumberStart" data-time-format="HH:mm" data-time-type="number" data-autoclose="1" name="time_start_monday" placeholder="Start" bs-timepicker>'+
//      '</div>'+      
//       '</div>'+
//        '<div class="col-md-4 form-group">'+
//      '<div class="col-md-6">'+
//       '<div >'+
//        '<input type="text" class="form-control time_' + data +'_end" size="5" ng-com.rotterdam.model="selectedTimeAsNumberTuesdayEnd" data-time-format="HH:mm" data-time-type="number" data-autoclose="1" name="time2" placeholder="End" bs-timepicker>'+
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

        $('#date_submit').click(function date_submit(){
                var selected_date  = {
                currentDate: $("#time_week_date").val()
//                currentMonth: $(".time_mount option:selected").text(),
//                currentWeekNumber: $(".time_week option:selected").text()
            };
             $('.time_date').empty();
            time_date(selected_date);
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

//        var time_start = new Array( data.weekList[0], data.weekList[1],data.weekList[3], data.weekList[4],data.weekList[5], data.weekList[6] );
//        alert(time_start[1]);
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
                    200: function () {
                        alert("good")
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
                    rideType :          $(".time_"+ day +"_ride_type option:selected").eq(data).val()
                };
            });
            return arr;
        }
        
        //            Settings tab start 

        $('#setting_btn').click(function save_btn(){
            var selected_settings  = {
           	check1:  $("#check1").is(':checked'),
        	test1:  $("#text1").text(),
           	check2:  $("#check2").is(':checked'),
            select1: $("#select1 option:selected").text(),
            select2: $("#select2 option:selected").text(),
            select3: $("#select3 option:selected").text(),
            check3:  $("#check3").is(':checked'),
            check4:  $("#check4").is(':checked'),
            select4: $("#select4 option:selected").text(),
        };
            alert("need backend");

        
       settings(selected_date);
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
                    alert("Success...");
                }
            }
        });
    };
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
    });