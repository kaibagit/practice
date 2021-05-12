package me.luliru.practice.ladp;

import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

/**
 * me.luliru.practice.ladp.PersonRepository
 * Created by luliru on 5/12/21.
 */
public interface PersonRepository extends CrudRepository<Person, Name> {

}
