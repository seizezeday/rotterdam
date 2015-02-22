       $(document).ready(function(){
           $("#login-btn").click(function submitButton() {
            var Auth = {
            login: $('#inputLoginEmail').val(),
            password: $('#login_password').val()
        };
          addData(Auth);
            });
             $("#reg-btn").click(function registerButton() {
//                 var payment = {
//                    cardNumber : $('#card-number').val(),
//                    cardType : $('#card-type').val(),
//                    expireMonth : $('#expiry-month').val(),
//                    expireYear : $('#expiry-year').val(),
//                    cvv2 : $('#cvv2').val(),
//                    firstName : $('#inputName').val(),
//                    lastName : $('#inputLastName').val()
//                 };
            var User = {
                Name: $('#inputName').val(),
                LastName: $('#inputLastName').val(),
                pass: $('#inputPassword').val(),
                email: $('#inputEmail').val(),
                passconfirm:$('#inputPasswordConfirm').val(),
                regNum:$('#regNum').val()//,
                //payment : payment
            };
            addUser(User);
        });
        $("#btn-forgot-password").click(function forgotButton() { 
           var Forgot_pass = {
             email_forgot: $('#inputForgotPassword').val()   
           };
          forgotPass(Forgot_pass);
        });
    });

        function forgotPass(data) {// pass your data in method
            $.ajax({
                type: "POST",
                url: "api/restore",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function () {
                        alert("Success...");
                    },
                    401: function(){
                        alert("Invalid email");
                    }
                }
            });
        };
      
        function addUser(data) {
            
            $.ajax({
                type: "POST",
                url: "api/registration",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function (data) {
                        $("#reg-btn").html("Pay");
                        //data is payment url
                        $("#reg-btn").unbind('click');
                        $("#reg-btn").click( function submitButton() {
                            location.href = data.link;
                        });
                        alert("Success...");
                    },
                    401: function () {
                        alert("Invalid login or password");
                    },
                    405: function () {
                        alert("Payment error");
                    }
                }
            });
        }
            
            
        function addData(data) {
          $.ajax({
              type: "POST",
              url: "api/login",
              data: JSON.stringify(data),
              contentType: "application/json; charset=utf-8",
              dataType: "json",
              statusCode: {
                  200: function (data) {
                      if(data.payed)
                            location.href = "client_page.html";
                      else{
                          alert("Not payed");
                          $("#login-btn").html("Pay");
                          //data is payment url
                          $("#login-btn").unbind('click');
                          $("#login-btn").click( function submitButton() {
                              location.href = data.link;
                          });
                      }

                  },
                  401: function () {
                      alert("Invalid login or password");
                  }
              }
          });
      }
//        function userInfo(data){
//                        var userinfo = $.ajax({
//                        //data: str,
//                        type: "POST",
//                        url: "api/home",
//                        datatype: "json",
//                        contentType: "application/json",
////                      success: function(data) {
//                        statusCode: {
//                        200: function () {
//                        alert("ok");
////                        console.log(data);
//                        alert(userinfo);
//            }
//                        }
//                    });
//        }