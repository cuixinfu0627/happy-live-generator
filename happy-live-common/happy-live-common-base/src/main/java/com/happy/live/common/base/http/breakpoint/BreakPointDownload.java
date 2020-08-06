package com.happy.live.common.base.http.breakpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 断点下载文件：
 * 
 * @author admin
 */
public class BreakPointDownload {

	private static final Logger logs = LoggerFactory.getLogger(BreakPointDownload.class);

	/**
	 * 根据文件的大小范围来给定下载文件需要开启的线程数量：
	 */
	//一 兆
	public static final int RESOURCE_SIZE_YIZHAO = 1024 * 1024;
	// 十兆
	public static final int RESOURCE_SIZE_SHIZHAO = RESOURCE_SIZE_YIZHAO * 10;
	// 一百兆
	public static final int RESOURCE_SIZE_BAIZHAO = RESOURCE_SIZE_YIZHAO * 100;
	// 一个G
	public static final int RESOURCE_SIZE_QIANZHAO = RESOURCE_SIZE_YIZHAO * 1024;

	/**
	 * 下载服务器的文件：
	 * 
	 * @param httpUrl
	 *            文件服务器上的路径
	 * @param localFile
	 *            要下载到本地的文件
	 */
	public static void download(String httpUrl, String localFile) {
		final String fileUrl = httpUrl;
		final String downloadFile = localFile;
		new Thread() {
			public void run() {
				try {
					// 1.根据url地址创建URL对象,打开一个http类型的连接;
					URL url = new URL(fileUrl);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					// 2.设置请求参数:请求方式和请求连接的超时时间;
					conn.setRequestMethod("GET");
					// 请求连接的超时时间
					conn.setConnectTimeout(3000);
					// conn.setRequestProperty("User-Agent", "");
					// 3.接收服务器端返回的响应数据:首先判断响应码是不是200
					int code = conn.getResponseCode();
					// 判断响应码是不是为200k
					if (code == 200) {
						// 1.得到服务器端目标文件的大小
						int length = conn.getContentLength();
						// 根据文件的大小判断要开启的线程数：
						int threadCount = 1;
						if (length <= BreakPointDownload.RESOURCE_SIZE_SHIZHAO) {
							threadCount += 0;
						} else if (length > BreakPointDownload.RESOURCE_SIZE_SHIZHAO
								&& length <= BreakPointDownload.RESOURCE_SIZE_BAIZHAO) {
							threadCount += 1;
						} else if (length > BreakPointDownload.RESOURCE_SIZE_BAIZHAO
								&& length <= BreakPointDownload.RESOURCE_SIZE_QIANZHAO) {
							threadCount += 2;
						} else {
							threadCount += 3;
						}
						// 在客户端本地创建一个与服务器端大小一模一样的空白文件
						RandomAccessFile raf = new RandomAccessFile(
								downloadFile, "rw");
						raf.setLength(length);
						// 2.创建子线程
						for (int threadId = 0; threadId < threadCount; threadId++) {
							// 3.计算每个子线程下载数据的开始位置和结束位置;
							// 计算每个子线程下载数据范围的大小
							int blockSize = length / threadCount;
							// 计算每个子线程下载数据开始位置
							int startIndex = threadId * blockSize;
							// 计算每个子线程下载数据结束位置
							int endIndex = (threadId + 1) * blockSize - 1;
							// 如果当前线程是最后一个线程
							if (threadId == (threadCount - 1)) {
								endIndex = length - 1;
							}
							new MultiThreadDownloador(fileUrl, startIndex,
									endIndex, threadId, threadCount,
									downloadFile).start();
						}
					}
					if (code == 404) {
						logs.error("找不到你要访问的资源");
					}
					if (code == 503) {
						logs.error("服务器端故障,请您稍后重试！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	public static void main(String[] args) {
		String url="https://banana2018.oss-cn-qingdao.aliyuncs.com/69b0c05f19e54b2fba091f1af540b7ca.jpg";
		String fileName = url.substring(url.lastIndexOf("/")+1);
		String localFilePath = "E:/测试文件/wallpaper"+ File.separator+fileName;
		BreakPointDownload.download(url, localFilePath);
	}
}
