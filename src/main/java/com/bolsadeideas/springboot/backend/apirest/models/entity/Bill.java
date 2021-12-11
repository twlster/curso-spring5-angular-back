package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

@Builder
@Entity
@Table(name = "bills")

public class Bill implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "can't be empty")
	private String description;

	private String observation;

	@NotNull
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "bills" })
	private Client client;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "bill_id")
	private List<BillItem> items;

	public Bill() {
		this.items = new ArrayList<>();
	}

	public BigDecimal getTotal() {
		return items.stream().map(BillItem::calculateValue).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<BillItem> getItems() {
		return items;
	}

	public void setItems(List<BillItem> items) {
		this.items = items;
	}


}
