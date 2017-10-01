app.controller('CityCtrl', [ '$scope', function($scope) {
    $scope.cities = [{
        'name' : 'Odessa',
        'district' : '',
        'region' : 'Odessa'
    },
        {
            'name' : 'Izmail',
            'district' : 'Izmail',
            'region' : 'Odessa'
        }];
}
]);
