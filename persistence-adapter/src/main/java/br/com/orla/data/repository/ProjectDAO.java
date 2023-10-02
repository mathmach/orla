package br.com.orla.data.repository;

import br.com.orla.data.ProjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectDAO extends PagingAndSortingRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(String name);

}
