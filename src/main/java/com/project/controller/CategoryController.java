package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.Category;
import com.project.service.ICategoryService;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private ICategoryService serviceCategory;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showIndex(Model model) {
		List<Category> list = serviceCategory.searchAll();
		model.addAttribute("categories", list);
		return "categories/categories";

	}

	@GetMapping(value = "/indexPaginate")
	public String showPagination(Model model, Pageable page) {
		Page<Category> list = serviceCategory.searchAll(page);
		model.addAttribute("categories", list);
		return "categories/categories";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Category category) {
		return "categories/formCategories";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)

	public String save(Category category, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			return "categories/formCategories";
		}

		serviceCategory.save(category);
		attributes.addFlashAttribute("msg", "Registro Exitoso");
		return "redirect:/categories/index";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idCategory, RedirectAttributes attributes) {

		try {
			serviceCategory.delete(idCategory);
			attributes.addFlashAttribute("msg", "La categoría fue eliminada con éxito");
		} catch (Exception ex) {
			attributes.addFlashAttribute("msg", "No es posible eliminar la Categoría seleccionada");
		}
		return "redirect:/categories/index/";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int idCategory, Model model) {

		Category category = serviceCategory.searchById(idCategory);
		System.out.println(category.getName());
		model.addAttribute("category", category);
		return "categories/formCategories";
	}

}
