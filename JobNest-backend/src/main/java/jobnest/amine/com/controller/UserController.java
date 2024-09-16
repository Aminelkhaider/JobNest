package jobnest.amine.com.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jobnest.amine.com.model.User;
import jobnest.amine.com.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/all")
    public List<User> getAll() {
        return service.getAllUsers();
    }
}
