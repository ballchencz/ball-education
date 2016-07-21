/**
 * Created by ChenZhao on 2016/6/17.
 */

define(function (require, exports, module) {
    var public = require("common");
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.iframeAuto(index);
    var CONSTS = {
        "INSERT_SUCCESS_TITLE": "添加成功",
        "INSERT_FAILED_TITLE": "添加失败",
        "UPDATE_SUCCESS_TITLE": "修改成功",
        "UPDATE_FAILED_TITLE": "修改失败"
    };
    var URL = {
        "getAllAccountJSON":contextPath+"/accountController/getAllAccountJSON",
        "validRepeatIdNumber":contextPath+"/userController/validRepeatIdNumber",
        "getAccessoryBytesByAccessoryId":contextPath+"/accessoryController/getAccessoryBytesByAccessoryId",
    }
    parent.layer.iframeAuto(index);
    //require("validate-messages_zh");
    $.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || public.isIdCardNo(value);
    }, "请正确输入您的身份证号码");
    $.validator.setDefaults({
        highlight: function (e) {
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
        },
        success: function (e) {
            e.closest(".form-group").removeClass("has-error").addClass("has-success")
        },
        errorElement: "span",
        errorPlacement: function (e, r) {
            e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
        },
        errorClass: "help-block m-b-none",
        validClass: "help-block m-b-none"
    });
    $(function () {
            var editor = new Simditor({textarea: $("#editor")})
            $('#birthday').datepicker({
                keyboardNavigation: true,
                forceParse: true,
                autoclose: true
            });
            $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",});
            var e = "<i class='fa fa-times-circle'></i> ";
            $("#userAmForm").validate({
                submitHandler: function (form) {
                    //console.log(form);
                    //var formData = public.serializeForm(form);
                    $(form).ajaxSubmit({
                        url: contextPath + "/adminController/amUserBasic",
                        success: function (data) {
                            var contentWindow = parent.$("iframe.J_iframe:visible").get(0).contentWindow;
                            if (data.name == "insert") {
                                if (data.flag) {
                                    contentWindow.addBootstrapAlert("success", CONSTS.INSERT_SUCCESS_TITLE);
                                    contentWindow.$('#userManageDataGrid').bootstrapTable('refresh');
                                } else {
                                    contentWindow.addBootstrapAlert("warning", CONSTS.INSERT_FAILED_TITLE);
                                }
                            } else if (data.name == "update") {
                                if (data.flag) {
                                    contentWindow.addBootstrapAlert("success", CONSTS.UPDATE_SUCCESS_TITLE);
                                    contentWindow.$('#userManageDataGrid').bootstrapTable('refresh');
                                } else {
                                    contentWindow.addBootstrapAlert("warning", CONSTS.UPDATE_FAILED_TITLE);
                                }
                            }
                            parent.layer.close(index);
                        }
                    });
                },
                rules: {
                    userName: {
                        required: true,
                        minlength: 1,
                        maxlength: 50
                    },
                    nickName: {
                        required: true,
                        minlength: 1,
                        maxlength: 10
                    }, sex: {
                        required: true
                    },
                    birthday: {
                        required: true
                    },
                    email: {
                        maxlength: 50,
                        email: true
                    },
                    idNumber: {
                        maxlength: 18,
                        isIdCardNo: true,
                        remote: {
                            url: URL.validRepeatIdNumber,     //后台处理程序
                            type: "get",               //数据发送方式
                            dataType: "json",
                            data:{
                                id: function() {
                                    return $("#userBasicId").val();
                                }
                            }
                        }
                    }, phone: {
                        maxlength: 30
                    }, homePhone: {
                        maxlength: 30
                    }, denied: {
                        required: true
                    },accountId:{
                        required:true
                    },mark:{
                        maxlength:500
                    }
                },
                messages: {
                    userName: {
                        required: e + "请输入用户姓名",
                        minlength: e + "用户姓名不能小于5个字符",
                        maxlength: e + "用户姓名不能大于50个字符"
                    }, nickName: {
                        required: e + "请输入昵称",
                        minlength: e + "昵称不能小于1个字符",
                        maxlength: e + "昵称不能大于10个字符"
                    }, sex: {
                        required: e + "请选择性别"
                    }, birthday: {
                        required: e + "请输入出生日期"
                    }, email: {
                        maxlength: e + "邮箱不能大于50个字符",
                        email: e + "请填写合法的email地址"
                    }, idNumber: {
                        maxlength: e + "身份证号码不能大于18个字符",
                        isIdCardNo: e + "请填写合法的身份证号码",
                        remote:e+"身份证号码已存在"
                    }, phone: {
                        maxlength: e + "手机号码不能大于30个字符"
                    }, homePhone: {
                        maxlength: e + "家庭电话不能大于30个字符"
                    }, denied: {
                        required: e + "请选择禁用状态"
                    },accountId:{
                        required: e + "请选择所属账户"
                    },mark:{
                        maxlength:e+"备注不能大于500个字符"
                    }
                }
            });
            /*初始化账户select*/
            $.get(URL.getAllAccountJSON,function(data){
                data = $.parseJSON(data);
                for(var i=0;i<data.length;i++){
                    $("select").append("<option value='"+data[i].id+"'>"+data[i].accountName+"</option>")
                }
            })
            /*初始化角色数据*/
            var roleCodeArr = [];
            $("input[name='roleCodeTemp']").each(function(index,value){
                roleCodeArr.push($(value).val());
            });
            if(roleCodeArr.length>0){
                for(var i=0;i<roleCodeArr.length;i++){
                    $(".roleCode").each(function(index,value){
                        if($(value).val() == roleCodeArr[i]){
                            $(value).iCheck('check');
                        }
                    });
                }
                $("input[name='roleCodeTemp']").iCheck('destroy');
            }
            /*初始化用户头像*/
            var accessoryId = $("input[name='accessoryTemp']").val();
            if(accessoryId){
                $("#preview").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+accessoryId);
            }
        }
    );
//头像上传
    /*    $('#img-cropper').cropper({
     aspectRatio: 16 / 9,
     crop: function(e) {
     // Output the result data for cropping image.
     console.log(e.x);
     console.log(e.y);
     console.log(e.width);
     console.log(e.height);
     console.log(e.rotate);
     console.log(e.scaleX);
     console.log(e.scaleY);
     }
     });*/
    $('#checkImgFile').bind("click",function(){
        $('#imgFile').click();
    })


});
