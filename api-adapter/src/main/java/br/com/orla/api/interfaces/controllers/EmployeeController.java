package br.com.orla.api.interfaces.controllers;

import br.com.orla.api.interfaces.controllers.dto.EmployeeRequestDTO;
import br.com.orla.api.interfaces.controllers.dto.EmployeeResponseDTO;
import br.com.orla.domain.Employee;
import br.com.orla.ports.in.CreateEmployeeUseCase;
import br.com.orla.ports.in.GetEmployeeUseCase;
import br.com.orla.ports.in.UpdateEmployeeUseCase;
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
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final GetEmployeeUseCase getEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final Converter<Employee, EmployeeResponseDTO> employeeEmployeeResponseDTOConverter;
    private final Converter<EmployeeRequestDTO, Employee> employeeRequestDTOEmployeeConverter;

    public EmployeeController(final CreateEmployeeUseCase createEmployeeUseCase,
                              final GetEmployeeUseCase getEmployeeUseCase,
                              final UpdateEmployeeUseCase updateEmployeeUseCase,
                              final Converter<Employee, EmployeeResponseDTO> employeeEmployeeResponseDTOConverter,
                              final Converter<EmployeeRequestDTO, Employee> employeeRequestDTOEmployeeConverter) {
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.getEmployeeUseCase = getEmployeeUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.employeeEmployeeResponseDTOConverter = employeeEmployeeResponseDTOConverter;
        this.employeeRequestDTOEmployeeConverter = employeeRequestDTOEmployeeConverter;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Criar um novo funcionário", response = EmployeeResponseDTO.class)
    public ResponseEntity<EmployeeResponseDTO> request(@Valid @RequestBody final EmployeeRequestDTO request) {
        LOGGER.debug("m=create(create={})", request);
        final Employee requested = createEmployeeUseCase.create(employeeRequestDTOEmployeeConverter.convert(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeEmployeeResponseDTOConverter.convert(requested));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recuperar resultado de Funcionários", response = EmployeeResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação ocorreu com sucesso."),
            @ApiResponse(code = 404, message = "Nenhum Funcionário encontrado pelo identfiicador."),
            @ApiResponse(code = 500, message = "Ocorreu um erro inesperado ao tentar realizar a operação.")
    })
    public ResponseEntity<EmployeeResponseDTO> get(@RequestParam final String name) throws Exception {
        LOGGER.debug("m=get(name={})", name);
        final Employee employee = getEmployeeUseCase.get(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeEmployeeResponseDTOConverter.convert(employee));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Adicionar projeto ao funcionário", response = EmployeeResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação ocorreu com sucesso."),
            @ApiResponse(code = 404, message = "Nenhum Funcionário encontrado pelo identfiicador."),
            @ApiResponse(code = 500, message = "Ocorreu um erro inesperado ao tentar realizar a operação.")
    })
    public ResponseEntity<EmployeeResponseDTO> put(@RequestParam final Long employee, @RequestParam final Long project) throws Exception {
        LOGGER.debug("m=put(employee={},project={})", employee, project);
        final Employee e = updateEmployeeUseCase.update(employee, project);
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeEmployeeResponseDTOConverter.convert(e));
    }

}
