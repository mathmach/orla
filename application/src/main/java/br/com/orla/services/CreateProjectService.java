package br.com.orla.services;

import br.com.orla.domain.Project;
import br.com.orla.domain.repository.ProjectRepository;
import br.com.orla.exceptions.BusinessException;
import br.com.orla.ports.in.CreateProjectUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateProjectService implements CreateProjectUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProjectService.class);

    private final ProjectRepository projectRepository;

    public CreateProjectService(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    @Override
    public Project create(final Project project) {
        LOGGER.debug("m=create(project={})", project);
        final Optional<Project> alreadyRegisteredProject = projectRepository.findByName(project.getName());

        if (alreadyRegisteredProject.isPresent()) {
            throw new BusinessException(String.format("Projeto %s já existe na base", project.getName()));
        }

        return projectRepository.save(project);
    }

}
