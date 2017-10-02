describe('Testing City Controller', function() {

    beforeEach(module("app"));

    var scope = {};
    beforeEach(inject(function($controller) {
        $controller('CityCtrl', { $scope : scope});
    }));

    afterEach(function() {
        //Cleaning resources
    });

    it('Rows per page should have initial value', function() {

        expect(scope.rowsPerPage).toBeDefined();
        expect(scope.rowsPerPage).toBe(10);
    });

});