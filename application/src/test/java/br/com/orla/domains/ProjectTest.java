package br.com.orla.domains;

import br.com.orla.domain.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do domínio Project")
public class ProjectTest {

    @Test
    @DisplayName("Teste de criação com builder ")
    void builder() throws MethodArgumentNotValidException {
        String name = "Orla";
        LocalDateTime creationDate = LocalDateTime.now();

        Project project = Project.builder()
                .withName(name)
                .withCreationDate(creationDate)
                .build();

        assertAll(
                () -> assertNotNull(project),
                () -> assertTrue(project instanceof Project),
                () -> assertEquals(name, project.getName()),
                () -> assertEquals(creationDate, project.getCreationDate())
        );
    }

    @Test
    @DisplayName("Teste de criação com builder sem name")
    void builderWithoutName() {
        LocalDateTime creationDate = LocalDateTime.now();

        assertThrows(MethodArgumentNotValidException.class,
                () -> Project.builder()
                        .withCreationDate(creationDate)
                        .build()
        );
    }

    @Test
    @DisplayName("Teste de criação com builder sem creationDate")
    void builderWithoutCreationDate() {
        String name = "Orla";

        assertThrows(MethodArgumentNotValidException.class,
                () -> Project.builder()
                        .withName(name)
                        .build()
        );
    }
}
