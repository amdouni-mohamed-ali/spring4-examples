package org.spring.tutorial.examples.rest.war.web.controllers;

import org.spring.tutorial.examples.rest.war.dao.CustomerDAO;
import org.spring.tutorial.examples.rest.war.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    /*
     * Now let's create CustomerRestController class. This class is annotated with @RestController annotation.
     * Also note that we are using new annotations @GetMapping, @PostMapping, @PutMapping and @DeleteMapping instead of standard @RequestMapping.
     * These annotations are available since Spring MVC 4.3 and are standard way of defining REST endpoints. They act as wrapper to @RequestMapping.
     * For example @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
     */

    @Autowired
    private CustomerDAO customerDAO;


    /*
        in my case after deploying the war on tomcat the url is : http://localhost:8080/rest-war-example/customers
     */
    @GetMapping
    public List getCustomers() {
        return customerDAO.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") Long id) {

        Customer customer = customerDAO.get(id);
        if (customer == null) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody Customer customer) {

        customerDAO.create(customer);
        return new ResponseEntity(customer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {

        if (!customerDAO.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

        if (!customerDAO.update(id, customer)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customer, HttpStatus.OK);
    }
}
