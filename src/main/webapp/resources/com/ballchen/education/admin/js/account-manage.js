/**
 * Created by ballchen on 2016/5/30.
 */

define(function(require,exports,module){

    var URL = {
        "getAccountPagination":contextPath+"/adminController/getAccountPagination",
        "accessoryOrDeniedAccount":contextPath+"/adminController/accessOrDeniedAccount",
        "deleteAccount":contextPath+"/adminController/deleteAccount"
    }
    var common = require("common");

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
    /**
     * 禁用用户
     */
    $('#denied').bind('click',function(){
        var selectRow = $("#accountDataGrid").bootstrapTable("getAllSelections")[0];
        if(selectRow){
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
        }else{
            parent.layer.alert('请选择要禁用的账户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }

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
        }else{
            parent.layer.alert('请选择要启用的账户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }
    });

    /**
     * 删除账户
     */
    $(".glyphicon-trash").parent().bind('click',function(){
        var selectRows = $("#accountDataGrid").bootstrapTable("getAllSelections");
        if(selectRows.length){
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
        }else{
            parent.layer.alert('请选择要删除的账户', {
                icon: 0,
                skin: 'layer-ext-moon'
            })
        }

    });

    /*-----------------查询条件-----------------------*/
    $('#searchAccountNameBtn').bind('click',function(){
        $(this).parent().parent().next().remove()
        var searchInputHtml = "<form class='form-inline'><input type='text' placeholder='账户名称' name='accountName' class='form-control' aria-label=''/></form>";
        $(this).parent().parent().parent().append(searchInputHtml);
        $(this).parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        $(this).parent().parent().next().attr("name","accountName");
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
    $('#searchDeniendBtn').bind('click',function(){
        $(this).parent().parent().next().remove();//.remove();
        $(this).parent().prev().html($(this).text()+" <span class=\"caret\"></span>");
        var deniedHtml = " <form class='form-inline'> <div class='radio' style='margin-left: 5px;'>"+
            "<label>"+
            "<input type='radio' value='true' name='denied'> 禁用"+
            "</label>"+
            "<label>" +
            "<input type='radio' value='false' name='denied'> 开启"+
            "</label>"+
            "</div></form>";
        $(this).parent().parent().next().remove();
        $(this).parent().parent().parent().append(deniedHtml);
    });
    /*搜索按钮点击事件*/
    $('#searchBtn').bind('click',function(){
        var form = $(this).prev().children(".input-group").children(".form-inline")
        var queryData = common.serializeForm(form);
        $("#accountDataGrid").bootstrapTable('refresh',{query: queryData});
    });
    var accountManage = {};
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
function formatDenied(value,row,index){
    if(value){
        return '禁用';
    }else{
        return '启用';
    }
}
