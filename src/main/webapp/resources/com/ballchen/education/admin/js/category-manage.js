/**
 * Created by ballchen on 2016/5/30.
 */

define(function(require,exports,module){

    var URL = {
        "getCategoryPagination":contextPath+"/adminController/getCategoryPaginationData",
        "accessoryOrDeniedAccount":contextPath+"/adminController/accessOrDeniedAccount",
        "deleteAccount":contextPath+"/adminController/deleteAccount",
        "getCategoryTypeJSON":contextPath+"/categoryController/getCategoryTypeJSON",
        "getCategoryByParentId":contextPath+"/categoryController/getCategoryByParentId"
    }
    var common = require("common");
    var click = false;
    /*添加按钮绑定点击事件*/
    $('.glyphicon-plus').parent().bind('click',function(){
        var index = parent.layer.open({
            title:'添加分类',
            type: 2,
            area: ['700px', '530px'],
            fix: false, //不固定
            maxmin: true,
            content: contextPath+'/adminController/getCategoryAMPage'
        });
        parent.layer.full(index);
    });
    /*修改按钮绑定点击事件*/
    $('.glyphicon-pencil').parent().bind('click',function(){
        var selectRow = $("#categoryTypeTable").treegrid("getSelections")[0];
        if(selectRow){
            var index = parent.layer.open({
                title:'修改分类',
                type: 2,
                area: ['700px', '530px'],
                fix: false, //不固定
                maxmin: true,
                content: contextPath+'/adminController/getCategoryAMPage?id='+selectRow.id
            });
            parent.layer.full(index);
        }else{
            parent.layer.alert('请选择要修改的账户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }

    });



    /*-----------------查询条件-----------------------*/
        /*获得分类类型*/
        $.get(URL.getCategoryTypeJSON,function(data){
            var deniedHtml = "";
            for(var i=0;i<data.length;i++){
                deniedHtml += "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
            }
            $("select[name='categoryType']").append(deniedHtml);
        })

    /*搜索按钮点击事件*/
    $('#searchBtn').bind('click',function(){
        var form = $(this).prev().children(".input-group").children(".form-inline")
        var queryData = common.serializeForm(form);
        $("#categoryTypeTable").treegrid('load',queryData);
    });

    /**s
     * 添加bootstrapalert
     * @param state 状态
     * @param info 信息
     */
    window.addBootstrapAlert = function(state,info){
        var alertDiv =  common.bootstrapAlert(state,info,"");
        $(".alert").remove();
        $("#tab-1").prepend(alertDiv).children(".alert").hide().fadeIn('slow').delay(3000).fadeOut('slow');
    }



    return addBootstrapAlert;
});




