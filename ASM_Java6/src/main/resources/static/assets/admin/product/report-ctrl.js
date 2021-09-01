app.controller("report-ctrl", function ($scope,$http){
    $scope.items = [];

    $scope.initialize = function(){
        // load product
        $http.get("/rest/products/inventory-by-category").then(resp =>{
            $scope.items = resp.data;
        })

    }

    // Init
    $scope.initialize();

})