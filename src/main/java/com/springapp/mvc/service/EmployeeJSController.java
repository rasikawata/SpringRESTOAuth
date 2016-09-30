package com.springapp.mvc.service;

import com.springapp.mvc.model.Employee;
import com.springapp.mvc.model.JsonResponse;
import org.springframework.http.HttpStatus;
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
@Controller
@RequestMapping("/employee/js")
public class EmployeeJSController extends BaseController{

    static Set employees;

    static {
        employees = new HashSet();
        for( int i=0 ; i<10; i++){
            double sal = new SecureRandom().nextInt(400)*500;
            employees.add(new Employee(i,"Employee" +i, sal));
        }
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public JsonResponse getFoobar(@PathVariable int employeeId) {
        for (Object employee : employees) {
            Employee f = (Employee) employee;
            if (f.getId() == employeeId){

                /**
                 * ADD JSON DATA TO MAP
                 */
                addJsonData("employees", f);
            }
        }

        /**
         * SET HTTP CODE
         */
        setStatusCode(HttpStatus.PARTIAL_CONTENT);


        return getJsonResponse();
    }

    @RequestMapping(value = "/htmllist", method = RequestMethod.GET, headers = "Accept=text/html", produces = {"text/html"})
    @ResponseBody
    public JsonResponse getFoobarListHTML() {
        String retVal = "<html><body><table border=1>";
        for (Object employee : employees) {
            Employee f = (Employee) employee;
            retVal += "<tr><td>" + f.getId() + "</td><td>" + f.getName() + "</td><td>" + f.getSalary() + "</td></tr>";
        }
        retVal += "</table></body></html>";

        addJsonData("employeesHTML", retVal);
        return getJsonResponse();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public JsonResponse getFoobarList() {
        addJsonData("employees", employees);
        return getJsonResponse();
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public JsonResponse getFoobars() {
        addJsonData("employees", employees);
        return getJsonResponse();
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public JsonResponse editFoobar(@RequestBody Employee foobar, @PathVariable int employeeId) {
        for (Object employee : employees) {
            Employee f = (Employee) employee;
            if (employeeId == f.getId()) {
                f.setId(foobar.getId());
                f.setName(foobar.getName());
                addJsonData("employees", f);
                return getJsonResponse();
            }
        }
        return getJsonResponse();
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
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

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public boolean createFoobar(@RequestBody Employee employee) {
        return employees.add(employee);
    }

}
