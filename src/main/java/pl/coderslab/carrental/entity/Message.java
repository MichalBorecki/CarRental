package pl.coderslab.carrental.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NotBlank
	@Size(max = 1000)
	@Column(columnDefinition = "TEXT")
	private String messageText;

	@Column(updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat
	private Date created;

	@ManyToOne/*(fetch = FetchType.EAGER)*/
	private User user;
	
	@ManyToOne/*(fetch = FetchType.EAGER)*/
	private User receiver;

	@NotNull
	private boolean urgent;

	@NotNull
	private boolean ifRead;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getCreated() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		String dateString = sdf.format(created);
		return dateString;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public boolean isIfRead() {
		return ifRead;
	}

	public void setIfRead(boolean ifRead) {
		this.ifRead = ifRead;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return String.format("Message [id=%s, messageText=%s, created=%s, user=%s, receiver=%s, urgent=%s, ifRead=%s]",
			id, messageText, created, user, receiver, urgent, ifRead);
	}



}
