package model;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    /** * Zwraca pracownika o podanym id */
    Optional findOne(Integer id);

    /** * Zwraca wszystkich pracowników */
    List<Employee> findAll();

    /** * Zwraca pracownika o podanym nazwisku */
    Optional findByName(String name);

    /** * Usuwa pracownika */
    void delete(Employee employee);

    /** * Dodaje, jeśli brak, lub aktualizuje pracownika */
    void save(Employee employee);
}

