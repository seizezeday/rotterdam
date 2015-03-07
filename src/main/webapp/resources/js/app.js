var app = angular.module('mgcrea.ngStrapDocs', ['ngAnimate', 'ngSanitize', 'mgcrea.ngStrap', 'ui.router', 'pascalprecht.translate']);

angular.module('mgcrea.ngStrapDocs');

app.controller('AlertCtrl', function($scope, $alert) {
    $scope.alertInvalidLoginOrPassWord = $alert({
        title: "Invalid login or password",
        container: '#alerts-container',
        placement: 'top-right',
        type: 'info',
        show : false, duration : 2
    });

    $scope.alertNotPayed = $alert({
        title: "Not payed",
        container: '#alerts-container',
        placement: 'top-right',
        type: 'info',
        show : false, duration : 2
    });
});

app.directive('overNightValidator', function() {
    return {
        restrict: 'A', // only activate on element attribute
        require: '?ngModel', // get a hold of NgModelController
        link: function(scope, elem, attrs, ngModel) {
            if(!ngModel) return; // do nothing if no ng-model



            // watch own value and re-validate on change
            scope.$watch(attrs.ngModel, function() {
                validate();
            });

            // watch date change
            scope.$watch("overNight.start.date", function() {
                validate();
            });

            // observe the other value and re-validate on change
            attrs.$observe('overNightValidator', function (val) {
                validate();
            });

            var validate = function() {
                // values
                var val1 = ngModel.$viewValue;
                var val2 = attrs.overNightValidator;

                // set validity
                ngModel.$setValidity('overNightValidator', scope.checkDayValidness());
                //ngModel.$setValidity('overNightValidator', val1 == val2);
                //console.log("Validated: val1: " + val1 + " val2: " + val2 + " | " + (val1 == val2));
                console.log("Validated");
            };
        }
    }
});

