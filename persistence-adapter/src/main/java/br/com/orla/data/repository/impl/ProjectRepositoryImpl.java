package br.com.orla.data.repository.impl;

import br.com.orla.data.ProjectEntity;
import br.com.orla.data.repository.ProjectDAO;
import br.com.orla.domain.Project;
import br.com.orla.domain.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ProjectRepositoryImpl implements ProjectRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRepositoryImpl.class);

    private final ProjectDAO projectDAO;
    private final Converter<ProjectEntity, Project> projectEntityProjectConverter;
    private final Converter<Project, ProjectEntity> projectProjectEntityConverter;

    public ProjectRepositoryImpl(final ProjectDAO projectDAO,
                                 final Converter<ProjectEntity, Project> projectEntityProjectConverter,
                                 final Converter<Project, ProjectEntity> projectProjectEntityConverter) {

        this.projectDAO = projectDAO;
        this.projectEntityProjectConverter = projectEntityProjectConverter;
        this.projectProjectEntityConverter = projectProjectEntityConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(Long id) {
        LOGGER.debug("m=findById(id={})", id);
        Optional<ProjectEntity> projectEntity = projectDAO.findById(id);
        return projectEntity.map(projectEntityProjectConverter::convert);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findByName(String name) {
        LOGGER.debug("m=findByName(name={})", name);
        Optional<ProjectEntity> projectEntity = projectDAO.findByName(name);
        return projectEntity.map(projectEntityProjectConverter::convert);
    }

    @Override
    @Transactional
    public Project save(Project project) {
        LOGGER.debug("m=save(project={})", project);
        ProjectEntity projectEntity = projectProjectEntityConverter.convert(project);
        ProjectEntity savedProjectEntity = projectDAO.save(projectEntity);
        return projectEntityProjectConverter.convert(savedProjectEntity);
    }

}
