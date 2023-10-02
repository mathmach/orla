package br.com.orla.services;

import br.com.orla.domain.Employee;
import br.com.orla.domain.repository.EmployeeRepository;
import br.com.orla.exceptions.BusinessException;
import br.com.orla.ports.in.CreateEmployeeUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateEmployeeService implements CreateEmployeeUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateEmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public CreateEmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public Employee create(final Employee employee) {
        LOGGER.debug("m=create(employee={})", employee);
        final Optional<Employee> alreadyRegisteredEmployee = employeeRepository.findByDocument(employee.getDocument());

        if (alreadyRegisteredEmployee.isPresent()) {
            throw new BusinessException(String.format("O documento %s já existe na base", employee.getName()));
        }

        return employeeRepository.save(employee);
    }

}
