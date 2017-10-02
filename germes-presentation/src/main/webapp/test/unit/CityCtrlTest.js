describe('Testing City Controller', function() {

    it('Rows per page should have initial value', function() {
        module("app");
        var scope = {};
        var ctrl;
        inject(function($controller) {
            ctrl = $controller('CityCtrl', { $scope : scope});
        });

        expect(scope.rowsPerPage).toBeDefined();
        expect(scope.rowsPerPage).toBe(10);
    });

});