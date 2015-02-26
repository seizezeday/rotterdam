var defaultLang = 'nl';

app.config(function($stateProvider, $urlRouterProvider) {


    $urlRouterProvider.otherwise("/" + defaultLang);

    $stateProvider
        .state('en', {
            url: "/en",
            controller: function($scope, $translate) {
                $translate.use('en');
            }
        })
        .state('nl', {
            url: "/nl",
            controller: function($scope, $translate) {
                $translate.use('nl');
            }
        })
    ;
});

app.config(['$translateProvider', function ($translateProvider) {
    $translateProvider.translations('en', {
        //for client-page
        'Name': 'Name',
        'RegNum': 'Registration number',
        'Date': 'Date',
        'Apply': 'Apply',
        'OverTrip': 'Overnight trip',
        'AvailableTFT': 'Available time-for-time',
        'Save': 'Save',
        'AnotherTrip': 'Another trip',
        'Delete': 'Delete',
        'TotalWorked': 'Total worked',
        'TotalWorkedMonFri': 'Total worked mon-fri',
        'TotalOverHours': 'Total over hours',
        'TotalWorkedSaturday': 'Total worked Saturday',
        'TotalWorkedSunday': 'Total worked Sunday',
        'RestMin': 'Rest min',
        'End': 'End',
        'Start': 'Start',
        'StartDate': 'Start date',
        'Close': 'Close',
        'SaveChanges': 'Save changes',
        'TotalHours': 'Total hours',
        'worked': 'worked',
        'DownloadPDF': 'Download PDF',
        'KortenDeclaration': 'Korten declaration',
        'Total': 'Korten declaration',
        'ScheduledWorkours': 'Scheduled work hours',
        'UseSaturdayHoursAsTFT': 'Use Saturday hours as time-for-time',
        'Period': 'Period',
        'StartTFTRegulation': 'Start time-for-time regulation',
        'Home': 'Home',
        'Time': 'Time',
        'Overview': 'Overview',
        'Declaration': 'Declaration',
        'Settings': 'Settings',
        'Logout': 'Logout',
        //for login page
        'Login': 'Login',
        'Registration': 'Registration',
        'ForgotPassword': 'Forgot password',
        'EnterUsername': 'EnterUsername',
        'LastName': 'Lastname',
        'EnterLastName': 'Enter lastname',
        'Email': 'Email',
        'InvalidEmail': 'Invalid Email',
        'InvalidRegNum': 'Invalid Registration number ',
        'Password': 'Password ',
        'PasswordsNotMatch': 'Passwords do not match ',
        'RepeatPassword': 'Repeat password ',
        'InsufficientlyComplexPassword': 'Insufficiently complex password ',
        'EnterYourEmail': 'Enter your Email ',
        'SendPassword': 'Send password ',
        'EnterEmailAddress': 'Enter Email address ',
        'PasswordEmpty': 'Password empty'
    });

    $translateProvider.translations('nl', {
        //for client-page
        'Name': 'Naam',
        'RegNum': 'Personeels nummer',
        'Date': 'Datum',
        'Apply': 'Brengen',
        'OverTrip': 'Nachtelijke reis',
        'AvailableTFT': 'Beschikbare  tijd-voor-tijd',
        'Save': 'Opslaan',
        'AnotherTrip': 'Toevoegen rit',
        'Delete': 'Verwijderen',
        'TotalWorked': 'Totaal aantal',
        'TotalWorkedMonFri': 'Taantal maan-vrij',
        'TotalOverHours': 'Totaal meer dan uur',
        'TotalWorkedSaturday': 'Totaal werkte Zaterdag',
        'TotalWorkedSunday': 'Totaal werkte Zondag',
        'RestMin': 'Rust min',
        'End': 'Einde',
        'Start': 'Begin',
        'StartDate': 'Begin datum',
        'Close': 'Dicht',
        'SaveChanges': 'Wijzigingen opslaan',
        'TotalHours': 'Totaal aantal uren',
        'worked': 'gewerkt',
        'DownloadPDF': 'Download as PDF',
        'KortenDeclaration': 'Korten declaration',
        'Total': 'Totaal',
        'ScheduledWorkours': 'Geplande werkzaamheden uur',
        'UseSaturdayHoursAsTFT': 'Gebruik zaterdag uren als tijd-voor-tijd',
        'Period': 'Periode',
        'StartTFTRegulation': 'Start tijd-voor-tijd regeling',
        'Home': 'Hoofdpagina',
        'Time': 'Tijd',
        'Overview': 'Overzicht',
        'Declaration': 'Declaraties',
        'Settings': 'Instellingen',
        'Logout': 'Uitloggen',
        //for login page
        'Login': 'Log In',
        'Registration': 'Registratie',
        'ForgotPassword': 'Wachtwoord vergeten',
        'EnterUsername': 'Voer gebruikersnaam',
        'LastName': 'Achternaam',
        'EnterLastName': 'Voer achternaam',
        'Email': 'Email',
        'InvalidEmail': 'Ongeldig',
        'InvalidRegNum': 'Ongeldig Registration number',
        'Password': 'Wachtwoord',
        'PasswordsNotMatch': 'Wachtwoorden komen niet overeen',
        'RepeatPassword': 'Herhaal wachtwoord',
        'InsufficientlyComplexPassword': 'Onvoldoende complex wachtwoord',
        'EnterYourEmail': 'Vul uw e-mail',
        'SendPassword': 'Stuur wachtwoord',
        'EnterEmailAddress': 'Geef e-mail adres',
        'PasswordEmpty': 'Password leeg'
    });

    $translateProvider.preferredLanguage(defaultLang);
}]);