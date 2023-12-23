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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tajma eBay
 */
public class SignUpServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            
            if (createUser(username, password, email)) {
                out.println("SUCCESS");
            } else {
                out.println("FAIL");
            }
            
        }
    }
    
    private boolean createUser(String username, String password, String email) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenSuperMarket", "root", "");
            
            PreparedStatement pst = con.prepareStatement("insert into login (login_users_id, login_username, login_password, login_email) values('0',?,?,?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, email);
            
            int rowsAffected = pst.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
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
