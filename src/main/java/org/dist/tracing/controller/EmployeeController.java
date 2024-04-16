package org.dist.tracing.controller;

import org.dist.tracing.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/employee")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String getEmployee() throws InterruptedException {
        logger.info("Get employee");
        employeeService.getEmployee();
        return "Employee 1";
    }

    @GetMapping("/all")
    public List<String> getAllEmployees() {
        logger.info("Get all employees");
        employeeService.getMultipleEmployees();
        return List.of("Employee1", "Employee2");
    }

    @GetMapping("/send-notification")
    public String sendNotification() throws InterruptedException {
        logger.info("Sending notification");
        employeeService.sendNotification();
        logger.info("Async notification scheduled");
        return "Notification sent";
    }
}
