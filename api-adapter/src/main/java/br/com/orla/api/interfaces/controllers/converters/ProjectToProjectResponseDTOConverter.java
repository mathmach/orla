package br.com.orla.api.interfaces.controllers.converters;

import br.com.orla.annotations.Mapper;
import br.com.orla.api.interfaces.controllers.dto.ProjectResponseDTO;
import br.com.orla.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

@Mapper
public class ProjectToProjectResponseDTOConverter implements Converter<Project, ProjectResponseDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectToProjectResponseDTOConverter.class);

    @Override
    public ProjectResponseDTO convert(Project source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            ProjectResponseDTO dto = new ProjectResponseDTO();
            dto.setName(source.getName());
            dto.setCreationDate(source.getCreationDate());

            return dto;
        } catch (Exception e) {
            LOGGER.error("exception=(message={}, cause={}): {}", e.getMessage(), e.getCause(), e);
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }

}
