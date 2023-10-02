package br.com.orla.domain;

import br.com.orla.utils.SelfValidating;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Project implements Serializable {

    private Long id;
    private String name;
    private LocalDateTime creationDate;

    private Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.creationDate = builder.creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
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
        @NotNull(message = "Campo creationDate não deve ser nulo")
        private LocalDateTime creationDate;

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

        public Builder withCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Project build() throws MethodArgumentNotValidException {
            validateSelf();
            return new Project(this);
        }
    }

}
