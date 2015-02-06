var app = angular.module('mgcrea.ngStrapDocs', ['ngAnimate', 'ngSanitize', 'mgcrea.ngStrap']);

angular.module('mgcrea.ngStrapDocs');

app.controller('TimepickerDemoController', function($scope, $http) {
  $scope.time = new Date(1970, 0, 1, 10, 30);
  $scope.selectedTimeAsNumber = 10 * 36e5;
//  $scope.selectedTimeAsString = '10:00';
  $scope.sharedDate = new Date(new Date().setMinutes(0));
  $scope.options = [
    {val: '1', deskription: 'Werkdag'},{val: '2', deskription: 'Weekenddag'},{val: '3', deskription: 'Wachtdag'},{val: '4', deskription: 'Ziektedag'},{val: '5', deskription: 'Vakantiedag'},{val: '6', deskription: 'ATV-dag'},{val: '7', deskription: 'Betaald verlof'},{val: '8', deskription: 'Ontbetaald verlof'},{val: '9', deskription: 'Tijd-voor-tijd'},{val: '10', deskription: 'Overstaandag'},{val: '11', deskription: 'Zwangerschapsverlof'},{val: '12', deskription: 'Feestdag'},{val: '13', deskription: 'Geen werkdag'}
];
});



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
    {day: 'Monday', settingsDay: 'monday'},{day: 'Tuesday',settingsDay: 'tuesday'},{day: 'Wednesday',settingsDay: 'wednesday'},{day: 'Thursday',settingsDay: 'thursday'},{day: 'Friday',settingsDay: 'friday'}
];
    

  }]);
app.controller("declaration_controller", function($scope, $http, $filter){

    $scope.selectedDate = DateConverter.convertDateToString(new Date());;

    $scope.costTypes = [
        { id: "0", name: 'Eendaagse netto' },
        { id: "1", name: 'Eendaagse bruto' },
        { id: "2", name: 'Meerdaagse netto' },
        { id: "3", name: 'Overstaan netto' },
        { id: "4", name: 'Overstaan bruto' }
    ];

    $scope.removeRow = function(index){
        $scope.declarations.splice(index, 1);
    };

    $scope.addRow = function(){
        $scope.declarations.push({
                costType: $scope.costTypes["0"].id,
                price: 0
            }
        )
    };

    $scope.applyDate = function(){
        var formattedDate = $scope.selectedDate;

        $http.get('api/declaration', {params : {date : formattedDate}}).then(function(res){
            switch (res.status){
                case 200 : {
                    $scope.declarations = res.data.declarations;
                    if($scope.declarations.length == 0)
                        $scope.addRow();
                    break;
                }
                case 204 : {
                    $scope.declarations = [];
                    $scope.addRow();
                    break;
                }
            }

        });

    };

    $scope.save = function(){
        var declarationsToTransfer = {
            date : $scope.selectedDate,
            declarations : $scope.declarations
        };
        $http.post('api/declaration', declarationsToTransfer).then(function(){
           addAlert();
        });
    };



    $scope.applyDate();

});

app.config(function ($datepickerProvider) {
    angular.extend($datepickerProvider.defaults, {
        dateFormat: 'dd.MM.yyyy',
        modelDateFormat: "dd.MM.yyyy",
        dateType: "string"
    });
});


app.controller("overview_controller", ['$scope', function($scope){
$scope.totalTimes = [
    {tolalProc: '', overviewId: ''},{tolalProc: '100%', overviewId: '_100'},{tolalProc: '130%', overviewId: '_130'},{tolalProc: '150%', overviewId: '_150'},{tolalProc: '200%', overviewId: '_200'}
];
  }]);



//app.controller("time_select", ['$scope', function($scope){
//$scope.options = [
//    {val: '1', deskription: 'Werkdag'},{val: '2', deskription: 'Weekenddag'},{val: '3', deskription: 'Wachtdag'},{val: '4', deskription: 'Ziektedag'},{val: '5', deskription: 'Vakantiedag'},{val: '6', deskription: 'ATV-dag'},{val: '7', deskription: 'Betaald verlof'},{val: '8', deskription: 'Ontbetaald verlof'},{val: '9', deskription: 'Tijd-voor-tijd'},{val: '10', deskription: 'Overstaandag'},{val: '11', deskription: 'Zwangerschapsverlof'},{val: '12', deskription: 'Feestdag'},{val: '13', deskription: 'Geen werkdag'}
//];
//  }]);
