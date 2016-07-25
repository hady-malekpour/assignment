define(['jquery', 'underscore', 'backbone','collections/document/DocumentCollection','text!templates/list/listTemplate.html'], function($, _, Backbone,DocumentCollection, listTemplate) {
    var ListView = Backbone.View.extend({
        el: $("#page"),
        render: function() {
        	var that = this;
        	this.collection = new DocumentCollection();
        	this.collection.fetch({
               success: function(collection, response) {
                   var template = _.template(listTemplate, {
                       docs: that.collection.models
                   });
                   that.$el.html(template);
               },
               error: function(collection, response) {
                   console.log("error");
               }
           });
        },
    });
    return ListView;
});