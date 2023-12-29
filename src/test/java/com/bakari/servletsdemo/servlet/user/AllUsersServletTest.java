package com.bakari.servletsdemo.servlet.user;

import com.bakari.servletsdemo.entity.User;
import com.bakari.servletsdemo.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class AllUsersServletTest {

    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        UserService userService = Mockito.mock(UserService.class);

        List<User> users = new UserService().getUsers();

        Mockito.when(userService.getUsers()).thenReturn(users);
        Mockito.when(request.getRequestDispatcher("users.jsp")).thenReturn(dispatcher);

        new AllUsersServlet().doGet(request, response);

        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("users.jsp");
        Mockito.verify(dispatcher, Mockito.times(1)).forward(request, response);

    }
}
