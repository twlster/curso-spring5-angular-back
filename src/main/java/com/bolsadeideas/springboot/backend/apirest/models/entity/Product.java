package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

	@Transient
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "can't be empty")
	@Column(nullable = false)
	private String name;

	@NotEmpty(message = "can't be empty")
	@Column(nullable = false)
	private BigDecimal price;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	public void defaulValues() {
		this.createdAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
