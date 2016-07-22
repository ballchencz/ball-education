/**
 * Created by ballchen on 2016/6/29.
 */
define(function(require,exports,module){
    var URL = {
        "saveFileServerProperties":contextPath+"/adminController/saveFileServerProperties",
        "saveQiniuFileServerProperties":contextPath+"/adminController/saveQiniuFileServerProperties"
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
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",});
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
                    minlength:1,
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
                    minlength:e+"密码不能小于1个字符",
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

        $("#qiniucloudForm").validate({
            submitHandler: function(form){
                $(form).ajaxSubmit({
                    url:URL.saveQiniuFileServerProperties,
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
                accessKey: {
                    required:true,
                    minlength:1,
                    maxlength:100
                },
                secretKey:{
                    required:true,
                    minlength:1,
                    maxlength:100
                },
                bucketName: {
                    required: true,
                    minlength: 1,
                    maxlength: 50
                },
                filePath: {
                    required:true,
                    minlength: 1,
                    maxlength: 50
                }
            },
            messages: {
                accessKey: {
                    required: e+"请输入AccessKey",
                    minlength:e+"AccessKey不能小于1个字符",
                    maxlength:e+"AccessKey不能大于100个字符"
                },secretKey: {
                    required: e+"请输入SecretKey",
                    minlength:e+"SecretKey不能小于1个字符",
                    maxlength:e+"SecretKey不能大于100个字符"
                },bucketName: {
                    required: e+"请输入空间名",
                    minlength:e+"空间名不能小于1个字符",
                    maxlength:e+"空间名不能不能大于50个字符",
                },filePath: {
                    required:e+"请输入路径",
                    maxlength: e+"路径不能大于50个字符"
                }
            }
        });
    })
})
