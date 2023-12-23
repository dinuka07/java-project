/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tajma eBay
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }


   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            User user = validateLogin(username, password);
            
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("loginId", user.getLoginId());
                
                Cookie loginIdCookie = new Cookie("loginId", String.valueOf(user.getLoginId()));
                Cookie usernameCookie = new Cookie("username", String.valueOf(user.getUsername()));
                
                loginIdCookie.setMaxAge(24 * 60 * 60);
                usernameCookie.setMaxAge(24 * 60 * 60);
                
                response.addCookie(loginIdCookie);
                response.addCookie(usernameCookie);
                
                response.addCookie(loginIdCookie);
                response.addCookie(usernameCookie);
                
                response.sendRedirect("/loginPage/home_page/index.html");
            } else {
                out.println("Invalid Login Credentials");
            }
        }
    }
    
    private User validateLogin(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenSuperMarket", "root", "");
            
            PreparedStatement pst = con.prepareStatement("SELECT * FROM login l where l.login_username=? and l.login_password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("login_username"));
                user.setLoginId(rs.getString("login_id"));
                return user;
            } else {
                return null;
            }
                  
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public class User {
        private String username;
        private String loginId;
        
        public User() {
        }
        
        public String getUsername() {
            return username;
        }
        
        public void setUsername(String username) {
            this.username = username;
        }
        
        public String getLoginId() {
            return loginId;
        }
        
        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