app.controller('time_tab_controller', function($scope, $http, $filter) {

    $scope.overNight = {}; $scope.overNight.start = {}; $scope.overNight.end = {};

    $scope.overNight.start.date = DateTools.convertDateToString(new Date());
//    $scope.overNight.start.date = "03.02.2015";
    $scope.overNight.start.time = DateTools.convertTimeToString(new Date());
//    $scope.overNight.start.time = "14:20";

    $scope.overNight.end.time = DateTools.convertTimeToString(new Date());
//    $scope.overNight.end.time = "20:33";

    $scope.overNightSave = function(){

        console.log($scope.overNight.start.time.$valid);

        var days = $scope.days;
        var startDayI, endDayI;
        for(var dayI in days){
            var day = days[dayI];
            if(day.date == $scope.overNight.start.date)
                startDayI = dayI;
        }
        endDayI = (parseInt(startDayI) + 1) + "";
        //before hard push we need to try find and replace existing overnight
        var needToPush = true;

        //first we need to check startDayI
        var startDay = days[startDayI];
        var whStart;
        for(var whI = 0; whI < startDay.workHours.length; whI++){
            var wh = startDay.workHours[whI];
            if(wh.endWorkingTime == "23:59" ){
                needToPush = false;
                whStart = whI;
                break;
                //here we find variant for replacement
            }
        }

        var whEnd;
        if(!needToPush){
            //if previous search find something
            var endDay = days[endDayI];
            for(var whI = 0; whI < endDay.workHours.length; whI++){
                var wh = endDay.workHours[whI];
                if(wh.startWorkingTime == "00:00" ){
                    needToPush = false;
                    whEnd = whI;
                    //here we find variant for replacement
                }
            }
        }

        if (!needToPush) {
            //here we need to replace data of existing overhight
            days[startDayI].workHours[whStart].startWorkingTime = $scope.overNight.start.time;
            days[endDayI].workHours[whEnd].endWorkingTime = $scope.overNight.end.time;
        } else {
            $scope.days[startDayI].workHours.push({
                    startWorkingTime: $scope.overNight.start.time,
                    endWorkingTime: "23:59",
                    restTime: "",
                    dayType: '0'
                }
            );
            $scope.days[endDayI].workHours.push({
                    startWorkingTime: "00:00",
                    endWorkingTime: $scope.overNight.end.time,
                    restTime: "",
                    dayType: '0'
                }
            );
        }
        //console.log($scope.days);
        $scope.clearWorkHours(startDayI);
        $scope.rowChanged(startDayI);
        $scope.clearWorkHours(endDayI);
        $scope.rowChanged(endDayI);
        $('#modal_close').click();
    };

    $scope.clearWorkHours = function(dayIndex){
          var day = $scope.days[dayIndex];
        for(var whI = 0; whI < day.workHours.length; whI++){
            var wh = day.workHours[whI];
            if((wh.startWorkingTime == "00:00" || wh.startWorkingTime == "" )
                && (wh.endWorkingTime == "00:00" || wh.endWorkingTime == "" )
                && (wh.restTime == "0" || wh.restTime == "" )){
                day.workHours.splice(whI, 1);
                whI--;
            }
        }
    };

    $scope.dayTypes = [
        {val: '0', description: 'Werkdag'},
        {val: '1', description: 'Weekenddag'},
        {val: '2', description: 'Wachtdag'},
        {val: '3', description: 'Ziektedag'},
        {val: '4', description: 'Vakantiedag'},
        {val: '5', description: 'ATV-dag'},
        {val: '6', description: 'Betaald verlof'},
        {val: '7', description: 'Ontbetaald verlof'},
        {val: '8', description: 'Tijd-voor-tijd'},
        {val: '9', description: 'Overstaandag'},
        {val: '10', description: 'Zwangerschapsverlof'},
        {val: '11', description: 'Feestdag'},
        {val: '12', description: 'Geen werkdag'}
    ];

    $scope.weekTitles = ["Maandag", "Dinsdag","Woensdag","Donderdag","Vrijdag","Zaterdag","Zondag"];

    $scope.selectedDate = DateTools.convertDateToString(new Date());

    $scope.overTimeMinDate = "";
    $scope.overTimeMaxDate = "";
//    $scope.selectedDate = "01.02.2015";

    $scope.active = true;

    $scope.daysTotalTime = [];

    $scope.timeForTime = 0;

    $scope.totalMonFri =  {h : 0, m : 0};

    $scope.overTime =  {h : 0, m : 0};

    $scope.applyDate = function(){
        var formattedDate = $scope.selectedDate;

        $http.get('api/timeTab', {params : {date : formattedDate}}).then(function(res){
            switch (res.status){
                case 200 :
                {
                    $scope.days = res.data.days;
                    for(var dayI in $scope.days) {
                        var day = $scope.days[dayI];
                        if (day.workHours.length == 0) {
                            $scope.addRow(dayI);
                        }
                        $scope.$watch('days[' + dayI + '].workHours[0]', function (newValue, oldValue) {
//                          console.log(newValue.dayType + ":::" + oldValue.dayType);
                            if(newValue.dayType != oldValue.dayType) {
                                if (newValue.dayType == '8') {
                                    $scope.timeForTime -= 11;
                                } else if (oldValue.dayType == '8' && newValue.dayType != '8') {
                                    $scope.timeForTime += 11;
                                }
                            }
                        }, true);
                    }
                    $scope.active = res.data.active;
                    $scope.daysTotalTime = res.data.totalTime.days;
                    $scope.promisedTime = res.data.promisedTime;
                    $scope.overTimeMinDate = DateTools.revertDayAndMonth(res.data.startEnd.start);
                    $scope.overNight.start.date = res.data.startEnd.start;
                    var date = DateTools.plusDays(res.data.startEnd.end, -1);
                    $scope.overTimeMaxDate = DateTools.revertDayAndMonth(date);
                    break;
                }
                case 204 : {
                    //$scope.days = [];
                    //$scope.addRow();
                    break;
                }
            }
            $scope.calculateTableTotal();
            $scope.calculateOverTime();
        });

        $http.get('api/timeFor').then(function(res){
            $scope.timeForTime = res.data.timeForTime;
        });
    };


    $scope.checkDayValidness = function(){
        //first we need to construct workHour
        var workHour = {
            startWorkingTime : $scope.overNight.start.time,
            endWorkingTime : $scope.overNight.end.time,
            restTime : 0
        };

        //second we need to determine day by date
        var days = $scope.days;
        if(days != undefined){
            var day;
            for(var dayI in days){
                var dayS = days[dayI];
                if(dayS.date == $scope.overNight.start.date)
                    day = dayS;
            }

            //now we can validate
            if(!(isCurrentWorkHourValid(workHour))) {
                return false;
            }

            //check if start time doesn't belong to another time periods
            if(!isTimeNotInPeriod(workHour.startWorkingTime, -1, day)) {
                return false;
            }

            //check if end time doesn't belong to another time periods
            if(!isTimeNotInPeriod(workHour.endWorkingTime, -1, day)) {
                return false;
            }
        } else return false;
        return true;
    };

    var isCurrentWorkHourValid = function(workHour){
        var start = DateTools.convertTimeStringToIntMinutes(workHour.startWorkingTime);
        var end = DateTools.convertTimeStringToIntMinutes(workHour.endWorkingTime);
        var rest = workHour.restTime != "" ? parseInt(workHour.restTime) : 0;
        //checking if end less than start and rest not greater than all time of trip
        if(start > end || rest > (end - start)) {
            return false;
        }
        return true;
    };

//return true if passed time isn't in presented day period
    var isTimeNotInPeriod = function(time, whI, day){
        for(var whJ = 0; whJ < day.workHours.length; whJ++){
            if(whI != whJ){
                var workHourPeriod = day.workHours[whJ];
                if(DateTools.isTimeStringInPeriodString(time,
                        workHourPeriod.startWorkingTime, workHourPeriod.endWorkingTime)){
                    return false;
                }
            }
        }
        return true;
    };


    $scope.checkTimeValidness = function(){

        var displayWarningMessage = function(i){
            var weekTitle = $filter('translate')('WeekDays.' + i);
            var message = $filter('translate')('dataNotValidSaving');
            addAlertWarning(weekTitle + " " + message + ".");
        };

        for(var i = 0; i < $scope.days.length; i++){
            var day = $scope.days[i];
            for(var whI = 0; whI < day.workHours.length; whI++){
                var workHour = day.workHours[whI];


                if(!(isCurrentWorkHourValid(workHour))) {
                    displayWarningMessage(i);
                    return false;
                }

                //check if start time doesn't belong to another time periods
                if(!isTimeNotInPeriod(workHour.startWorkingTime, whI, day)) {
                    displayWarningMessage(i);
                    return false;
                }

                //check if end time doesn't belong to another time periods
                if(!isTimeNotInPeriod(workHour.endWorkingTime, whI, day)) {
                    displayWarningMessage(i);
                    return false;
                }
            }
        }
        return true;
    };

    $scope.save = function() {
        if ($scope.checkTimeValidness()) {
            var daysToTransfer = {
                date: $scope.selectedDate,
                days: $scope.days
            };
            $http.post('api/timeTab', daysToTransfer).then(function () {
                $scope.calculateRest();
                $scope.calculateTableTotal();
                addAlert();
            });
        }
    };

    $scope.calculateRest = function(){
        for (var i = 0; i < 7; i++) {
            var day = $scope.days[i];
            for(var whI = 0; whI < day.workHours.length; whI++) {
                var workHour = day.workHours[whI];
                var start = DateTools.convertTimeStringToIntMinutes(workHour.startWorkingTime);
                var end = DateTools.convertTimeStringToIntMinutes(workHour.endWorkingTime);
                var rest = workHour.restTime != "" ? parseInt(workHour.restTime) : 0;
                if (rest === 0) {
                    var time = end - start - rest;

                    time = time / 60;

                    if (time >= 4.5 && time < 7.5) workHour.restTime = 30;
                    else if (time >= 7.5 && time < 10.5) workHour.restTime = 60;
                    else if (time >= 10.5 && time < 13.5) workHour.restTime = 90;
                    else if (time >= 13.5 && time < 16.5) workHour.restTime = 120;
                    else if (time >= 16.5) workHour.restTime = 150;
                }
            }
        }
    };

    $scope.addRow = function(index){
        $scope.days[index].workHours.push({
                startWorkingTime: "",
                endWorkingTime: "",
                restTime: "",
                dayType : '0'
            }
        );

    };

    $scope.removeRow = function(dayIndex, index){
        $scope.days[dayIndex].workHours.splice(index, 1);
    };

    $scope.rowChanged = function(index) {
        //changing day total
        var oldDayTotal = $scope.daysTotalTime[index];
        var oldTotalDayTime = DateTools.convertTimePairToIntMinutes(oldDayTotal);
        var day = $scope.days[index];
        var total = $scope.calculateRowTotal(index);
        if (total > 0) {
            var times = DateTools.convertIntMinutesToTimeArray(total);
            $scope.daysTotalTime[index].h = times[0];
            $scope.daysTotalTime[index].m = times[1];
        } else {
            $scope.daysTotalTime[index].h = 0;
            $scope.daysTotalTime[index].m = 0;
        }
        //we need to calculate overTime
        var overTime = $scope.calculateOverTime();
        ////now we need to calculate total mon-fri
        //var currentTotalTime = DateTools.convertTimePairToIntMinutes($scope.totalMonFri);
        //currentTotalTime -= oldTotalDayTime;
        //currentTotalTime += total;
        var currentTotalTime = $scope.calculateTotalMonFri();
        var totalMonFri = DateTools.convertIntMinutesToTimePair(currentTotalTime - overTime);
        //DateTools.convertIntMinutesToTimePair(currentTotalTime); // -overTime
        //var totalMonFri = DateTools.convertIntMinutesToTimePair(currentTotalTime); // -overTime
        $scope.totalMonFri = totalMonFri;
        var totalMonSun = currentTotalTime
            + DateTools.convertTimePairToIntMinutes($scope.daysTotalTime[5])
            + DateTools.convertTimePairToIntMinutes($scope.daysTotalTime[6]);

        //now we need to change time-for-time
        if(day.dayType == '8'){

        }
    };

    $scope.calculateRowTotal = function(index){
        //changing day total
        var day = $scope.days[index];
        var total = 0;
        for(var whI = 0; whI < day.workHours.length; whI++){
            var workHour = day.workHours[whI];
            var start = DateTools.convertTimeStringToIntMinutes(workHour.startWorkingTime);
            var end = DateTools.convertTimeStringToIntMinutes(workHour.endWorkingTime);
            var rest = workHour.restTime != "" ? parseInt(workHour.restTime) : 0;
            var workHourTotal = end - start - rest;
            if(workHourTotal > 0)
                total += workHourTotal;
        }
        return total;
    };

    $scope.calculateTableTotal = function(){
        //fully calculate
        var totalFull = $scope.calculateTotalMonFri();
        var overTime = $scope.calculateOverTime();
        if (totalFull >= 0) {
            totalFull -=overTime;
            var times = DateTools.convertIntMinutesToTimeArray(totalFull);
            $scope.totalMonFri.h = times[0];
            $scope.totalMonFri.m = times[1];
        }
    };

    $scope.calculateTotalMonFri = function(){
        var totalFull = 0;
        for (var i = 0; i < 5; i++) {
            totalFull += $scope.calculateRowTotal(i);
            //$scope.rowChanged(i);
        }
        return totalFull;
    };

    $scope.calculateOverTime = function(){
        var currentTotalTime = $scope.calculateTotalMonFri();
        //var currentTotalTime = DateTools.convertTimePairToIntMinutes($scope.totalMonFri);
        var totalMonSun = currentTotalTime
            + DateTools.convertTimePairToIntMinutes($scope.daysTotalTime[5])
            + DateTools.convertTimePairToIntMinutes($scope.daysTotalTime[6]);
        var promised = $scope.calculatePromisedTimeInMinutes();
        var time = 0;
        if (totalMonSun > promised) {
            time = totalMonSun - promised;
            var times = DateTools.convertIntMinutesToTimeArray(time);
            $scope.overTime.h = times[0];
            $scope.overTime.m = times[1];
        } else {
            $scope.overTime.h = 0;
            $scope.overTime.m = 0;
        }
        return time;
    };

    $scope.calculatePromisedTimeInMinutes = function(){
        var promised = 0;
        for (var i = 0; i < 5; i++) {
            promised += parseInt($scope.promisedTime[i]);
        }
        promised *= 60;
        return promised;
    };

    $scope.dayTypeIsDisabled = function(dayIndex, index){
        var rez = $scope.days[dayIndex].workHours[index].dayType != '0';
        if(rez){
            $scope.days[dayIndex].workHours[index].startWorkingTime = "00:00";
            $scope.days[dayIndex].workHours[index].endWorkingTime = "00:00";
            $scope.days[dayIndex].workHours[index].restTime = "0";
        } else {
            var wh =  $scope.days[dayIndex].workHours[index];
            if(wh.startWorkingTime == "00:00" && wh.startWorkingTime == "00:00" && wh.restTime == "0"){
                $scope.days[dayIndex].workHours[index].startWorkingTime = "";
                $scope.days[dayIndex].workHours[index].endWorkingTime = "";
                $scope.days[dayIndex].workHours[index].restTime = "";
            }
        }
        return  rez;
    };

    $scope.isActiveWorkHourHide = function(dayIndex, index){
        var wh = $scope.days[dayIndex].workHours[0];
        var b = wh.dayType != '0' && index != 0;
        return  b;
    };

    $scope.applyDate();
});

