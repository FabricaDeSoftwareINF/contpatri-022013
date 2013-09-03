Ctrl = (function() {
  "use strict";

  function Ctrl() {}

  Ctrl.prototype.List = function($scope, InventarioResource) {
    $scope.inventario = Resource.query();
  }

  Ctrl.prototype.New = function($scope, $location, InventarioResource) {
	  $scope.inventario = new InventarioResource({
	  	"id":0, "name":'', 
	  	"description":'', "url":''
	  });
	  
	  $scope.save = function() {
	  	console.log('save button');
	  	
	  	$scope.inventario.$save(function(res) {
	  		$location.path('/');
	  	});
	  }
  }
  
  Ctrl.prototype.Edit = function(
  	$scope, $routeParams, $window, 
  	$location, InventarioResource
  ) {
  	InventarioResource.get({param1: $routeParams.id}, function(res) {
  		$scope.inventario = res;
  		
  		console.log($scope.inventario);
  	});
  	
    $scope.save = function() {
    	$scope.bookmark.$update({param1: $routeParams.id}, function(res) {
    			$location.path('/');
    	});
    }
    
    $scope.destroy = function() {
    	var confirm = $window.confirm('Delete '+$scope.inventario.name+ ' inventario?');
    	if(confirm) {
    		$scope.inventario.$delete({param1: $routeParams.id}, function(res) {
    			$location.path('/');
    		});
    	}
    }
  }

  return Ctrl;
})();

var ctrl = new Ctrl();