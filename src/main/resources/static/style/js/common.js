var URL = "";

$.extend({
    noauthAjax: hkshopAjaxNoAuth,
})

function hkshopAjaxNoAuth(options) {
    var options = getOptions(options)
    $.ajax({
        type: options.type,
        async: options.async,
        contentType: options.contentType,
        url: URL + options.url,
        data: options.data,
        dataType: options.dataType,
        success: options.success,
        error: options.error
    });
};

function getOptions(options) {
    options.url = (options.url == null || options.url == "" || typeof(options.url) == "undefined") ? "" : options.url;
    options.async = (options.async == null || options.async == "" || typeof(options.async) == "undefined") ? "true" : options.async;
    options.type = (options.type == null || options.type == "" || typeof(options.type) == "undefined") ? "post" : options.type;
    options.dataType = (options.dataType == null || options.dataType == "" || typeof(options.dataType) == "undefined") ? "json" : options.dataType;
    options.data = (options.data == null || options.data == "" || typeof(options.data) == "undefined") ? "" : options.data;
    options.success = (options.success == null || options.success == "" || typeof(options.success) == "undefined") ? function(data) {} : options.success;
    options.error = (options.error == null || options.error == "" || typeof(options.error) == "undefined") ? function(xhr, status, error) {} : options.error;
    options.beforeSend = (options.beforeSend == null || options.beforeSend == "" || typeof(options.beforeSend) == "undefined") ? function(request) {} : options.beforeSend;
    options.contentType = (options.contentType == null || options.contentType == "" || typeof(options.contentType) == "undefined") ? "application/x-www-form-urlencoded" : options.contentType;
    if(options.contentType.toLowerCase() == "application/json") {
        options.data = JSON.stringify(options.data);
    }
    return options;
}