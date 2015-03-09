app.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

    //$locationProvider.html5Mode(true);

    $stateProvider
        .state('index', {
            url : "/index",
            templateUrl: 'auth.html',
            parent : 'locale'
        })
        .state('client', {
            url : "/client",
            templateUrl: 'client.html',
            parent : 'locale'
        })
    ;
});