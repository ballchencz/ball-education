/**
 * Created by ChenZhao on 2016/5/19.
 */
define(function(require,exports,module){
    var $ = require('jquery');
    var public = require('common');
    $(function(){
        /*绑定登录按钮事件*/
        $('.btn').bind('click',function(){
            console.log(public);
            var formData = public.serializeForm($('.btn').parents('form'));
            $.post(contextPath+'/adminController/validLogin',formData,function(data){
                if(data.flag){
                    window.location.href = contextPath+"/adminController/getAdminIndexPage";
                }
            });
        })
    })
})
/*$(function(){
    /!*绑定登录按钮事件*!/
    $('.btn').bind('click',function(){
        var formData = serializeFrom($('.btn').parents('form'));
        console.info(formData);
        $.post(contextPath+'/adminController/validLogin',formData,function(data){
            console.info(data);
        });
    })
})*/
