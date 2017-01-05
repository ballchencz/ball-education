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
        "getCourseUserBasicByCourseId":contextPath+"/courseController/getCourseUserBasicByCourseId",
        "getCourseKnowledgeByCourseId":contextPath+"/courseController/getKnowledgePointByCourseId",
        "getCourseChapterType":contextPath+"/courseController/getCourseChapterType"
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
            //$( 'input[name="chapterFile"]' ).prettyFile();
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
                if(knowledgeName){
                    var knowledgePointPanelTemp = $("#knowledgePointPanelTemp");
                    //判断有无知识点面板元素
                    var childrenLength = $("#accordion").children().length;
                    var knowledgeNameInput = knowledgePointPanelTemp.find("a").next("input");
                    var knowledgeDesInput = knowledgePointPanelTemp.find(".panel-body").next("input");
                    if(childrenLength==0){//设置input框中的name为0
                        knowledgeNameInput.attr('name',"knowledgePoints[0].knowledgeName");
                        knowledgeDesInput.attr("name","knowledgePoints[0].description");
                        //设置panelID和a标签的指向
                        knowledgePointPanelTemp.find("a").attr("href","tabs_panels.html#knowledgePoints0");
                        knowledgePointPanelTemp.find(".panel-collapse").attr("id","knowledgePoints0");
                    }else{//设置input框中的name为length+1
                        knowledgeNameInput.attr('name',"knowledgePoints["+childrenLength+"].knowledgeName");
                        knowledgeDesInput.attr("name","knowledgePoints["+childrenLength+"].description");
                        knowledgePointPanelTemp.find("a").attr("href","tabs_panels.html#knowledgePoints"+childrenLength);
                        knowledgePointPanelTemp.find(".panel-collapse").attr("id","knowledgePoints"+childrenLength);
                    }
                    //给知识点元素赋值
                    knowledgePointPanelTemp.find("a").html(knowledgeName);
                    knowledgeNameInput.val(knowledgeName);
                    knowledgePointPanelTemp.find(".panel-body").html(knowledgeDescription);
                    knowledgeDesInput.val(knowledgeDescription);
                    //克隆知识点元素
                    var temp = knowledgePointPanelTemp.clone().removeAttr("id").appendTo($("#accordion"));
                    //添加知识点面板
                    //$("#accordion").append(temp);
                    //添加完成后将值设置为空
                    $(this).parent("div#knowledgePointForm").find('input#knowledgeName').val("");
                    $(this).parent("div#knowledgePointForm").find('textarea#knowledgeDes').val("");
                    //显示添加后的元素
                    $("#accordion").children().show();
                }
            });
            /*绑定课程知识点删除按钮事件*/
            $(document).on("click","button.close",function(){
                //删除当前知识点
                $(this).parents(".panel-default").remove();
                //重新排列当前元素知识点元素的ID和name
                var childrenElementLength = $("#accordion").children().length;
                if(childrenElementLength>0){
                    $("#accordion").children().each(function(index,value){
                        //改变a标签的href属性
                        $(value).find("a").attr("href","tabs_panels.html#knowledgePoints"+index);
                        //改变知识点名称的name属性
                        $(value).find("a").next().attr("name","knowledgePoints["+index+"].knowledgeName");
                        //改变panel-collapse的ID属性值
                        $(value).find(".panel-collapse").attr("id","knowledgePoints"+index);
                        //改变课程知识点描述name属性值
                        $(value).find(".panel-collapse").children("input").attr("name","knowledgePoints["+index+"].description");
                    });
                }
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

        var chosenConfig = {
            ".chosen-select-provence": {
                disable_search_threshold: 10,
                no_results_text: "Oops, nothing found!",
                width: "100%"
            },
            ".chosen-select-city":{
                disable_search_threshold: 10,
                no_results_text: "Oops, nothing found!",
                width: "100%"
            },
            ".chosen-select-xian":{
                disable_search_threshold: 10,
                no_results_text: "Oops, nothing found!",
                width: "100%"
            },
            ".chosen-select-street":{
                disable_search_threshold: 10,
                no_results_text: "Oops, nothing found!",
                width: "100%"
            }
        };
        for (var selector in chosenConfig)
            $(selector).chosen(chosenConfig[selector]);
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
        success: function(msg){
            zNodes = msg;
            $.fn.zTree.init($("#courseTypeCategory"), setting, zNodes);
        }
    });
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
                $("#courseImg").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+data.id);
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
    /*初始化知识点*/
    if(courseId){
        $.get(URL.getCourseKnowledgeByCourseId,{id:courseId},function(data){
            $.each(data,function(index,value){
                var knowledgePointPanelTemp = $("#knowledgePointPanelTemp");
                var knowledgeNameInput = knowledgePointPanelTemp.find("a").next("input");
                var knowledgeDesInput = knowledgePointPanelTemp.find(".panel-body").next("input");
                knowledgeNameInput.attr('name',"knowledgePoints["+index+"].knowledgeName");
                knowledgeDesInput.attr("name","knowledgePoints["+index+"].description");
                //设置panelID和a标签的指向
                knowledgePointPanelTemp.find("a").attr("href","tabs_panels.html#knowledgePoints"+index);
                knowledgePointPanelTemp.find(".panel-collapse").attr("id","knowledgePoints"+index);
                //给知识点元素赋值
                knowledgePointPanelTemp.find("a").html(value.knowledgeName);
                knowledgeNameInput.val(value.knowledgeName);
                knowledgePointPanelTemp.find(".panel-body").html(value.description);
                knowledgeDesInput.val(value.description);
                //克隆知识点元素
                knowledgePointPanelTemp.clone().removeAttr("id").show().appendTo($("#accordion"));
            })
        });
    }

    /*------------------------关于课节的js脚本开始-------------------------------*/
    /*初始化课节类型*/
    $.get(URL.getCourseChapterType,function(data){
        for(var key in data){
            $("select[name='chapterType']").append("<option value='"+key+"'>"+data[key]+"</option>");
        }
    });

    /*新增视频按钮绑定点击事件*/
    $("#addNewVideoBtn").bind('click',function(){
        //获得课节input
        var courseChapterVideo = $("#courseChapterVideo").clone();
        //获得课程节删除按钮
        var courseChapterVideoBtn = $("#courseChapterVideoBtn").clone();
        //添加课节input和删除按钮
        $("#courseChapterVideoContent").append(courseChapterVideo);
        $("#courseChapterVideoContent").append(courseChapterVideoBtn);
        $("#courseChapterVideoContent").find("input").removeAttr("id").removeClass("hide");
        $("#courseChapterVideoContent").find("a").removeAttr("id").removeClass("hide").attr("name","courseChapterRemoveBtn");
        /*课节删除按钮绑定点击事件*/
        $("a[name='courseChapterRemoveBtn']").on("click",function(){
            var length = $("a[name='courseChapterRemoveBtn']").length;
            console.log(length);
            if(length>1){
                $(this).prev().remove();
                $(this).remove();
            }else{
                $(this).prev().val();
            }
        });
        //$( 'input[name="chapterFile"]' ).prettyFile();
    });

    /*-----------------------关于课节的js脚本结束--------------------------------*/
    /*判断有无课程ID，显示或隐藏课程章节信息维护*/
    if(!courseId){
        $("#tab-2-title").addClass("hide")
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