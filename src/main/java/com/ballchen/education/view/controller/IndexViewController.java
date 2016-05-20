package com.ballchen.education.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/5/18.
 */
@Controller
@RequestMapping("indexViewController")
public class IndexViewController {
    /**
     * 获得主页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getIndexView",method = {RequestMethod.GET})
    public ModelAndView getIndexView(){
        ModelAndView mv = new ModelAndView("/view/index");
        return mv;
    }
}
