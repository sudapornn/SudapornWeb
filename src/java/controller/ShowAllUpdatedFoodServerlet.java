/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Addfood;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ShowAllUpdatedFoodServerlet
 */
@WebServlet(name = "ShowAllUpdatedFoodServerlet", urlPatterns = {"/ShowAllUpdatedFoodServerlet"})
public class ShowAllUpdatedFoodServerlet extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        DBConnection dbConnection = new DBConnection();
        List<Addfood> foods = dbConnection.getAllFoods();  // ใช้ List<Addfood> แทน Addfood

        JSONArray jsonArray = new JSONArray();

        if (foods != null && !foods.isEmpty()) {
            for (Addfood food : foods) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", food.getFoodname());
                jsonObject.put("type", food.getType());
                jsonObject.put("crispness", food.getCrispness());
                jsonObject.put("hotlevel", food.getHotlevel());
                jsonObject.put("sauce", food.getSauce());
                jsonObject.put("price", food.getFoodPrice());
                jsonArray.put(jsonObject);
            }
        } else {
            System.out.println("No foods found");
        }

        try (PrintWriter out = response.getWriter()) {
            System.out.println("JSON Output: " + jsonArray.toString()); // ตรวจสอบข้อมูลที่พิมพ์ลงคอนโซล
            out.print(jsonArray.toString());
            out.flush();
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
