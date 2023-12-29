package com.bakari.servletsdemo.servlet.user;


import com.bakari.servletsdemo.entity.User;
import com.bakari.servletsdemo.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "addUserServlet", value = "/add-user")
public class AddUserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("add-user.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = this.userService.validateRequest(req, resp);

        if (user == null) return;

        int status = this.userService.saveUser(user);

        this.setSessionAttributes(status, req.getSession());

        resp.sendRedirect("/add-user");
    }

    private void setSessionAttributes(int status, HttpSession session) {
        if (status == -1) {
            session.setAttribute("error", "Couldn't save the user");
        } else {
            session.setAttribute("success", "User saved successfully");
        }
    }
}
