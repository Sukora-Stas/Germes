'use strict';

var app = angular.module('app', []);

app.factory('cityService', [function () {
    var cities = [{
        'name': 'Odessa',
        'district': '',
        'region': 'Odessa'
    },
        {
            'name': 'Izmail',
            'district': 'Izmail',
            'region': 'Odessa'
        }];

    return {
        'getCities': function () {
            return cities;
        }
    }
}
]);
