package com.happy.live.common.base.http.breakpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiThreadDownloador extends Thread {
	private static final Logger logs = LoggerFactory.getLogger(MultiThreadDownloador.class);

	private String path; //文件服务器的路径
	private String downloadFile; //保存下载文件和路径
	private int startIndex; //文件开始下载的位置
	private int endIndex;  //文件结束下载的位置
	private int threadId; //线程id
	private static int threadCount; //线程数量
	
	public MultiThreadDownloador(String path, int startIndex, int endIndex,
			int threadId,int threadCount,String downloadFile) {
		this.path = path;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.threadId = threadId;
		this.threadCount = threadCount;
		this.downloadFile =downloadFile ;
	}

	@Override
	public void run() {
		
		//记录下载数据保存的目录:
		File downloadPath = new File(downloadFile);
		String filepath =downloadPath.getParent() ;
		//同步下载文件
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(3000);
			//下次下载时应该读取上下载的位置
			File file = new File(filepath+ "/"+threadId+".txt");
			if(file.exists()&&file.length()>0){
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);
				String line = br.readLine();
				int currentPosition = Integer.parseInt(line);
				startIndex = currentPosition;
				fis.close();
				isr.close();
				br.close();
			}
			
			//告诉服务器端客户端只请求这个范围内的数据
			conn.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
			
			int  code = conn.getResponseCode();
			//要求每次修改文件的数据内容后立即同步到磁盘文加上
			RandomAccessFile raf = new RandomAccessFile(downloadFile, "rwd");
			
			//指定该线程从文件的哪个位置开始写数据
			raf.seek(startIndex);
			//判断服务端是否成功返回数据,206表示请求部分数据资源成功
			if(code == 206){
				InputStream is = conn.getInputStream();
				
				int len = -1;
				byte[] buffer = new byte[1024*256];
				//logs.info("线程ThreadId:"+threadId+"开始下载.....startIndex:"+startIndex+",endIndex:"+endIndex);
				long start = System.currentTimeMillis(); 
				//线程下载数据的总数
				int total = 0;
				while((len = is.read(buffer)) != -1){
					raf.write(buffer, 0, len);
					//计算线程下载数据的大小
					total = total + len;
					//计算线程下载到了哪个位置
					int currentPosition = startIndex + total;
					//把下载的位置记录到一个文件中,便于下次下载时从这个文件中读取开始下载的位置
					RandomAccessFile threadRaf = new RandomAccessFile(filepath + "/"+threadId+".txt", "rwd");
					threadRaf.write((currentPosition + "").getBytes());
					threadRaf.close();
					//System.out.println("线程threadId:"+threadId+"下载的当前位置:"+currentPosition);
				}
				
				is.close();
				raf.close();
				//logs.info("线程ThreadId:"+threadId+"下载完成...");
				synchronized (MultiThreadDownloador.class) {
					threadCount --;
					//当前线程下载结束后,删除对应的文件
					File f = new File(filepath+ "/"+threadId+".txt");
					f.delete();
					//logs.info("线程ThreadId:"+threadId+"删除文件...");
					
					if(threadCount == 0){
						logs.info("文件:{}下载完成...",downloadFile);
						long end = System.currentTimeMillis();
						logs.info("下载文件耗时: " + (end - start));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logs.error("线程ThreadId:"+threadId+"...访问网络错误...");
		}
	}
}
