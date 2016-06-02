/**
 * Created by ballchen on 2016/5/30.
 */

define(function(require,exports,module){
    $('#exampleTableEvents').bootstrapTable({
        search: true,
        pagination: true,

        showRefresh: true,
        showToggle: true,
        showColumns: true,
        iconSize: "outline",
        toolbar: "#exampleTableEventsToolbar",
        icons: {
            refresh: "glyphicon-repeat",
            toggle: "glyphicon-list-alt",
            columns: "glyphicon-list"
        }
    });
});
