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
    console.log($('.glyphicon-plus'));
    $('.glyphicon-plus').parent().bind('click',function(){
       var alertDiv =  public.bootstrapAlert("success","添加成功","");
        $(".alert").remove();
        $("#tab-1").prepend(alertDiv).hide().fadeIn('slow').delay(3000).fadeOut('slow');
    });
});
