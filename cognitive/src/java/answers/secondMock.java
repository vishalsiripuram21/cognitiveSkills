/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answers;

import connection.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import marksUpdation.UpdateMarks;

/**
 *
 * @author vishal
 */
@WebServlet(name = "secondMock", urlPatterns = {"/secondMock"})
public class secondMock extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int marks = 0;
            String[] answers = {"b", "a", "c", "d", "b", "b", "c", "a", "c", "a", "d", "a", "b", "c", "c"};
            String[] userAnswers = new String[15];
            for (int i = 0; i < 15; i++) {
                userAnswers[i] = "-";
            }
            for (int i = 0; i < 15; i++) {
                if (i < 10) {
                    if (request.getParameter("S2") + (i + 1) != null) {
                        userAnswers[i] = request.getParameter("S2" + (i + 1));
                    }
                } else {
                    if (request.getParameter("S2_") + (i + 1) != null) {
                        userAnswers[i] = request.getParameter("S2_" + (i + 1));
                    }

                }
            }
            if (userAnswers[0] != null && userAnswers[0].equals(answers[0])) {
                marks++;
            }
            if (userAnswers[1] != null && userAnswers[1].equals(answers[1])) {
                marks++;
            }
            if (userAnswers[2] != null && userAnswers[2].equals(answers[2])) {
                marks++;
            }
            if (userAnswers[3] != null && userAnswers[3].equals(answers[3])) {
                marks++;
            }
            if (userAnswers[4] != null && userAnswers[4].equals(answers[4])) {
                marks++;
            }
            if (userAnswers[5] != null && userAnswers[5].equals(answers[5])) {
                marks++;
            }
            if (userAnswers[6] != null && userAnswers[6].equals(answers[6])) {
                marks++;
            }
            if (userAnswers[7] != null && userAnswers[7].equals(answers[7])) {
                marks++;
            }
            if (userAnswers[8] != null && userAnswers[8].equals(answers[8])) {
                marks++;
            }
            if (userAnswers[9] != null && userAnswers[9].equals(answers[9])) {
                marks++;
            }
            if (userAnswers[10] != null && userAnswers[10].equals(answers[10])) {
                marks++;
            }
            if (userAnswers[11] != null && userAnswers[11].equals(answers[11])) {
                marks++;
            }
            if (userAnswers[12] != null && userAnswers[12].equals(answers[12])) {
                marks++;
            }
            if (userAnswers[13] != null && userAnswers[13].equals(answers[13])) {
                marks++;
            }
            if (userAnswers[14] != null && userAnswers[14].equals(answers[14])) {
                marks++;
            }
            String display = "d-none";
            String prevScore = "Your previous Score :";
            String presentScore = "Your present Score:";
            String score = "You Scored :";
            String userans = "Your Answer :";
            String correctAnsw = "Correct Answer:";
            HttpSession s = request.getSession();
            s.setAttribute("display", display);
            s.setAttribute("answers", answers);
            s.setAttribute("userAnswers", userAnswers);
            s.setAttribute("score", score + marks);
            
            if (s.getAttribute("user") == null) {
                response.setHeader("REFRESH","0");
                RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
                rs.forward(request, response);
            } else {
                String user = s.getAttribute("user").toString();
                Connection con = ConnectionProvider.getConnection();
                String usermarks = UpdateMarks.getMarks(con, user, "marks2");
                s.setAttribute("usermarks", usermarks);
                UpdateMarks.updateMarks(con, user, String.valueOf(marks), "update marks set marks2=? where email=?");
                con.close();

                response.sendRedirect("secondMock.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(secondMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(secondMock.class.getName()).log(Level.SEVERE, null, ex);
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
