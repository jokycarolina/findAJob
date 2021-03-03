package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;

import com.project.model.Profile;
import com.project.model.User;
import com.project.model.Vacant;
import com.project.service.ICategoryService;
import com.project.service.IUserService;
import com.project.service.IVacantService;

@Controller
public class HomeController {

	@Autowired
	private IVacantService serviceVacancies;

	@Autowired
	private IUserService serviceUsers;

	@Autowired
	private ICategoryService serviceCategories;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/index")
	public String showindex(Authentication auth, HttpSession session,RedirectAttributes attributes) {
		String username = auth.getName();

		for (GrantedAuthority rol : auth.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}

		if (session.getAttribute("user") == null) {
			User user = serviceUsers.findByUsername(username);
			user.setPassword(null);
			System.out.println(user.getName() + " " + user.getUsername());
			session.setAttribute("user", user);
		}
		attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");

		return "redirect:/";
	}

	@GetMapping("/signup")
	public String register(User user, Model model) {
		System.out.println("envio al form");
		return "user/formRegister";
	}

	@PostMapping("/signup")
	public String saveRegister(User user, RedirectAttributes attributes) {

		String pwd = user.getPassword();
		String pwdncryp = passwordEncoder.encode(pwd);
		user.setPassword(pwdncryp);
		System.out.println(pwdncryp);
		user.setStatus(1);
		user.setRegistrationDate(new Date());

		Profile profile = new Profile();
		profile.setId(2);
		user.add(profile);
		serviceUsers.save(user);
		System.out.println(user.getName());
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
		
	}

	@GetMapping("/")
	public String showHome(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "formLogin";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}

	@GetMapping("/search")
	public String search(@ModelAttribute("search") Vacant vacant, Model model) {
		System.out.println("Buscando por: " + vacant);
		// en la consulta select where description like '%?%'
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("description",
				ExampleMatcher.GenericPropertyMatchers.contains());

		Example<Vacant> exampleVacant = Example.of(vacant, matcher);
		List<Vacant> list = serviceVacancies.searchByExample(exampleVacant);
		model.addAttribute("vacancies", list);
		return "index";
	}

	// Para detectar los String que vienen vacios los settea a null
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@ModelAttribute
	public void setGeneric(Model model) {
		Vacant vacantSearch = new Vacant();
		vacantSearch.reset();
		model.addAttribute("vacancies", serviceVacancies.findFeatured());
		model.addAttribute("categories", serviceCategories.searchAll());
		model.addAttribute("search", vacantSearch);
	}

	@GetMapping("/table")
	public String showTable(Model model) {
		List<Vacant> list = serviceVacancies.searchAll();
		model.addAttribute("vacancies", list);
		return "table";
	}

	@GetMapping("/list")
	public String showList(Model model) {
		LinkedList<String> list = new LinkedList<String>();
		list.add("Ingeniero de Sistema");
		list.add("Auxiliar de contabilidad");
		list.add("Vendedor");
		list.add("Arquitecto");

		model.addAttribute("jobs", list);
		return "list";
	}

	@GetMapping("/details")
	public String showDetails(Model model) {
		Vacant vacant = new Vacant();
		vacant.setName("Ingeniero de comunicaciones");
		vacant.setDescription("Se solicita Ingeniero para dar soporte");
		vacant.setDate(new Date());
		vacant.setSalary(9700.00);
		vacant.setFavorite(1);
		vacant.setStatus("Aprobada");
		model.addAttribute("vacant", vacant);
		return "details ";

	}

	@GetMapping("/bcrypt/{text}")
	@ResponseBody
	public String encrypt(@PathVariable("text") String text) {
		return text + "Encriptado en Bcrypt" + passwordEncoder.encode(text);
	}

}