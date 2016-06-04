/**
 * Created by ballchen on 2016/5/30.
 */

define(function(require,exports,module){

    var URL = {
        "getAccountPagination":contextPath+"/adminController/getAccountPagination",
        "accessoryOrDeniedAccount":contextPath+"/adminController/accessOrDeniedAccount",
        "deleteAccount":contextPath+"/adminController/deleteAccount"
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
        clickToSelect:true,
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
    /*删除按钮绑定点击事件*/
    $('.glyphicon-trash').parent().bind('click',function(){

    });
    /**
     * 禁用用户
     */
    $('#denied').bind('click',function(){
            parent.layer.confirm('是否禁用账户？', {
                btn: ['是','否'] //按钮
            }, function(){
                var selectRows = $("#accountDataGrid").bootstrapTable("getAllSelections");
                if(selectRows.length>0) {
                    var idsArray = [];
                    for (var i = 0; i < selectRows.length; i++) {
                        idsArray.push(selectRows[i].id);
                    }
                    $.post(URL.accessoryOrDeniedAccount,{"ids":""+idsArray+"",denied:true},function(data){
                        if(data.flag){
                            $("#accountDataGrid").bootstrapTable("refresh");
                            parent.layer.alert("所选账户已禁用");
                        }
                    });
                }
            }, function(){}
        );

    })
    /**
     * 启用账户
     */
    $('#access').bind('click',function(){
        var selectRows = $("#accountDataGrid").bootstrapTable("getAllSelections");
        if(selectRows.length>0) {
            var idsArray = [];
            for (var i = 0; i < selectRows.length; i++) {
                idsArray.push(selectRows[i].id);
            }
            $.post(URL.accessoryOrDeniedAccount,{"ids":""+idsArray+"",denied:false},function(data){
                if(data.flag){
                    $("#accountDataGrid").bootstrapTable("refresh");
                    parent.layer.alert("所选账户已启用");
                }
            });
        }
    });

    /**
     * 删除账户
     */
    $(".glyphicon-trash").parent().bind('click',function(){
        parent.layer.confirm('是否删除所选账户？', {
            btn: ['是','否'] //按钮
        }, function(){
            var selectRows = $("#accountDataGrid").bootstrapTable("getAllSelections");
            if(selectRows.length>0) {
                var idsArray = [];
                for (var i = 0; i < selectRows.length; i++) {
                    idsArray.push(selectRows[i].id);
                }
                $.post(URL.deleteAccount,{"ids":""+idsArray+""},function(data){
                    if(data.flag){
                        $("#accountDataGrid").bootstrapTable("refresh");
                        parent.layer.alert("所选账户已删除");
                    }
                });
            }
        },function(){})

    });

    /*-----------------查询条件-----------------------*/
    $('#searchAccountNameBtn').bind('click',function(){
        $(this).parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        $(this).parent().parent().next().attr("name","accountName");
    });
    $('#searchCreateTimeBtn').bind('click',function(){
        $(this).parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        $(this).parent().parent().next().attr("name","createTime");
    });
    $('#searchDeniendBtn').bind('click',function(){
        $(this).parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        var deniedHtml = "<select class=\"m-b\" name=\"denied\">"+
                            "<option value='false'>启用</option>"+
                            "<option value='true'>禁用</option>"+
                          "</select>"
        $(this).parent().parent().next().remove();
        $(this).parent().parent().append(deniedHtml);
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
 function formatDenied(value,row,index){
    if(value){
        return '禁用';
    }else{
        return '启用';
    }
}
