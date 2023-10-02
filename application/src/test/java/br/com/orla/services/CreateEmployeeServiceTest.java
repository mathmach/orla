/*
 * Copyright (c) Audsat, Todos os direitos reservados
 *
 * Este arquivo e uma propriedade confidencial da Audsat. Nenhuma parte do mesmo
 *  pode ser copiada, reproduzida, impressa ou transmitida por qualquer meio sem
 *  autorizacao expressa e por escrito de um representante legal da Audsat.
 *
 *  All rights reserved
 *
 *  This file is a confidential property of Audsat. No part of this file may be
 *  reproduced or copied in any form or by any means without written permission
 *  from an authorized person from Audsat.
 */

package br.com.orla.services;

import br.com.orla.domain.Employee;
import br.com.orla.domain.Project;
import br.com.orla.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Testes do servico de Employee")
@SpringBootTest(classes = {
        CreateEmployeeService.class
})
class CreateEmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @DisplayName("Teste de cadastro com sucesso")
    @Test
    void save() throws MethodArgumentNotValidException {
        String name = "Teste";
        String email = "teste@teste.com";
        String document = "82951449011";
        Double salary = 1234.0;
        Set<Project> projects = new HashSet<>();

        Employee employee = Employee.builder()
                .withName(name)
                .withEmail(email)
                .withDocument(document)
                .withSalary(salary)
                .withProjects(projects)
                .build();

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee result = employeeRepository.save(employee);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(Employee.class, result.getClass()),
                () -> verify(employeeRepository, times(1)).save(isA(Employee.class))
        );
    }

}
