app.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

    //$locationProvider.html5Mode(true);

    $stateProvider
        .state('index', {
            url : "/index",
            templateUrl: '/pages/auth.html',
            parent : 'locale'
        })
        .state('client', {
            url : "/client",
            templateUrl: '/pages/client.html',
            parent : 'locale'
        })
        .state('client.home', {
            url : "/home",
            templateUrl: '/pages/client/home.html',
            controller : "HomeCtrl"
        })
        .state('client.time', {
            url : "/time",
            templateUrl: '/pages/client/time.html',
            controller : "time_tab_controller"
        })
        .state('client.overview', {
            url : "/overview",
            templateUrl: '/pages/client/overview.html',
            controller : "OverViewCtrl"
        })
        .state('client.declaration', {
            url : "/declaration",
            templateUrl: '/pages/client/declaration.html',
            controller : "declaration_controller"
        })
        .state('client.settings', {
            url : "/settings",
            templateUrl: '/pages/client/settings.html',
            controller : "SettingsCtrl"
        })
    ;
});