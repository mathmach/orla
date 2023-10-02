package br.com.orla.api.interfaces.controllers.converters;

import br.com.orla.annotations.Mapper;
import br.com.orla.api.interfaces.controllers.dto.ProjectRequestDTO;
import br.com.orla.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

@Mapper
public class ProjectRequestDTOToProjectConverter implements Converter<ProjectRequestDTO, Project> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRequestDTOToProjectConverter.class);

    @Override
    public Project convert(ProjectRequestDTO source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            return Project.builder()
                    .withName(source.getName())
                    .withCreationDate(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            LOGGER.error("exception=(message={}, cause={}): {}", e.getMessage(), e.getCause(), e);
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }
}
