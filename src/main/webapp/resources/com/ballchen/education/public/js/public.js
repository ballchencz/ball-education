/**
 * Created by ChenZhao on 2016/5/19.
 */
seajs.config({
    base: contextPath+"/resources/com/ballchen/education/",
    alias: {
        "jquery": "public/js/jquery.js",
        "public": "public/js/comm.js",
        "bootstrap":"public/js/bootstrap.js",
        "jquery-form":"public/js/jquery.form.js",
        "common":"public/js/comm.js",
        "jquery-validate":"hplus/js/plugins/validate/jquery.validate.min.js",
        "validate-messages_zh":"hplus/js/plugins/validate/messages_zh.min.js"
    }
});
//下面用于图片上传预览功能
function setImagePreview(avlaue) {
    var docObj=document.getElementById("imgFile");

    var imgObjPreview=document.getElementById("preview");
    if(docObj.files &&docObj.files[0])
    {
        //火狐下，直接设img属性
        imgObjPreview.style.display = 'block';
        imgObjPreview.style.width = imgObjPreview.width;
        imgObjPreview.style.height = imgObjPreview.height;
        //imgObjPreview.src = docObj.files[0].getAsDataURL();

        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
        imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
    }
    else
    {
        //IE下，使用滤镜
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("localImag");
        //必须设置初始大小
        localImagId.style.width = localImagId.width;
        localImagId.style.height = localImagId.height;
        //图片异常的捕捉，防止用户修改后缀来伪造图片
        try{
            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        }
        catch(e)
        {
            alert("您上传的图片格式不正确，请重新选择!");
            return false;
        }
        imgObjPreview.style.display = 'none';
        document.selection.empty();
    }
    return true;
}