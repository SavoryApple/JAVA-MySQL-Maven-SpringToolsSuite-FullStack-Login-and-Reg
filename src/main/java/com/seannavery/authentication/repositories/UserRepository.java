package com.seannavery.authentication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seannavery.authentication.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long>{
    // this method retrieves all the books from the database
    List<UserModel> findAll();
    // this method finds books with descriptions containing the search string
    Optional<UserModel> findByEmail(String email); //be CAREFUL with these bottom three
    // this method counts how many titles contain a certain string
//    Long countByTitleContaining(String search);
    // this method deletes a book that starts with a specific title
//    Long deleteByTitleStartingWith(String search);
}