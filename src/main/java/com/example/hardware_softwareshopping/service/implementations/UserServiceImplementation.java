package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.dto.AuthDTO;
import com.example.hardware_softwareshopping.dto.UserPageDTO;
import com.example.hardware_softwareshopping.dto.UsersForAdminDTO;
import com.example.hardware_softwareshopping.model.Address;
import com.example.hardware_softwareshopping.model.User;
import com.example.hardware_softwareshopping.repository.AddressRepository;
import com.example.hardware_softwareshopping.repository.UserRepository;
import com.example.hardware_softwareshopping.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;


    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserPageDTO findByEmailAndPassword(AuthDTO authDTO) {

        User user= userRepository.findByEmailAndPassword(authDTO.getEmail(),authDTO.getPassword());
        if(user==null)
            return null;
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setEmail(authDTO.getEmail());
        userPageDTO.setUserRole(user.getUserRole());
        return userPageDTO;
    }



    @Override
    public User save(User user) {

        if(userRepository.findByEmail(user.getEmail())!=null)
            return null;
        User u =userRepository.save(user);
        return u;
    }

    @Override
    public List<UsersForAdminDTO> findAll() {

        List<User> users = userRepository.findAll();

        List<UsersForAdminDTO> list = new ArrayList<>();
        for(User user:users){
            UsersForAdminDTO usersForAdminDTO = new UsersForAdminDTO();
            usersForAdminDTO.setEmail(user.getEmail());
            usersForAdminDTO.setFirstName(user.getFirstName());
            usersForAdminDTO.setLastName(user.getLastName());
            usersForAdminDTO.setNumberOfTelephone(user.getNumberOfTelephone());
            usersForAdminDTO.setUserRole(user.getUserRole());
            list.add(usersForAdminDTO);
        }

        return list;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User deleteByEmail(String email) {

        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return user;
    }



    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


}
