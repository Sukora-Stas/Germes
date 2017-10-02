'use strict';

var app = angular.module('app', ['ngResource', 'pascalprecht.translate']);

app.factory('cityService', ['$resource', function ($resource) {
    return $resource('/Germes/api/cities');
}]);

app.config(function ($translateProvider) {
    $translateProvider.translations('en', {
        TITLE: 'Germes project',
        CITIES: 'Cities',
        CITY_NAME: 'Name',
        CITY_DISTRICT: 'District',
        CITY_REGION: 'Region'
    });
    $translateProvider.translations('ru', {
        TITLE: 'Проект Гермес',
        CITIES: 'Населенные пункты',
        CITY_NAME: 'Название',
        CITY_DISTRICT: 'Район',
        CITY_REGION: 'Область'
    });
    $translateProvider.translations('uk', {
        TITLE: 'Проект Гермес',
        CITIES: 'Населені пункти',
        CITY_NAME: 'Назва',
        CITY_DISTRICT: 'Район',
        CITY_REGION: 'Область'
    });
    $translateProvider.preferredLanguage('en');
});