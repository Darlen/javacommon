/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   RenameAllFileDemo.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.common;

import org.apache.commons.io.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Description:修改指定文件夹及子文件夹下所有文件的名字，比如说从某个网站上下载下来的电影都会带有那个网站的信息
 * （飞鸟娱乐(bbs.hdbird.com).王牌特工：特工学院（完整无删版）.720p.中英字幕）
 * Created on  2015-07-29 下午11:45
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午11:45              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class RenameAllFileDemo {
    private static Logger logger = Logger.getLogger(RenameAllFileDemo.class);
    public static void main(String[] args) throws IOException {
        //org.apache.commons.io.FileUtils.copyFile();
        String targetDir = "F:\\test1";
        String srcStr = "abc";
        String targetStr = "Test";
        logger.debug("开始修改指定路径【"+targetDir+"】文件...");
        long start = System.currentTimeMillis();
        renameFiles(targetDir,targetStr,srcStr);
        logger.debug("修改指定路径的文件成功，总共耗时【"+(System.currentTimeMillis()-start)+"】 ms");
    }

    /**
     *
     * @param src 源路径，
     * @param  replaceStrSrc 需要被替换的源字符串
     * @param replaceStrDes  需要替换成的目的字符串
     * TODO:1.加上是否需要修改当前文件夹2.过滤某些文件
     * @throws IOException
     */
    public static void renameFiles(String src,String replaceStrSrc,String replaceStrDes) throws IOException {
        File file = new File(src);
       //\Course\Spring\尚硅谷Spring4视频教程\2. 尚硅谷_佟刚_Spring_IOC&DI概述 - 副本.wmv
        if(file.exists()){
            logger.info("   文件【"+file.getAbsolutePath()+"】存在，开始执行修改文件名...");
            if(file.isFile() ){//如果是文件，则直接修改名字&& file.renameTo(new File(file.getAbsolutePath()))
                String path = file.getParent();
                String name = file.getName();
                logger.info("       是文件 【"+name+"】，"+"路径 【"+path+"】, 是否包含被替换的源字符串 【"+ (name.indexOf(replaceStrSrc) != -1)+"】");
                if(name.indexOf(replaceStrSrc) != -1){
                    String beforePath = path + File.separator+name;
                    name = name.replace(replaceStrSrc,replaceStrDes);
                    path += File.separator+name;
                    if(file.renameTo(new File(path))){
                        logger.info("           修改文件名成功，现在的路径为【"+path+"】，以前的路径为【"+beforePath+"】");
                    }else{
                        throw new IOException("         修改文件名失败,以前的路径为【"+beforePath+"】");
                    }
                }
            }else{//如果是文件夹，则修改自己名字后继续迭代子文件夹中的文件
                //1.先修改文件夹
                boolean changeDirectory = false;
                String dirParentPath = file.getParent();
                String dicName = file.getName();
                logger.info("       文件夹【"+dicName+"】，"+"父节点路径【"+dirParentPath+"】,文件名 【"+dicName+"】， 是否包含被替换的源字符串 【"+ (dicName.indexOf(replaceStrSrc) != -1)+"】");
                if(dicName.indexOf(replaceStrSrc) != -1){
                    String beforePath = dirParentPath + File.separator+dicName;
                    dicName = dicName.replace(replaceStrSrc,replaceStrDes);
                    dirParentPath += File.separator+dicName;
                    if(file.renameTo(new File(dirParentPath))){
                        changeDirectory = true;
                        logger.info("           修改文件夹名成功，现在的路径为【"+dirParentPath+"】，以前的路径为【"+beforePath+"】");
                    }else{
                        throw new IOException("         修改文件名失败，以前的路径为【"+beforePath+"】");
                    }
                }

                //2.修改后的文件夹名字变了,file其实的路径就不存在了，必需重新获取文件夹的文件流，然后再遍历里面的文件
                if(changeDirectory) file = new File(dirParentPath);
                File[] files = file.listFiles();
                for(File f :files){
                    logger.info("   迭代文件夹 【"+f.getParent()+"】 下的文件 【"+f.getAbsolutePath()+"】...");
                    renameFiles(f.getAbsolutePath(),replaceStrSrc,replaceStrDes);
                }
            }
        }else{
            throw new IOException("文件不存在，请确认后再执行程序...");
        }
    }
}
