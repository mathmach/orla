package br.com.orla.data.repository;

import br.com.orla.data.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends PagingAndSortingRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByName(String name);

    Optional<EmployeeEntity> findByDocument(String document);

}
