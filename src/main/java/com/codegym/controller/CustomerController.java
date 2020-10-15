package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    ICustomerService customerService;
    @Autowired
    IProvinceService provinceService;

    @GetMapping
    public ResponseEntity<Iterable<Customer>> listCustomers(){
        Iterable<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") Long id){
        Customer customer = customerService.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK) ;
    }
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        customer.setId(null);
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED) ;
    }
    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK) ;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteProvince(@PathVariable("id") Long id){
        Customer customer = customerService.findById(id);
        if (customer != null) {
            provinceService.deleteById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
}
