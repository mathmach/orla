package br.com.orla.api.interfaces.controllers.converters;

import br.com.orla.annotations.Mapper;
import br.com.orla.api.interfaces.controllers.dto.EmployeeRequestDTO;
import br.com.orla.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;

@Mapper
public class EmployeeRequestDTOToEmployeeConverter implements Converter<EmployeeRequestDTO, Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRequestDTOToEmployeeConverter.class);

    @Override
    public Employee convert(EmployeeRequestDTO source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            return Employee.builder()
                    .withName(source.getName())
                    .withDocument(source.getDocument())
                    .withEmail(source.getEmail())
                    .withSalary(source.getSalary())
                    .withProjects(new HashSet<>())
                    .build();
        } catch (Exception e) {
            LOGGER.error("exception=(message={}, cause={}): {}", e.getMessage(), e.getCause(), e);
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }
}
