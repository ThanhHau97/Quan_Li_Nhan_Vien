package Poly.Controller;

import java.util.List;

import javax.persistence.Id;
import javax.servlet.http.HttpSession;

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
import Poly.Entity.Staff;
import Poly.Entity.User;

@Transactional
@Controller
@RequestMapping("/Web/")
public class UserController {
	@Id
	@Autowired
	SessionFactory factory;

	@RequestMapping("ListAccount")
	public String ListAccount(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "from User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("Users", list);
		return "Web/ListAccount";

	}

	// Hiển thị form
	@RequestMapping(value = "TaiKhoan", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("user", new User());
		return "Web/TaiKhoan";
	}

	// thêm user mới
	@RequestMapping(value = "TaiKhoan", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("user") User user, BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if (user.getUsername().trim().length() == 0) {
			errors.rejectValue("username", "user", "Vui lòng nhập username !");
		}

		else if (user.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "user", "Vui lòng nhập password !");
		} else if (user.getFullname().trim().length() == 0) {
			errors.rejectValue("password", "user", "Vui lòng nhập Fullname!");
		} else {

			try {
				session.save(user);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công !");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại !");
			} finally {
				session.close();
			}
		}
		model.addAttribute("users", getUser());
		return "Web/TaiKhoan";
	}

	@RequestMapping("{username}")
	public String Edit(ModelMap model, @PathVariable("username") String username) {
		Session session = factory.getCurrentSession();
		User User = (User) session.get(User.class, username);
		// map được với attribute
		model.addAttribute("user", User);
		model.addAttribute("users", getUser());
		return "Web/UpdateAccount";
	}

	public List<User> getUser() {
		Session session = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		return list;
	}

	@RequestMapping(value = "Update", method = RequestMethod.GET)
	public String Update(ModelMap model) {
		model.addAttribute("user", new User());
		return "Web/UpdateAccount";
	}

	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String Update(ModelMap model, @ModelAttribute("user") User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			t.commit();
			model.addAttribute("message", "Cập Nhật thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập Nhật thất bại !");
		} finally {
			session.close();
		}
		model.addAttribute("users", getUser());
		return "Web/UpdateAccount";
	}

	@RequestMapping("{username}Xoa")
	public String Delete(ModelMap model, @PathVariable("username") String username) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User User = (User) session.get(User.class, username);
		// map được với attribute
		model.addAttribute("user", User);

		try {
			session.delete(User);
			t.commit();
			model.addAttribute("message", "Xóa thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại !");

		} finally {
			session.close();
		}
		model.addAttribute("Users", getUser());

		return "Web/ListAccount";
	}

	public boolean check(String username, String password) {
		boolean check = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM User where username=? and password=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		List<User> list = query.list();
		if (list != null && (list.size() > 0)) {
			check = true;
		}
		return check;
	}

	public List<Object> getindex() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT r.staffid.id,r.staffid.name,r.staffid.photo,r.staffid.departid.name,"
				+ " SUM(case when r.type=0 then 1 else 0 end)-"
				+ " SUM(case when r.type=1 then 1 else 0 end)  as thanhtich" + " FROM Records r"
				+ " GROUP BY r.staffid.id,r.staffid.name,r.staffid.photo,r.staffid.departid.name"
				+ " ORDER BY thanhtich desc";
		Query query = session.createQuery(hql);
		List<Object> list = query.list();
		return list;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String Login(ModelMap model) {
		model.addAttribute("user", new User());
		return "Web/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String Login(ModelMap model, @ModelAttribute("user") User user, HttpSession session) {
		if (check(user.getUsername(), user.getPassword()) == true) {
			session.setAttribute("username", user.getUsername());
			model.addAttribute("username", user.getUsername());
			model.addAttribute("password", user.getPassword());
			model.addAttribute("message", "Đăng nhập thành công");
			model.addAttribute("index", getindex());
			return "Web/index";

		} else {
			model.addAttribute("username", user.getUsername());
			model.addAttribute("password", user.getPassword());
			model.addAttribute("message", " Đăng nhập thất bại");
			return "Web/login";
		}
	}
}
