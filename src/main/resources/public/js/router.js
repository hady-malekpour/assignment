// Filename: router.js
define([
  'jquery',
  'underscore',
  'backbone',
  'views/list/ListView',
], function($, _, Backbone, ListView) {
  
  var AppRouter = Backbone.Router.extend({
    routes: {
      // Define some URL routes
	  		'list': 'showList',         //list view as well
      
      // Default
      '*actions': 'defaultAction'
    }
  });
  
  var initialize = function(){

    var app_router = new AppRouter;
	
	app_router.on('route:showList', function () {
    
        var listView = new ListView();
        listView.render();
    });
	
    app_router.on('route:defaultAction', function () {
     
    	var listView = new ListView();
        listView.render();
    });

    // Unlike the above, we don't call render on this view as it will handle
    // the render call internally after it loads data. Further more we load it
    // outside of an on-route function to have it loaded no matter which page is
    // loaded initially.

    Backbone.history.start();
  };
  return { 
    initialize: initialize
  };
});
