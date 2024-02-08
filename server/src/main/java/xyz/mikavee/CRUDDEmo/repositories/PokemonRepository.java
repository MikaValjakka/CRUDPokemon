package xyz.mikavee.CRUDDEmo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.mikavee.CRUDDEmo.entities.Pokemon;

public interface PokemonRepository extends MongoRepository<Pokemon,String> {
}
