app.controller("authority-ctrl", function ($scope,$http,$location){

    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];


    $scope.initialize = function() {
        //load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })

        //load staff and directors(administrator)
        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })

        //load authorities
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorize");
        });
    }

        $scope.authority_of = function(acc,role){
            if($scope.authorities){
                return $scope.authorities.find(a => a.account.username == acc.username && a.role.id == role.id);
            }
        }

        $scope.authority_changed = function(acc,role){
            var authority = $scope.authority_of(acc,role);
            if(authority){ // has authorized => recall authorize(delete)
                $scope.revoke_authority(authority);
            }else{ // has not authorized => update authorize
                authority = { account : acc, role : role};
                $scope.grant_authority(authority);
            }
        }

        $scope.revoke_authority = function(authority){
            $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
                var index = $scope.authorities.findIndex(a => a.id == authority.id);
                $scope.authorities.splice(index,1);
                alert("User was recalled authority");
            }).catch(error => {
                alert('Error when processing to recall authority');
                console.log('Error', error);
            })
        }

        $scope.grant_authority = function(authority){
            $http.post(`/rest/authorities`,authority).then(resp => {
                $scope.authorities.push(resp.data);
                alert('User was added authority');
            }).catch(error => {
                alert('Error when processing to add authority');
                console.log('Error',error);
            })
        }


    $scope.initialize();
});