app.directive('timepicker', ['$parse', function($parse) {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            //attrs.ngModel contains title of model element
            var modelElement = $parse(attrs.ngModel);
            $(element).clockpicker({
                    placement: 'bottom',
                    align: 'left',
                    autoclose: true,
                    'default': 'now',
                    afterDone: function() {
                        //assign html element value to model value
                        modelElement.assign(scope, element.val());
                        scope.rowChanged(scope.$parent.$index);
                        scope.$apply();
                    }
                })
        }
    }
}]);

app.directive('timepickerModal', ['$parse', function($parse) {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            //attrs.ngModel contains title of model element
            var modelElement = $parse(attrs.ngModel);
            $(element).clockpicker({
                placement: 'bottom',
                align: 'left',
                autoclose: true,
                'default': 'now',
                afterDone: function() {
                    //assign html element value to model value
                    modelElement.assign(scope, element.val());
                    scope.$apply();
                }
            })
        }
    }
}]);

app.controller('DatepickerDemoController', function($scope, $http) {

  $scope.selectedDate = new Date();
  $scope.selectedDateAsNumber = Date.UTC(1986, 1, 22);
  // $scope.fromDate = new Date();
  // $scope.untilDate = new Date();
  $scope.getType = function(key) {
    return Object.prototype.toString.call($scope[key]);
  };

  $scope.clearDates = function() {
    $scope.selectedDate = null;
  };

});

