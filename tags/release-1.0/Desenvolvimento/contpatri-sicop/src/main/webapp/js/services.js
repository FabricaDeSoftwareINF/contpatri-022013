angular.module('InventarioService', ['ngResource'])

  .factory('InventarioResource', function($resource) {
    
    var api = $resource(
      'api/inventario/:param1/:param2',
      {
        'param1': ''
      , 'param2': ''
      }, {
        'update': { 'method': 'PUT' }
      }
    );

    return api;
  	  
  });

// http://docs.angularjs.org/api/ngResource.$resource