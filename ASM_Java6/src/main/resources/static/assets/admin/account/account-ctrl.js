app.controller("account-ctrl", function ($scope,$http){
    $scope.items = [];
    $scope.form = {};



    $scope.initialize = function(){
        // load product
        $http.get("/rest/accounts").then(resp =>{
            $scope.items = resp.data;
        })
    }


    // Init
    $scope.initialize();

    //Reset form
    $scope.reset = function(){
        $scope.form = {};
        $(".icon-refresh").addClass("btn--disabled")
        $(".icon-trash").addClass("btn--disabled")
        $(".icon-plus").removeClass("btn--disabled")
    }

    //Edit form
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
        $(".icon-plus").addClass("btn--disabled")
    }

    //Create new product
    $scope.create = function(){
        var item = angular.copy($scope.form);
        $http.post(`/rest/accounts`,item).then(resp =>{
            $scope.items.push(resp.data);
            $scope.reset();
            alert('New account was created successfully!');
        }).catch(error => {
            alert('Error when creating new account!');
            console.log('Error',error);
        })
    }

    //Update product
    $scope.update = function(){
        let item = angular.copy($scope.form);
        $http.put(`/rest/accounts/${item.username}`,item).then(resp =>{
            var index = $scope.items.findIndex(p => p.username == item.username);
            $scope.items[index] = item;
            alert('Account was updated successfully!');
        }).catch(error => {
            alert('Error when updating this account!');
            console.log('Error',error);
        })
    }

    //Delete product
    $scope.delete = function(item){
        $http.delete(`/rest/accounts/${item.username}`).then(resp =>{
            var index = $scope.items.findIndex(p => p.username == item.username);
            $scope.items.splice(index,1);
            $scope.reset();
            $scope.initialize();
            alert('Account was deleted successfully!');
        }).catch(error => {
            alert('Error when deleting this account!');
            console.log('Error',error);
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