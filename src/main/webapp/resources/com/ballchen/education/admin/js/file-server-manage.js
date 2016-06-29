/**
 * Created by ballchen on 2016/6/29.
 */
define(function(require,exports,module){
    var URL = {
        "saveFileServerProperties":contextPath+"/adminController/saveFileServerProperties"
    }
    $.validator.setDefaults({
        highlight: function(e) {
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
        },
        success: function(e) {
            e.closest(".form-group").removeClass("has-error").addClass("has-success")
        },
        errorElement: "span",
        errorPlacement: function(e, r) {
            e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
        },
        errorClass: "help-block m-b-none",
        validClass: "help-block m-b-none"
    });
    $(function(){
        var e = "<i class='fa fa-times-circle'></i> ";
        $("#fileServerForm").validate({
            submitHandler: function(form){
                $(form).ajaxSubmit({
                    url:URL.saveFileServerProperties,
                    success:function(data){
                        if(data.flag){
                            parent.layer.alert("保存成功");
                        }else{
                            parent.layer.alert("保存失败");
                        }
                    }
                });
            },
            rules: {
                userName: {
                    required:true,
                    minlength:1,
                    maxlength:50
                },
                password:{
                    required:true,
                    minlength:5,
                    maxlength:50
                },
                host: {
                    required: true,
                    minlength: 5,
                    maxlength: 50
                },
                port: {
                    required:true,
                    maxlength: 10
                }
            },
            messages: {
                userName: {
                    required: e+"请输入用户名",
                    minlength:e+"用户名不能小于5个字符",
                    maxlength:e+"用户名不能大于50个字符"
                },password: {
                    required: e+"请输入您的密码",
                    minlength:e+"密码不能小于5个字符",
                    maxlength:e+"密码不能大于50个字符"
                },host: {
                    required: e+"请输入服务器地址",
                    minlength:e+"服务器地址不能小于5个字符",
                    maxlength:e+"服务器地址不能不能大于50个字符",
                },port: {
                    required:e+"请输入端口号",
                    maxlength: e+"端口号不能大于十个字符"
                }
            }
        });
    })
})
