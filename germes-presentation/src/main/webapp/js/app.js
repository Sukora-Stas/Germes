'use strict';

var app = angular.module('app', [ 'ngResource', 'pascalprecht.translate' ]);

app.factory('cityService', [ '$resource', function($resource) {
    return $resource('/Germes/api/cities');
} ]);

app.config(function($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: 'l10n/locale-',
        suffix: '.json'
    });
    $translateProvider.preferredLanguage('en');
    $translateProvider.useSanitizeValueStrategy('escape');
});