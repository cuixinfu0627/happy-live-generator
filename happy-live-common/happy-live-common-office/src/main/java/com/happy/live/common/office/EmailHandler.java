package com.happy.live.common.office;
//
//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.HtmlEmail;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import java.nio.charset.Charset;
//import java.util.*;

import com.happy.live.common.base.StringHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

/**
 * 类名:EmailHandler <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/4/11 15:02 <tb>
 */
public class EmailHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmailHandler.class);

	private final static String default_charset = "UTF-8";

	public static enum EncryptionTypes {
		Default, TLS, SSL
	}

	private String mail_host = "";
	private int mail_port = 25;
	private int encryptionType = EncryptionTypes.Default.ordinal();
	private boolean auth = false;
	private String mail_host_account = "";
	private String mail_host_password = "";
	private boolean isHtml = false;
	private boolean ssl = false;

	public EmailHandler(String mail_host) {
		this.mail_host = mail_host;
	}

	public EmailHandler(String mail_host, boolean auth, String account, String password) {
		this(mail_host, 25, EncryptionTypes.Default.ordinal(), auth, account, password);
	}

	/**
     * 初始化邮箱信息
     *
     * @param mail_host
     *            邮箱主机
     * @param mail_port
     *            邮箱端口
     * @param encryptionType
     *            加密类型
     * @param auth
     *            是否身份验证
     * @param account
     *            发件箱账户
     * @param password
     *            发件箱密码
     */
	public EmailHandler(String mail_host, int mail_port, int encryptionType, boolean auth, String account, String password) {
		this.mail_host = mail_host;
		this.mail_port = mail_port;
		this.encryptionType = encryptionType;
		this.auth = auth;
		this.mail_host_account = account;
		this.mail_host_password = password;
	}

	/**
     * 初始化邮箱信息
     *
     * @param mail_host
     *            邮箱主机
     * @param auth
     *            是否身份验证
     * @param account
     *            发件箱账户
     * @param password
     *            发件箱密码
     * @param isHtml
     *            是否是html
     */
	public EmailHandler(String mail_host, boolean auth, String account, String password, boolean isHtml) {
		this(mail_host, 25, EncryptionTypes.Default.ordinal(), auth, account, password, isHtml);
	}

	 /**
     * 初始化邮箱信息
     *
     * @param mail_host
     *            邮箱主机
     * @param mail_port
     *            邮箱端口
     * @param encryptionType
     *            加密类型
     * @param auth
     *            是否身份验证
     * @param account
     *            发件箱账户
     * @param password
     *            发件箱密码
     * @param isHtml
     *            是否是html
     */
	public EmailHandler(String mail_host, int mail_port, int encryptionType, boolean auth, String account, String password, boolean isHtml) {
		this.mail_host = mail_host;
		this.mail_port = mail_port;
		this.encryptionType = encryptionType;
		this.auth = auth;
		this.mail_host_account = account;
		this.mail_host_password = password;
		this.isHtml = isHtml;
	}

	/**
     * 方法: isAsynSendEmail <br>
     * 描述: 是否异步发送邮件 <br>
     * 作者: hailong@xiu8.com<br>
     * 时间: 2015年5月5日 下午1:51:28
     * @param senderAddress	发送地址
     * @param senderName	发送方名称
     * @param receiverAddress	接收地址
     * @param sub	邮件标题
     * @param msg	邮件内容
     * @param isAsyn	是否异步  true=异步；false=同步
     * @throws Exception
     */
	public void isAsynSendEmail(final String senderAddress, final String senderName, final String receiverAddress, final String sub, final String msg,
			boolean isAsyn) throws Exception {
		if (isAsyn) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						sendEmail(senderAddress, senderName, receiverAddress, sub, msg);
					} catch (Exception e) {
						new RuntimeException("异步发送邮件异常！" + StringHandler.getExceptionStack(e));
					}
				}
			}).start();
		} else {
			sendEmail(senderAddress, senderName, receiverAddress, sub, msg);
		}
	}

	/**
     * Send email to a single recipient or recipient string.
     *
     * @param senderAddress
     *            the sender email address
     * @param senderName
     *            the sender name
     * @param receiverAddress
     *            the recipient email address
     * @param sub
     *            the subject of the email
     * @param msg
     *            the message content of the email
     */
	public void sendEmail(String senderAddress, String senderName, String receiverAddress, String sub, String msg) throws Exception {
		String[] address = receiverAddress.split(";");
		List<String> recipients = new ArrayList<>();
		for (int i = 0; i < address.length; i++) {
			if (address[i].trim().length() > 0) {
				recipients.add(address[i]);
			}
		}

		this.sendEmail(senderAddress, senderName, recipients, sub, msg);
	}

	public void sendEmail(String senderAddress, String senderName, List<String> recipients, String sub, String msg) throws SendFailedException {
		this.sendEmail(senderAddress, senderName, recipients, sub, msg, null);
	}

	/**
     * 发送邮件
     *
     * @param senderAddress
     *            发送方邮箱地址
     * @param senderName
     *            发送者名称
     * @param receiverAddress
     *            接收者邮箱地址，多个发送者用;分开
     * @param sub
     *            主题
     * @param msg
     *            邮件内容
     * @param attachments
     *            附件
     */
	public void sendEmail(String senderAddress, String senderName, String receiverAddress, String sub, String msg, Collection<Object> attachments)
			throws Exception {
		String[] address = receiverAddress.split(";");
		List<String> recipients = new ArrayList<>();
		for (int i = 0; i < address.length; i++) {
			if (address[i].trim().length() > 0) {
				recipients.add(address[i]);
			}
		}
		this.sendEmail(senderAddress, senderName, recipients, sub, msg, attachments);
	}

	/**
     * Send email to a list of recipients.
     *
     * @param senderAddress
     *            发件人电子邮件地址
     * @param senderName
     *            发送者的名字
     * @param recipients
     *            收件人集合
     * @param sub
     *            电子邮件的主题
     * @param msg
     *            电子邮件的消息内容
     * @param attachments
     *            附件集合
     */
	public void sendEmail(String senderAddress, String senderName, List<String> recipients, String sub, String msg, Collection<Object> attachments)
			throws SendFailedException {

		Transport transport = null;

		try {

			Properties props = this.getProperties();
			MyAuthenticator myAuth = new MyAuthenticator(this.mail_host_account, this.mail_host_password);

			//Session session = Session.getInstance(props, myAuth);

			Session session = this.getSession(props);

			MimeMessage message = new MimeMessage(session);

			if (this.getDefaultIsHtml()) {
				message.addHeader("Content-type", "text/html");
			} else {
				message.addHeader("Content-type", "text/plain");
			}

			message.setSubject(sub, default_charset);
			message.setFrom(new InternetAddress(senderAddress, senderName));

			for (Iterator<String> it = recipients.iterator(); it.hasNext();) {
				String email = (String) it.next();
				message.addRecipients(Message.RecipientType.TO, email);
			}

			Multipart mp = new MimeMultipart();

			//content
			MimeBodyPart contentPart = new MimeBodyPart();

			if (this.getDefaultIsHtml()) {
				contentPart.setContent("<meta http-equiv=Content-Type content=text/html; charset=" + default_charset + ">" + msg, "text/html;charset="
						+ default_charset);
			} else {
				contentPart.setText(msg, default_charset);
			}

			mp.addBodyPart(contentPart);

			//attachment
			if (attachments != null) {
				MimeBodyPart attachPart;
				for (Iterator<Object> it = attachments.iterator(); it.hasNext();) {
					attachPart = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(it.next().toString().trim());
					attachPart.setDataHandler(new DataHandler(fds));
					if (fds.getName().indexOf("$") != -1) {
						attachPart.setFileName(fds.getName().substring(fds.getName().indexOf("$") + 1, fds.getName().length()));
					} else {
						attachPart.setFileName(fds.getName());
					}
					mp.addBodyPart(attachPart);
				}

			}

			message.setContent(mp);

			message.setSentDate(new Date());

			if (this.getDefaultEncryptionType() == EncryptionTypes.SSL.ordinal()) {
				Transport.send(message);
			} else {
				transport = session.getTransport("smtp");
				transport.connect(this.mail_host, this.mail_port, this.mail_host_account, this.mail_host_password);
				transport.sendMessage(message, message.getAllRecipients());
			}

		} catch (Exception e) {
			logger.error("send mail error", e);
			throw new SendFailedException(e.toString());
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (Exception ex) {
				}
			}
		}
	}

	private Properties getProperties() {

		Properties props = System.getProperties();

		int defaultEncryptionType = this.getDefaultEncryptionType();

		if (defaultEncryptionType == EncryptionTypes.TLS.ordinal()) {
			props.put("mail.smtp.auth", String.valueOf(this.auth));
			props.put("mail.smtp.starttls.enable", "true");
		} else if (defaultEncryptionType == EncryptionTypes.SSL.ordinal()) {
			props.put("mail.smtp.host", this.mail_host);
			props.put("mail.smtp.socketFactory.port", this.mail_port);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", String.valueOf(this.auth));
			props.put("mail.smtp.port", this.mail_port);
		} else {
			props.put("mail.smtp.host", this.mail_host);
			props.put("mail.smtp.auth", String.valueOf(this.auth));
		}

		return props;
	}

	private Session getSession(Properties props) {
		Session session = null;

		if (this.getDefaultEncryptionType() == EncryptionTypes.TLS.ordinal()) {
			session = Session.getInstance(props);
		} else if (this.getDefaultEncryptionType() == EncryptionTypes.SSL.ordinal()) {
			session = Session.getInstance(props, new MyAuthenticator(this.mail_host_account, this.mail_host_password));
		} else {
			session = Session.getDefaultInstance(props, null);
		}
		return session;
	}

	private boolean getDefaultIsHtml() {
		boolean rst = this.isHtml;
		return rst;
	}

	private class MyAuthenticator extends Authenticator {
		String user;
		String password;

		public MyAuthenticator(String user, String password) {
			this.user = user;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(this.user, this.password);
		}
	}

	/**
     * get default encryption type, for 465, SSL for 587, TLS
     *
     * @return
     */
	private int getDefaultEncryptionType() {
		int rst = this.encryptionType;
		if (this.encryptionType == EncryptionTypes.Default.ordinal()) {
			if (this.mail_port == 465) {
				rst = EncryptionTypes.SSL.ordinal();
			} else if (this.mail_port == 587) {
				rst = EncryptionTypes.TLS.ordinal();
			}
		}

		return rst;
	}


    /**
     * 发邮件
     * @param title 标题
     * @param msg 内容
     * @param subject 主题
     * @param receiverEmail 收件人
     * @return true=发邮件成功,false=发邮件失败
     */
    public static boolean sendEmail(EmailHandler emailHandler,String title, String msg, String subject, String receiverEmail) {
        try {
			emailHandler.sendEmail(emailHandler.mail_host_account, title, receiverEmail, subject, msg);
        } catch (Exception e) {
            logger.error("发邮件出错,", e);
            return false;
        }
        return true;
    }

    /**
     * 发邮件,主题=标题
     * @param title 标题
     * @param msg 内容
     * @param receiverEmail 收件人
     * @return true=发邮件成功,false=发邮件失败
     */
    public static boolean sendEmail(EmailHandler emailHandler,String title, String msg, String receiverEmail) {
        return sendEmail(emailHandler,title, msg, title, receiverEmail);
    }

	/**
	 *
	 * @param toAddress		收件人邮箱
	 * @param mailSubject	邮件主题
	 * @param mailBody		邮件正文
	 * @param mailBody		title
	 * @return
	 */
	public boolean sendMail(String toAddress, String mailSubject, String mailBody,String sendNick,Collection<Object> attachments){
//		try {
//			// Create the email message
//			HtmlEmail email = new HtmlEmail();
//
//			//email.setDebug(true);		// 将会打印一些log
//			//email.setTLS(true);		// 是否TLS校验，，某些邮箱需要TLS安全校验，同理有SSL校验
//			//email.setSSL(true);
//
//			email.setHostName(this.mail_host);
//
//			if (this.ssl) {
//				email.setSslSmtpPort(String.valueOf(this.mail_port));
//				email.setSSLOnConnect(true);
//			} else {
//				email.setSmtpPort(Integer.valueOf(this.mail_port));
//			}
//
//			email.setAuthenticator(new DefaultAuthenticator(this.mail_host_account, this.mail_host_password));
//			email.setCharset(Charset.defaultCharset().name());
//
//			email.setFrom(this.mail_host_account, sendNick);
//			email.addTo(toAddress);
//			email.setSubject(mailSubject);
//			email.setMsg(mailBody);
//
//			MimeMultipart mp = new MimeMultipart();
//			//content
//			MimeBodyPart contentPart = new MimeBodyPart();
//
//			if (this.getDefaultIsHtml()) {
//				contentPart.setContent("<meta http-equiv=Content-Type content=text/html; charset=" + default_charset + ">" + mailBody, "text/html;charset="
//						+ default_charset);
//			} else {
//				contentPart.setText(mailBody, default_charset);
//			}
//			mp.addBodyPart(contentPart);
//
//			//attachment
//			if (attachments != null) {
//				MimeBodyPart attachPart;
//				for (Iterator<Object> it = attachments.iterator(); it.hasNext();) {
//					attachPart = new MimeBodyPart();
//					FileDataSource fds = new FileDataSource(it.next().toString().trim());
//					attachPart.setDataHandler(new DataHandler(fds));
//					if (fds.getName().indexOf("$") != -1) {
//						attachPart.setFileName(fds.getName().substring(fds.getName().indexOf("$") + 1, fds.getName().length()));
//					} else {
//						attachPart.setFileName(fds.getName());
//					}
//					mp.addBodyPart(attachPart);
//				}
//
//			}
//
//			email.setContent((MimeMultipart) mp);
//			//email.attach(attachment);	// add the attachment
//			email.send();				// send the email
//			return true;
//		} catch (EmailException e) {
//			logger.error(e.getMessage(), e);
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
		return false;
	}
}
