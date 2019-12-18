package co.edu.unal.software_engineering.meetu.service;

import co.edu.unal.software_engineering.meetu.model.Plan;
import co.edu.unal.software_engineering.meetu.model.User;
import co.edu.unal.software_engineering.meetu.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.meetu.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService( UserRepository userRepository ){
        this.userRepository = userRepository;
    }

    public User findByEmail( String email ){
        return userRepository.findByEmail(  email );
    }

    public void save( User user ){
        userRepository.save( user );
    }

    public void delete (User user) {
        userRepository.delete(user);
    }

    public boolean isRightUser( RegisterUserPOJO user ){
        boolean correctness = user.getPassword( ) != null && user.getUsername( ) != null;
        if( correctness ){
            correctness = !user.getUsername( ).trim( ).isEmpty( )
                    && !user.getLast_name( ).trim( ).isEmpty( )
                    && !user.getCity( ).trim( ).isEmpty( )
                    && !user.getEmail( ).trim( ).isEmpty( )
                    && !user.getPhone_number( ).trim( ).isEmpty( )
                    && !user.getPassword( ).trim( ).isEmpty( )
                    /*
                    The password policy is:
                        At least 8 chars
                        Contains at least one digit
                        Contains at least one lower alpha char and one upper alpha char
                        Contains at least one char within a set of special chars (@#%$^ etc.)
                        Does not contain space, tab, etc.
                    */
                    && user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
                    && user.getLast_name().matches("[a-zA-Z]{3,30}")
                    && user.getCity().matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$")
                    && user.getUsername().matches("^[a-zA-Z0-9._-]{3,}$")
                    && user.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                    && user.getPhone_number().matches("[0-9]+") ;
        }
        return correctness;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}