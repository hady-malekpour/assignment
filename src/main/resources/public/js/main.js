var AppRouter = Backbone.Router.extend({

    routes: {
        ""                  : "list",
        "search/:query"           : "list",
        "documents/add"         : "addDocument",
        "documents/:id"         : "documentDetails"
    },

    initialize: function () {
        this.headerView = new HeaderView();
        $('.header').html(this.headerView.el);
    },

	list: function(page) {
        var documentList = new DocumentCollection();
        documentList.fetch({success: function(){
            $("#content").html(new DocumentListView({model: documentList}).el);
        }});
    },

    documentDetails: function (id) {
        var document = new DocumentModel({id: id});
        document.fetch({success: function(){
            $("#content").html(new DocumentView({model: document}).el);
        }});
    },

	addDocument: function() {
        var document = new DocumentModel();
        $('#content').html(new DocumentView({model: document}).el);
	}
});

utils.loadTemplate(['HeaderView', 'DocumentView', 'DocumentListView', 'DocumentSearchView'], function() {
    app = new AppRouter();
    Backbone.history.start();
});