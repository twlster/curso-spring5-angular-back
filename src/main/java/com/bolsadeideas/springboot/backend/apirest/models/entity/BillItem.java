package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Entity
@Table(name="bill_items")

public class BillItem implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "can't be empty")
	@Column(nullable = false)
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@NotNull(message = "The product can't be empty")
	private Product product;
	
	public BigDecimal calculateValue() {
		return product.getPrice().multiply(BigDecimal.valueOf(quantity));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
