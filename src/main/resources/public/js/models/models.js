window.DocumentModel = Backbone.Model.extend({

    urlRoot: "api/documents",

    initialize: function () {
        this.validators = {};

        this.validators.title = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter a title"};
        };

        this.validators.text = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter a text"};
        };
    },

    validateItem: function (key) {
        return (this.validators[key]) ? this.validators[key](this.get(key)) : {isValid: true};
    },

   validateAll: function () {

        var messages = {};

        for (var key in this.validators) {
            if(this.validators.hasOwnProperty(key)) {
                var check = this.validators[key](this.get(key));
                if (check.isValid === false) {
                    messages[key] = check.message;
                }
            }
        }

        return _.size(messages) > 0 ? {isValid: false, messages: messages} : {isValid: true};
    },

    defaults: {
        id: null,
        title: "",
        text: "",
        rank: null
    }
});

window.DocumentCollection = Backbone.Collection.extend({

    model: DocumentModel,
    url: function(){
        if($(location).attr('hash').startsWith("#search/")){
            return "api/documents?query="+$(location).attr('hash').substring(8);
        }
        return "api/documents";
    }

});