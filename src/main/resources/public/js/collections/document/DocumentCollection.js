define(['jquery', 'underscore', 'backbone', 'models/document/DocumentModel'], function($, _, Backbone, DocumentModel) {
    var documentCollection = Backbone.Collection.extend({
        model: DocumentModel,
        url: "/documents",
        parse: function(data) {
            return data;
        }
    });
    return documentCollection;
});