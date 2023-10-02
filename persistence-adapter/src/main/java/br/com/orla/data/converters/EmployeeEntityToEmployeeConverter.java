package br.com.orla.data.converters;

import br.com.orla.annotations.Mapper;
import br.com.orla.data.EmployeeEntity;
import br.com.orla.data.ProjectEntity;
import br.com.orla.domain.Employee;
import br.com.orla.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public class EmployeeEntityToEmployeeConverter implements Converter<EmployeeEntity, Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeEntityToEmployeeConverter.class);

    private final Converter<ProjectEntity, Project> projectEntityProjectConverter;

    public EmployeeEntityToEmployeeConverter(final Converter<ProjectEntity, Project> projectEntityProjectConverter) {
        this.projectEntityProjectConverter = projectEntityProjectConverter;
    }

    @Override
    public Employee convert(final EmployeeEntity source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            Set<Project> projects = source.getProjects().stream()
                    .map(projectEntityProjectConverter::convert)
                    .collect(Collectors.toSet());

            return Employee.builder()
                    .withId(source.getId())
                    .withName(source.getName())
                    .withDocument(source.getDocument())
                    .withEmail(source.getEmail())
                    .withSalary(source.getSalary())
                    .withProjects(projects)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("exception=(message={}, cause={}): {}", e.getMessage(), e.getCause(), e);
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }

}
