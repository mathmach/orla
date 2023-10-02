package br.com.orla.data.converters;

import br.com.orla.annotations.Mapper;
import br.com.orla.data.ProjectEntity;
import br.com.orla.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

@Mapper
public class ProjectEntityToProjectConverter implements Converter<ProjectEntity, Project> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectEntityToProjectConverter.class);

    @Override
    public Project convert(final ProjectEntity source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            return Project.builder()
                    .withId(source.getId())
                    .withName(source.getName())
                    .withCreationDate(source.getCreationDate())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("exception=(message={}, cause={}): {}", e.getMessage(), e.getCause(), e);
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }

}
