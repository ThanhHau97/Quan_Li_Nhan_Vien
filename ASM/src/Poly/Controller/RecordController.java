package Poly.Controller;

import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Poly.Entity.Records;
import Poly.Entity.Staff;




@Transactional
@Controller
@RequestMapping("/Record/")
public class RecordController {
	@Autowired
	SessionFactory factory;
	@Id
	 @RequestMapping("ListRecord")
	 public String ListRecord(ModelMap model){
			Session session = factory.getCurrentSession();
			String hql = "FROM Records";
			Query query = session.createQuery(hql);
			List<Records> list = query.list();
			model.addAttribute("records", list);
		return "Record/ListRecord";
		 
	 }
	@RequestMapping(value = "AddRecord", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("record", new Records());
		return "Record/AddRecord";
	}

	@RequestMapping(value = "AddRecord", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("record") Records record,BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		 if (record.getReason().trim().length() == 0) {
			errors.rejectValue("reason", "record", "Vui lòng nhập Lí Do !");
		}
		
		else {
		try {
			record.setDate(new Date());
			session.save(record);
			t.commit();
			model.addAttribute("message", "Thêm Mới Thành Công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm Mới Thất Bại !");
			model.addAttribute("message", e);
		} finally {
			session.close();
		}
		}
		model.addAttribute("records",getRecords());
		return "Record/AddRecord";
	}

	@ModelAttribute("staffs")
	public List<Staff> getStaffs() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff";
		Query query = session.createQuery(hql);
		List<Staff> list = query.list();
		return list;
	}
	@RequestMapping("{id}")
	public String Edit(ModelMap model,@PathVariable("id")String id) {
		Session session = factory.getCurrentSession();
		Records Records=(Records) session.get(Records.class, id);
		//map được với attribute
		model.addAttribute("record", Records);
		model.addAttribute("records", getRecords());
		return "Record/UpdateRecord";
	}
	public List<Records> getRecords(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Records";
		Query query = session.createQuery(hql);
		List<Records> list = query.list();
		return list;
	
}
	//Hiện lai form
	@RequestMapping(value= "Update", method = RequestMethod.GET)
	public String Update(ModelMap model) {
		model.addAttribute("record", new Records());
	return "Record/UpdateRecord";
	}
	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String Update(ModelMap model,
			@ModelAttribute("record") Records st ) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(st);
			t.commit();
			model.addAttribute("message", "Cập Nhật thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập Nhật thất bại !");
			model.addAttribute("message", e);
		} finally {
			session.close();
		}
		model.addAttribute("records",getRecords());
		return "Record/UpdateRecord";
	}
	@RequestMapping("{id}Xoa")
	public String Delete(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Records Records=(Records) session.get(Records.class, id);
		// map được với attribute
		model.addAttribute("record", Records);
	
		try {
			session.delete(Records);
			t.commit();
			model.addAttribute("message", "Xóa thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại !");
			model.addAttribute("message", e);
		} finally {
			session.close();
		}
		model.addAttribute("records",getRecords());

		return "Record/ListRecord";
	}
}
