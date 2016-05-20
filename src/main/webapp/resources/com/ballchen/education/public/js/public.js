/**
 * Created by ChenZhao on 2016/5/19.
 */
seajs.config({
    base: contextPath+"/resources/com/ballchen/education/",
    alias: {
        "jquery": "public/js/jquery.js",
        "public": "public/js/public.js",
        "bootstrap":"public/js/bootstrap.js"
    }
});

define(function(require,exports,module){
    var public  = {};
    public.SerializeFrom = function(formObject){
        var formData = formObject.serializeArray();
        var returnObject = {};
        for(var i=0;i<formData.length;i++){
            returnObject[formData[i].name] = formData[i].value;
        }
        return returnObject;
    }
    module.exports = public;
});
