package com.ballchen.education.utils;

import com.ballchen.education.consts.PublicConsts;
import com.jcraft.jsch.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Administrator on 2016/6/30.
 */
public class SftpUtils {
    private ChannelSftp sftp = null;
    private  String userName;
    private  String password;
    private  String host;
    private  Integer port;
    private Session sshSession;
    private Channel channel;

    /*---------------------------有参构造函数---------------------------------------*/
    public SftpUtils(String userName,String password,String host,Integer port){
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /*---------------------------无惨构造函数---------------------------------------*/
    public SftpUtils(){

    }

    /**
     * 连接sftp服务器
     * @throws JSchException
     */
    public ChannelSftp connect() throws JSchException {
        JSch jsch = new JSch();
        jsch.getSession(userName, host, port);
        sshSession = jsch.getSession(userName, host, port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
        return sftp;
    }

    /**
     * 与sftp服务器断开
     */
    public void disconnect(){
        if(sftp != null){
            if (sshSession != null) {
                sshSession.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
            if (sftp != null) {
                sftp.disconnect();
            }
        }
    }

    /**
     * 根据路径获得该路径下的所有文件夹
     * @param url 路径
     * @return List<FileBean>
     * @throws JSchException
     * @throws SftpException
     * @throws ParseException
     */
/*    public List<FileBean> getDirectoryByUrl(String url) throws JSchException, SftpException, ParseException{
        List<FileBean> list = new ArrayList<FileBean>();
        //this.connect();
        Vector<LsEntry> vectors =  this.sftp.ls(url);
        for(LsEntry le : vectors){
            SftpATTRS sa = le.getAttrs();
            if(sa.isDir()){
                FileBean fileBean = new FileBean();
                fileBean.setFileName(le.getFilename());
                //fileBean.setFileSize("");
                fileBean.setFileType(SftpConsts.DIRECTORY_FLAG);
                SimpleDateFormat sdf = new SimpleDateFormat(SftpConsts.SIMPLEDATEFORMAT_FORMAT_STRING_TWO, Locale.US);
                Date date = null;
                date = sdf.parse(le.getAttrs().getMtimeString());
                fileBean.setLastModifyTime(date);
                list.add(fileBean);
            }
        }
        //this.disconnect();
        return list;
    }*/

    /**
     * 根据路径获得该路径下的所有文件
     * @param url 路径
     * @return List<FileBean>
     * @throws JSchException
     * @throws SftpException
     * @throws ParseException
     */
/*    public List<FileBean> getFileByUrl(String url) throws JSchException, SftpException, ParseException{
        List<FileBean> list = new ArrayList<FileBean>();
        //this.connect();
        Vector<LsEntry> vectors =  this.sftp.ls(url);
        for(LsEntry le : vectors){
            SftpATTRS sa = le.getAttrs();
            if(!sa.isDir()){
                FileBean fileBean = new FileBean();
                fileBean.setFileName(le.getFilename());
                fileBean.setFileSize(this.formatFileSize(le.getAttrs().getSize()));
                fileBean.setFileType(this.getFileTypeName(le.getFilename()));
                SimpleDateFormat sdf = new SimpleDateFormat(SftpConsts.SIMPLEDATEFORMAT_FORMAT_STRING_TWO, Locale.US);
                Date date = null;
                date = sdf.parse(le.getAttrs().getMtimeString());
                fileBean.setLastModifyTime(date);
                list.add(fileBean);
            }
        }
        //this.disconnect();
        return list;
    }*/

    /**
     * 根据文件路径获得文件字节数组
     * @return byte []
     * @throws SftpException
     * @throws JSchException
     * @throws IOException
     */
    public byte[] getFileByteArrayByFileArray(String url) throws SftpException, JSchException, IOException {
        //this.connect();
        InputStream in = sftp.get(url);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte [] buff = new byte[1024];
        int length = 0;
        while((length = in.read(buff))!=-1){
            out.write(buff, 0, length);
        }
        //sftp.disconnect();
        //sshSession.disconnect();
        return out.toByteArray();
    }

    /**
     * 添加文件夹
     * @param path
     * @param fileBean
     * @throws JSchException
     * @throws SftpException
     */
    public void addFolder(String path) throws JSchException, SftpException{
        //this.connect();
        String dirName = path;
        sftp.mkdir(dirName);
    }

    /**
     *  移除文件夹
     * @throws JSchException
     * @throws SftpException
     */
/*    public void removeDir(String path,FileBean fileBean) throws JSchException, SftpException{
        this.removeDir(path+fileBean.getFileName()+"/");
        //this.removeDir2(path+fileBean.getFileName()+"/");
    }*/

/*    private void removeDir2(String folderPath) throws SftpException, JSchException{

        //this.connect();
        Vector<LsEntry> vectors = this.sftp.ls(folderPath);
        if(vectors.size()>0){
            for(LsEntry le : vectors){
                SftpATTRS sa = le.getAttrs();

                String s = folderPath +le.getFilename();
                if(sa.isDir()){
                    this.removeDir(s+"/");
                    this.sftp.rmdir(folderPath);
                }
            }
        }else{
            sftp.rmdir(folderPath);
        }

    }*/

    /**
     * 移除文件
     * @throws JSchException
     * @throws SftpException
     */
    public void removeFile(String filePath) throws JSchException, SftpException{
        sftp.rm(filePath);
    }


    /**
     * 根据文件名获得文件类型
     * @param fileName
     * @return String
     */
    private String getFileTypeName(String fileName){
        int lastPoint = fileName.lastIndexOf(".");
        fileName = fileName.substring(lastPoint+1, fileName.length());
        return fileName;
    }

    private void removeDir(String folderPath) throws SftpException {
        Vector<ChannelSftp.LsEntry> vectors = this.sftp.ls(folderPath);
        if(vectors.size()>0){
            for(ChannelSftp.LsEntry le : vectors){
                SftpATTRS sa = le.getAttrs();
                String s = folderPath +le.getFilename();
                if(sa.isDir()){
                    this.removeDir(s+"/");
                    this.sftp.rmdir(folderPath);
                    continue;
                }else{
                    sftp.rm(s);
                }
            }
        }else{
            sftp.rmdir(folderPath);
        }
    }

    private Long formatFileSize(Long fileSize){
        DecimalFormat df = new DecimalFormat("#.00");
        Long returnLong = 0L;
        String returnStr = "";
        returnLong = fileSize/1024L;
        if(fileSize%1024L!=0){
            returnLong = returnLong + 1L;
            returnStr = returnLong+"KB";
        }else{
            returnStr = returnLong+"KB";
        }
        Double fileSizeDouble = fileSize+0.0;
        fileSizeDouble = fileSizeDouble/(1024*1024);
        if(fileSizeDouble>=1){
            Double returnFloat = fileSizeDouble;
            returnStr = df.format(returnFloat)+"MB";
        }
        return returnLong;
    }

    public void uploadFile(InputStream in,String filePath) throws IOException, SftpException{
        sftp.put(in,filePath);
    }

    public static void main(String[] args) throws JSchException {
        SftpUtils su = new SftpUtils("root", "root", "192.168.84.128", 22);
        su.connect();
        String filePath = "D:/fileServer.properties";
        String saveFilePath = "/home/sftptest/file.txt";
        try {
            //byte [] b = su.getFileByteArrayByFileArray("/report/fileServer.txt");
            //su.removeFile("/report/fileServer.txt");
            //System.out.println(b);
            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            su.uploadFile(is,saveFilePath);
           // su.addFolder(saveFilePath);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SftpException e) {
            e.printStackTrace();
        }finally{
            su.disconnect();
        }
    }




    /*-------------------------------setter、getter方法---------------------------------------*/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public ChannelSftp getSftp() {
        return sftp;
    }

    public void setSftp(ChannelSftp sftp) {
        this.sftp = sftp;
    }

    public Session getSshSession() {
        return sshSession;
    }

    public void setSshSession(Session sshSession) {
        this.sshSession = sshSession;
    }
}
