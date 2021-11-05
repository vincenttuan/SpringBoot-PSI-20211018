package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@RequestMapping("/")
	public String index(Model model, 
						@RequestParam(name="customer_id", required = false) Long customer_id) {
		
		Order order = new Order();
		
		if(customer_id == null) {
			model.addAttribute("orders", orderRepository.findAll());
		} else {
			Customer customer = customerRepository.findById(customer_id).get();
			order.setCustomer(customer);
			model.addAttribute("orders", orderRepository.findByCustomer(customer));
		}
		model.addAttribute("order", order);
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "order";
	}
	
	@RequestMapping("/add")
	public String add(Order order) {
		orderRepository.save(order);
		return "redirect:/order/";
	}
	
	@RequestMapping("/edit/{oid}")
	public String edit(Model model, @PathVariable("oid") Long oid) {
		Order order = orderRepository.findById(oid).get();
		model.addAttribute("order", order);
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "order-update";
	}
	
	@RequestMapping("/update/{oid}")
	public String update(Order order, @PathVariable("oid") Long oid) {
		order.setId(oid); // 重要 ! 不加入會變成新增
		orderRepository.save(order);
		return "redirect:/order/";
	}
	
	@RequestMapping("/delete/{oid}")
	public String delete(@PathVariable("oid") Long oid) {
		orderRepository.deleteById(oid);
		return "redirect:/order/";
	}
	
}
