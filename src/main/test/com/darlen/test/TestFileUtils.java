/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   TestFileUtils.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

/**
 * Description.
 * Created on  2015-07-26 下午10:49
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午10:49              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class TestFileUtils {
    private static final Logger logger = Logger.getLogger(TestDateUtils.class);
    /**
     * 功能：复制文件或者文件夹。
     *
     * @author Darlen
     * @date 2014年06月24日
     * @param inputFile
     *            源文件
     * @param outputFile
     *            目的文件
     * @param isOverWrite
     *            是否覆盖(只针对文件)
     * @throws java.io.IOException
     */
    @Test
    public  void testCopy(File inputFile, File outputFile, boolean isOverWrite){

    }

    /**
     * 功能：为copy 做递归使用。
     *
     * @author Darlen
     * @date 2014年06月24日
     * @param inputFile
     * @param outputFile
     * @param isOverWrite
     * @throws java.io.IOException
     */
    private static void copyPri(File inputFile, File outputFile,
                                boolean isOverWrite){

    }

    /**
     * 功能：copy单个文件
     *
     * @author Darlen
     * @date 2014年06月24日
     * @param inputFile
     *            源文件
     * @param outputFile
     *            目标文件
     * @param isOverWrite
     *            是否允许覆盖
     * @throws java.io.IOException
     */
    private static void copySimpleFile(File inputFile, File outputFile,
                                       boolean isOverWrite){

    }

    /**
     * 功能：删除文件
     *
     * @author Darlen
     * @date 2014年06月24日
     * @param file
     *            文件
     */
    public static void delete(File file) {

    }

    /**
     * 功能：删除文件，内部递归使用
     *
     * @author Darlen
     * @date 2014年06月24日
     * @param file
     *            文件
     * @return boolean true 删除成功，false 删除失败。
     */
    private static void deleteFile(File file) {

    }

    /**
     * 从文件路径中抽取文件的扩展名, 例如. "mypath/myfile.txt" -> "txt". * @author Darlen
     *
     * @date 2014年06月24日
     * @param path 文件路径
     * @return 如果path为null，直接返回null。
     */
    public void getFilenameExtension(String path) {

    }

    /**
     * 从文件路径中抽取文件名, 例如： "mypath/myfile.txt" -> "myfile.txt"。 * @author Darlen
     *
     * @date 2014年06月24日
     * @param path
     *            文件路径。
     * @return 抽取出来的文件名, 如果path为null，直接返回null。
     */
    public void getFilename(String path) {

    }

    /**
     * 功能：保存文件。
     *
     * @author Darlen
     * @date 2014年06月24日
     * @param content
     *            字节
     * @param file
     *            保存到的文件
     * @throws java.io.IOException
     */
    public static void save(byte[] content, File file){

    }

    /**
     * 功能：保存文件
     *
     * @author Darlen
     * @date 2014年06月24日
     * @param streamIn
     *            文件流
     * @param file
     *            保存到的文件
     * @throws java.io.IOException
     */
    public static void save(InputStream streamIn, File file){

    }



}
