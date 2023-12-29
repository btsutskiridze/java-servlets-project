package com.bakari.servletsdemo.service;

import com.bakari.servletsdemo.entity.User;
import com.bakari.servletsdemo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class UserService {

    private final UserRepository userRepository = new UserRepository();
    public int saveUser(User user) {
        return this.userRepository.saveUser(user);
    }

    public int updateUser(User user) {
        return this.userRepository.updateUser(user);
    }

    public int deleteUserById(String id) {
        return this.userRepository.deleteUserById(Long.parseLong(id));
    }

    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

    public User getUserById(String id) {
        return this.userRepository.getUserById(Long.parseLong(id));
    }

    public User validateRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        if (email.isEmpty() || username.isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute("error", "Username and email cannot be empty");
            res.sendRedirect("/add-user");
            return null;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        if (id != null) {
            user.setId((Long.parseLong(id)));
        }

        return user;
    }

}
