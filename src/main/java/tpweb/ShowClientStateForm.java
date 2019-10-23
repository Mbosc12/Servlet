/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
public class ShowClientStateForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowClientStateForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowClientStateForm at " + request.getContextPath() + "</h1>");

            try {
                NewDAO dao = new NewDAO(DataSourceFactory.getDataSource());
                List<String> list = dao.liststate();

                String param = request.getParameter("state");

                if (param == null) {
                    list.get(0);
                }
                
                out.println("<form>");

                out.println("<select name='state'>");

                for (String s : list) {

                    out.printf("<option value='%s'>%s</option>%n",
                            s,
                            s);
                }
                out.println("</select>");
                out.println("<input type='submit'>");
                out.println("</form>");

                out.println("<table border=2>");
                out.println("<tr> <th>Id</th> <th>Name</th> <th>Address</th> </tr>");
                
                List<CustomerEntity> cs = dao.customersInState(param);
                
                for (CustomerEntity c : cs) {
                    // Afficher les propriétés du client			
                    out.printf("<tr> <td>%d</td> <td>%s</td> <td>%s</td> </tr>%n",
                            c.getCustomerId(),
                            c.getName(),
                            c.getAddressLine1());
                }
                out.println("</table>");
                
            } catch (DAOException e) {
                out.println("<p>Erreur :" + e.getMessage() + "</p>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {

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
        processRequest(request, response);
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
        processRequest(request, response);
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
