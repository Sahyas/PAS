package src.main.java.com.pas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Valid
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(Admin.class),
        @JsonSubTypes.Type(Moderator.class),
        @JsonSubTypes.Type(Client.class)}
)
public class User {
    private UUID id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String personalId;

    private float debt;
    @NotNull
    private int age;

    private boolean isActive;

    public User(UUID id, String login, String password, String firstName, String lastName,
                String personalId, float debt, int age, boolean isActive) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.debt = debt;
        this.age = age;
        this.isActive = isActive;
    }
}
