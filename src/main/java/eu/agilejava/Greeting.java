package eu.agilejava;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Greeting extends PanacheEntity {

    @Id
    @SequenceGenerator(
            name = "greetingSequence",
            sequenceName = "greeting_id_seq",
            allocationSize = 1,
            initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "greetingSequence")
    private Long id;
    private String message;

    public Greeting() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return Objects.equals(id, greeting.id) &&
                Objects.equals(message, greeting.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
