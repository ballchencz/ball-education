/**
 * Created by chenzhao on 2016/5/19.
 */
define(function(require,exports,module){
    var $ = require('jquery');
    require('bootstrap');
    $(function () {
        $('#menu-nav .navbar-collapse a').click(function (e) {
            var href = $(this).attr('href');
            var tabId = $(this).attr('data-tab');
            if ('#' !== href) {
                e.preventDefault();
                $(document).scrollTop($(href).offset().top - 70);
                if (tabId) {
                    $('#feature-tab a[href="#' + tabId + '"]').tab('show');
                }
            }
        });
    });
});