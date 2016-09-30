package com.springapp.mvc.service;

import com.springapp.mvc.model.Employee;
import com.springapp.mvc.model.MyDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author :rasikaw
 * @version :1.0
 * @since :4/1/15
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    static Set employees;

    static {
        employees = new HashSet();
        for( int i=0 ; i<10; i++){
            double sal = new SecureRandom().nextInt(400)*500;
            employees.add(new Employee(i,"Employee" +i, sal));
        }
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    public Employee getFoobar(@PathVariable int employeeId) {
        for (Object employee : employees) {
            Employee f = (Employee) employee;
            if (f.getId() == employeeId) return f;
        }
        return null;
    }

    @RequestMapping(value = "/htmllist", method = RequestMethod.GET, headers = "Accept=text/html", produces = {"text/html"})
    public String getFoobarListHTML() {
        String retVal = "<html><body><table border=1>";
        for (Object employee : employees) {
            Employee f = (Employee) employee;
            retVal += "<tr><td>" + f.getId() + "</td><td>" + f.getName() + "</td><td>" + f.getSalary() + "</td></tr>";
        }
        retVal += "</table></body></html>";

        return retVal;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    public Set getFoobarList() {
        return employees;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    public Set getFoobars() {
        return employees;
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
    public Employee editFoobar(@RequestBody Employee foobar, @PathVariable int employeeId) {
        for (Object employee : employees) {
            Employee f = (Employee) employee;
            if (employeeId == f.getId()) {
                f.setId(foobar.getId());
                f.setName(foobar.getName());
                return f;
            }
        }
        return null;
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = {"application/json"})
    public boolean deleteFoobar(@PathVariable int employeeId) {
        System.out.println("Delete call.");
        Iterator fooIterator = employees.iterator();
        while (fooIterator.hasNext()) {
            Employee foobar = (Employee) fooIterator.next();
            System.out.println(foobar);
            if (foobar.getId() == employeeId) {
                fooIterator.remove();
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json"})
    public boolean createFoobar(@RequestBody Employee employee) {
        return employees.add(employee);
    }


    @RequestMapping(value = "/lame",method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json"})
    public boolean createFoobarS(@RequestBody MyDto my) {
        System.out.println(my);
        return true;
    }
}
