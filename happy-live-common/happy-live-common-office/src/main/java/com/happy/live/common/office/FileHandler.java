package com.happy.live.common.office;

import com.happy.live.common.base.StringHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLEncoder;


/**
 * 此类描述的是: 文件助手
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:31:22
 */
@SuppressWarnings("restriction")
public class FileHandler {

	private static final Logger logs = LoggerFactory.getLogger(FileHandler.class);
	public static File dirFrom;
	public static File dirTo;
	
	/**
	 * 得到文件所有内容
	* @Title: readFileByLines
	* @param filePath 文件地址
	* @return String 文件内容
	 */
	public static String readByLines(String filePath) throws IOException {
		//判断文件是否存在
		File file = new File(filePath);
		if(!file.exists()){
			throw new RuntimeException("文件不存在");
		}
		//读取文件内容
		StringBuffer info = new StringBuffer();
        BufferedReader reader = null;
        try {
        	reader = new BufferedReader(new FileReader(file));
        	String line = null;
        	while ((line = reader.readLine()) != null) {
        		info.append(line);
        	}
        	reader.close();
        } finally {
        	if (reader != null) {
        		try {
        			reader.close();
        		} catch (IOException e1) {
        		}
        	}
        }
        return info.toString();
	}
	
	/**
	 * 方法: writeFile <br>
	 * 描述: 生成文件，并写入文件内容 <br>
	 * 作者: hailong@xiu8.com<br>
	 * 时间: 2016年10月25日 下午6:17:00
	 * @param path	文件存储的路径
	 * @param fileName	文件名称(需要带后缀)
	 * @param fileContent	需要写入文件的内容
	 * @return
	 */
	public static boolean writeFile(String path, String fileName, String fileContent) {
		Boolean isSucc = false;
		try {
			//如果文件夹不存在，则创建新的文件夹
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			File file = new File(fileName);
			//如果文件不存在，则创建新的文件
			if (!file.exists()) {
				file.createNewFile();
				logs.info("success create file,the file is " + path + fileName);
			}
			//创建文件成功后，写入内容到文件里
			isSucc = writeFileContent(path + fileName, fileContent);
		} catch (Exception e) {
			logs.error("创建文件异常，" + StringHandler.getExceptionStack(e));
		}
		return isSucc;
	}
	
	/**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
	private static boolean writeFileContent(String filepath,String newstr) throws IOException{
        Boolean bool = false;
        FileWriter fw = null;
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            fw = new FileWriter(filepath, true);
            fw.write(newstr);
            fw.flush();
            bool = true;
        } catch (Exception e) {
        	logs.error("文件内容写入异常，"+ StringHandler.getExceptionStack(e));
        }finally {
            //不要忘记关闭
            if (fw != null) {
            	fw.close();
            }
        }
        return bool;
    }
	/**
	 * 此方法描述的是：   
	 * @param file 上传文件
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @throws Exception 
	 * void
	 */
	public static boolean uploadFile(byte[] file, String filePath, String fileName){ 
        boolean flag = true ;
		File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out;
		try {
			out = new FileOutputStream(filePath+"\\"+fileName);
			out.write(file);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			flag = false ;
			e.printStackTrace();
		}
		return flag;
    }
	
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param fileName 下载文件名
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public static String encodeDownloadFilename(String fileName)
			throws IOException {
		try {
			// 英文操作系统下IE11必须是fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
			// Chrome必须不能设置GBK,
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
			// Firefox可以设置，也可以不设置
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		return fileName;
	}
	
	public static String encodeDownloadFilename2(String filename, String agent)throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?"
					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
					+ "?=";
			filename = filename.replaceAll("\r\n", "");
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+"," ");
		}
		return filename;
	}
	
	/**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if(!file.exists()) {
            return flag;
            
        }else {
            // 判断是否为文件
            if(file.isFile()) {  
            	// 为文件时调用删除文件方法
                return deleteFile(sPath);
            }else {  
            	// 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
    
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag){
                	 break;
                }
            }else {
            	//删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag){
                	break;
               }
            }
        }
        if (!flag) {
        	return false;
        }
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        }else {
            return false;
        }
    }
	   
	/**
	 * 此方法描述的是：   目标路径创建文件夹   
	 * @param file 
	 * void
	 */
    public static  void copyFileToDir(File file) {   
        File[] files = file.listFiles();   
        for (File file_ : files) {   
             String tempfrom = file_.getAbsolutePath();   
             String tempto = tempfrom.replace(dirFrom.getAbsolutePath(),   
                     dirTo.getAbsolutePath()); // 后面的路径 替换前面的路径名   
            if (file_.isDirectory()) {   
                 File tempFile = new File(tempto);   
                 tempFile.mkdirs();   
                 copyFileToDir(file_);   
             }else {   
                int endindex = tempto.lastIndexOf("\\");// 找到"/"所在的位置   
                String mkdirPath = tempto.substring(0, endindex);   
                File tempFile = new File(mkdirPath);   
                tempFile.mkdirs();// 创建立文件夹   
                copy(tempfrom, tempto);   
             }   
         }   
     }   
	    
     /**
      * 此方法描述的是：   封装好的文件拷贝方法
      * @param from
      * @param to 
      * void
      */
    public static void copy(String from, String to) {
        try {
            InputStream in = new FileInputStream(from);   
            OutputStream out = new FileOutputStream(to);   
            byte[] buff = new byte[1024];   
            int len = 0;   
            while ((len = in.read(buff)) != -1) {   
                 out.write(buff, 0, len);   
             }   
             in.close();   
             out.close();   
         } catch (FileNotFoundException e) {   
             e.printStackTrace();   
         } catch (IOException e) {   
             e.printStackTrace();   
         }   
     } 
    public static void main(String[] args) {
    	String path = "G:/迅雷下载1/";
		FileHandler.writeFile(path, "delAvatar.txt", "5c/566/5e579c333d88a40af74a6666c2a5665d.png\r\n");
	}
	
}
