package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.User;
import com.project.service.IUserService;

@Controller
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private IUserService serviceUser;

	@GetMapping("/index")
	public String showIndex(Model model) {

		List<User> list = serviceUser.searchAll();
		model.addAttribute("users", list);
		return "user/index";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		serviceUser.delete(idUser);
		attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
		return "redirect:/user/index";
	}

}
