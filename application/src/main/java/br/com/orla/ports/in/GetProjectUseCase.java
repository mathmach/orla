package br.com.orla.ports.in;

import br.com.orla.annotations.UseCase;
import br.com.orla.domain.Project;

@UseCase
public interface GetProjectUseCase {

    Project get(String name) throws Exception;

}
