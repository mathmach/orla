package br.com.orla.services;

import br.com.orla.domain.Employee;
import br.com.orla.domain.Project;
import br.com.orla.domain.repository.EmployeeRepository;
import br.com.orla.domain.repository.ProjectRepository;
import br.com.orla.exceptions.ResourceNotFoundException;
import br.com.orla.ports.in.UpdateEmployeeUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeService implements UpdateEmployeeUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateEmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public UpdateEmployeeService(final EmployeeRepository employeeRepository,
                                 final ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Employee update(final Long employee, final Long project) {
        LOGGER.debug("m=update(employee={},project={})", employee, project);
        Employee e = employeeRepository.findById(employee).orElseThrow(ResourceNotFoundException::new);
        Project p = projectRepository.findById(employee).orElseThrow(ResourceNotFoundException::new);
        e.getProjects().add(p);
        return employeeRepository.save(e);
    }

}
