package com.ballchen.education.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.accessory.service.IAccessoryService;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.account.service.IAccountService;
import com.ballchen.education.admin.consts.AdminConsts;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.annotation.AuthorizationAnno;
import com.ballchen.education.annotation.RoleCode;
import com.ballchen.education.category.entity.Category;
import com.ballchen.education.category.service.ICategoryService;
import com.ballchen.education.consts.PublicConsts;
import com.ballchen.education.security.consts.SecurityConsts;
import com.ballchen.education.security.service.IRoleService;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.service.IUserService;
import com.ballchen.education.utils.PublicUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ChenZhao on 2016/5/19.
 */
@Controller
@RequestMapping("adminController")
public class AdminController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICategoryService categoryService;

    //@Autowired
    //private IRoleService roleService;

    @Autowired
    private IAccessoryService accessoryService;
    /**
     * 获得后台登录页面
     * @return
     */
    @RequestMapping(value="/getAdminLoginPage",method = RequestMethod.GET)
    public ModelAndView getAdminLoginPage(){
        ModelAndView mv = new ModelAndView("/admin/login");
        return mv;
    }

    /**
     * 验证后台登录
     * @param account 账户名称
     * @return ModelAndView
     */
    @RequestMapping(value = "/validLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(Account account){
        ModelAndView mv = new ModelAndView("/admin/index");
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("flag",true);
        returnMap.put("info","我是中文，还是乱码？");
        return returnMap;
    }

    /**
     * 获得管理后台页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getAdminIndexPage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getAdminIndexPage(){
        ModelAndView mv = new ModelAndView("/admin/index");
        return mv;
    }

    /**
     * 获得管理后台右侧首页页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getAdminIndexRightIndexPage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getAdminIndexRightIndexPage(){
        ModelAndView mv = new ModelAndView("/admin/index-right_index");
        return mv;
    }
    /*----------------------------------------------账户管理开始--------------------------------------------------------*/
    /**
     * 获得账户管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getAccountManagePage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN})
    public ModelAndView getAccountManagePage(){
        ModelAndView mv = new ModelAndView("/admin/account/account-manage");
        return mv;
    }


    /**
     * 获得账户分页数据
     * @param account
     * @return
     */
    @RequestMapping(value = "/getAccountPagination",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN})
    @ResponseBody
    public String getAccountPagination(Account account){
        List<Account> accounts = this.accountService.getAccountPagination(account);
        JSONObject jsonO = new JSONObject();
        jsonO.put("total",accountService.getAccountPaginationCount(account));
        jsonO.put("rows", JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(accounts, AdminConsts.DATETIME_FORMAT_STRING)).toArray());
        return jsonO.toJSONString();
    }

    /**
     * 获得账户添加/修改页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getAccountAMPagination",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN})
    public ModelAndView getAccountAMPagination(Account account){
        if(account!=null){
            if(account.getId()!=null){
                account = this.accountService.getAccountById(account.getId());
            }
        }
        ModelAndView mv = new ModelAndView("/admin/account/account-am");
        mv.addObject("account",account);
        return mv;
    }

    /**
     * 添加/修改账户
     * @param account 账户实体类
     * @return
     */
    @RequestMapping(value="/amAccount",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> amAccount(Account account){
        Map<String,Object> resultMap = new HashMap<>();
        if(account.getId()==null || account.getId().equals("")){//添加
            account.setId(UUID.randomUUID().toString());
            resultMap.put("name","insert");
            try{
                this.accountService.insertAccount(account);
                resultMap.put("flag",true);
            }catch(Exception e){
                resultMap.put("flag",false);
            }
        }else{//修改
            resultMap.put("name","update");
            try{
                this.accountService.updateAccount(account);
                resultMap.put("flag",true);
            }catch(Exception e){
                resultMap.put("flag",false);
            }
        }
        return resultMap;
    }

    /**
     * 禁用或启用账户
     * @param ids 账户ID数组
     * @return Map<String,Object>
     */
    @RequestMapping(value = "accessOrDeniedAccount",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> accessOrDeniedAccount(String [] ids,Account account){
        Map<String,Object> resultMap = new HashMap<>();
        int result = this.accountService.accessOrDeniedAccount(ids,account.getDenied());
        if(result>=0){
            resultMap.put("flag",true);
        }else{
            resultMap.put("flag",false);
        }
        return resultMap;
    }

    /**
     * 根据ID数组删除账户
     * @param ids 账户ID数组
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/deleteAccount",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> deleteAccount(String [] ids){
        Map<String,Object> resultMap = new HashMap<>();
        int result = this.accountService.deleteByIds(ids);
        if(result>=0){
            resultMap.put("flag",true);
        }else{
            resultMap.put("flag",false);
        }
        return resultMap;
    }
    /*----------------------------------------------账户管理结束--------------------------------------------------------*/

    /*----------------------------------------------用户管理开始--------------------------------------------------------*/
    /**
     * 获得用户管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getUserManagePage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getUserManagePage(){
        ModelAndView mv = new ModelAndView("/admin/user/user-manage");
        return mv;
    }
    /**
     * 获得账户分页数据
     * @param userBasic
     * @param pageHelper
     * @return
     */
    @RequestMapping(value = "/getUserBasicPagination",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN})
    @ResponseBody
    public String getUserBasicPagination(UserBasic userBasic, PageHelper pageHelper){
        List<UserBasic> userBasics = this.userService.getUserBasicPagination(userBasic,pageHelper);
        JSONObject jsonO = new JSONObject();
        jsonO.put("total",this.userService.getUserBasicPaginationCount(userBasic,pageHelper));
        jsonO.put("rows", JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(userBasics, AdminConsts.DATE_FORMAT_STRING)).toArray());
        return jsonO.toJSONString();
    }

    /**
     * 获得用户添加修改页面
     * @param userBasic
     * @return ModelAndView
     */
    @RequestMapping(value = "/getUserBasicAMPage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN})
    public ModelAndView getUserBasicAMPage(UserBasic userBasic){
        ModelAndView mv = new ModelAndView("/admin/user/user-am");
        Accessory idCardPositive = null;//用户身份证正面
        Accessory idCardNegative = null;//用户身份证反面
        if(userBasic.getId()==null){//添加

        }else{//修改
            String userBasicId = userBasic.getId();
            userBasic = this.userService.selectUserBasicWithRolesAndHeadPictureAccessoryByPrimaryKey(userBasicId);
            if(userBasic==null){
                userBasic = userService.selectUserBasicWithRolesByPrimaryKey(userBasicId);
            }
            /*根据用户ID获得身份证的反正面照片*/
            List<Accessory> idCardPositives = this.accessoryService.selectAccessoryByUserIdAndIdCardPicture(
                    userBasicId,
                    PublicConsts.USER_FILE_TYPE_IDCARD_POSITIVE,
                    null);
            if(idCardPositives!=null&&idCardPositives.size()>0){
                idCardPositive = idCardPositives.get(0);
            }
            List<Accessory> idCardNegatives = this.accessoryService.selectAccessoryByUserIdAndIdCardPicture(
                    userBasicId,
                    null,
                    PublicConsts.USER_FILE_TYPE_IDCARD_NEGATIVE);
            if(idCardNegatives!=null&&idCardNegatives.size()>0){
                idCardNegative = idCardNegatives.get(0);
            }
        }
        mv.addObject("idCardPositive",idCardPositive);
        mv.addObject("idCardNegative",idCardNegative);
        mv.addObject("userBasic",userBasic);
        mv.addObject("roles",SecurityConsts.roleMap);
        return mv;
    }

    /**
     * 添加/修改用户
     * @param userBasic 用户实体类
     * @param imgFile 用户头像文件
     * @return Map<String,Object>
     */
    @RequestMapping(value="/amUserBasic",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> amUserBasic(UserBasic userBasic, MultipartFile imgFile){
        Map<String,Object> resultMap = new HashMap<>();
        if(userBasic.getId()==null || userBasic.getId().equals("")){//添加
            userBasic.setId(UUID.randomUUID().toString());
            resultMap.put("name","insert");
            try{
                Accessory accessory = this.accessoryService.getAccessoryByMultipartFile(imgFile,PublicConsts.USER_FILE_TYPE_HEAD_PICTURE);
                this.userService.insertSelective(userBasic,accessory);
                resultMap.put("flag",true);
            }catch(Exception e){
                resultMap.put("flag",false);
            }
        }else{//修改
            resultMap.put("name","update");
            try{
                Accessory accessory = this.accessoryService.getAccessoryByMultipartFile(imgFile,PublicConsts.USER_FILE_TYPE_HEAD_PICTURE);
                this.userService.updateByPrimaryKeySelective(userBasic,accessory);
                resultMap.put("flag",true);
            }catch(Exception e){
                resultMap.put("flag",false);
            }
        }
        return resultMap;
    }
    /**
     * 根据ID数组删除用户
     * @param ids 用户ID数组
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/deleteUserBasicByIds",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> deleteUserBasicByIds(String [] ids){
        Map<String,Object> resultMap = new HashMap<>();
        int result = this.userService.deleteByIds(ids);
        if(result>=0){
            resultMap.put("flag",true);
        }else{
            resultMap.put("flag",false);
        }
        return resultMap;
    }

    /**
     * 禁用用户
     * @param ids 用户ID数组
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/accessOrDeniedUser",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> accessOrDeniedUser(String [] ids,UserBasic userBasic){
        Map<String,Object> returnMap = new HashMap<>();
        int result = this.userService.accessOrDeniedUser(ids,userBasic);
        if(result>0){
            returnMap.put("flag",true);
        }else{
            returnMap.put("flag",false);
        }
        return returnMap;
    }

    /**
     * 获得用户展示数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/getFirstCreateTimeUserBasic",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> getFirstCreateTimeUserBasic(String id){
        Map<String,Object> returnMap = new HashMap<>();
        UserBasic userBasic = this.userService.selectFirstUserBasic(id);
        if(userBasic!=null){
            returnMap.put("userBasic",JSONObject.toJSONString(userBasic));
        }
        return returnMap;
    }

    /**
     * 保存用户身份证图片
     * @param id 用户基本信息ID
     * @param idCardPositiveImgFile 用户身份证照片正面
     * @param idCardNegativeImgFile 用户身份证照片反面
     * @return Map<String,Object>
     */
    @RequestMapping(value="/amUserIdCardPicture",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> amUserIdCardPicture(String id,MultipartFile idCardPositiveImgFile,MultipartFile idCardNegativeImgFile){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("name","insert");
        if(id!=null){
            try {
                Accessory idCardPositive = this.accessoryService.getAccessoryByMultipartFile(idCardPositiveImgFile,PublicConsts.USER_FILE_TYPE_IDCARD_POSITIVE);
                Accessory idCardNegative = this.accessoryService.getAccessoryByMultipartFile(idCardNegativeImgFile,PublicConsts.USER_FILE_TYPE_IDCARD_NEGATIVE);
                this.accessoryService.insertIdCardPictureAccessory(idCardPositive,idCardNegative,id);
                resultMap.put("flag",true);
            } catch (Exception e) {
                resultMap.put("flag",false);
            }
        }
        return resultMap;
    }

    /*----------------------------------------------用户管理结束--------------------------------------------------------*/
    /*----------------------------------------------分类管理开始--------------------------------------------------------*/

    /**
     * 获得分类管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getCategoryManagePage",method=RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getCategoryManagePage(){
        ModelAndView mv = new ModelAndView("/admin/category/category-manage");
        return mv;
    }

    /**
     * 获得分类分页数据
     * @param category
     * @param pageHelper
     * @return String
     */
    @RequestMapping(value = "/getCategoryPaginationData",method = RequestMethod.GET)
    @ResponseBody
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public String getCategoryPaginationData(Category category,PageHelper pageHelper){
        List<Category> categories = this.categoryService.getCategoryPagination(category,pageHelper);
        long total = this.categoryService.getCategoryPaginationCount(category,pageHelper);
        return this.categoryService.getCategoryPagination(total,categories).toJSONString();
    }

    /**
     * 获得分类管理添加修改页面
     * @param id 分类ID
     * @return ModelAndView
     */
    @RequestMapping(value = "/getCategoryAMPage",method=RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getCategoryAMPage(String id){
        ModelAndView mv = new ModelAndView("/admin/category/category-am");
        Category category = null;
        if(id!=null){//修改

        }
        mv.addObject("category",category);
        return mv;
    }
    /*----------------------------------------------分类管理结束--------------------------------------------------------*/
    /*----------------------------------------------文件服务器管理开始--------------------------------------------------*/
    @RequestMapping(value = "/getFileServerManagePage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    public ModelAndView getFileServerManagePage(HttpServletRequest request){
       // Map<String,Object> returnMap = PublicUtils.getAllProperties();
        String sftpFilePath = this.getClass().getClassLoader().getResource("/").getPath()+PublicConsts.SFTP_FILE_SERVER_PROPERTIES_NAME;
        String qiniuCloudPath = this.getClass().getClassLoader().getResource("/").getPath()+PublicConsts.QINIU_CLOUD_PEOPERTIES_NAME;
        ModelAndView mv = new ModelAndView("/admin/fileServer/file-server-manage");
        mv.addObject("sftpProperties", PublicUtils.getAllProperties(sftpFilePath));
        mv.addObject("qiniuCloudProperties",PublicUtils.getAllProperties(qiniuCloudPath));
        return mv;
    }
    /*--------------sftp文件服务器表单提交开始-------------------*/
    @RequestMapping(value = "/saveFileServerProperties",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> saveFileServerProperties(String userName,String password,String host,String port,String filePath,Boolean denied){
        Map<String,Object> returnMap = new HashMap<>();
        Map<String,Object> param = new HashMap<>();
        param.put("userName",userName);
        param.put("password",password);
        param.put("host",host);
        param.put("port",port);
        param.put("filePath",filePath);
        param.put("denied",denied);
        String path = this.getClass().getClassLoader().getResource("/").getPath()+ PublicConsts.SFTP_FILE_SERVER_PROPERTIES_NAME;
        boolean flag = PublicUtils.writeAllProperties(path,param);
        returnMap.put("flag",flag);
        return returnMap;
    }
    /*--------------sftp文件服务器表单提交结束-------------------*/
    /*--------------七牛云存储表单提交开始-------------------*/
    @RequestMapping(value = "/saveQiniuFileServerProperties",method = RequestMethod.POST)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public Map<String,Object> saveQiniuFileServerProperties(String accessKey,String secretKey,String bucketName,String filePath,Boolean denied){
        Map<String,Object> returnMap = new HashMap<>();
        Map<String,Object> param = new HashMap<>();
        param.put("accessKey",accessKey);
        param.put("secretKey",secretKey);
        param.put("bucketName",bucketName);
        param.put("filePath",filePath);
        param.put("denied",denied);
        String path = this.getClass().getClassLoader().getResource("/").getPath()+ PublicConsts.QINIU_CLOUD_PEOPERTIES_NAME;
        boolean flag = PublicUtils.writeAllProperties(path,param);
        returnMap.put("flag",flag);
        return returnMap;
    }
    /*--------------七牛云存储表单提交结束-------------------*/

    /*----------------------------------------------文件服务器管理结束--------------------------------------------------*/


}
