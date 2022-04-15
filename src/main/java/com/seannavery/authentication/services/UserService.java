package com.seannavery.authentication.services;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.seannavery.authentication.models.LoginUserModel;
import com.seannavery.authentication.models.UserModel;
import com.seannavery.authentication.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public UserModel register(UserModel newUser, BindingResult result) {
    	Optional<UserModel> potentialUser = userRepository.findByEmail(newUser.getEmail());
    	
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "unique", "The email already exists!");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
    	if(result.hasErrors()) {
    		return null;
    	}
    	String hashed =BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	return userRepository.save(newUser);
    	
    }
    
    
    public UserModel login(LoginUserModel newLogin, BindingResult result) {
    	Optional<UserModel> potentialUser = userRepository.findByEmail(newLogin.getEmail());
    	
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "unique", "Unknown email!");
    		return null;
    	}
    	
    	UserModel user = potentialUser.get();
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    		result.rejectValue("password", "Matches", "Incorrect password!");
    	}
    	if(result.hasErrors()) {
    		return null;
    	}
    	return user;
    }
    // returns all the books
//    public List<UserModel> allUsers() {
//        return userRepository.findAll();
//    }
//    
//    public Optional<UserModel> userIdByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//    // creates a book
//    public User createUser(User b) {
//        return bookRepository.save(b);
//    }
//    
//    //deletes a book
//    public void deleteBook(Long id) {
//    	bookRepository.deleteById(id);
//    }
//    
//    // updates a book
//     public Book updateBook(Book book) {
//    	Long bookId = book.getId();
//    	String bookTitle = book.getTitle();
//    	String bookDesc = book.getDescription();
//    	String bookLang = book.getLanguage();
//    	Integer bookPages = book.getNumberOfPages();
//    	Book bookToUpdate = this.findBook(bookId);   //set method below for this to work	
//    	bookToUpdate.setTitle(bookTitle);
//    	bookToUpdate.setDescription(bookDesc);
//    	bookToUpdate.setLanguage(bookLang);
//    	bookToUpdate.setNumberOfPages(bookPages);
//    	bookRepository.save(bookToUpdate);
//        return bookToUpdate;
//    }
    // retrieves a book
    public UserModel findUser(Long id) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
}
