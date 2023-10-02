package br.com.orla.services;

import br.com.orla.domain.Employee;
import br.com.orla.domain.repository.EmployeeRepository;
import br.com.orla.exceptions.ResourceNotFoundException;
import br.com.orla.ports.in.GetEmployeeUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeService implements GetEmployeeUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetEmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public GetEmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee get(final String name) {
        LOGGER.debug("m=get(name={})", name);
        return employeeRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

}
