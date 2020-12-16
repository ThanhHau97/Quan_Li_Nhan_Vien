package Poly.Entity;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Poly.Entity.Staff;

@Entity
@Table(name="Departs")
public class Departs {
@Id
	
	@Column(name="Id")
	private String id;
	
	@Column(name="Name")
	private String name;
	@OneToMany(mappedBy = "departid",fetch = FetchType.EAGER)
	private Collection<Staff> staffs;
	
	public Collection<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(Collection<Staff> staffs) {
		this.staffs = staffs;
	}

public Departs() {
		super();
	}

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

	public Departs(String id, String name, Collection<Staff> staffs) {
		super();
		this.id = id;
		this.name = name;
		this.staffs = staffs;
	}




}
