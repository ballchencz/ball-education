/**
 * Created by chenzhao on 2016/6/3.
 */
define(function(require,exports,module){
    var public = require("public");
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.iframeAuto(index);
    var CONSTS = {
        "INSERT_SUCCESS_TITLE":"添加成功",
        "INSERT_FAILED_TITLE":"添加失败",
        "UPDATE_SUCCESS_TITLE":"修改成功",
        "UPDATE_FAILED_TITLE":"修改失败"

    };
    parent.layer.iframeAuto(index);
    //require("validate-messages_zh");
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
    $(function() {
            var e = "<i class='fa fa-times-circle'></i> ";
            $("#accountAmForm").validate({
                submitHandler: function(form){
                    $(form).ajaxSubmit({
                        url:contextPath+"/adminController/amAccount",
                        success:function(data){
                            var contentWindow = parent.$("iframe.J_iframe:visible").get(0).contentWindow;
                            if(data.name=="insert"){
                                if(data.flag){
                                    contentWindow.addBootstrapAlert("success",CONSTS.INSERT_SUCCESS_TITLE);
                                    contentWindow.$('#accountDataGrid').bootstrapTable('refresh');
                                }else{
                                    contentWindow.addBootstrapAlert("warning",CONSTS.INSERT_FAILED_TITLE);
                                }
                            }else if(data.name=="update"){
                                if(data.flag){
                                    contentWindow.addBootstrapAlert("success",CONSTS.UPDATE_SUCCESS_TITLE);
                                    contentWindow.$('#accountDataGrid').bootstrapTable('refresh');
                                }else{
                                    contentWindow.addBootstrapAlert("warning",CONSTS.UPDATE_FAILED_TITLE);
                                }
                            }
                            parent.layer.close(index);
                        }
                    });
                },
                rules: {
                    accountName: {
                        required:true,
                        minlength:1,
                        maxlength:50
                    },
                    password:{
                        required:true,
                        minlength:5,
                        maxlength:50
                    },
                    confirm_password: {
                        required: true,
                        minlength: 5,
                        maxlength: 50,
                        equalTo: "#password"
                    },
                    mark: {
                        maxlength: 500
                    }
                },
                messages: {
                    accountName: {
                        required: e+"请输入用户名",
                        minlength:e+"账户名称不能小于5个字符",
                        maxlength:e+"账户名称不能大于50个字符"
                    },password: {
                        required: e+"请输入您的密码",
                        minlength:e+"账户密码不能小于5个字符",
                        maxlength:e+"账户密码不能大于50个字符"
                    },confirm_password: {
                        required: e+"请再次输入密码",
                        minlength:e+"账户密码不能小于5个字符",
                        maxlength:e+"账户密码不能大于50个字符",
                        equalTo: e+"两次输入的密码不一致"
                    },mark: {
                        maxlength: e+"备注不能大于500个字符"
                    }
                }
            });
        }
    );

});
