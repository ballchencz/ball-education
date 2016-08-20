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
        "UPDATE_FAILED_TITLE": "修改失败",
        "SAVE_SUCCESS_TITLE":"保存成功",
        "SAVE_FAILED_TITLE":"保存失败",
    };
    var URL = {
        "getAllCourseTypeJSON":contextPath+"/courseController/getCourseTypeJSON",
        "getAllCourseChapterTypeJSON":contextPath+"/courseController/getCourseChapterTypeJSON",
        "getAccessoryBytesByAccessoryId":contextPath+"/accessoryController/getAccessoryBytesByAccessoryId",
        "amUserIdCardPicture":contextPath+"/adminController/amUserIdCardPicture",
        "getAllCourseCategory":contextPath+"/categoryController/getAllCourseCategory"
    }
    parent.layer.iframeAuto(index);
    //require("validate-messages_zh");
    $.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || public.isIdCardNo(value);
    }, "请正确输入您的身份证号码");
    $.validator.setDefaults({
        ignore: "",
        highlight: function (e) {
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
        },
        success: function (e) {
            e.closest(".form-group").removeClass("has-error").addClass("has-success")
        },
        errorElement: "span",
        errorPlacement: function (e, r) {
            e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
            e.appendTo(r.is(":file")? r.parent().prev(): r.parent());
            e.appendTo(r.is(":hidden")? r.next(): r.parent());
        },
        errorClass: "help-block m-b-none",
        validClass: "help-block m-b-none"
    });
    $(function () {
            var editor = new Simditor({textarea: $("#editor"),upload:true})
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
        $("#userIdCardAmForm").validate({
            submitHandler: function (form) {
                //console.log(form);
                //var formData = public.serializeForm(form);
                $(form).ajaxSubmit({
                    url: URL.amUserIdCardPicture,
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
                        }else if(data.name == "save"){
                            if (data.flag) {
                                contentWindow.addBootstrapAlert("success", CONSTS.SAVE_SUCCESS_TITLE);
                                contentWindow.$('#userManageDataGrid').bootstrapTable('refresh');
                            } else {
                                contentWindow.addBootstrapAlert("warning", CONSTS.SAVE_FAILED_TITLE);
                            }
                        }
                        parent.layer.close(index);
                    }
                });
            },
            rules: {
                id:{
                  required:true
                },
                idCardPositiveImgFile: {
                    required: true
                },
                idCardNegativeImgFile: {
                    required: true
                }
            },
            messages: {
                id:{
                  required:e+"请先完善用户基本信息"
                },
                idCardPositiveImgFile: {
                    required: e + "请选择要上传的身份证正面"
                }, idCardNegativeImgFile: {
                    required: e + "请选择要上传的身份证正面"
                }
            }
        });
            /*初始化课程类型select*/
            $.get(URL.getAllCourseTypeJSON,function(data){
                for(var key in data){
                    $("select[name='courseType']").append("<option value='"+key+"'>"+data[key]+"</option>")
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
            /*初始化用户身份证正面*/
            var idCardPositiveId = $("input[name='idCardPositiveTemp']").val();
            if(idCardPositiveId){
                $("#previewIdCardPositive").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+idCardPositiveId);
            }
            /*初始化用户身份证反面*/
            var idCardNegativeId = $("input[name='idCardNegativeTemp']").val();
            if(idCardPositiveId){
                $("#previewIdCardNegative").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+idCardNegativeId);
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
    /*用户头像*/
    $('#checkImgFile').bind("click",function(){
        $('#previewImgFile').click();
    })

    /*身份证正面*/
    $('#checkIdCardPositiveImgFile').bind("click",function(){
        $('#idCardPositiveImgFile').click();
    })
    /*身份证反面*/
    $('#checkIdCardNegativeImgFile').bind("click",function(){
        $('#idCardNegativeImgFile').click();
    })
    var setting = {
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true,
                pIdKey: "parentId",
            },key: {
                name: "categoryName",
                rootPId: null
            }
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        },
    	view: {
            addDiyDom: function(treeId, treeNode) {
                var aObj = $("#" + treeNode.tId + "_a");
                $.get(contextPath+"/categoryController/getCategoryTypeJSON",function(data){
                    for(var i=0;i<data.length;i++){
                        if(data[i].value==treeNode.categoryType){
                            var editStr = "<span class=\"text-success\" style='padding-left: 30px;'>"+data[i].label+"</span>";
                            aObj.append(editStr);
                        }
                    }
                });
            }
        }
    };

    var zNodes =[
        {id:1, parentId:0, categoryName:"北京",value:"BEIJING"},
        {id:2, parentId:0, categoryName:"天津"},
        {id:3, parentId:0, categoryName:"上海"},
        {id:6, parentId:0, categoryName:"重庆"},
        {id:4, parentId:0, categoryName:"河北省", open:true, nocheck:true},
        {id:41, parentId:4, categoryName:"石家庄"},
        {id:42, parentId:4, categoryName:"保定"},
        {id:43, parentId:4, categoryName:"邯郸"},
        {id:44, parentId:4, categoryName:"承德"},
        {id:5, parentId:0, categoryName:"广东省", open:true, nocheck:true},
        {id:51, parentId:5, categoryName:"广州"},
        {id:52, parentId:5, categoryName:"深圳"},
        {id:53, parentId:5, categoryName:"东莞"},
        {id:54, parentId:5, categoryName:"佛山"},
        {id:6, parentId:0, categoryName:"福建省", open:true, nocheck:true},
        {id:61, parentId:6, categoryName:"福州"},
        {id:62, parentId:6, categoryName:"厦门"},
        {id:63, parentId:6, categoryName:"泉州"},
        {id:64, parentId:6, categoryName:"三明"}
    ];
    $.ajax({
        type: "GET",
        url: URL.getAllCourseCategory,
        async:false,
        success: function(msg){
            zNodes = msg;
        }
    });
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});


function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
}

function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true),
        v = "";
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].categoryName + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#citySel");
    cityObj.attr("value", v);
    $("input[name='categoryId']").val(treeNode.id);
}

function showMenu(input) {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}