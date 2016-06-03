/**
 * Created by chenzhao on 2016/6/3.
 */
define(function(require,exports,module){
    var public  = {};
    var ALERT_STATE = {
        "success":"alert-success",
        "info":"alert-info",
        "warning":"alert-warning",
        "danger":"alert-danger"
    }
    /**
     *序列化表单参数
     * @param formObject 表单jquery对象
     * @returns {{}}
     * @constructor
     */
    public.SerializeFrom = function(formObject){
        var formData = formObject.serializeArray();
        var returnObject = {};
        for(var i=0;i<formData.length;i++){
            returnObject[formData[i].name] = formData[i].value;
        }
        return returnObject;
    }
    /**
     * 获得bootstrapalert弹出框
     * @param state 状态，success,info,warning,danger
     * @param title 信息标题
     * @param info 信息内容
     * @returns {string}
     */
    public.bootstrapAlert = function(state,title,info){
        var alertDiv = "<div class=\"alert "+ALERT_STATE[state]+" alert-dismissible\" role=\"alert\">"+
            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>"+
            "<strong>"+title+"</strong>"+info+
            "</div>";
        return alertDiv;
    }
    module.exports = public;
});