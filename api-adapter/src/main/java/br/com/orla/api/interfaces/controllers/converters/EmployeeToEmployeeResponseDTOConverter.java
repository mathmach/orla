package br.com.orla.api.interfaces.controllers.converters;

import br.com.orla.annotations.Mapper;
import br.com.orla.api.interfaces.controllers.dto.EmployeeResponseDTO;
import br.com.orla.api.interfaces.controllers.dto.ProjectResponseDTO;
import br.com.orla.domain.Employee;
import br.com.orla.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public class EmployeeToEmployeeResponseDTOConverter implements Converter<Employee, EmployeeResponseDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeToEmployeeResponseDTOConverter.class);

    private final Converter<Project, ProjectResponseDTO> projectProjectResponseDTOConverter;

    public EmployeeToEmployeeResponseDTOConverter(final Converter<Project, ProjectResponseDTO> projectProjectResponseDTOConverter) {
        this.projectProjectResponseDTOConverter = projectProjectResponseDTOConverter;
    }

    @Override
    public EmployeeResponseDTO convert(Employee source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            Set<ProjectResponseDTO> projects = source.getProjects().stream()
                    .map(projectProjectResponseDTOConverter::convert)
                    .collect(Collectors.toSet());

            EmployeeResponseDTO dto = new EmployeeResponseDTO();
            dto.setName(source.getName());
            dto.setDocument(source.getDocument());
            dto.setEmail(source.getEmail());
            dto.setSalary(source.getSalary());
            dto.setProjects(projects);
            return dto;
        } catch (Exception e) {
            LOGGER.error("exception=(message={}, cause={}): {}", e.getMessage(), e.getCause(), e);
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }
}
