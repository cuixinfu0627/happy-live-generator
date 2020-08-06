package com.happy.live.common.office;

import java.io.Serializable;
import java.util.Collection;

/**
 * 类名: EmailBean <tb>
 * 描述: 邮箱实体 <tb>
 * 作者: cuixinfu@51play.com <tb>
 * 时间: 2019/4/15 15:06<tb>
 */
public class EmailBean implements Serializable {

    /** 邮件发送人 **/
    private String senderName;
    /** 邮件接收人 **/
    private String recipient;
    /** 邮件主题 **/
    private String subject;
    /** 邮件内容 **/
    private String content;
    /** 点击跳转链接 **/
    private String toLink;
    /** 邮件附件 **/
    private Collection<Object> attachments ;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToLink() {
        return toLink;
    }

    public void setToLink(String toLink) {
        this.toLink = toLink;
    }

    public Collection<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Object> attachments) {
        this.attachments = attachments;
    }
}
