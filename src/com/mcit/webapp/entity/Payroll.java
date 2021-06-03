package com.mcit.webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ems_payroll")
public class Payroll {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="gross_amount")
	private double grossAmount;
	
	@Column(name="net_amount")
	private double netAmount;
	
	@Column(name="tax_amount")
	private double tax;

	//constructor 
	public Payroll() {
		super();
	}

	public Payroll(double grossAmount, double netAmount, double tax) {
		this.grossAmount = grossAmount;
		this.netAmount = netAmount;
		this.tax = tax;
	}

	// getters and setters methods.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	// override toString()
	@Override
	public String toString() {
		return "Payroll [id=" + id + ", grossAmount=" + grossAmount + ", netAmount=" + netAmount + ", tax=" + tax + "]";
	}
	
	
}
