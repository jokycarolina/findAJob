package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.Vacant;
import com.project.service.ICategoryService;
import com.project.service.IVacantService;
import com.project.util.Props;

@Controller
@RequestMapping("/vacancies")
public class VacanciesController {

	@Value("${findAJob.route.images}")
	private String route;

	@Autowired
	private IVacantService serviceVacancies;
	@Autowired
	private ICategoryService serviceCategory;

	@GetMapping("/")
	public String showIndex(Model model) {
		List<Vacant> list = serviceVacancies.searchAll();
		model.addAttribute("vacancies", list);
		return "vacancies/vacanciesList";
	}

	@GetMapping(value = "/indexPaginate")
	public String showPagination(Model model, Pageable page) {
		Page<Vacant> list = serviceVacancies.findAll(page);
		model.addAttribute("vacancies", list);
		System.out.println(list.getNumber());
		return "vacancies/vacanciesList";
	}

	@PostMapping("/save")
	public String save(Vacant vacant, BindingResult result, RedirectAttributes attributes,
			@RequestParam("fileImage") MultipartFile multipart) {

		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			return "vacancies/formVacant";
		}

		if (!multipart.isEmpty()) {

			String imagename = Props.saveFile(multipart, route);

			if (imagename != null) {
				vacant.setImage(imagename);
			}

		}

		serviceVacancies.save(vacant);
		attributes.addFlashAttribute("msg", "Registro Exitoso");
		return "redirect:/vacancies/";
	}

	@GetMapping("/create")
	public String create(Vacant vacant, Model model) {
		return "vacancies/formVacant";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idVacant, RedirectAttributes attributes) {
		serviceVacancies.delete(idVacant);
		attributes.addFlashAttribute("msg", "La vacante fue eliminada con éxito");
		return "redirect:/vacancies/";

	}

	@GetMapping("/view/{id}")
	public String viewDetails(@PathVariable("id") int idVacant, Model model) {

		Vacant vacant = serviceVacancies.searchById(idVacant);
		model.addAttribute("vacant", vacant);
		return "details";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int idVacant, Model model) {
		Vacant vacant = serviceVacancies.searchById(idVacant);
		System.out.println(vacant.getName());
		model.addAttribute("vacant", vacant);

		return "vacancies/formVacant";
	}

	@ModelAttribute
	public void setGeneric(Model model) {
		model.addAttribute("categories", serviceCategory.searchAll());

	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
