/** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    ProjectName javacommon
 *    File Name   FileUtils.java 
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 *    Copyright (c) 2015 Darlen . All Rights Reserved. 
 *    注意： 本内容仅限于XXX公司内部使用，禁止转发
 * ** ** ** ** ** ** ** **** ** ** ** ** ** ** **** ** ** ** ** ** ** **
 * */
package com.darlen.common;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Collection;

/**
 * Description.
 * Created on  2015-07-26 下午12:09
 * -------------------------------------------------------------------------
 * 版本          修改时间              作者               修改内容 
 * 1.0.0        下午12:09              Darlen              create
 * -------------------------------------------------------------------------
 *
 * @author Darlen liu
 */
public class FileUtils {
    private final static Logger logger = Logger.getLogger(FileUtils.class);
    private static final String FOLDER_SEPARATOR = "/";
    private static final char EXTENSION_SEPARATOR = '.';

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
    public static void copy(File inputFile, File outputFile, boolean isOverWrite)
            throws IOException {
        if (!inputFile.exists()) {
            throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
        }
        copyPri(inputFile, outputFile, isOverWrite);
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
    public static void copyPri(File inputFile, File outputFile,
                                boolean isOverWrite) throws IOException {
        // 是个文件。
        if (inputFile.isFile()) {
            copySimpleFile(inputFile, outputFile, isOverWrite);
        } else {
            // 文件夹
            if (!outputFile.exists()) {
                outputFile.mkdir();
            }
            // 循环子文件夹
            for (File child : inputFile.listFiles()) {
                copy(child,
                        new File(outputFile.getPath() + "/" + child.getName()),
                        isOverWrite);
            }
        }
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
                                       boolean isOverWrite) throws IOException {
        // 目标文件已经存在
        if (outputFile.exists()) {
            if (isOverWrite) {
                if (!outputFile.delete()) {
                    throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
                }
            } else {
                // 不允许覆盖
                return;
            }
        }
        InputStream in = new FileInputStream(inputFile);
        OutputStream out = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
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
        deleteFile(file);
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
        if (file == null || !file.exists()) {
            return;
        }
        // 单文件
        if (!file.isDirectory()) {
            boolean delFlag = file.delete();
            if (!delFlag) {
                throw new RuntimeException(file.getPath() + "删除失败！");
            } else {
                return;
            }
        }
        // 删除子目录
        for (File child : file.listFiles()) {
            deleteFile(child);
        }
        // 删除自己
        file.delete();
    }

    /**
     * 从文件路径中抽取文件的扩展名, 例如. "mypath/myfile.txt" -> "txt". * @author Darlen
     *
     * @date 2014年06月24日
     * @param path 文件路径
     * @return 如果path为null，直接返回null。
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return null;
        }
        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return null;
        }
        return path.substring(extIndex + 1);
    }

    /**
     * 从文件路径中抽取文件名, 例如： "mypath/myfile.txt" -> "myfile.txt"。 * @author Darlen
     *
     * @date 2014年06月24日
     * @param path
     *            文件路径。
     * @return 抽取出来的文件名, 如果path为null，直接返回null。
     */
    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1)
                : path);
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
    public static void save(byte[] content, File file) throws IOException {
        if (file == null) {
            throw new RuntimeException("保存文件不能为空");
        }
        if (content == null) {
            throw new RuntimeException("文件流不能为空");
        }
        InputStream is = new ByteArrayInputStream(content);
        save(is, file);
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
    public static void save(InputStream streamIn, File file) throws IOException {
        if (file == null) {
            throw new RuntimeException("保存文件不能为空");
        }
        if (streamIn == null) {
            throw new RuntimeException("文件流不能为空");
        }
        // 输出流
        OutputStream streamOut = null;
        // 文件夹不存在就创建。
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        streamOut = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
            streamOut.write(buffer, 0, bytesRead);
        }
        streamOut.close();
        streamIn.close();
    }


    /**
     * 获取系统的临时目录路径
     * @return
     */
    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");//C:\Users\ADMINI~1\AppData\Local\Temp\

    }

    public static File getTempDirectory() {
        return new File(getTempDirectoryPath());

    }

    /**
     * 获取用户的主目录路径
     * @return  C:\Users\Administrator
     */
    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    public static File getUserDirectory() {
        return new File(getUserDirectoryPath());
    }

    /**
     * 根据指定的文件获取一个新的文件输入流
     * @param file
     * @return
     * @throws Exception
     */
    public static FileInputStream createInputStream(File file) throws Exception {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is adirectory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new Exception("File '" + file + "' does notexist");
        }
        return new FileInputStream(file);
    }

    /**
     * 根据指定的文件获取一个新的文件输出流
     * @param file
     * @return
     * @throws IOException
     */
    public static FileOutputStream createOutputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File'" + file + "' exists but is a directory");
            }

            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null &&parent.exists() == false) {
                if (parent.mkdirs() ==false) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file);
    }

    /**
     * 字节转换成直观带单位的值（包括单位GB，MB，KB或字节）
     * @param size
     * @return
     */
    public static String byteCountToDisplaySize(long size) {
        String displaySize = "";
//        if (size / ONE_GB > 0) {
//            displaySize =String.valueOf(size / ONE_GB) + " GB";
//        } else if (size / ONE_MB > 0) {
//            displaySize =String.valueOf(size / ONE_MB) + " MB";
//        } else if (size / ONE_KB > 0) {
//            displaySize =String.valueOf(size / ONE_KB) + " KB";
//        } else {
//            displaySize =String.valueOf(size) + " bytes";
//        }
        return displaySize;
    }

    /**
     * 创建一个空文件，若文件已经存在则只更改文件的最近修改时间
     * @param file
     * @throws IOException
     */
    public static void touch(File file) throws IOException {
        if (!file.exists()) {
            OutputStream out =createOutputStream(file);
            IOUtils.closeQuietly(out);
        }
        boolean success =file.setLastModified(System.currentTimeMillis());
        if (!success) {
            throw new IOException("Unableto set the last modification time for " + file);
        }
    }

    /**
     * 把相应的文件集合转换成文件数组
     * @param files
     * @return
     */
    public static File[] convertFileCollectionToFileArray(Collection<File> files) {
        return files.toArray(new File[files.size()]);
    }

    public static void main(String[] args) {
        //http://blog.csdn.net/gao36951/article/details/38302553
        logger.info(getUserDirectoryPath());
    }
}