app.run(function($rootScope, $http) {
    if(document.URL.indexOf("client_page") > -1) {
        $http.get('api/timeTab/getAvailableDates').then(function (res) {
            switch (res.status) {
                case 200 :
                {
                    $rootScope.minDate = DateTools.revertDayAndMonth(res.data.start);
                    $rootScope.maxDate = DateTools.revertDayAndMonth(res.data.end);
                    break;
                }
            }
        });
    }
});

//app.controller('DatepickerModalController', function($scope, $http) {
//
//  $scope.selectedDate = new Date();
//  $scope.selectedDateAsNumber = Date.UTC(1986, 1, 22);
//  // $scope.fromDate = new Date();
//  // $scope.untilDate = new Date();
//  $scope.getType = function(key) {
//    return Object.prototype.toString.call($scope[key]);
//  };
//
//  $scope.clearDates = function() {
//    $scope.selectedDate = null;
//  };
//
//    app.config(function($datepickerProvider) {
//  angular.extend($datepickerProvider.defaults, {
//    dateFormat: 'dd.MM.yyyy',
//    startWeek: 1,
//    daysOfWeekDisabled: '2'
//  });
//});
//});

//app.config(function($datepickerProvider) {
//  angular.extend($datepickerProvider.defaults, {
//    dateFormat: 'dd.MM.yyyy',
//    startWeek: 1
////    daysOfWeekDisabled: '0234567'
//  });
//});
app.directive('numberMask', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            $(element).numeric();
        }
    }
});
app.controller("settings_controller", ['$scope', function($scope){
$scope.days = [
    {day: 'Maandag', settingsDay: 'monday'},{day: 'Dinsdag',settingsDay: 'tuesday'},{day: 'Woensdag',settingsDay: 'wednesday'},{day: 'Donderdag',settingsDay: 'thursday'},{day: 'Vrijdag',settingsDay: 'friday'}
];
    

  }]);

