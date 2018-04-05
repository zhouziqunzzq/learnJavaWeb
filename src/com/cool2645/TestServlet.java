package com.cool2645;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class TestServlet extends javax.servlet.http.HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Hello Java Servlet!");
    }

    @Override
    public void destroy() {
        System.out.println("Bye Java Servlet");
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String user = request.getParameter("name");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>TestServlet</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Hello, " + (user == null || user.isEmpty() ? "Anonymous" : user) + "." +
                "</h2>\n" +
                "</body>\n" +
                "</html>"
        );
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        // Test Servlet Config
        String username = getInitParameter("username");
        // Test Servlet Context read from .properties
        InputStream is = getServletContext().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(is);
        String username1 = properties.getProperty("username");
        // Write out response
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>" + getServletContext().getInitParameter("WebsiteName") + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>This is " + this.getClass() + "" +
                ", using GET method.</h2>\n" +
                "<h2>Username(Config) = " + username + "</h2>\n" +
                "<h2>Username(Context) = " + username1 + "</h2>\n" +
                "<form action=\"TestServlet\" method=\"post\">\n" +
                "<input type=\"text\" placeholder=\"Name\" name=\"name\" />\n" +
                "<button type=\"submit\">Submit</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n"
        );
        out.flush();
        out.close();
    }
}
