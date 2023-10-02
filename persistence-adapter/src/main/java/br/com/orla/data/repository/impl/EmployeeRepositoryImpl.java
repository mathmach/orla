package br.com.orla.data.repository.impl;

import br.com.orla.data.EmployeeEntity;
import br.com.orla.data.repository.EmployeeDAO;
import br.com.orla.domain.Employee;
import br.com.orla.domain.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    private final EmployeeDAO employeeDAO;
    private final Converter<EmployeeEntity, Employee> employeeEntityEmployeeConverter;
    private final Converter<Employee, EmployeeEntity> employeeEmployeeEntityConverter;

    public EmployeeRepositoryImpl(final EmployeeDAO employeeDAO,
                                  final Converter<EmployeeEntity, Employee> employeeEntityEmployeeConverter,
                                  final Converter<Employee, EmployeeEntity> employeeEmployeeEntityConverter) {

        this.employeeDAO = employeeDAO;
        this.employeeEntityEmployeeConverter = employeeEntityEmployeeConverter;
        this.employeeEmployeeEntityConverter = employeeEmployeeEntityConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        LOGGER.debug("m=findById(id={})", id);
        Optional<EmployeeEntity> employeeEntity = employeeDAO.findById(id);
        return employeeEntity.map(employeeEntityEmployeeConverter::convert);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findByName(String name) {
        LOGGER.debug("m=findByName(name={})", name);
        Optional<EmployeeEntity> employeeEntity = employeeDAO.findByName(name);
        return employeeEntity.map(employeeEntityEmployeeConverter::convert);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findByDocument(String document) {
        LOGGER.debug("m=findByDocument(document={})", document);
        Optional<EmployeeEntity> employeeEntity = employeeDAO.findByDocument(document);
        return employeeEntity.map(employeeEntityEmployeeConverter::convert);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        LOGGER.debug("m=save(employee={})", employee);
        EmployeeEntity employeeEntity = employeeEmployeeEntityConverter.convert(employee);
        EmployeeEntity savedEmployeeEntity = employeeDAO.save(employeeEntity);
        return employeeEntityEmployeeConverter.convert(savedEmployeeEntity);
    }

}
