package Poly.Entity;


import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name="Staffs")
public class Staff {
	@Id
	@Column(name="Id")
	private String id;
	@Column(name="Name")
	private String name;
	@Column(name="Gender")
	private Boolean gender;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name="Birthday")
	private Date birthday;
	@Column(name="Photo")
	private String photo;	
	@Column(name="Email")
	private String email;
	@Column(name="Phone")
	private String phone;
	@Column(name="Salary")
	private Float salary;
	@Column(name="Notes")
	private String notes;
	@ManyToOne
	@JoinColumn(name="DepartId")
	private Departs departid;
	@OneToMany(mappedBy = "staffid",fetch = FetchType.EAGER)
	private Collection<Records> record;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Boolean getGender() {
		return gender;
	}


	public void setGender(Boolean gender) {
		this.gender = gender;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Float getSalary() {
		return salary;
	}


	public void setSalary(Float salary) {
		this.salary = salary;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Departs getDepartid() {
		return departid;
	}


	public void setDepartid(Departs departid) {
		this.departid = departid;
	}

	public Collection<Records> getRecord() {
		return record;
	}


	public void setRecord(Collection<Records> record) {
		this.record = record;
	}


	public Staff(String id, String name, Boolean gender, Date birthday, String photo, String email, String phone,
			Float salary, String notes, Departs departid, Collection<Records> record) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.photo = photo;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.notes = notes;
		this.departid = departid;
		this.record = record;
	}


	public Staff() {
		super();
	}
	
	
	
}