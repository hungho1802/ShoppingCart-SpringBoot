app.controller("invoice-detail-ctrl", function ($rootScope,$scope,$http){
    $scope.items = [];

    $scope.initialize = function(){
        // load product
        $scope.items = $rootScope.invoiceDetail;
        $scope.total = 0;
        for(let i = 0 ; i < $scope.items.length; i++){
            $scope.total += $scope.items[i].price;
        }
    }

    // Init
    $scope.initialize();


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