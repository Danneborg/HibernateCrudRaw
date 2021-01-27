package kononikhin.Controllers;

import kononikhin.Entityes.Customer;
import kononikhin.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();

        model.addAttribute("customers", customers);

        return "listCustomers";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerForm";
    }

    @GetMapping("/showFormUpdate")
    public String showUpdateForm(@RequestParam(name = "customerId") int customerId,Model model) {

        Customer customer = customerService.getCustomer(customerId);

        model.addAttribute("customer", customer);

        return "customerForm";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute(name = "customer") Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){

        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String searchName,
                                  Model model) {

        // search customers from the service
        List<Customer> customers = customerService.searchCustomers(searchName);

        // add the customers to the model
        model.addAttribute("customers", customers);

        return "listCustomers";
    }
}
