package com.itis.controllers;

import com.itis.services.CustomerService;
import com.itis.models.Customer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomerControllers {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/")
	public String home(Model model) {
		List<Customer> listCustomer = customerService.listAll();
		model.addAttribute("customers", listCustomer);
		return "index";
	}
	
	@RequestMapping("/new")
	public String newCustomerForm(Model model) {
		return "new";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.save(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public String editCustomerForm(@RequestParam long id, Model model) {
		Customer customer = customerService.get(id);
		model.addAttribute("customer", customer);
		return "edit";
	}
	
	@RequestMapping("/delete")
	public String deleteCustomerForm(@RequestParam long id) {
		customerService.delete(id);
		return "redirect:/";		
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam String keyword, Model model) {
		List<Customer> result = customerService.search(keyword);
		model.addAttribute("customers", result);
		return "search";
	}	
}
