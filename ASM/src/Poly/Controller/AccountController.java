package Poly.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import Poly.Entity.User;
@Transactional
@Controller
@RequestMapping("/Web/")
public class AccountController {
	@Autowired
	SessionFactory factory;
	 @RequestMapping("index")
	 public String index(ModelMap model){
			Session session = factory.getCurrentSession();
			String hql ="SELECT r.staffid.id,r.staffid.name,r.staffid.photo,r.staffid.departid.name,"
					+ " SUM(case when r.type=0 then 1 else 0 end)-"
					+ " SUM(case when r.type=1 then 1 else 0 end)  as thanhtich" + " FROM Records r"
					+ " GROUP BY r.staffid.id,r.staffid.name,r.staffid.photo,r.staffid.departid.name"
					+ " ORDER BY thanhtich desc";
			Query query = session.createQuery(hql);
			List<Object> list = query.list();
			model.addAttribute("index", list);
		return "Web/index";
		 
	 }

 
 @RequestMapping("ListTotal1")
 public String ListTotal1(ModelMap model){
			Session session = factory.getCurrentSession();
			String hql = "SELECT r.staffid.id,  r.staffid.name , " + " SUM(case when r.type=1 then 1 else 0 end), "
					+ " SUM(case when r.type=0 then 1 else 0 end)" + " FROM Records r" + " GROUP BY r.staffid.id, r.staffid.name";
			Query query = session.createQuery(hql);
			List<Object[]> list = query.list();
			model.addAttribute("Records", list);
	return "Web/ListTotal1";
	 
 }
 
 @RequestMapping("ListTotal2")
 public String ListTotal2(ModelMap model){
		Session session = factory.getCurrentSession();
		String hql = "SELECT s.departid.name," +" SUM(case when type=1 then 1 else 0 end), "
				+ " SUM(case when type=0 then 1 else 0 end)" + " FROM Records r," + "Staff s" + " where s.id = r.staffid.id"+" GROUP BY s.departid.name" ;
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		model.addAttribute("Record", list);
	return "Web/ListTotal2";
	 
 }
 


	 
 
 @RequestMapping("register")
 public String register(ModelMap model){
	 
	return "Web/register";
	 
 }
 @RequestMapping("reset-pass")
 public String resetpass(ModelMap model){
	 
	return "Web/reset-pass";
	 
 }

 @RequestMapping("UpdateDepart")
 public String UpdateDepart(ModelMap model){
	 
	return "Web/UpdateDepart";
	 
 }
 @RequestMapping("UpdateRecord")
 public String UpdateRecord(ModelMap model){
	 
	return "Web/UpdateRecord";
	 
 }
 @RequestMapping("404")
 public String Expression(ModelMap model){
	 
	return "Web/404";
	 
 }

}
