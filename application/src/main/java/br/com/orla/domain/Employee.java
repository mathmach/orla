package br.com.orla.domain;

import br.com.orla.domain.validators.IsValidCPF;
import br.com.orla.utils.SelfValidating;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public class Employee implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String document;
    private Double salary;
    private Set<Project> projects;

    private Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.document = builder.document;
        this.salary = builder.salary;
        this.projects = builder.projects;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public Double getSalary() {
        return salary;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static final class Builder extends SelfValidating<Builder> {

        private Long id;
        @NotNull(message = "Campo name não deve ser nulo")
        private String name;
        @NotNull(message = "Campo document não deve ser nulo")
        @IsValidCPF
        private String document;
        @NotNull(message = "Campo email não deve ser nulo")
        private String email;
        @Min(value = 0, message = "Campo salary deve ser maior que 0")
        private Double salary;
        @NotNull(message = "Campo projects não deve ser nulo")
        private Set<Project> projects;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withDocument(String document) {
            this.document = document;
            return this;
        }

        public Builder withSalary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Builder withProjects(Set<Project> projects) {
            this.projects = projects;
            return this;
        }

        public Employee build() throws MethodArgumentNotValidException {
            validateSelf();
            return new Employee(this);
        }
    }
}
