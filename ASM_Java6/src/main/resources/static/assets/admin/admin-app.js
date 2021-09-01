let app = angular.module("admin-app", ["ngRoute"]);

app.run(function($rootScope,$http,$timeout){
    $rootScope.invoiceDetail = null;
})

app.config(function($routeProvider, $locationProvider) {

    $routeProvider
        .when("/product", {
            templateUrl: "product/index.html",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .when("/inventory-report", {
            templateUrl: "product/inventory-report.html",
            controller: "report-ctrl"
        })
        .when("/account-management", {
            templateUrl: "account/index.html",
            controller: "account-ctrl"
        })
        .when("/invoice-report", {
            templateUrl: "invoice/index.html",
            controller: "invoice-ctrl"
        })
        .when("/invoice-details", {
            templateUrl: "invoice/invoice-details.html",
            controller: "invoice-detail-ctrl"
        })
        .otherwise({
            template: "<h1 class='text-center'>FPT Polytechnic Administrator</h1>"
        });
})


app.controller("admin-app", function ($scope,$http){
    $scope.amountOfProducts = 0;
    $scope.amountOfAccounts = 0;
    $scope.amountOfInvoices = 0;

    $scope.count = function(){
        //load count product
        $http.get("/rest/products/count").then(resp => {
            $scope.amountOfProducts = resp.data;
        })
    }

    $scope.countAccounts = function(){
        //load count product
        $http.get("/rest/accounts/count").then(resp => {
            $scope.amountOfAccounts = resp.data;
        })
    }

    $scope.countInvoices = function(){
        //load count product
        $http.get("/rest/orders/count").then(resp => {
            $scope.amountOfInvoices = resp.data;
        })
    }

    $scope.countInvoices();
    $scope.countAccounts();
    $scope.count();


})