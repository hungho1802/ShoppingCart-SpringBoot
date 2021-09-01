app.controller("invoice-ctrl", function ($rootScope,$scope,$http){
    $scope.items = [];

    $scope.initialize = function(){
        // load product
        $http.get("/rest/orders").then(resp =>{
            $scope.items = resp.data;
            $scope.items.reverse();
        })
    }

    // Init
    $scope.initialize();

    $scope.find = function(item){
        $http.get(`/rest/orders/find/${item.id}`).then(resp =>{
            $rootScope.invoiceDetail = resp.data;
            window.location.href="index.html#!invoice-details";
        })
    }

    $scope.pager = {
        page: 0,
        size: 10,
        get items(){
            var start = this.page * this.size;
            return $scope.items.slice(start,start + this.size)
        },
        get count(){
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first(){
            this.page = 0;
        },
        prev(){
            this.page--;
            if(this.page < 0){
                this.last();
            }
        },
        next(){
            this.page++;
            if(this.page >= this.count){
                this.first();
            }
        },
        last(){
            this.page = this.count - 1;
        }
    }

})