/**
 * created by ballchen on 2016/5/20.
 */
define(function(require,exports,module){
    require('bootstrap');
    var $ = require('jquery');
    $(function(){
        //给左侧菜单添加css
        $('.menu-item').bind('click',function(){
            $('.menu-item').removeClass('menu-item-active');
            $(this).addClass('menu-item-active');
        });
        $('.rightContent').height($(window).height());
        $('.rightContent').width($(window).width()-200);

    })


});
