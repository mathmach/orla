package br.com.orla.ports.in;

import br.com.orla.annotations.UseCase;
import br.com.orla.domain.Employee;

@UseCase
public interface GetEmployeeUseCase {

    Employee get(String name) throws Exception;

}
