function addAlert() {
    var message;
    if(endsWith("nl", document.URL)){
        message = "Gered";
    } else
        message = "Saved";
    $(".append_alert").append(
            '<div class="alert alert-success alert-dismissable">'+
            '<button type="button" class="close" ' +
            'data-dismiss="alert" aria-hidden="true">' +
            '&times;' +
            '</button>' +
                message +
            '</div>');
    $(".alert").show();
    $(".alert").fadeTo(2000, 500).slideUp(500, function(){
        $(".alert").alert('close');
    });


};

function addAlertWarning(message) {
    $(".append_alert").append(
            '<div class="alert alert-danger alert-dismissable">'+
            '<button type="button" class="close" ' +
            'data-dismiss="alert" aria-hidden="true">' +
            '&times;' +
            '</button>' +
            message +
            '</div>');
    $(".alert").show();
    $(".alert").fadeTo(2000, 500).slideUp(500, function(){
        $(".alert").alert('close');
    });


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
        //Получение значений текущего дня недели и запись на таб время
        var week_days = ["monday", "tuesday","wednesday","thursday","friday","saturday","sunday"];
    var week_days_upercase = ["Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];



//
        // Редирект на index.html после нажатия кнопки logout

    $('#logout').click(function(){
             $.ajax({
              type: "POST",
              url: "api/logout",
              contentType: "application/json; charset=utf-8",
              dataType: "json",
              statusCode: {
                  200: function () {
                      if(endsWith("en", document.URL)){
                          location.href = "index.html#/en";
                      } else
                        location.href='index.html';
                  }
              }
          });
        });
});
var pdf_report = [];

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

            height = drawDeclarationsTable(doc, data, declaration.daysDeclaration, height, xOffset);

            if (height >= pageHeight) {
                doc.addPage();
                height = heightDefault;
            }
        }
        if(jsonData.weekOverViews.length > 0){
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
    var days = declaraion;
    for (var dayI in days){
        var day = days[dayI];
        var date = day.date;
        var decs = day.declarations;
        for (var dec in decs) {
            data.push({
                "Date": date,
                "Type": findCostTypeById(decs[dec].costType),
                "Price": decs[dec].price
            });
        }
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
