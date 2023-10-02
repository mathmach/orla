package br.com.orla.domain.repository;

import br.com.orla.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findById(Long id);

    Optional<Employee> findByName(String name);

    Optional<Employee> findByDocument(String document);

    Employee save(Employee employee);

}