var costTypes = [
    { id: "0", name: 'Eendaagse netto' },
    { id: "1", name: 'Eendaagse bruto' },
    { id: "2", name: 'Meerdaagse netto' },
    { id: "3", name: 'Overstaan netto' },
    { id: "4", name: 'Overstaan bruto' }
];

function findCostTypeById(id){
    var name;
    for(var key in costTypes){
        var value = costTypes[key];
        if(value.id == id)
            name = value.name;
    }
    return name;
}

app.controller("declaration_controller", function($scope, $http, $filter){

    $scope.selectedDate = DateTools.convertDateToString(new Date());

    $scope.total = 0;

    $scope.activeV = true;

    $scope.costTypes = costTypes;

    $scope.removeRow = function(dayIndex, index){
        $scope.days[dayIndex].declarations.splice(index, 1);
    };

    $scope.addRow = function(index){
        $scope.days[index].declarations.push({
                costType: $scope.costTypes["0"].id,
                price: 0,
                active: true
            }
        )
    };

    $scope.applyDate = function(){
        var formattedDate = $scope.selectedDate;

        $http.get('api/declaration', {params : {date : formattedDate}}).then(function(res){
            switch (res.status){
                case 200 :
                {
                    $scope.days = res.data.daysDeclaration;
                    for (var dayI = 0; dayI <$scope.days.length; dayI++) {
                        if($scope.days[dayI].declarations == undefined)
                            $scope.days[dayI].declarations = [];
                        for(var decI = 0; decI<$scope.days[dayI].declarations.length; decI++){
                            $scope.days[dayI].declarations[decI].active = false;
                        }
                        if($scope.days[dayI].declarations.length == 0)
                            $scope.addRow(dayI);

                    }
                    $scope.activeV = res.data.active;
                    $scope.calculateTotal();
                    break;
                }
                case 204 : {
                    $scope.days = [];
                    //$scope.addRow();
                    break;
                }
            }

        });

    };

    $scope.calculateTotal = function(){
        var total = 0;
        for (var dayI = 0; dayI <$scope.days.length; dayI++) {
            for (var decI = 0; decI < $scope.days[dayI].declarations.length; decI++) {
                total += parseInt($scope.days[dayI].declarations[decI].price);
            }
        }
        $scope.total = total;
    };

    $scope.save = function(){
        var declarationsToTransfer = {
            date : $scope.selectedDate,
            daysDeclaration : $scope.days
        };
        $http.post('api/declaration', declarationsToTransfer).then(function(){
            for (var dayI = 0; dayI <$scope.days.length; dayI++) {
                for (var decI = 0; decI < $scope.days[dayI].declarations.length; decI++) {
                    if($scope.days[dayI].declarations[decI].price != 0)
                        $scope.days[dayI].declarations[decI].active = false;
                }
            }
           addAlert();
        });
    };

    $scope.getDecLength = function(index){
        var length = $scope.days[index].declarations.length;
        if(length > 0) length--;
        return  length;
    };

    $scope.applyDate();

});

app.config(function ($datepickerProvider) {
    angular.extend($datepickerProvider.defaults, {
        dateFormat: 'dd.MM.yyyy',
        modelDateFormat: "dd.MM.yyyy",
        dateType: "string",
        startWeek: 1,
        autoclose: true
    });
});


app.controller("overview_controller", ['$scope', function ($scope) {

    //$scope.isDownloadDisabled = function(){
    //    //alert("Boom");
    //    console.log("Ok");
    //    var val = $('#overview_week_select').val();
    //    return val == null;
    //};


    $scope.totalTimes = [
        {tolalProc: '', overviewId: ''}, {tolalProc: '100%', overviewId: '_100'}, {
            tolalProc: '130%',
            overviewId: '_130'
        }, {tolalProc: '150%', overviewId: '_150'}, {tolalProc: '200%', overviewId: '_200'}
    ];
}]);
app.controller('LanguageCtrl', function($scope) {
  $scope.button = {
    radio: 0
  };  
//  $scope.button = {
//    radioClient: 0
//  };
});
