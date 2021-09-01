

const app = angular.module('shopping-cart-app',[]);

app.controller("shopping-cart-ctrl",function($scope,$http){
    $scope.cartLines = [];
    //Cart-Management
    $scope.cart ={
        items : [],
        //Adding product
        add(id){
            var item = this.items.find(item => item.id == id);
            if(item){
                item.qty++;
                this.saveToLocalStorage();
            }else{
                $http.get(`/rest/products/${id}`).then(resp =>{
                    $scope.cartLines.push(resp.data);
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                    alert('The item id: ' + id +' was added in the your cart');
                });
            }
        },
        //Removing product
        remove(id){
            var index = this.items.findIndex(item => item.id == id);
            var c = confirm("Bạn thực sự muốn xoá sản phẩm này?");
            if(c == true){
                this.items.splice(index,1);
                this.saveToLocalStorage();
            }

        },
        //Removing all product
        clear(){
            this.items = [];
            this.saveToLocalStorage();
        },
        //total of one product
        amt_of(item){},

        //get amount of items in cart
        get count(){
          return this.items
              .map(item => item.qty)
              .reduce((total,qty) => total += qty,0);
        },

        //get total money of items in cart
        get amount(){
          return this.items
              .map(item => item.qty * item.price)
              .reduce((total,qty) => total += qty,0);
        },
        //Saving the cart to LocalStorage
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);
        },

        //Reading items in the cart from LocalStorage
        loadFromLocalStorage(){
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }

    };

    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate : new Date(),
        address: "",
        account: {username: $("#username").text()},
        get orderDetails(){
            return $scope.cart.items.map(item => {
                return{
                    product: {id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase(){
            var order = angular.copy(this);
            //processing order
            $http.post('/rest/orders',order).then(resp =>{
                alert("Your order was submitted !!")
                $scope.cart.clear();
                location.href = "/order/details/" + resp.data.id;
            }).catch(error =>{
                alert("An error occurred during processing, please try again!!");
                console.log(error);
            })

        }
    }
})



