package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Employee;
import model.EmployeeDAO;
import model.EmployeeDAOImpl;

import java.io.IOException;

public class MainController {

    @FXML
    private Menu menu_file;

    @FXML
    private MenuItem mi_exit;

    @FXML
    private Menu menu_edit;

    @FXML
    private TableView<Employee> tv_employee;

    @FXML
    private TableColumn<Employee, Integer> col_id;

    @FXML
    private TableColumn<Employee, String> col_name;

    @FXML
    private TableColumn<Employee, String> col_email;

    @FXML
    private TableColumn<Employee, Integer> col_salary;

    @FXML
    private Button but_addEmployees;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void addEmployee(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/AddUpdateEmployeesView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        Stage stage = new Stage();
        stage.setTitle("Add or update employees");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deleteEmployee(ActionEvent event) {
        Employee employee = tv_employee.getSelectionModel().getSelectedItem();
        EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();
        employeeDAOImpl.delete(employee);
        employees.remove(employee);
    }

    @FXML
    void refreshTableView(ActionEvent event) {
        employees.clear();
        EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();
        employees = FXCollections.observableArrayList(employeeDAOImpl.findAll());
        tv_employee.setItems(employees);
    }

    public void addDataToTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tv_employee.setItems(employees);
    }

    public void initialize() {
        EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();
        employees = FXCollections.observableArrayList(employeeDAOImpl.findAll());
        addDataToTable();
    }

}
