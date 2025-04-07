package com.hnk.auth;

import com.hnk.auth.model.Permission;
import com.hnk.auth.model.Role;
import com.hnk.auth.model.User;
import com.hnk.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(UserRepository userRepository) {
//        return args -> {
//            Permission per1 = new Permission();
//            per1.setName("READ");
//            Permission per2 = new Permission();
//            per2.setName("CREATE");
//            Permission per3 = new Permission();
//            per3.setName("UPDATE");
//            Permission per4 = new Permission();
//            per4.setName("DELETE");
//
//            Role role1 = new Role();
//            role1.setName("ADMIN");
//            role1.setPermissions(Set.of(per1, per2, per3, per4));
//            Role role2 = new Role();
//            role2.setName("USER");
//            role2.setPermissions(Set.of(per1));
//
//            User user1 = new User();
//            user1.setUsername("jose");
//            user1.setPassword("1234");
//            user1.setRoles(Set.of(role1));
//
//            User user2 = new User();
//            user2.setUsername("maria");
//            user2.setPassword("1234");
//            user2.setRoles(Set.of(role2));
//
//            userRepository.saveAll(List.of(user1,user2));
//        };
//    }
}