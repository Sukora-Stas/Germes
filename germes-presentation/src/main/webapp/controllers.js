app.controller('CityCtrl', ['$scope', 'cityService', function ($scope, cityService) {
    $scope.cities = cityService.getCities();
}
]);
