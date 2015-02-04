var app = angular.module('mgcrea.ngStrapDocs', ['ngAnimate', 'ngSanitize', 'mgcrea.ngStrap']);

angular.module('mgcrea.ngStrapDocs')

app.controller('TimepickerDemoController', function($scope, $http) {
  $scope.time = new Date(1970, 0, 1, 10, 30);
  $scope.selectedTimeAsNumber = 10 * 36e5;
//  $scope.selectedTimeAsString = '10:00';
  $scope.sharedDate = new Date(new Date().setMinutes(0));
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

app.config(function($datepickerProvider) {
  angular.extend($datepickerProvider.defaults, {
    dateFormat: 'dd.MM.yyyy',
    startWeek: 1,
//    daysOfWeekDisabled: '0234567'
  });
});
app.directive('numberMask', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            $(element).numeric();
        }
    }
});
//app.controller('bookCtrl', function($scope) {
//    $scope.book = {
//        name: 'A Game of Thrones',
//        tags: [
//            'Tyrion',
//            'John Snow',
//            'Arya',
//            'Sean Bean'
//        ]
//    };
app.controller("settings_controller", ['$scope', function($scope){

//$scope.population = 7000;
//$scope.countries = [
//    {name: 'France', population: 63.1},
//    {name: 'United Kingdom', population: 61.8}
//];
$scope.days = [
    {day: 'Monday', settingsDay: 'monday'},{day: 'Tuesday',settingsDay: 'tuesday'},{day: 'Wednesday',settingsDay: 'wednesday'},{day: 'Thursday',settingsDay: 'thursday'},{day: 'Friday',settingsDay: 'friday'}
//    {name: 'United Kingdom', population: 61.8}
//    {settingsDay: 'monday'},{settingsDay: 'tuesday'},{settingsDay: 'wednesday'},{settingsDay: 'thursday'},{settingsDay: 'friday'}
];
    

  }]);