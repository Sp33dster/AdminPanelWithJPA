package pl.sda.lodz9.adminPanel.servlets;

import pl.sda.lodz9.adminPanel.database.daos.UserDao;
import pl.sda.lodz9.adminPanel.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();

        User userByLogin = UserDao.getUserByLogin(user);
        if (userByLogin.getLogin() == null){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            writer.println("<font color = red> Użytkownik o podanym adresie email nie istnieje. </font>");

            requestDispatcher.include(request,response);
            return;
        }

        if (!userByLogin.getPassword().equals(password)){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            writer.println("<font color = red> Hasło nieprawidłowe. </font>");

            requestDispatcher.include(request,response);
        } else {

            session.setAttribute("user", userByLogin.getLogin());
            session.setMaxInactiveInterval(15 * 60);
            response.sendRedirect("/serverList.jsp");
        }
    }


}
