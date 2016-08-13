package com.ballchen.education.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * Created by ballchen on 2016/7/23.
 * 七牛云存储工具类
 */
public class QiniuCloudUtils {
    private Auth auth;
    //要上传的空间
    private String bucketName;
    //上传文件的路径
    private String filePath;
    //创建上传对象
    private UploadManager uploadManager;
    private BucketManager bucketManager;
    public QiniuCloudUtils(String accessKey,String secritKey,String bucketName,String filePath) {
        auth = Auth.create(accessKey,secritKey);
        this.bucketName = bucketName;
        this.filePath = filePath;
        this.uploadManager = new UploadManager();
        this.bucketManager = new BucketManager(auth);
    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getSimpleUpToken(){
        return auth.uploadToken(bucketName);
    }

    //覆盖上传
    public String getOverWriteUpToken(String saveFileName){
        return auth.uploadToken(bucketName, saveFileName);
    }

    /**
     * 简单上传
     * @param bytes
     * @param saveFileName
     * @throws IOException
     */
    public String simpleUpload(byte [] bytes,String saveFileName) throws QiniuException {
        //调用put方法上传
        Response res = uploadManager.put(bytes, saveFileName, getSimpleUpToken());
        return res.bodyString();
    }

    /**
     * 覆盖上传
     * @param bytes
     * @param saveFileName
     * @throws IOException
     */
    public String overWriteUpload(byte [] bytes,String saveFileName) throws QiniuException{
        //调用put方法上传，这里指定的key和上传策略中的key要一致
        Response res = uploadManager.put(bytes, saveFileName, getOverWriteUpToken(saveFileName));
        return res.bodyString();
    }

    /**
     * 删除文件
     * @param saveFileName
     * @throws QiniuException
     */
    public void removeSimpleFile(String saveFileName) throws QiniuException {
        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth);
        //调用delete方法移动文件
        bucketManager.delete(bucketName, saveFileName);
    }

    /**
     *根据保存名称获得文件字节数组
     * @param saveName
     * @return byte[]
     * @throws IOException
     */
    public byte [] getFileBytesBySaveName(String saveName) throws IOException {
        String urlStr = "http://oaph9wwmm.bkt.clouddn.com/"+saveName;
        URL url = new URL(urlStr);
        InputStream in = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte [] buff = new byte[1024];
        int length;
        while((length = in.read(buff))!=-1){
            out.write(buff, 0, length);
        }
        return out.toByteArray();
    }

    public String getFileURLString(String saveName){
        String urlStr = "http://oaph9wwmm.bkt.clouddn.com/"+saveName;
        return urlStr;
    }


    public static void main(String [] args){
        QiniuCloudUtils qiniuCloudUtils = new QiniuCloudUtils("1asbmET-Q5da9M9TFvUiJXgHdVUJY5NQdtLo2sIX","msr-RG48kraUZe89ScvesacCg7wdHVP2PXjqssIL","ballchen","/");
        //File file = new File("D:/图片/信联网智图片/psb.jpg");
        try {
            byte [] bytes = qiniuCloudUtils.getFileBytesBySaveName("0.jpg");
            System.out.println(bytes);
/*            InputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte [] buff = new byte[1024];
            int length;
            while((length = in.read(buff))!=-1){
                out.write(buff, 0, length);
            }
            String fileName = file.getName();
            String s = qiniuCloudUtils.overWriteUpload(out.toByteArray(), "01a9f72c-b4de-4495-b69d-e3f7e1a2ebbb"+fileName.substring(fileName.lastIndexOf("."),fileName.length()));
            System.out.println(s);*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
