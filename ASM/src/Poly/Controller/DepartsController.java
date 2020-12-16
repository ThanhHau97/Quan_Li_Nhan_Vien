package Poly.Controller;

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

import Poly.Entity.Departs;
import Poly.Entity.Records;



/**
 * @author nguye
 *
 */
@Transactional
@Controller
@RequestMapping("/Departs/")
public class DepartsController {
	@Autowired
	SessionFactory factory;

	@Id
	@RequestMapping("ListDeparts")
	public String ListDepart(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Departs";
		Query query = session.createQuery(hql);
		List<Departs> list = query.list();
		model.addAttribute("Departs", list);
		return "Departs/ListDeparts";

	}

	@RequestMapping(value = "AddDeparts", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("phongban", new Departs());
		return "Departs/AddDeparts";
	}

	@RequestMapping(value = "AddDeparts", method = RequestMethod.POST)
	public String them(ModelMap model, @ModelAttribute("phongban") Departs pb,BindingResult errors) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if (pb.getId().trim().length() == 0) {
			errors.rejectValue("id", "phongban", "Vui lòng nhập ID !");
		}

		else if (pb.getName().trim().length() == 0) {
			errors.rejectValue("name", "phongban", "Vui lòng nhập Tên Phòng Ban !");
		} else {
		try {
			// Lưu tạm dữ liệu
			session.save(pb);
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
		model.addAttribute("phongbans", getDeparts());
		return "Departs/AddDeparts";
	}

	@RequestMapping("{id}")
	public String Sua(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		Departs phongban = (Departs) session.get(Departs.class, id);
		// map được với attribute
		model.addAttribute("phongban", phongban);
		model.addAttribute("phongbans", getDeparts());
		return "Departs/UpdateDepart";
	}

	public List<Departs> getDeparts(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Departs";
		Query query = session.createQuery(hql);
		List<Departs> list = query.list();
		return list;

	}

	// Hiện lai form
	@RequestMapping(value = "Update", method = RequestMethod.GET)
	public String Update(ModelMap model) {
		model.addAttribute("phongban", new Departs());
		return "Departs/UpdateDepart";
	}

	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String Update(ModelMap model, 
			@ModelAttribute("phongban") Departs pb) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(pb);
			t.commit();
			model.addAttribute("message", "Cập Nhật thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập Nhật thất bại !");
			
		} finally {
			session.close();
		}
		model.addAttribute("phongbans", getDeparts());
		return "Departs/UpdateDepart";
	}
	@RequestMapping("{id}Xoa")
	public String Delete(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Departs phongban = (Departs) session.get(Departs.class, id);
		// map được với attribute
		model.addAttribute("record", phongban);
	
		try {
			session.delete(phongban);
			t.commit();
			model.addAttribute("message", "Xóa thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại !");
			model.addAttribute("message", e);
		} finally {
			session.close();
		}
		model.addAttribute("Departs", getDeparts());

		return "Departs/ListDeparts";
	}

}
