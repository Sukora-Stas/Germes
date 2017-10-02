'use strict';

var app = angular.module('app', ['ngResource']);

app.factory('cityService', [ '$resource', function($resource) {
    return $resource('/api/cities');
}
]);
