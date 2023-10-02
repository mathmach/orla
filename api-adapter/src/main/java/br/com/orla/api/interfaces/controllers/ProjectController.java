package br.com.orla.api.interfaces.controllers;

import br.com.orla.api.interfaces.controllers.dto.ProjectRequestDTO;
import br.com.orla.api.interfaces.controllers.dto.ProjectResponseDTO;
import br.com.orla.domain.Project;
import br.com.orla.ports.in.CreateProjectUseCase;
import br.com.orla.ports.in.GetProjectUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    private final CreateProjectUseCase createProjectUseCase;
    private final GetProjectUseCase getProjectUseCase;
    private final Converter<Project, ProjectResponseDTO> projectProjectResponseDTOConverter;
    private final Converter<ProjectRequestDTO, Project> projectRequestDTOProjectConverter;

    public ProjectController(final CreateProjectUseCase createProjectUseCase,
                             final GetProjectUseCase getProjectUseCase,
                             final Converter<Project, ProjectResponseDTO> projectProjectResponseDTOConverter,
                             final Converter<ProjectRequestDTO, Project> projectRequestDTOProjectConverter) {
        this.createProjectUseCase = createProjectUseCase;
        this.getProjectUseCase = getProjectUseCase;
        this.projectProjectResponseDTOConverter = projectProjectResponseDTOConverter;
        this.projectRequestDTOProjectConverter = projectRequestDTOProjectConverter;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Criar um novo projeto", response = ProjectResponseDTO.class)
    public ResponseEntity<ProjectResponseDTO> request(@Valid @RequestBody final ProjectRequestDTO request) {
        LOGGER.debug("m=create(create={})", request);
        final Project requested = createProjectUseCase.create(projectRequestDTOProjectConverter.convert(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectProjectResponseDTOConverter.convert(requested));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recuperar resultado de Projetos", response = ProjectResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação ocorreu com sucesso."),
            @ApiResponse(code = 404, message = "Nenhum Projeto encontrado pelo identfiicador."),
            @ApiResponse(code = 500, message = "Ocorreu um erro inesperado ao tentar realizar a operação.")
    })
    public ResponseEntity<ProjectResponseDTO> get(@RequestParam final String name) throws Exception {
        LOGGER.debug("m=get(name={})", name);
        final Project project = getProjectUseCase.get(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectProjectResponseDTOConverter.convert(project));
    }

}
