package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Optional findOne(Integer id) {
        Optional<Employee> optionalEmployee = Optional.empty();
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.getCon();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee WHERE id_employee = " + id);

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id_employee"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getInt("salary"));
                optionalEmployee = Optional.of(employee);

                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalEmployee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeesList = new ArrayList<>();
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.getCon();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id_employee"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getInt("salary"));
                employeesList.add(employee);
            }
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeesList;
    }

    @Override
    public Optional findByName(String name) {
        Optional<Employee> optionalEmployee = Optional.empty();
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.getCon();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee WHERE name = '" + name + "'");
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id_employee"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getInt("salary"));
                optionalEmployee = Optional.of(employee);

                stmt.close();
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return optionalEmployee;
    }

    @Override
    public void delete(Employee employee) {
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.getCon();

        try {
            String query = "DELETE FROM employee WHERE id_employee = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, employee.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(Employee employee) {
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.getCon();

        try {
            String query = "SELECT * FROM employee WHERE id_employee = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, employee.getId());
            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) {
                String query2 = "UPDATE employee SET name=?, email=?, salary=? WHERE id_employee=?";
                PreparedStatement preparedStmt2 = con.prepareStatement(query2);
                preparedStmt2.setString(1, employee.getName());
                preparedStmt2.setString(2, employee.getEmail());
                preparedStmt2.setInt(3, employee.getSalary());
                preparedStmt2.setInt(4, employee.getId());
                preparedStmt2.execute();
            } else {
                String query2 = "INSERT INTO employee values (?, ?, ?, ?)";
                PreparedStatement preparedStmt2 = con.prepareStatement(query2);
                preparedStmt2.setInt(1, employee.getId());
                preparedStmt2.setString(2, employee.getName());
                preparedStmt2.setString(3, employee.getEmail());
                preparedStmt2.setInt(4, employee.getSalary());
                preparedStmt2.execute();
            }
            preparedStmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
