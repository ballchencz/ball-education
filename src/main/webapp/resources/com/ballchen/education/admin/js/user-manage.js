/**
 * Created by Administrator on 2016/6/16.
 */
define(function(require,exports,module){
    var URL = {
        "getUserManagePagination":contextPath+"/adminController/getUserBasicPagination",
        "deleteUserBasicByIds":contextPath+"/adminController/deleteUserBasicByIds",
        "accessoryOrDeniedUser":contextPath+"/adminController/accessOrDeniedUser",
        "realNameValid":contextPath+"/adminController/realNameValid",
        "getFirstCreateTimeUserBasic":contextPath+"/adminController/getFirstCreateTimeUserBasic",
        "getAccessoryBytesByAccessoryId":contextPath+"/accessoryController/getAccessoryBytesByAccessoryId",
        "defaultHeadPicture":contextPath+"/resources/com/ballchen/education/hplus/img/a2.jpg",
        "defaultIdCardPicturePositive":contextPath+"/resources/com/ballchen/education/hplus/img/a2.jpg",
        "defaultIdCardPictureNegative":contextPath+"/resources/com/ballchen/education/hplus/img/a2.jpg",
        "getAccessoryByUserBasicIdAndIdCardPictureFileType":contextPath+"/accessoryController/getAccessoryByUserBasicIdAndIdCardPictureFileType"
    }

    var common =require("common");

    $('#userManageDataGrid').bootstrapTable({
        classes:'table table-hover',
        locale:'zh-CN',
        idField:'id',
        url:URL.getUserManagePagination,
        sidePagination:"server",
        pagination:true,
        pageSize:10,
        pageList:[10,20,30],
        search:false,
        sortOrder:"desc",
        showRefresh:true,
        showToggle:true,
        showColumns:true,
        showExport:true,
        clickToSelect:true,
        toolbar:'#userManageDataGridToolbar',
        rowStyle:function(row,index){
            if (index % 2 !== 0) {
                return {
                    classes: 'active'
                };
            }
            return {};
        },
        onClickRow:function(row){
            var id = row.id;
            /*显示右侧用户信息*/
            $.get(URL.getFirstCreateTimeUserBasic,{id:id},function(data){
                data = $.parseJSON(data.userBasic);
                if(data){
                    //加载用户姓名
                    $(".userName").text(data.userName);
                    //加载个人简介
                    $(".description").html(data.description);
                    //加载备注
                    $(".mark").text(data.mark);
                    //加载头像
                    var accessoryId;
                    if(data.accessories){
                        accessoryId = data.accessories[0].id
                    }
                    if(accessoryId){
                        $("#headpicture").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+accessoryId);
                    }else{
                        $("#headpicture").attr("src",URL.defaultHeadPicture);
                    }
                    //加载身份正面
                    $("#idCardPicturePositive").attr("src",URL.getAccessoryByUserBasicIdAndIdCardPictureFileType+"?userBasicId="+data.id+"&idCardPictureFileType=positive");
                    //加载身份证反面
                    $("#idCardPictureNegative").attr("src",URL.getAccessoryByUserBasicIdAndIdCardPictureFileType+"?userBasicId="+data.id+"&idCardPictureFileType=negative");
                    //加载认证状态
                    $("#realNameValidStatusSpan").removeClass();
                    if(data.realNameValid){
                        $("#realNameValidStatusSpan").addClass("label label-primary").text("已认证");
                    }else{
                        $("#realNameValidStatusSpan").addClass("label label-warning").text("未认证");
                    }
                }
            })
        }
    });

    /*添加按钮绑定点击事件*/
    $('.glyphicon-plus').parent().bind('click',function(){
        var index = parent.layer.open({
            title:'添加用户',
            type: 2,
            area: ['700px', '530px'],
            fix: false, //不固定
            maxmin: true,
            content: contextPath+'/adminController/getUserBasicAMPage'
        });
        parent.layer.full(index);
    });
    /*修改按钮绑定点击事件*/
    $('.glyphicon-pencil').parent().bind('click',function(){
        var selectRow = $("#userManageDataGrid").bootstrapTable("getAllSelections")[0];
        if(selectRow){
            var index = parent.layer.open({
                title:'修改用户',
                type: 2,
                area: ['700px', '530px'],
                fix: false, //不固定
                maxmin: true,
                content: contextPath+'/adminController/getUserBasicAMPage?id='+selectRow.id
            });
            parent.layer.full(index);
        }else{
            parent.layer.alert('请选择要修改的用户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }

    });

    /**
     * 删除账户
     */
    $(".glyphicon-trash").parent().bind('click',function(){
        var selectRows = $("#userManageDataGrid").bootstrapTable("getAllSelections");
        if(selectRows.length){
            parent.layer.confirm('是否删除所选用户？', {
                btn: ['是','否'] //按钮
            }, function(){
                var selectRows = $("#userManageDataGrid").bootstrapTable("getAllSelections");
                if(selectRows.length>0) {
                    var idsArray = [];
                    for (var i = 0; i < selectRows.length; i++) {
                        idsArray.push(selectRows[i].id);
                    }
                    $.post(URL.deleteUserBasicByIds,{"ids":""+idsArray+""},function(data){
                        if(data.flag){
                            $("#userManageDataGrid").bootstrapTable("refresh");
                            parent.layer.alert("所选用户已删除");
                        }
                    });
                }
            },function(){})
        }else{
            parent.layer.alert('请选择要删除的用户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }

    });

    /**
     * 禁用用户
     */
    $("#deniedBtn").bind("click",function(){
        var selectRow = $("#userManageDataGrid").bootstrapTable("getAllSelections")[0];
        if(selectRow){
            parent.layer.prompt({title: '禁用用户', formType: 2}, function(text){
                var selectRows = $("#userManageDataGrid").bootstrapTable("getAllSelections");
                if(selectRows.length>0) {
                    var idsArray = [];
                    for (var i = 0; i < selectRows.length; i++) {
                        idsArray.push(selectRows[i].id);
                    }
                    $.post(URL.accessoryOrDeniedUser,{"ids":""+idsArray+"",denied:true,deniedReason:text},function(data){
                        if(data.flag){
                            $("#userManageDataGrid").bootstrapTable("refresh");
                            parent.layer.alert("所选账户已禁用");
                        }
                    });
                }
            });
        }else{
            parent.layer.alert('请选择要禁用的用户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }
    })

    $("#accessBtn").bind("click",function(){
        var selectRows = $("#userManageDataGrid").bootstrapTable("getAllSelections");
        if(selectRows.length>0) {
            var idsArray = [];
            for (var i = 0; i < selectRows.length; i++) {
                idsArray.push(selectRows[i].id);
            }
            $.post(URL.accessoryOrDeniedUser,{"ids":""+idsArray+"",denied:false,deniedReason:""},function(data){
                if(data.flag){
                    $("#userManageDataGrid").bootstrapTable("refresh");
                    parent.layer.alert("所选用户已启用");
                }
            });
        }else{
            parent.layer.alert('请选择要启用的用户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }
    });
    /**
     * 实名认证
     */
    $("#realNameCheckTrue").bind('click',function(){
        var selectRows = $("#userManageDataGrid").bootstrapTable("getAllSelections");
        if(selectRows.length>0) {
            var idsArray = [];
            for (var i = 0; i < selectRows.length; i++) {
                idsArray.push(selectRows[i].id);
            }
            $.post(URL.realNameValid,{"ids":""+idsArray+"",realNameValid:true},function(data){
                if(data.flag){
                    $("#userManageDataGrid").bootstrapTable("refresh");
                    parent.layer.alert("所选用户已认证");
                }
            });
        }else{
            parent.layer.alert('请选择要实名认证的用户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }
    });
    /**
     * 取消实名认证
     */
    $("#realNameCheckFalse").bind('click',function(){
        var selectRows = $("#userManageDataGrid").bootstrapTable("getAllSelections");
        if(selectRows.length>0) {
            var idsArray = [];
            for (var i = 0; i < selectRows.length; i++) {
                idsArray.push(selectRows[i].id);
            }
            $.post(URL.realNameValid,{"ids":""+idsArray+"",realNameValid:false},function(data){
                if(data.flag){
                    $("#userManageDataGrid").bootstrapTable("refresh");
                    parent.layer.alert("所选用户已取消认证");
                }
            });
        }else{
            parent.layer.alert('请选择要取消实名认证的用户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }
    });
    /**
     * 查询条件
     */
    $("#seachParam").children("li").bind("click",function(){
        var elementText = $(this).children("a").text();
        switch(elementText){
            case "用户姓名" :
                $(this).parents(".input-group-btn").prev().children().remove();
                $(this).parents(".input-group-btn").prev().append("<input type=\"text\" placeholder=\"用户姓名\" name='userName' class=\"input form-control\">");
                $(this).parent().prev().html(elementText+"<span class=\"caret\"></span>");
                break;
            case "昵称":
                $(this).parents(".input-group-btn").prev().children().remove();
                $(this).parents(".input-group-btn").prev().append("<input type=\"text\" placeholder=\"昵称\" name='nickName' class=\"input form-control\">");
                $(this).parent().prev().html(elementText+"<span class=\"caret\"></span>");
                break;
            case "性别":
                $(this).parents(".input-group-btn").prev().children().remove();
                $(this).parents(".input-group-btn").prev().append("<select class='form-control m-b' name='sex'><option value='false'>女</option><option value='true'>男</option></select>");
                $(this).parent().prev().html(elementText+"<span class=\"caret\"></span>");
                break;
            case "出生日期":
                $(this).parents(".input-group-btn").prev().children().remove();
                $(this).parents(".input-group-btn").prev().append("<div class='input-daterange input-group' id='datepicker' style='width: 100%' >"+
                                                                        "<input type='text' placeholder='开始日期' class='form-control m-b' aria-label=''name='birthday'><span class='input-group-addon'>到</span>" +
                                                                        "<input type='text' placeholder='结束日期' class='form-control m-b' aria-label='' name='endBirthday'>" +
                                                                   "</div>");
                $(this).parent().prev().html(elementText+"<span class=\"caret\"></span>");
                $("#datepicker").datepicker({
                    keyboardNavigation: true,
                    forceParse: true,
                    autoclose: true
                });
                break;
            case "实名认证":
                $(this).parents(".input-group-btn").prev().children().remove();
                $(this).parents(".input-group-btn").prev().append("<select class='form-control m-b' name='realNameValid'><option value='false'>未验证</option><option value='true'>已验证</option></select>");
                $(this).parent().prev().html(elementText+"<span class=\"caret\"></span>");
                break;
            case "禁用":
                $(this).parents(".input-group-btn").prev().children().remove();
                $(this).parents(".input-group-btn").prev().append("<select class='form-control m-b' name='denied'><option value='true'>禁用</option><option value='false'>启用</option></select>");
                $(this).parent().prev().html(elementText+"<span class=\"caret\"></span>");
                break;
            case "禁用原因":
                $(this).parents(".input-group-btn").prev().children().remove();
                $(this).parents(".input-group-btn").prev().append("<input type=\"text\" placeholder=\"禁用原因\" name='deniedReason' class=\"input form-control\">");
                $(this).parent().prev().html(elementText+"<span class=\"caret\"></span>");
                break;
        }
    });
    /*显示右侧用户信息*/
    $.get(URL.getFirstCreateTimeUserBasic,function(data){
        data = $.parseJSON(data.userBasic);
        if(data){
            //加载用户姓名
            $(".userName").text(data.userName);
            //加载个人简介
            $(".description").html(data.description);
            //加载备注
            $(".mark").html(data.mark);
            //加载头像
            var accessoryId;
            if(data.accessories){
                accessoryId = data.accessories[0].id
            }
            if(accessoryId){
                $("#headpicture").attr("src",URL.getAccessoryBytesByAccessoryId+"?id="+accessoryId);
            }else{
                $("#headpicture").attr("src",URL.defaultHeadPicture);
            }
            //加载身份正面
            $("#idCardPicturePositive").attr("src",URL.getAccessoryByUserBasicIdAndIdCardPictureFileType+"?userBasicId="+data.id+"&idCardPictureFileType=positive");
            //加载身份证反面
            $("#idCardPictureNegative").attr("src",URL.getAccessoryByUserBasicIdAndIdCardPictureFileType+"?userBasicId="+data.id+"&idCardPictureFileType=negative");
            //加载认证状态
            $("#realNameValidStatusSpan").removeClass();
            if(data.realNameValid){
                $("#realNameValidStatusSpan").addClass("label label-primary").text("已认证");
            }else{
                $("#realNameValidStatusSpan").addClass("label label-warning").text("未认证");
            }

        }
    })
    //绑定查询按钮点击事件
    $("#searchBtn").bind("click",function(){
        var form = $(this).parent().prevAll("form");
        var queryData = common.serializeForm(form);
        $("#userManageDataGrid").bootstrapTable('refresh',{query: queryData});
    });
    window.addBootstrapAlert = function(state,info){
        var alertDiv =  common.bootstrapAlert(state,info,"");
        $(".alert").remove();
        $("#tab-1").prepend(alertDiv).hide().fadeIn('slow').delay(3000).fadeOut('slow');
    }
    return addBootstrapAlert;


})
//格式化性别
function formatterSex(value,row,index) {
    if(value==false)
        return '女'
    else
        return '男'
}
//格式化认证
function formatterRealNameValid(value,row,index){
    if(value==true)
        return '<span class="label label-primary">已认证</span>';
    else
        return '<span class="label label-warning">未认证</span>';
}

//格式化禁用状态
function formatterDeniedState(value,row,index) {
    if(value==true)
        return '<span class="label label-danger">禁用</span>';
    else
        return '<span class="label label-primary">启用</span>';
}