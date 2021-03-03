package com.project.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Date dateR; // Fecha en que aplico el usuario para esta oferta de trabajo
	private String commentR;
	private String archive; // El nombre del archivo PDF, DOCX del CV.

	@OneToOne
	@JoinColumn(name = "idVacant")
	private Vacant Vacant;

	@OneToOne
	@JoinColumn(name = "idUser")
	private User User;

	public Request() {

	}

	public Request(Date dateR) {
		super();
		this.dateR = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateR() {
		return dateR;
	}

	public void setDateR(Date dateR) {
		this.dateR = dateR;
	}

	public String getCommentR() {
		return commentR;
	}

	public void setCommentR(String commentR) {
		this.commentR = commentR;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	public Vacant getIdVacant() {
		return Vacant;
	}

	public void setIdVacant(Vacant idVacant) {
		this.Vacant = idVacant;
	}

	public User getIdUser() {
		return User;
	}

	public void setIdUser(User idUser) {
		this.User = idUser;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", dateR=" + dateR + ", commentR=" + commentR + ", archive=" + archive
				+ ", idVacant=" + Vacant + ", idUser=" + User + "]";
	}

}