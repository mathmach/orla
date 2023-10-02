package br.com.orla.ports.in;

import br.com.orla.annotations.UseCase;
import br.com.orla.domain.Employee;

@UseCase
public interface UpdateEmployeeUseCase {

    Employee update(Long employee, Long project) throws Exception;

}
