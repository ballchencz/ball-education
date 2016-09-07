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
        "getAllCourseCategory":contextPath+"/categoryController/getAllCourseCategory",
        "amCourse":contextPath+"/adminController/amCourse",
        "getUserBasicByRoles":contextPath+"/userController/getUserBasicByRoles",
        "getCategoryById":contextPath+"/categoryController/getCategoryById",
        "getCoursePictureByCourseId":contextPath+"/courseController/getCoursePictureByCourseId",
        "getCourseByCourseId":contextPath+"/courseController/getCourseByCourseId",
        "getCourseUserBasicByCourseId":contextPath+"/courseController/getCourseUserBasicByCourseId"
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
            $("#courseAmForm").validate({
                submitHandler: function (form) {
                    $(form).ajaxSubmit({
                        url: URL.amCourse,
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
                    courseName: {
                        required: true,
                        minlength: 1,
                        maxlength: 50
                    },
                    courseType: {
                        required: true
                    },
                    citySel: {
                        required: true
                    },
                    price: {
                        required: true
                    },
                    denied: {
                        required: true
                    },
                    recommend: {
                        required:true
                    },
                    courseInfo:{
                        maxlength:500
                    },
                    courseIntro:{
                        maxlength:500
                    }
                },
                messages: {
                    courseName: {
                        required: e + "请输入课程名称",
                        minlength: e + "课程名称不能小于5个字符",
                        maxlength: e + "课程名称不能大于50个字符"
                    }, courseType: {
                        required: e + "请选择课程类型"
                    }, citySel: {
                        required: e + "请选择课程分类"
                    }, price: {
                        required: e + "请输入课程价格"
                    }, denied: {
                        required: e + "请选择禁用状态"
                    }, recommend: {
                        required: e + "请选择推荐状态"
                    }, courseInfo:{
                        maxlength:e + "课程须知不能超过500个字符"
                    }, courseIntro:{
                        maxlength:e + "课程简介不能超过500个字符"
                    }
                }
            });
            /*绑定知识点添加按钮点击事件*/
            $("#addKnowledgePointBtn").bind('click',function(){
                var knowledgeName = $(this).parent("div#knowledgePointForm").find('input#knowledgeName').val();
                var knowledgeDescription = $(this).parent("div#knowledgePointForm").find('textarea#knowledgeDes').val();
                var arr = [];
                if(knowledgeName){
                    var knowledgePointPanelTemp = $("#knowledgePointPanelTemp");
                    //判断有无知识点面板元素
                    var childrenLength = $("#accordion").children().length;
                    var knowledgeNameInput = knowledgePointPanelTemp.find("a").next("input");
                    var knowledgeDesInput = knowledgePointPanelTemp.find(".panel-body").next("input");
                    if(childrenLength==0){//设置input框中的name为0
                        knowledgeNameInput.attr('name',"knowledgePoints[0].knowledgeName");
                        knowledgeDesInput.attr("name","knowledgePoints[0].description");
                    }else{//设置input框中的name为length+1
                        knowledgeNameInput.attr('name',"knowledgePoints["+length+1+"].knowledgeName");
                        knowledgeDesInput.attr("name","knowledgePoints["+length+1+"].description");
                    }
                    //给知识点元素赋值
                    knowledgePointPanelTemp.find("a").html(knowledgeName);
                    knowledgeNameInput.val(knowledgeName);
                    knowledgePointPanelTemp.find(".panel-body").html(knowledgeDescription);
                    knowledgeDesInput.valueOf(knowledgeDescription);
                    //克隆知识点元素
                    var temp = knowledgePointPanelTemp.clone(true);
                    arr.push(temp);
                }
                //添加知识点面板
                $("#accordion").append(arr[0]);
                $("#accordion").children().show();
            });
            /*初始化课程类型select*/
            $.get(URL.getAllCourseTypeJSON,function(data){
                for(var key in data){
                    $("select[name='courseType']").append("<option value='"+key+"'>"+data[key]+"</option>")
                }
            });
            /*初始化课程用户*/
            var roleCodes = ["TEACHER"];
            $.get(URL.getUserBasicByRoles,{roleCode:""+roleCodes+""},function(data){
                for(var i=0;i<data.length;i++){
                    $("select[name='userBasicIds']").append("<option value='"+data[i].id+"'>"+data[i].userName+"</option>");
                }
            })


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

    var zNodes;
    $.ajax({
        type: "GET",
        url: URL.getAllCourseCategory,
        async:false,
        success: function(msg){
            zNodes = msg;
        }
    });
    $.fn.zTree.init($("#courseTypeCategory"), setting, zNodes);

    /*初始化课程分类*/
    var categoryId = $("input[name='categoryId']").val();
    if(categoryId){
        $.get(URL.getCategoryById,{id:categoryId},function(data){
            $("input[name='citySel']").val(data.categoryName);
        });
    }
    /*初始化课程图片*/
    var courseId = $("#courseId").val();
    if(courseId){
        $.get(URL.getCoursePictureByCourseId,{id:courseId},function(data){
            if(data){
                $("#preview").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+data.id);
            }
        });
    }
    /*初始化课程类型,课程所属用户*/
    if(courseId){
        $.get(URL.getCourseByCourseId,{id:courseId},function(data){
            $("select[name='courseType']").val(data.courseType);
        })
    }
    /*初始化课程用户*/
    if(courseId){
        $.get(URL.getCourseUserBasicByCourseId,{id:courseId},function(data){
            $("select[name='userBasicIds']").val(data[0].id);
        })
    }

});

var menuContentId;
var ztreeNodeId;
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(ztreeNodeId);
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
}

function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(ztreeNodeId),
        nodes = zTree.getCheckedNodes(true),
        v = "";
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].categoryName + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#citySel");
    cityObj.val(v);
    $("input[name='categoryId']").val(treeNode.id);
}

function showMenu(input,menuContent) {
    var cityObj = $(input);
    var cityOffset = $(input).offset();
    $("#"+menuContentId).css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
    menuContentId = menuContent;
    ztreeNodeId = $("#"+menuContentId).children(".panel-body").children(".ztree").attr("id");
    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#"+menuContentId).fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    var targetId = event.target.id;
    if (!(targetId.indexOf("switch")>0)) {
        hideMenu();
    }
}