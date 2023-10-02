package br.com.orla.domain.repository;

import br.com.orla.domain.Project;

import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findById(Long id);

    Optional<Project> findByName(String name);

    Project save(Project project);

}
