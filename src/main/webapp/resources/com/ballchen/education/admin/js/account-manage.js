/**
 * Created by ballchen on 2016/5/30.
 */

define(function(require,exports,module){

    var URL = {
        "getAccountPagination":contextPath+"/adminController/getAccountPagination"
    }
    var public = require("public");

    $('#accountDataGrid').bootstrapTable({
        classes:'table table-hover',
        locale:'zh-CN',
        idField:'id',
        url:URL.getAccountPagination,
        sidePagination:"server",
        pagination:true,
        pageSize:10,
        pageList:[10,20,30],
        toggle:'table',
        search:false,
        sortOrder:"desc",
        showRefresh:true,
        showToggle:true,
        showColumns:true,
        showExport:true,
        searchOnEnterKey:true,
        strictSearch:true,
        toolbar:'#accountDataGridToolbar',
        rowStyle:function(row,index){
            if (index % 2 !== 0) {
                return {
                    classes: 'active'
                };
            }
            return {};
        }
    });

    /*添加按钮绑定点击事件*/
    $('.glyphicon-plus').parent().bind('click',function(){
        parent.layer.open({
            title:'添加账户',
            type: 2,
            area: ['700px', '530px'],
            fix: false, //不固定
            maxmin: true,
            content: contextPath+'/adminController/getAccountAMPagination'
        });
    });
    /*修改按钮绑定点击事件*/
    $('.glyphicon-pencil').parent().bind('click',function(){

    });
    /*删除按钮绑定点击事件*/
    $('.glyphicon-trash').parent().bind('click',function(){

    });
    var accountManage = {};
    /**
     * 添加bootstrapalert
     * @param state 状态
     * @param info 信息
     */
    window.addBootstrapAlert = function(state,info){
        var alertDiv =  public.bootstrapAlert(state,info,"");
        $(".alert").remove();
        $("#tab-1").prepend(alertDiv).hide().fadeIn('slow').delay(3000).fadeOut('slow');
    }
    return addBootstrapAlert;
});
