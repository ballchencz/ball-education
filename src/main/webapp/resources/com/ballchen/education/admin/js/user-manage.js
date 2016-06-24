/**
 * Created by Administrator on 2016/6/16.
 */
define(function(require,exports,module){
    var URL = {
        "getUserManagePagination":contextPath+"/adminController/getUserBasicPagination"
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
    window.addBootstrapAlert = function(state,info){
        var alertDiv =  common.bootstrapAlert(state,info,"");
        $(".alert").remove();
        $("#tab-1").prepend(alertDiv).hide().fadeIn('slow').delay(3000).fadeOut('slow');
    }
    return addBootstrapAlert;
})