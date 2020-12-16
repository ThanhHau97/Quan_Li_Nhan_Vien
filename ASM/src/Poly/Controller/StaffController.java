package Poly.Controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import Poly.Entity.Departs;
import Poly.Entity.Staff;

@Transactional
@Controller
@RequestMapping("/Staff/")
public class StaffController {
	@Autowired
	@Id
	SessionFactory factory;
	ServletContext context;
	@RequestMapping("ListStaff")
	public String ListStaff(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff";
		Query query = session.createQuery(hql);
		List<Staff> list = query.list();
		model.addAttribute("staffs", list);
		return "Staff/ListStaff";

	}

	@RequestMapping(value = "AddNhanVien.htm", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("staff", new Staff());
		return "Staff/AddNhanVien";
	}

	@RequestMapping(value = "AddNhanVien.htm", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("staff") Staff st, BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if (st.getId().trim().length() == 0) {
			errors.rejectValue("id", "staff", "Vui lòng nhập ID !");
		}

		else if (st.getName().trim().length() == 0) {
			errors.rejectValue("name", "staff", "Vui lòng nhập Họ và Tên !");
		}	else if (st.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "staff", "Vui lòng nhập Email!");
		}
		else if (st.getPhone().trim().length() == 0) {
			errors.rejectValue("phone", "staff", "Vui lòng nhập Số Điện Thoại!");
		}
		else if (st.getSalary().doubleValue()<=0) {
			errors.rejectValue("salary", "staff", "Vui lòng nhập Lương >0!");
		}
		else {

		try {
			// Lưu tạm dữ liệu
//			st.setBirthday(new Date());
			session.save(st);
			// Thực thi luu dữ liệu vào data
			t.commit();
			model.addAttribute("message", "Thêm mới thành công!");
		} catch (Exception e) {
			// Trả lại dữ liệu cũ
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		}

		finally {
			session.close();
		}
		}
		model.addAttribute("staffs", getStaff());

		return "Staff/AddNhanVien";
	}
	
	@RequestMapping("AddNhanVien")
	public String apply(ModelMap model, String fullname,
			@RequestParam("photo") MultipartFile photo) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if (photo.isEmpty()) {
			model.addAttribute("message", "vui lòng chọn file !");
		} else {
			try {
				String photoPath = context.getRealPath("/files/" + photo.getOriginalFilename());
					photo.transferTo(new File(photoPath));			
					model.addAttribute("photo", photo.getOriginalFilename());
					return "AddNhanVien";
				
			} catch (Exception e) {
				model.addAttribute("message", "lổi lưu file !");
			}
		}
		return "Staff/AddNhanVien";
	}

	@ModelAttribute("depart")
	public List<Departs> getDeparts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM  Departs";
		Query query = session.createQuery(hql);
		List<Departs> list = query.list();
		return list;
	}

	// Lấy Lại Dữ Liêu
	@RequestMapping("{id}")
	public String Edit(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		Staff staff = (Staff) session.get(Staff.class, id);
		// map được với attribute
		model.addAttribute("staff", staff);
		model.addAttribute("staffs", getStaff());
		return "Staff/UpdateStaff";
	}

	public List<Staff> getStaff() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff";
		Query query = session.createQuery(hql);
		List<Staff> list = query.list();
		return list;

	}

	// Hiện lai form
	@RequestMapping(value = "Update", method = RequestMethod.GET)
	public String Update(ModelMap model) {
		model.addAttribute("staff", new Staff());
		return "Staff/UpdateStaff";
	}

	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String Update(ModelMap model, @ModelAttribute("staff") Staff st) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(st);
			t.commit();
			model.addAttribute("message", "Cập Nhật thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập Nhật thất bại !");
		} finally {
			session.close();
		}
		model.addAttribute("staffs", getStaff());
		return "Staff/UpdateStaff";
	}
	@RequestMapping("Update")
	public String Upload(ModelMap model, String fullname,
			@RequestParam("photo") MultipartFile photo) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		if (photo.isEmpty()) {
			model.addAttribute("message", "vui lòng chọn file !");
		} else {
			try {
				String photoPath = context.getRealPath("/files/" + photo.getOriginalFilename());
					photo.transferTo(new File(photoPath));			
					model.addAttribute("photo", photo.getOriginalFilename());
					model.addAttribute("photo_name", photo.getOriginalFilename());
					return "Update";
				
			} catch (Exception e) {
				model.addAttribute("message", "lổi lưu file !");
			}
		}
		return "Staff/Update";
	}

	@RequestMapping("{id}Xoa")
	public String Delete(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Staff staff = (Staff) session.get(Staff.class, id);
		// map được với attribute
		model.addAttribute("staff", staff);
		// model.addAttribute("staffs", getStaff());
		try {
			session.delete(staff);
			t.commit();
			model.addAttribute("message", "Xóa thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại !");
			model.addAttribute("message", e);
		} finally {
			session.close();
		}
		model.addAttribute("staffs", getStaff());

		return "Staff/ListStaff";
	}
}
