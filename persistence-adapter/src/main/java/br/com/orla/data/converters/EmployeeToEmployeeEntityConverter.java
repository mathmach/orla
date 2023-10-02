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
public class EmployeeToEmployeeEntityConverter implements Converter<Employee, EmployeeEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeToEmployeeEntityConverter.class);

    private final Converter<Project, ProjectEntity> projectProjectEntityConverter;

    public EmployeeToEmployeeEntityConverter(final Converter<Project, ProjectEntity> projectProjectEntityConverter) {
        this.projectProjectEntityConverter = projectProjectEntityConverter;
    }

    @Override
    public EmployeeEntity convert(final Employee source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            Set<ProjectEntity> projects = source.getProjects().stream()
                    .map(projectProjectEntityConverter::convert)
                    .collect(Collectors.toSet());

            EmployeeEntity entity = new EmployeeEntity();
            entity.setId(source.getId());
            entity.setName(source.getName());
            entity.setDocument(source.getDocument());
            entity.setEmail(source.getEmail());
            entity.setSalary(source.getSalary());
            entity.setProjects(projects);

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }

}
