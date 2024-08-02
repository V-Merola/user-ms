package com.vincenzomerola.user_ms.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.vincenzomerola.user_ms.RoleEnum.Role;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String fullName;
	
	@NotBlank
    @Email
    @Size(max = 100)
    @Column(unique = true)
	private String email;


	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

	
	public User() {};

	public User (String fullName,String email, String password, Role role) {
		this.fullName =  fullName;
		this.email = email;
		this.role = role;
	}

	public User (String fullName,String email,  Role role) {
		this.fullName =  fullName;
		this.email = email;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", email='" + email + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", role=" + role +
				'}';
	}
}
