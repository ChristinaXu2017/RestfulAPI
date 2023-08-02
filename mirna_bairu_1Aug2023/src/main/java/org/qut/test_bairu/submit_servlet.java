/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qut.test_bairu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;

@WebServlet(name = "submit_servlet", urlPatterns = {"/submit_servlet"})
@MultipartConfig(// when you use FormData in javascriptfff
        fileSizeThreshold = 1024 * 1024 * 1024,
        maxFileSize = 1024 * 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 1024
)
public class submit_servlet extends HttpServlet {

    Connection conn = null;
    Statement stmt = null;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://resmysql02.qut.edu.au/benth_2023", "an_rw", "P.waterh0use!!");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTPMData(String geneName) {
        String tpmData = "";
        try {
            String query5 = "SELECT * FROM tpm WHERE read_tissue_genome = 'NbLab360' AND reference_mRNA_name = ?";
            PreparedStatement pstmt5 = conn.prepareStatement(query5);
            pstmt5.setString(1, geneName);
            ResultSet rs = pstmt5.executeQuery();
            while (rs.next()) {
                float tpm = rs.getFloat("TPM");
                String readTissueName = rs.getString("read_tissue_name");
                float numReads = rs.getFloat("NumReads");
                tpmData += readTissueName + "," + tpm + "," + numReads + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tpmData;
    }

    public String getGFF3Data(String geneName) {
        String gff3Data = "";
        try {
            String query6 = "SELECT * FROM gff3 WHERE attributes LIKE ?";
            PreparedStatement pstmt6 = conn.prepareStatement(query6);
            pstmt6.setString(1, "%" + geneName + "%");
            ResultSet rs = pstmt6.executeQuery();
            while (rs.next()) {
                String attributes = rs.getString("attributes");
                String[] splitAttributes = attributes.split(";");
                boolean noNote = true;
                for (String attr : splitAttributes) {
                    String[] temp = attr.split("=");
                    if (temp[0].equals("ID")) {
                        gff3Data += temp[1] + "=";
                    } else if (temp[0].equals("Note")) {
                        gff3Data += temp[1] + ";";
                        noNote = false;
                    }
                }
                if (noNote) {
                    gff3Data += "NA;";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gff3Data;
    }

    public void close() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    private static float getAverage(ArrayList<Double> list) {
        double sum = 0;
        for (double i: list) {
            System.out.println(i);
            sum += i;
        }
        System.out.println("the sum is: " + sum);
        if (sum == 0) {
            return 0;
        } else {
            return !list.isEmpty() ? (float) sum / list.size() : 0;
        }
    }
    
    private static String getLargest( Map<String, Float> map) {
        double prev = 0.000000;
        String returnedString = null;
        for (Map.Entry<String,Float> entry : map.entrySet()) {
            if (entry.getValue() > prev) {
                returnedString = entry.getKey();
                prev = entry.getValue();
            }
        }
            
        return returnedString;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int image_width = 9000;
        try (PrintWriter out = response.getWriter()) {

            String gene_id = request.getParameter("gene_id");
            String rawdata = getTPMData(gene_id);
            
            if (rawdata.equals("")) {
                //access gff3 table and get all possile searches
                //go back to index and give popup of new searches
                String rawdatagff3 = getGFF3Data(gene_id);
                System.out.println("noimage#" + rawdatagff3);
                out.print(String.format("%04d", image_width));
                out.print("noimage#" + rawdatagff3);

            } else {

                //String rawdata = "flower,0.494373 flower,0.506921 flower,0.121723 flower,0.869756 leaf,0.436271 leaf,0.631758 leaf,0.385063 leaf,0.325525 root,2.01148 root,2.0115 seedl,0.732303 seedl,0.757914 seedl,0.0 stem,0.904243 stem,1.35754 stem,0.124818 stem,1.24729";
                System.out.println(rawdata);
                String[] splitrawdata = rawdata.split(" ");

                //here to reformat the data, first we average the tpm each part of the plant
                ArrayList<Double> flower = new ArrayList();
                ArrayList<Double> leaf = new ArrayList();
                ArrayList<Double> root = new ArrayList();
                ArrayList<Double> seedl = new ArrayList();
                ArrayList<Double> stem = new ArrayList();

                System.out.println("#####");
                for (String i : splitrawdata) {
                    String[] temp = i.split(",");
                    System.out.println(temp[0]);
                    if (temp[0].equals("flower")) {
                        flower.add(Double.valueOf(temp[1]));
                    } else if (temp[0].equals("leaf")) {
                        leaf.add(Double.valueOf(temp[1]));
                    } else if (temp[0].equals("root")) {
                        root.add(Double.valueOf(temp[1]));
                    } else if (temp[0].equals("seedl")) {
                        seedl.add(Double.valueOf(temp[1]));
                    } else if (temp[0].equals("stem")) {
                        stem.add(Double.valueOf(temp[1]));
                    }
                }

                Map<String, Float> averages = new HashMap<>();

                averages.put("flower", getAverage(flower));
                averages.put("leaf", getAverage(leaf));
                averages.put("root", getAverage(root));
                averages.put("seedl", getAverage(seedl));
                averages.put("stem", getAverage(stem));

                Map<String, Integer> sortedaverages = new HashMap<>();
                String largest;

                largest = getLargest(averages);
                if (largest != null) {
                    sortedaverages.put(largest, 100);
                    averages.remove(largest);
                }

                largest = getLargest(averages);
                if (largest != null) {
                    sortedaverages.put(largest, 80);
                    averages.remove(largest);
                }

                largest = getLargest(averages);
                if (largest != null) {
                    sortedaverages.put(largest, 60);
                    averages.remove(largest);
                }

                largest = getLargest(averages);
                if (largest != null) {
                    sortedaverages.put(largest, 40);
                    averages.remove(largest);
                }

                largest = getLargest(averages);
                if (largest != null) {
                    sortedaverages.put(largest, 20);
                    averages.remove(largest);
                }

                BufferedImage container = new BufferedImage(image_width, image_width / 2, BufferedImage.TYPE_INT_ARGB);

                // Get the URL of the image resource
                URL imageUrl = getServletContext().getResource("/image/All_Grey_Plant_wKey.png");

                // Read the image data
                BufferedImage image_background = ImageIO.read(getServletContext().getResource("/image/Outlines6.png"));

//            BufferedImage image_background = ImageIO.read(new File("/image/All_Grey_Plant_wKey.png"));
                BufferedImage image_flowers = null;
                BufferedImage image_leaves = null;
                BufferedImage image_roots = null;
                BufferedImage image_Seedling = null;
                BufferedImage image_stem = null;

                for (Map.Entry<String, Integer> entry : sortedaverages.entrySet()) {
                    if (entry.getKey().equals("flower")) {
                        image_flowers = ImageIO.read(getServletContext().getResource("/image/Flowers_" + entry.getValue() + ".png"));
                    } else if (entry.getKey().equals("leaf")) {
                        image_leaves = ImageIO.read(getServletContext().getResource("/image/Leaves_" + entry.getValue() + ".png"));
                    } else if (entry.getKey().equals("root")) {
                        image_roots = ImageIO.read(getServletContext().getResource("/image/Roots_" + entry.getValue() + ".png"));
                    } else if (entry.getKey().equals("seedl")) {
                        image_Seedling = ImageIO.read(getServletContext().getResource("/image/Seedling_" + entry.getValue() + ".png"));
                    } else if (entry.getKey().equals("stem")) {
                        image_stem = ImageIO.read(getServletContext().getResource("/image/Stem_" + entry.getValue() + ".png"));
                    }
                }

//                Graphics2D g2D = image.createGraphics();
                Graphics2D g2D = container.createGraphics();

                g2D.drawImage(image_background, 0, 0, 4500, 4500, null);
//                g2D.drawImage(image_background, 0, 0, null);
                g2D.drawImage(image_flowers, 0, 0, 4500, 4500, null);
                g2D.drawImage(image_leaves, 0, 0, 4500, 4500, null);
                g2D.drawImage(image_roots, 0, 0, 4500, 4500, null);
                g2D.drawImage(image_Seedling, 0, 0, 4500, 4500, null);
                g2D.drawImage(image_stem, 0, 0, 4500, 4500, null);
                //g2D.fillOval(0, 0, 100, 100);
                g2D.setColor(Color.blue);
                //g2D.fillRect(350, 0, 200, 200);

                averages.put("flower", getAverage(flower));
                averages.put("leaf", getAverage(leaf));
                averages.put("root", getAverage(root));
                averages.put("seedl", getAverage(seedl));
                averages.put("stem", getAverage(stem));

                double sum_of_exposure = averages.get("flower") + averages.get("leaf") + averages.get("root") + averages.get("seedl")
                        + averages.get("stem");
                double current_degree = 0;

                g2D.setColor(Color.blue);
                //System.out.println("degree here");
                //System.out.println(Integer.parseInt(c[1])*360/sum_of_exposure);
                g2D.fill(new Arc2D.Double(4500, 0, 4500, 4500, current_degree, averages.get("flower") * 360 / sum_of_exposure, Arc2D.PIE));
                current_degree = current_degree + averages.get("flower") * 360 / sum_of_exposure;
                g2D.setColor(Color.red);
                g2D.fill(new Arc2D.Double(4500, 0, 4500, 4500, current_degree, averages.get("leaf") * 360 / sum_of_exposure, Arc2D.PIE));
                current_degree = current_degree + averages.get("leaf") * 360 / sum_of_exposure;
                g2D.setColor(Color.CYAN);
                g2D.fill(new Arc2D.Double(4500, 0, 4500, 4500, current_degree, averages.get("root") * 360 / sum_of_exposure, Arc2D.PIE));
                current_degree = current_degree + averages.get("root") * 360 / sum_of_exposure;
                g2D.setColor(Color.MAGENTA);
                g2D.fill(new Arc2D.Double(4500, 0, 4500, 4500, current_degree, averages.get("seedl") * 360 / sum_of_exposure, Arc2D.PIE));
                current_degree = current_degree + averages.get("seedl") * 360 / sum_of_exposure;
                g2D.setColor(Color.PINK);
                g2D.fill(new Arc2D.Double(4500, 0, 4500, 4500, current_degree, averages.get("stem") * 360 / sum_of_exposure, Arc2D.PIE));

                //draw_image(image.getGraphics());
                out.print(String.format("%04d", image_width));
                out.print(encodeToBase64String(container, "png"));
                out.print("#" + rawdata);

//            draw_image(image.getGraphics());
                //send to client
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    void draw_image(Graphics g2d) throws IOException {
        String[] c = readFile().split(",");
        g2d.setColor(new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
        g2d.fillRect(10, 10, 150, 150);
    }

    String readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("c:/Users/bairu/Desktop/plantVisulise/exp_level.txt"));
        //String line = br.readLine();
        String line;
        String returned_line = "";
        while ((line = br.readLine()) != null) {
            String[] columnDetail = new String[2];
            columnDetail = line.split("\t");
            returned_line = returned_line + "," + columnDetail[1];
        }
        br.close();
        //System.out.println(returned_line);
        return returned_line;
    }

    private String encodeToBase64String(BufferedImage image, String type) {
        String encodedString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            encodedString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
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
