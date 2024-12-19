package com.example.User;

import com.example.User.User;
import com.example.User.UserRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository; // 필드 선언

    // 생성자 주입을 통해 userRepository를 초기화
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(int id, String username, String password, String email, LocalDateTime createdAt) {
        User user = new User();
        user.setId(id);
        user.setusername(username); // 메서드명 수정
        user.setpassword(password); // 메서드명 수정
        user.setemail(email); // 메서드명 수정
        user.setCreatedAt(createdAt);

        // 인스턴스를 통해 save 메서드 호출
        return userRepository.save(user); // 수정된 부분
    }

    public void someMethod() {
        // userRepository 사용 가능
        userRepository.findAll();
    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getpassword().equals(password)) {
            return true;
        }
        return false;
    }

    public User getuserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }
    public void deleteuser(int id, String password) {
        User user = getuserById(id);
        if (!user.getpassword().equals(password)) {
            throw new IllegalArgumentException("Password does not match");
        }
        userRepository.delete(user);
    }

}
