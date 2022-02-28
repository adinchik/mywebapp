package ru.geekbrains;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    //private static Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        //logger.trace("Log: GET");
        String s = "<html><body><h1>List of products</h1><br>";
        for (int i = 1; i <= 10; i++) {
            Product product = new Product(i, "product#" + i , i * 1000);
            s += product.toString() + "<br>";
        }
        s += "</body></html>";
        httpServletResponse.getWriter().printf(s);
    }
}