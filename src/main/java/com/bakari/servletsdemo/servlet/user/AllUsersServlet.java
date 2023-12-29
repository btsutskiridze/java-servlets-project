package com.bakari.servletsdemo.servlet.user;


import com.bakari.servletsdemo.entity.User;
import com.bakari.servletsdemo.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "userServlet", value = "/all-users")
public class AllUsersServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = this.userService.getUsers();

        if(users != null){
            req.setAttribute("users", users);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("users.jsp");
        dispatcher.forward(req, resp);
    }
}
