package br.com.orla.domains;

import br.com.orla.domain.Employee;
import br.com.orla.domain.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do domínio Employee")
public class EmployeeTest {

    @Test
    @DisplayName("Teste de criação com builder ")
    void builder() throws MethodArgumentNotValidException {
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

        assertAll(
                () -> assertNotNull(employee),
                () -> assertTrue(employee instanceof Employee),
                () -> assertEquals(name, employee.getName()),
                () -> assertEquals(email, employee.getEmail()),
                () -> assertEquals(document, employee.getDocument()),
                () -> assertEquals(salary, employee.getSalary()),
                () -> assertEquals(projects, employee.getProjects())
        );
    }

    @Test
    @DisplayName("Teste de criação com builder sem name")
    void builderWithoutName() {
        String email = "teste@teste.com";
        String document = "82951449011";
        Double salary = 1234.0;
        Set<Project> projects = new HashSet<>();

        assertThrows(MethodArgumentNotValidException.class,
                () -> Employee.builder()
                        .withEmail(email)
                        .withDocument(document)
                        .withSalary(salary)
                        .withProjects(projects)
                        .build()
        );
    }

    @Test
    @DisplayName("Teste de criação com builder sem email")
    void builderWithoutEmail() {
        String name = "Teste";
        String document = "82951449011";
        Double salary = 1234.0;
        Set<Project> projects = new HashSet<>();

        assertThrows(MethodArgumentNotValidException.class,
                () -> Employee.builder()
                        .withName(name)
                        .withDocument(document)
                        .withSalary(salary)
                        .withProjects(projects)
                        .build()
        );
    }

    @Test
    @DisplayName("Teste de criação com builder sem document")
    void builderWithoutDocument() {
        String name = "Teste";
        String email = "teste@teste.com";
        Double salary = 1234.0;
        Set<Project> projects = new HashSet<>();

        assertThrows(ValidationException.class,
                () -> Employee.builder()
                        .withName(name)
                        .withEmail(email)
                        .withSalary(salary)
                        .withProjects(projects)
                        .build()
        );
    }

    @Test
    @DisplayName("Teste de criação com builder com document inválido")
    void builderWithInvaludDocument() {
        String name = "Teste";
        String email = "teste@teste.com";
        String document = "1234";
        Double salary = 1234.0;
        Set<Project> projects = new HashSet<>();

        assertThrows(MethodArgumentNotValidException.class,
                () -> Employee.builder()
                        .withName(name)
                        .withEmail(email)
                        .withDocument(document)
                        .withSalary(salary)
                        .withProjects(projects)
                        .build()
        );
    }

    @Test
    @DisplayName("Teste de criação com builder sem projects")
    void builderWithoutProjects() {
        String name = "Teste";
        String email = "teste@teste.com";
        String document = "82951449011";
        Double salary = 1234.0;

        assertThrows(MethodArgumentNotValidException.class,
                () -> Employee.builder()
                        .withName(name)
                        .withEmail(email)
                        .withDocument(document)
                        .withSalary(salary)
                        .build()
        );
    }
}
