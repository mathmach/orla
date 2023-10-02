package br.com.orla.data.converters;

import br.com.orla.annotations.Mapper;
import br.com.orla.data.ProjectEntity;
import br.com.orla.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

@Mapper
public class ProjectToProjectEntityConverter implements Converter<Project, ProjectEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectToProjectEntityConverter.class);

    @Override
    public ProjectEntity convert(final Project source) {
        try {
            LOGGER.debug("m=convert(source={})", source);

            if (source == null) {
                return null;
            }

            ProjectEntity entity = new ProjectEntity();
            entity.setId(source.getId());
            entity.setName(source.getName());
            entity.setCreationDate(source.getCreationDate());

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error to convert " + source, e.getCause());
        }
    }

}
