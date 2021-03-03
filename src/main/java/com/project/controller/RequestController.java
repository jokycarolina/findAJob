package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.Request;
import com.project.model.User;
import com.project.model.Vacant;
import com.project.service.IRequestService;
import com.project.service.IUserService;
import com.project.service.IVacantService;
import com.project.util.Props;

@Controller
public class RequestController {

	@Autowired
	private IRequestService serviceRequest;

	@Autowired
	private IVacantService serviceVacant;

	@Autowired
	private IUserService serviceUser;

	@Value("${findAJob.route.cv}")
	private String route;

	@GetMapping("/indexRequest")
	public String showIndex(Model model) {
		List<Request> list = serviceRequest.findAll();
		model.addAttribute("request", list);
		return "request/listRequest";
	}

	@GetMapping("/indexPaginate")
	public String showIndexPag(Model model, Pageable page) {
		Page<Request> list = serviceRequest.findAll(page);
		model.addAttribute("request", list);
		return "request/listRequest";
	}

	@GetMapping("/apply/{id}")
	public String apply(@PathVariable("id") Integer idVacant, Model model, Request req) {

		Vacant vacant = serviceVacant.searchById(idVacant);
		model.addAttribute("vacant", vacant);
		System.out.println(vacant);

		return "request/formRequest";
	}

	@PostMapping("/saveRequest")
	public String save(Request req, BindingResult result, Model model, HttpSession session,
			@RequestParam("archiveCV") MultipartFile multiPart, RedirectAttributes attributes, Authentication auth) {
		System.out.println(req.getCommentR());
		String username = auth.getName();
		System.out.println("entro");
		System.out.println(multiPart.getName());

		if (result.hasErrors()) {

			System.out.println("Existieron errores");
			return "request/formRequest";
		}

		if (!multiPart.isEmpty()) {

			String filename = Props.saveFile(multiPart, route);
			if (filename != null) {
				req.setArchive(filename);
			}
		}

		User user = serviceUser.findByUsername(username);

		req.setIdUser(user);
		req.setDateR(new Date());

		serviceRequest.save(req);
		attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");
		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idRequest, RedirectAttributes attributes) {

		serviceRequest.delete(idRequest);

		attributes.addFlashAttribute("msg", "La solicitud fue eliminada!.");

		return "redirect:/indexPaginate";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
