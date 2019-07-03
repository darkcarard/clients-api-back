package co.darksquirrelsoftware.springboot.backend.apirest.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = -1162595007496879088L;

	private static final String CANT_BE_EMPTY_MESASGE = "Can´t be empty!";
	private static final String BAD_EMAIL_FORMAT_MESASGE = "Wrong format!";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = CANT_BE_EMPTY_MESASGE)
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message = CANT_BE_EMPTY_MESASGE)
	@Column(name = "last_name")
	private String lastName;

	@NotEmpty(message = CANT_BE_EMPTY_MESASGE)
	@Email(message = BAD_EMAIL_FORMAT_MESASGE)
	@Column(unique = true)
	private String email;

	@NotNull(message = CANT_BE_EMPTY_MESASGE)
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	private String picture;
	
	@NotNull(message = CANT_BE_EMPTY_MESASGE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zone_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Zone zone;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

}
