package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.User;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet  {

    UserService userService = new UserService();

    @Override
    public void doPost(HttpServletRequest Request,
                       HttpServletResponse Response )throws IOException{
        Integer Id = Integer.parseInt(Request.getParameter("Id"));
        String name = Request.getParameter("name");
        String email = Request.getParameter("email");
        String mobile = Request.getParameter("mobile");

        if(Id == null || name == null || email == null || mobile == null){
            //return 404
            Response.setStatus(400);
            Response.setContentType("application/json");
            Response.getWriter().write(
                    "{\n" +
                            "\"message\":\"not found\"\n" +
                            "\n" +
                            "}"
            );
        }
        User user = new User(Id, name , email,mobile);
        User createdUser = userService.createUser(user);
        Response.setStatus(201);
        Response.setContentType("application/json");
        Response.getWriter().write(
                "{\n" +
                        "\"message\":\"user added\"\n" +
                        "\n" +
                        "}"
        );
    }
    @Override
    public void doGet(HttpServletRequest Request,
                      HttpServletResponse Response) throws IOException{
        String idPara = Request.getParameter("id");
        if(idPara == null){
            List<User> userList = userService.getAllUser();
        }
        Integer id = Integer.parseInt(idPara);

        User userResp = userService.getUserById(id);

        if(userResp == null){
            Response.setStatus(404);
            Response.setContentType("application/json");
        }

        Response.setStatus(201);
        Response.setContentType("application/json");
        Response.getWriter().write(
                "{\n" +
                        " \"id\": " + userResp.getId()+ "\n" +
                        "\"name\": " + userResp.getName() + ",\n" +
                        "\"email\":" + userResp.getEmail() + "\n" +
                        "\"mobile\":" + userResp.getMobile() + " \n" +
                        "\n" +
                        "}"
        );

    }
    @Override
    public void doPut(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse){


    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse)
    {
        
    }
}
