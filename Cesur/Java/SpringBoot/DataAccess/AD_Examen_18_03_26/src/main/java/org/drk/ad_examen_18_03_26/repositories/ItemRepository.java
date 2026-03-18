package org.drk.ad_examen_18_03_26.repositories;

import org.drk.ad_examen_18_03_26.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Repositorio para la entidad Item.
 * Proporciona operaciones CRUD y consultas personalizadas sobre la colección "store" de MongoDB.
 */
@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findAll();
    Optional<Item> findByItemId(Integer itemId);
    void deleteByItemId(Integer itemId);
    boolean existsByItemId(Integer itemId);
    List<Item> findByCategory(String category);
    List<Item> findByCountLessThan(Integer count);
}

