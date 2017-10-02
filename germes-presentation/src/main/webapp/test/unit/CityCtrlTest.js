describe('Testing City Controller', function () {

    beforeEach(module("app"));

    var scope = {}, httpBackend;
    beforeEach(inject(function ($controller, $httpBackend) {
        $controller('CityCtrl', {$scope: scope});
        httpBackend = $httpBackend;
    }));

    afterEach(function () {
        //Cleaning resources
    });

    it('Rows per page should have initial value', function () {

        expect(scope.rowsPerPage).toBeDefined();
        expect(scope.rowsPerPage).toBe(10);
    });

    it('Should return false for non-region center', function () {

        var city = {
            name: 'Gribovka',
            district: 'Ovidiopol',
            region: 'Odessa'
        };
        var center = scope.isRegionCenter(city);

        expect(center).toBeFalsy();
    });

    it('Should return true for region center', function () {

        var city = {
            name: 'Kyiv',
            district: '',
            region: 'Kyiv'
        };
        var center = scope.isRegionCenter(city);

        expect(center).toBeTruthy();
    });

    it('Should query cities', function () {

        httpBackend.expectGET("l10n/locale-en.json").respond(
            [
                {}
            ]);

        httpBackend.expectGET("/api/cities").respond(
            [
                {
                    'name': 'Odessa',
                    'district': '',
                    'region': 'Odessa'
                }
            ]);
        httpBackend.flush();

        expect(scope.cities[0].name).toBe('Odessa');
        expect(scope.cities[0].district).toBe('');
        expect(scope.cities[0].region).toBe('Odessa');
    });
});