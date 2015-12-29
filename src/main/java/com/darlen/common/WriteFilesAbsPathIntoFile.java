/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   FileNameFileterWriteFile.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.common;

/**
 * Description. 打印某个目录下所有的文件，包括子文件夹中的文件，并把他们的绝对路径写入文件中
 * Created on  2015-07-26 下午10:30
 * -------------------------------------------------------------------------
 * 版本     修改时间        作者          修改内容 
 * 1.0.0        下午10:30              Darlen               create
 * 1.0.1        2015/12/29 20:55        Darlen              Update
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File文件综合应用
 * 需求：获取指定目录下面，指定扩展名的文件，将文件的绝对路径写到文本文件当中。
 *
 * 思路：1.需要深度遍历。--递归
 * 2.遍历的过程中过滤指定扩展名的文件--过滤器FileNameFilter，将文件存入容器中
 * 3.将容器中的内容遍历，写入到指定文件中
 *
 */
public class WriteFilesAbsPathIntoFile {
    private final static Logger logger = Logger.getLogger(WriteFilesAbsPathIntoFile.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        File dir = new File("F:\\test1");
        FilenameFilter fileter = new FileNameFilter(".txt");//过滤.bak文件
        List<File> fileList = new ArrayList<File>();
        getFileList(dir,fileter,fileList);
        File desFile = new File(dir, "FileList.txt");
        write2File(fileList, desFile);
    }
    /**
     *
     * @param dir 需要遍历的目录
     * @param filter 过滤满足条件的文件
     * @param fileList 存放符合条件的容器
     */
    public static void getFileList(File dir,FilenameFilter filter,List<File>fileList){
        if(dir.exists()){
            File[] files = dir.listFiles();//找到目录下面的所有文件
            for(File file:files){
                //递归
                if(file.isDirectory()){
                    getFileList(file,filter,fileList);
                }else{
                    //对遍历的文件进行过滤，符合条件的放入List集合中
                    if(filter.accept(dir, file.getName())){
                        fileList.add(file);
                    }
                }
            }

        }
    }
    /**
     * 将容器中的文件遍历，写入到目的文件中
     * @param fileList  存放满足条件的文件的集合
     * @param desFile 要写入的目的文件
     */
    public static void write2File(List<File>fileList,File desFile){
        BufferedWriter bw = null;
        try {
            //使用字符流写入到目的文件中
            bw = new BufferedWriter(new FileWriter(desFile));
            //遍历List集合
            for(File file:fileList){
                bw.write(file.getAbsolutePath());//写入目的文件绝对路径
                bw.newLine();
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(bw != null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * File文件综合应用
 * 需求：获取指定目录下面，指定扩展名的文件，将文件的绝对路径写到文本文件当中。
 * 思路：1.需要深度遍历。--递归
 * 2.遍历的过程中过滤指定扩展名的文件--过滤器FileNameFilter，将文件存入容器中
 * 3.将容器中的内容遍历，写入到指定文件中
 */
class FileNameFilter implements FilenameFilter {
    private final static Logger logger = Logger.getLogger(FileNameFilter.class);

    private String suffixName;//传入过滤的名称
    public FileNameFilter(String suffixName){
        this.suffixName = suffixName;
    }

    @Override
    public boolean accept(File dir, String name) {
        logger.info("dir：" + dir + "_____" + "name：" + name);
        return name.endsWith(suffixName);
    }

}
