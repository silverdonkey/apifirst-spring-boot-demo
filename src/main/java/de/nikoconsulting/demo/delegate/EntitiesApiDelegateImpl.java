package de.nikoconsulting.demo.delegate;

import de.nikoconsulting.demo.api.EntityApiDelegate;
import de.nikoconsulting.demo.model.GenericEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class EntitiesApiDelegateImpl implements EntityApiDelegate {

    Logger logger = LoggerFactory.getLogger(EntitiesApiDelegateImpl.class);

    private final List<GenericEntity> entityList = new ArrayList<>();

    {
        entityList.add(new GenericEntity(1L, "entity_1"));
        entityList.add(new GenericEntity(2L, "entity_2"));
        entityList.add(new GenericEntity(3L, "entity_3"));
        entityList.add(new GenericEntity(4L, "entity_4"));
    }

    @Override
    public ResponseEntity<List<GenericEntity>> listAllEntities() {
        // simulate slow response
        logger.info("Looking up entities...");
        logger.info("Returning found entities.");
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createEntity(GenericEntity entity) {
        logger.info("Adding a new entity: " + entity.getValue());
        entityList.add(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GenericEntity> findEntityById(Long id) {
        logger.info("Looking up entity by id: " + id);
        for (GenericEntity entity : entityList) {
            if (entity.getId().equals(id)) {
                return new ResponseEntity<>(Optional.of(entity).get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
