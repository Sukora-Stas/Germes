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

    it('Should return false for non-region center', function() {

        var city = {
            name : 'Gribovka',
            district : 'Ovidiopol',
            region : 'Odessa'
        };
        var center = scope.isRegionCenter(city);

        expect(center).toBeFalsy();
    });

    it('Should return true for region center', function() {

        var city = {
            name : 'Kyiv',
            district : '',
            region : 'Kyiv'
        };
        var center = scope.isRegionCenter(city);

        expect(center).toBeTruthy();
    });
});