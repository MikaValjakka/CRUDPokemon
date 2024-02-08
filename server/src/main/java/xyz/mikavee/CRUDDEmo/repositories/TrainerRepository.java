package xyz.mikavee.CRUDDEmo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.mikavee.CRUDDEmo.entities.Trainer;

public interface TrainerRepository extends MongoRepository<Trainer,String> {

    Trainer findByUserName(String userName);



}
