window.DocumentListView = Backbone.View.extend({

    initialize: function () {
        this.render();
    },

    render: function () {
        var documents = [];
        _.each(this.model.models, function(item) {
            documents.push(item.toJSON());
        });
        $(this.el).html(new DocumentSearchView().render().el);
        $(this.el).append(this.template({'documents' : documents}));
        return this;
    }
});

window.DocumentSearchView = Backbone.View.extend({

    initialize: function () {
         this.render();
    },

    render: function () {
        $(this.el).html(this.template());
        return this;
    },

    events: {
        "click .search"   : "search"
    },
     search: function () {
        app.navigate('search/' + $(this.el).find("#query").val(), true);
     }
});