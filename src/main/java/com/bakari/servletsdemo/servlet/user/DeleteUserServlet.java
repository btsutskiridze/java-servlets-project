package com.bakari.servletsdemo.servlet.user;

import com.bakari.servletsdemo.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "deleteUserServlet", value = "/delete-user")
public class DeleteUserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        int status = this.userService.deleteUserById(id);

        if(status == -1){
            req.getSession().setAttribute("error", "Couldn't delete user");
        }

        resp.sendRedirect("/all-users");
    }
}
