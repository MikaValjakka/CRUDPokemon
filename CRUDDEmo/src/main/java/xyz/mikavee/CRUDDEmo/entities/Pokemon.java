package xyz.mikavee.CRUDDEmo.entities;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "pokemons")
@Data
public class Pokemon {


    @Id
    private String id;


    @NotBlank(message="name is mandatory")
    private String name;

    @NotNull(message = "level is mandatory")
    @Min(value = 1, message = "Level must be greater than or equal to 1")
    private Integer level;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    // Custom constructor with only 'name' and 'level'
    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    // No need to explicitly write getters and setters, Lombok generates them for you

}
