package com.everis.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everis.spring.repository.EverisCustomer;
import com.everis.spring.services.EverisCustomerManagementServiceI;

@Controller
@RequestMapping("*")
public class EverisController {

	@Autowired
	private EverisCustomerManagementServiceI customerService;

	@GetMapping("/*")
	public String showViewIndex() {
		return "systemMenu";
	}

	@GetMapping("/showCustomersView")
	public String showAllCustomer(Model model) {

		/* Nos crea la lista de todos los clientes */
		final List<EverisCustomer> ListCustomer = customerService.searchAllCustomers();

		/* Creamos El modelo para enviarselo a la vista */
		model.addAttribute("customersList", ListCustomer);
		model.addAttribute("btnDropCustomerEnabled", false);

		return "showCustomers";
	}

	@GetMapping("/searchCustomerByView")
	public String CustomerSearchByName() {
		return "searchCustomerBy";
	}

	@GetMapping("/newCustomerView")
	public String AddCustomer(Model model, @Valid EverisCustomer newCustomer) {
		
		model.addAttribute(customerService.insertNewCustomer(newCustomer));

		
		return "newCustomer";
	}

}
