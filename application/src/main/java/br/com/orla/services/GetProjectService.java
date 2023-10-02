package br.com.orla.services;

import br.com.orla.domain.Project;
import br.com.orla.domain.repository.ProjectRepository;
import br.com.orla.exceptions.ResourceNotFoundException;
import br.com.orla.ports.in.GetProjectUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetProjectService implements GetProjectUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetProjectService.class);

    private final ProjectRepository projectRepository;

    public GetProjectService(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project get(final String name) {
        LOGGER.debug("m=get(name={})", name);
        return projectRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

}
