/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   FileCopy.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.common;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：这个demo的主要意图是为了保存本地修改的代码，保存所有SVN中修改了的代码
 * （因为某种原因不能提交到SVN服务器，所以先保存在本地）
 * txt文件的格式内容：第一行为项目所在路径，接下来的内容为在项目下用SVN的commit时获取所修改了的file路径，如：
 F:\java_workspace\javacommon\
 src\main\java\com\darlen\common\FileCopy.java
 pom.xml
 src\main\java\com\darlen\common\FileNameFileterWriteFile.java
 src\main\java\com\darlen\common\FileUtils.java
 思路：
 1.为每个file创建相应的forder
 2.把源路径的file复制到目的地
 * Created on  2015-07-28 下午11:59
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:59              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class CpFile2LocalBySVNPathDemo {
    private final static Logger logger = Logger.getLogger(CpFile2LocalBySVNPathDemo.class);

    public static void main(String[] args) throws IOException {
        // 目标文件夹,默认在当前工作目录下的tempFile下”.”或”.\”代表当前目录,这个目录也就是jvm启动路径
        logger.debug("开始复制文件...");
        long start = System.currentTimeMillis();
        String des = "./tmpSVNFiles";
        //当前工作目录，也就是项目所在目录
        System.out.println(System.getProperty("user.dir"));
        cpFileFrmTxt2Dst("FileCopyTxt.txt", des);
        logger.debug("复制指定的文件成功，总共耗时【"+(System.currentTimeMillis()-start)+"】 ms");
    }

    /**
     *
     * @param resourceTxtName  资源文件名
     * @param des
     * @return
     * @throws IOException
     */
    public static void cpFileFrmTxt2Dst(String resourceTxtName,String des) throws IOException {
        Assert.assertNotNull("资源文件名不能为空",resourceTxtName);
        Assert.assertNotNull("目标路径不能为空",des);
        InputStream is = ClassLoader.getSystemResourceAsStream(resourceTxtName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        int i = 0;
        //首行代表下面内容的基本路径
        String baseSrcPath = "";
        if(des.lastIndexOf(File.separatorChar) != des.length()-1){
            des += File.separator;
        }
        while ((line = (br.readLine()))!= null ){
            if(!"".equalsIgnoreCase(StringUtils.nullToString(line))){

                if(i ==0){//第一行为基路径
                    baseSrcPath = line;
                    //基本路径不是以\结尾，则加上\
                    if(baseSrcPath.lastIndexOf(File.separatorChar) != baseSrcPath.length()-1){
                        baseSrcPath += File.separator;
                    }
                    logger.info("首行基路径【"+baseSrcPath+"】");
                }else{//除第一行外每行都是需要处理的文件路径
                    logger.info("除首行外每行的路径【"+line+"】");
                    //File f = new File(baseSrcPath+line);
                    //1.为每个file创建相应的文件夹
                    String realDesPath = des + line;
                    String realDesPathParent = realDesPath.substring(0, realDesPath.lastIndexOf('\\'));
                    logger.info("创建真正的路径的文件夹【"+realDesPathParent+"】，文件名为【"+realDesPath+"】");
                    if(!new File(realDesPathParent).mkdirs()){
                        throw new IOException("目录："+realDesPathParent+"不能被创建");
                    }


                    // 2.把源路径的file复制到目的地
                    //copyFile(f,new File(realDesPath));
                    copyFile2(baseSrcPath+line,realDesPath);
                }
                i++;
            }
        }
    }


    /**
     * 复制文件
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    /**
     *  复制文件
     * @param srcPath  源路径
     * @param desPath  目标路径
     * @throws IOException
     */
    public static void copyFile2(String srcPath, String desPath) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(srcPath));
            bw = new BufferedWriter(new FileWriter(desPath));
            char[] buffer = new char[1024];
            int len ;
            while ((len = br.read(buffer)) > 0){
                bw.write(buffer,0,len);
            }
            bw.flush();
        } catch (IOException e) {
            logger.error("复制文件出现错误："+e);
        }finally {
            if (br != null)br.close();;
            if (bw != null)bw.close();
        }
    }


    private static void copyFileListToDst(List<File> fileListFromtxt, String des) throws IOException {
        // 1.创建目标文件夹
        (new File(des)).mkdirs();

        for(int i =0;i<fileListFromtxt.size();i++){
            copyFile(fileListFromtxt.get(i),new File(des+File.separator+fileListFromtxt.get(i).getParent()));

        }
    }

    public static List<File> getFileListFromTxt(String path) throws IOException {
        List<File> fileList = new ArrayList<>();
        InputStream is = ClassLoader.getSystemResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = (br.readLine()))!= null ){
            if(!"".equalsIgnoreCase(StringUtils.nullToString(line))){
                logger.info("每行内容："+line);
                fileList.add(new File(line));
            }
        }
        return fileList;
    }





    public static void copyFileList(String src, String des) throws IOException {
        // 1.创建目标文件夹
        (new File(des)).mkdirs();

        // 2.获取源文件夹当前下的文件或目录
        File[] file = (new File(src)).listFiles();
        for (int i = 0; i < file.length; i++) {
            //3.是文件，则 复制文件
            if (file[i].isFile()) {
                String type = file[i].getName().substring(file[i].getName().lastIndexOf(".") + 1);
                if (type.equalsIgnoreCase("txt")) {// 转码处理
                    copyFile(file[i], new File(des + File.separator + file[i].getName()), "UTF-8", "GBK");
                } else {
                    copyFile(file[i], new File(des + file[i].getName()));
                }
            }
            //4.是目录，则复制目录
            if (file[i].isDirectory()) {
                String sourceDir = src + File.separator + file[i].getName();
                String targetDir = des + File.separator + file[i].getName();
                copyDirectiory(sourceDir, targetDir);
            }
        }
    }



    /**
     * 复制文件夹
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 复制文件并转换编码
     * @param srcFileName
     * @param destFileName
     * @param srcCoding
     * @param destCoding
     * @throws IOException
     */
    public static void copyFile(File srcFileName, File destFileName, String srcCoding, String destCoding) throws IOException {// 把文件转换为GBK文件
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFileName), srcCoding));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFileName), destCoding));
            char[] cbuf = new char[1024 * 5];
            int len;
            while ((len = br.read(cbuf)) != -1) {
                bw.write(cbuf, 0, len);
            }
            bw.flush();
        } finally {
            if (br != null)
                br.close();
            if (bw != null)
                bw.close();
        }
    }
}
