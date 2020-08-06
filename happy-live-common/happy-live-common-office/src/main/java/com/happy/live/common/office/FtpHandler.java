package com.happy.live.common.office;

import com.happy.live.common.base.StringHandler;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;

/**
 * 此类描述的是:文件上传工具类
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:30:42
 */
public class FtpHandler {

	private static final Logger logger = LoggerFactory.getLogger(FtpHandler.class);

	/**
	 * 获取FTPClient对象
	 * 
	 * @param ftpHost
	 *            FTP主机服务器
	 * @param ftpPassword
	 *            FTP 登录密码
	 * @param ftpUserName
	 *            FTP登录用户名
	 * @param ftpPort
	 *            FTP端口 默认为21
	 * @return
	 */
	public static FTPClient getFTPClient(String ftpHost, String ftpPassword, String ftpUserName, int ftpPort) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.error("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
			} else {
				logger.info("FTP连接成功。");
			}
		} catch (SocketException e) {
			logger.error("FTP的IP地址可能错误，请正确配置。");
			StringHandler.getExceptionStack(e);
		} catch (IOException e) {
			logger.error("FTP的端口错误,请正确配置。");
			StringHandler.getExceptionStack(e);
		}
		return ftpClient;
	}

	/**
	 * 去 服务器的FTP路径下上读取文件
	 * @return
	 */
	public static InputStream readFromFtp(FTPClient ftpClient, String ftpPath, String fileName) {
		InputStream in = null;
		logger.info("开始读取绝对路径" + ftpPath + "文件!");
		try {
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);
			in = ftpClient.retrieveFileStream(fileName);
			return in;
		} catch (FileNotFoundException e) {
			logger.error("没有找到" + ftpPath + "文件");
			StringHandler.getExceptionStack(e);
			return null;
		} catch (SocketException e) {
			logger.error("连接FTP失败.");
			StringHandler.getExceptionStack(e);
			return null;
		} catch (IOException e) {
			StringHandler.getExceptionStack(e);
			logger.error("文件读取错误。");
			return null;
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				StringHandler.getExceptionStack(e);
			}
		}
	}

	/**
	 * 去 服务器的FTP路径下上读取文件
	 * @param ftpPath
	 * @return
	 */
	public static String readConfigFileForFTP(FTPClient ftpClient, String ftpPath, String fileName) {
		StringBuffer resultBuffer = new StringBuffer();
		InputStream in = null;
		logger.info("开始读取绝对路径" + ftpPath + "文件!");
		try {
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);
			in = ftpClient.retrieveFileStream(fileName);
		} catch (FileNotFoundException e) {
			logger.error("没有找到" + ftpPath + "文件");
			StringHandler.getExceptionStack(e);
			return "下载配置文件失败，请联系管理员.";
		} catch (SocketException e) {
			logger.error("连接FTP失败.");
			StringHandler.getExceptionStack(e);
		} catch (IOException e) {
			StringHandler.getExceptionStack(e);
			logger.error("文件读取错误。");
			return "配置文件读取失败，请联系管理员.";
		}
		if (in != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String data = null;
			try {
				while ((data = br.readLine()) != null) {
					resultBuffer.append(data + "\n");
				}
			} catch (IOException e) {
				logger.error("文件读取错误。");
				StringHandler.getExceptionStack(e);
				return "配置文件读取失败，请联系管理员.";
			} finally {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					StringHandler.getExceptionStack(e);
				}
			}
		} else {
			logger.error("in为空，不能读取。");
			return "配置文件读取失败，请联系管理员.";
		}
		return resultBuffer.toString();
	}

	/**
	 * 去 服务器的FTP路径下上读取文件
	 * 
	 * @param ftpPath
	 * @return
	 */
	public static String readLine(FTPClient ftpClient, String ftpPath, String fileName) {
		StringBuffer resultBuffer = new StringBuffer();
		InputStream in = null;
		logger.info("开始读取绝对路径" + ftpPath + "文件!");
		try {
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);
			in = ftpClient.retrieveFileStream(fileName);
		} catch (FileNotFoundException e) {
			logger.error("没有找到" + ftpPath + "文件");
			StringHandler.getExceptionStack(e);
			return "下载配置文件失败，请联系管理员.";
		} catch (SocketException e) {
			logger.error("连接FTP失败.");
			StringHandler.getExceptionStack(e);
		} catch (IOException e) {
			StringHandler.getExceptionStack(e);
			logger.error("文件读取错误。");
			return "配置文件读取失败，请联系管理员.";
		}
		if (in != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String data = null;
			try {
				while ((data = br.readLine()) != null) {
					resultBuffer.append(data + "\n");
				}
			} catch (IOException e) {
				logger.error("文件读取错误。");
				StringHandler.getExceptionStack(e);
				return "配置文件读取失败，请联系管理员.";
			} finally {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					StringHandler.getExceptionStack(e);
				}
			}
		} else {
			logger.error("in为空，不能读取。");
			return "配置文件读取失败，请联系管理员.";
		}
		return resultBuffer.toString();
	}
	
	/**  
     * Description: 向FTP服务器上传文件  
     * @param host FTP服务器hostname  
     * @param port FTP服务器端口  
     * @param username FTP登录账号  
     * @param password FTP登录密码  
     * @param basePath FTP服务器基础目录 
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath 
     * @param filename 上传到FTP服务器上的文件名  
     * @param input 输入流  
     * @return 成功返回true，否则返回false  
     */    
	public static boolean uploadFile(String host, int port, String username, String password, String basePath, String filePath, String filename,
			InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			// 切换到上传目录
			if (!ftp.changeWorkingDirectory(basePath + filePath)) {
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir))
						continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 上传文件
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
}
