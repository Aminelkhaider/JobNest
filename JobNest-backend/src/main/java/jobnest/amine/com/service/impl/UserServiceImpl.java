package jobnest.amine.com.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import jobnest.amine.com.model.User;
import jobnest.amine.com.repository.UserRepository;
import jobnest.amine.com.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	

}
