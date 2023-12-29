package com.bakari.servletsdemo.servlet.user;

import com.bakari.servletsdemo.entity.User;
import com.bakari.servletsdemo.service.UserService;
import com.github.javafaker.Faker;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddUserServletTest {

    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getRequestDispatcher("add-user.jsp")).thenReturn(dispatcher);

        new AddUserServlet().doGet(request, response);

        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("add-user.jsp");
        Mockito.verify(dispatcher, Mockito.times(1)).forward(request, response);
    }

    @Test
    public void testDoPost() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        UserService userService = Mockito.mock(UserService.class);
        User user = new User();

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("username")).thenReturn(Faker.instance().name().username());
        Mockito.when(request.getParameter("email")).thenReturn(Faker.instance().internet().emailAddress());
        Mockito.when(userService.validateRequest(request, response)).thenReturn(user);

        new AddUserServlet().doPost(request, response);

        Mockito.verify(request, Mockito.times(1)).getSession();
        Mockito.verify(session, Mockito.times(1)).setAttribute(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(response, Mockito.times(1)).sendRedirect("/add-user");
    }
}
