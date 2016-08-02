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
            title:'添加账户',
            type: 2,
            area: ['700px', '530px'],
            fix: false, //不固定
            maxmin: true,
            content: contextPath+'/adminController/getAccountAMPagination'
        });
        parent.layer.full(index);
    });
    /*修改按钮绑定点击事件*/
    $('.glyphicon-pencil').parent().bind('click',function(){
        var selectRow = $("#accountDataGrid").bootstrapTable("getAllSelections")[0];
        if(selectRow){
            var index = parent.layer.open({
                title:'修改账户',
                type: 2,
                area: ['700px', '530px'],
                fix: false, //不固定
                maxmin: true,
                content: contextPath+'/adminController/getAccountAMPagination?id='+selectRow.id
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
    $('#searchCategoryNameBtn').bind('click',function(){
        $(this).parent().parent().next().remove()
        var searchInputHtml = "<form class='form-inline'><input type='text' placeholder='分类名称' name='accountName' class='form-control' aria-label=''/></form>";
        $(this).parent().parent().parent().append(searchInputHtml);
        $(this).parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        $(this).parent().parent().next().attr("name","categoryName");
    });
    $('#searchCreateTimeBtn').bind('click',function(){
        $(this).parent().parent().next().remove();
        $(this).parent().parent().parent().append("<form class='form-inline'>"+
            "<div class='input-daterange input-group' id='datepicker' >"+
            "<input type='text' placeholder='开始时间' class='form-control' aria-label=''name='createTime'><span class='input-group-addon'>到</span>" +
            "<input type='text' placeholder='结束时间' class='form-control' aria-label='' name='endTime'>" +
            "</div>"+
            "</form>");
        $(this).parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        $(this).parent().parent().next().attr("name","createTime");
        $("#datepicker").datepicker({
            keyboardNavigation: true,
            forceParse: true,
            autoclose: true
        });
    });
    $('#searchCategoryTypeBtn').bind('click',function(){
        var obj = $(this);
        obj.parent().parent().next().remove();//.remove();
        obj.parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        /*获得分类类型*/
        $.get(URL.getCategoryTypeJSON,function(data){
            var deniedHtml = "<form class='form-inline'><select class='form-control m-b' name='categoryType'>";
            for(var i=0;i<data.length;i++){
                deniedHtml += "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
            }
            deniedHtml +="</select></form>";
            obj.parent().parent().next().remove();
            obj.parent().parent().parent().append(deniedHtml);
        })

    });
    /*搜索按钮点击事件*/
    $('#searchBtn').bind('click',function(){
        var form = $(this).prev().children(".input-group").children(".form-inline")
        var queryData = common.serializeForm(form);
        $("#categoryDataGrid").bootstrapTable('refresh',{query: queryData});
    });

    /**s
     * 添加bootstrapalert
     * @param state 状态
     * @param info 信息
     */
    window.addBootstrapAlert = function(state,info){
        var alertDiv =  common.bootstrapAlert(state,info,"");
        $(".alert").remove();
        $("#tab-1").prepend(alertDiv).hide().fadeIn('slow').delay(3000).fadeOut('slow');
    }



    return addBootstrapAlert;
});




