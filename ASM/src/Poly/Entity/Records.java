package Poly.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import Poly.Entity.Staff;

@Entity
@Table(name="Records")
public class Records {
	@Id
	@GeneratedValue
	
	@Column(name="Id")
	private String id;
	
	@Column(name="Type")
	private Boolean type;
	
	@Column(name="Reason")
	private String reason;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name="Date")
	private Date date;
	@ManyToOne
	@JoinColumn(name="StaffId")
	private Staff staffid;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Boolean getType() {
		return type;
	}


	public void setType(Boolean type) {
		this.type = type;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Staff getStaffid() {
		return staffid;
	}


	public void setStaffid(Staff staffid) {
		this.staffid = staffid;
	}


	public Records(String id, Boolean type, String reason, Date date, Staff staffid) {
		super();
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.date = date;
		this.staffid = staffid;
	}


	public Records() {
		super();
	}



	
}