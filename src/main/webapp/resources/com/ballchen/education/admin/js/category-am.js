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
    var URL = {
        "amCategory":contextPath+"/adminController/amCategory",
        "getAccessoryBytesByAccessoryId":contextPath+"/accessoryController/getAccessoryBytesByAccessoryId",
        "getCategoryByPrimaryKey":contextPath+"/categoryController/getCategoryById"
    }
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
            e.appendTo(r.is(":file")? r.parent().prev(): r.parent());
            e.appendTo(r.is(":hidden")? r.next(): r.parent());
        },
        errorClass: "help-block m-b-none",
        validClass: "help-block m-b-none"
    });
    $(function() {

        /*初始化分类图片*/
        var accessoryId = $("#accessoryId").val();
        console.log(accessoryId)
        if(accessoryId){
            $("#categoryImg").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+accessoryId);
        }
        /*初始化上级分类*/
        var parentId = $("#parentIdTemp").val();
        if(parentId){
            $.get(URL.getCategoryByPrimaryKey,{id:parentId},function(data){
                if(data){
                    $("#parentId").combotree("setValue",{id:data.id,text:data.categoryName});
                }
            });
        }
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",});
        /*获得分类类型*/
        $.get(contextPath+"/categoryController/getCategoryTypeJSON",function(data){
            var deniedHtml = "";
            for(var i=0;i<data.length;i++){
                deniedHtml += "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
            }
            $("select[name='categoryType']").append(deniedHtml);
        });
        /*设置combotree初始值*/
        var t = $('#parentId').combotree('tree');
        t.tree('expandAll');
        var root = t.tree('getRoot');
        t.tree("toggle");
            var e = "<i class='fa fa-times-circle'></i> ";
            $("#categoryAmForm").validate({
                submitHandler: function(form){
                    $(form).ajaxSubmit({
                        url:URL.amCategory,
                        success:function(data){
                            var contentWindow = parent.$("iframe.J_iframe:visible").get(0).contentWindow;
                            if(data.name=="insert"){
                                if(data.flag){
                                    contentWindow.addBootstrapAlert("success",CONSTS.INSERT_SUCCESS_TITLE);
                                    contentWindow.$('#categoryTypeTable').treegrid('reload');
                                }else{
                                    contentWindow.addBootstrapAlert("warning",CONSTS.INSERT_FAILED_TITLE);
                                }
                            }else if(data.name=="update"){
                                if(data.flag){
                                    contentWindow.addBootstrapAlert("success",CONSTS.UPDATE_SUCCESS_TITLE);
                                    contentWindow.$('#categoryTypeTable').treegrid('reload');
                                }else{
                                    contentWindow.addBootstrapAlert("warning",CONSTS.UPDATE_FAILED_TITLE);
                                }
                            }
                            parent.layer.close(index);
                        }
                    });
                },
                rules: {
                    categoryName: {
                        required:true,
                        minlength:1,
                        maxlength:50
                    },
                    sequence:{
                        number:true
                    },
                    level:{
                        number:true
                    },
                    mark: {
                        maxlength: 500
                    }
                },
                messages: {
                    categoryName: {
                        required: e+"请输入分类名称",
                        minlength:e+"分类名称不能小于5个字符",
                        maxlength:e+"分类名称不能大于50个字符"
                    },sequence:{
                        number:e+"排序序号必须为数字（0，1，2，3......）"
                    },level:{
                        number:e+"等级必须为数字（0：一级；1：二级；2：三级）"
                    },mark: {
                        maxlength: e+"备注不能大于500个字符"
                    }
                }
            });
        }
    );

});
