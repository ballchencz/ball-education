package com.ballchen.education.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ballchen on 2016/6/30.
 */
@Controller
@RequestMapping("sharePhotoController")
public class SharePhotoController {
    /**
     *上传头像 接口
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/shearphoto")
    public String  shearphoto(@RequestParam("UpFile")MultipartFile file12, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        return  "{'status':'success'}";
    }

    @ResponseBody
    @RequestMapping("/uploadCameraPhoto")
    public String uploadCameraPhoto(@RequestParam("UpFile")MultipartFile file12,HttpServletRequest request,HttpServletResponse response,Model model)throws IOException{
        return "{\"success\":\"file/temp/11111.jpg\"}";
    }

    @RequestMapping(value = "/getSharePhotoIndexPage",method = RequestMethod.GET)
    public ModelAndView getSharePhotoIndexPage(){
        ModelAndView mv = new ModelAndView("/admin/sharephoto/index");
        return mv;
    }


}
