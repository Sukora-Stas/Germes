app.controller('CityCtrl', [ '$scope', 'cityService', function($scope, cityService) {
    $scope.rowsPerPage = 10;
    $scope.cities = cityService.query();
}
]);
