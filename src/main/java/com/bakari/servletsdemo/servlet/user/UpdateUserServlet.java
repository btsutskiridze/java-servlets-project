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

@WebServlet(name = "updateUserServlet", value = "/update-user")
public class UpdateUserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = this.userService.getUserById(req.getParameter("id"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("update-user.jsp");
        HttpSession session = req.getSession();

        session.setAttribute("user", user);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = this.userService.validateRequest(req, resp);

        if (user == null) return;

        int status = this.userService.updateUser(user);

        this.setSessionAttributes(status, req.getSession());


        resp.sendRedirect("/all-users");
    }

    private void setSessionAttributes(int status, HttpSession session) {
        if (status == -1) {
            session.setAttribute("error", "Couldn't update the user");
        } else {
            session.setAttribute("success", "User updated successfully");
        }
    }

}
