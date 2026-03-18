package org.drk.di_examen_18_03_26.repositories;

import org.drk.di_examen_18_03_26.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findAll();
    List<Item> findByCategory(String category);

    // Corregido: 'findByCountLessThan' usa el campo 'count'
    List<Item> findByCountLessThan(Integer count);

    // Corregido: Cambiado 'Stock' por 'Count' para coincidir con la entidad Item
    Long countByCountLessThan(Integer count);
}
