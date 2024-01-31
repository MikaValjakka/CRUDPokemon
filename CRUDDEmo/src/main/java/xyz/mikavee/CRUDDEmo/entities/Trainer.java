package xyz.mikavee.CRUDDEmo.entities;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "trainers")
@Data
public class Trainer {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String userName;
    private int age;
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private List<Pokemon> pokemons = new ArrayList<>();

    private List<Role> roles = new ArrayList<>();

    public Trainer(String firstName, String lastName,String userName, int age, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.age = age;
        this.password = password;
    }

    // Method to add a Pokemon to the Trainer's list
    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        System.out.println("Pokemon " + pokemon + " was added to list");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return age == trainer.age &&
                Objects.equals(id, trainer.id) &&
                Objects.equals(firstName, trainer.firstName) &&
                Objects.equals(lastName, trainer.lastName) &&
                Objects.equals(pokemons, trainer.pokemons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, pokemons);
    }
}
