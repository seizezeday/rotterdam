       $(document).ready(function(){
           $("#login-btn").click(function submitButton() {
            var Auth = {
            login: $('#inputLoginEmail').val(),
            password: $('#login_password').val()
        };
          addData(Auth);
            });
             $("#reg-btn").click(function registerButton() {
            var User = {
                Name: $('#inputName').val(),
                LastName: $('#inputLastName').val(),
                pass: $('#inputPassword').val(),
                email: $('#inputEmail').val(),
                passconfirm:$('#inputPasswordConfirm').val()
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
                    200: function () {
                        alert("Success...");
                    },
                    401: function () {
                        alert("Invalid login or password");
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
                  200: function () {
//                        userInfo();
                      location.href='client_page.html';
//                     document.location = "client_page.html";
//                  function userInfo(data){
//                    var userinfo = $.ajax({
//                        //data: str,
//                        type: "POST",
//                        url: "api/home",
//                        datatype: "json",
//                        contentType: "application/json",
////                      success: function(data) {
//                        statusCode: {
//                        200: function () {
//                        alert("ok");
//                        console.log(data);
//                        alert(userinfo);
//            }
//                        }
//                    });
       
//                      alert("Login success");
//                      $.ajax({
//                          //data: str,
//                          type: "POST",
//                          url: "com.rotterdam.rest/userInfo",
//                          datatype: "json",
//                          contentType: "application/json",
//                          success: function(data) {
//                              document.getElementById("user-info").innerHTML = data.login;
//                              var str = (String)(window.location);
//                              if(str.indexOf("index.html") <= -1) window.location.reload(true);
//                          },
//                          statusCode: {
//                              403: function() {
//                                  alert("Internal error");
//                              }
//                          }
//                      });
//                      window.location.href = "client_page.html";
//                      var url = "client_page.html";
//                      $(location).attr('href',url);
//                      if(window.location.hash == 'client_page.html') {
//                      function userInfo(data){
//                       $.ajax({
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
//                      $(document).ready(function(){
//                      userInfo(data);   
//                      });

//                      $(window).load(function() {
//                        function userInfo(){
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
//                        console.log(data);
//                        alert(userinfo);
//            }
//                        }
//                    });
//        }
//                        });
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