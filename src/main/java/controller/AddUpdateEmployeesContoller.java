package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Employee;
import model.EmployeeDAO;
import model.EmployeeDAOImpl;

public class AddUpdateEmployeesContoller {

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_salary;

    @FXML
    private TextField tf_id;

    @FXML
    void addEmployee(ActionEvent event) {
        try {
            EmployeeDAO employeeDAOImp = new EmployeeDAOImpl();
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(tf_id.getText()));
            employee.setName(tf_name.getText());
            employee.setEmail(tf_email.getText());
            employee.setSalary(Integer.parseInt(tf_salary.getText()));
            employeeDAOImp.save(employee);

            new Alert(Alert.AlertType.INFORMATION, "The operation was successful!").showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "The operation was not successful!").showAndWait();
        }
    }

}

