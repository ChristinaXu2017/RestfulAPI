/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qut.test_bairu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

@WebServlet(name = "submit_servlet_genome", urlPatterns = {"/submit_servlet_genome"})
@MultipartConfig(// when you use FormData in javascriptfff
        fileSizeThreshold = 1024 * 1024 * 1024,
        maxFileSize = 1024 * 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 1024
)
public class submit_servlet_genome extends HttpServlet {

    Connection conn = null;
    Statement stmt = null;

    @Override
    public void init() {
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://resmysql02.qut.edu.au/benth_2023", "an_rw", "P.waterh0use!!");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //"SELECT * FROM lab360_mirna WHERE id = '" + geneName + "'"
    public String getLabData(String geneName) {
        String labData = "";
        try {
            String query1 = "SELECT * FROM lab360_mirna WHERE id = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(query1);
            pstmt1.setString(1, geneName);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                String chr = rs.getString("chr");
                String strand = rs.getString("strand");
                String start = rs.getString("start");
                labData += sequence + "," + precursor_seq + "," + structure
                        + "," + chr + "," + strand + "," + start + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labData;
    }

    public String getLabDataPureNum(String geneName) {
        String labData = "";
        try {
            String query2 = "SELECT * FROM lab360_mirna WHERE pure_number = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, geneName);
            ResultSet rs = pstmt2.executeQuery();
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                String chr = rs.getString("chr");
                String strand = rs.getString("strand");
                String start = rs.getString("start");
                labData += sequence + "," + precursor_seq + "," + structure
                        + "," + chr + "," + strand + "," + start + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labData;
    }

    //"SELECT * FROM qld183_mirna_updated WHERE id = '" + geneName + "'"
    public String getQldData(String geneName) {
        String qldData = "";
        try {
            String query3 = "SELECT * FROM qld183_mirna_updated WHERE id = ?";
            PreparedStatement pstmt3 = conn.prepareStatement(query3);
            pstmt3.setString(1, geneName);
            ResultSet rs = pstmt3.executeQuery();
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                String chr = rs.getString("chr");
                String strand = rs.getString("strand");
                String start = rs.getString("start");
                qldData += sequence + "," + precursor_seq + "," + structure
                        + "," + chr + "," + strand + "," + start + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qldData;
    }

    public String getQldDataPureNum(String geneName) {
        String qldData = "";
        try {
            String query4 = "SELECT * FROM qld183_mirna_updated WHERE pure_number = ?";
            PreparedStatement pstmt4 = conn.prepareStatement(query4);
            pstmt4.setString(1, geneName);
            ResultSet rs = pstmt4.executeQuery();
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                String chr = rs.getString("chr");
                String strand = rs.getString("strand");
                String start = rs.getString("start");
                qldData += sequence + "," + precursor_seq + "," + structure
                        + "," + chr + "," + strand + "," + start + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qldData;
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

    public static BufferedImage createDiagram(String input) {

        String[] splitbyspace = input.split(" ");

        int width = 2000;
        int height = splitbyspace.length * (150 + 15);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        for (int genome = 0; genome < splitbyspace.length; genome++) {

            String[] instances = splitbyspace[genome].split(",");

            String red_sequence = instances[0].replaceAll("\\(.*?\\)", "").toUpperCase().replaceAll("U", "T");

            String sequence = instances[1].toUpperCase();
            String symbols = instances[2].replaceAll("\\s", "");
            
            if (sequence.length() > width) {
                width = sequence.length();
            }

            String chr = instances[3];
            String strand = instances[4];
            int start = Integer.parseInt(instances[5]);
            int end = start + sequence.length();

            // Set the font for drawing the symbols
            Font font = new Font("Arial", Font.PLAIN, 12);
            g2d.setFont(font);

            // Set the color for drawing the text
            Color blackColor = Color.BLACK;
            Color redColor = Color.RED;

            g2d.setColor(blackColor);

            int width_counter = 0;
            g2d.drawString("chr: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("chr: ");
            g2d.drawString(chr, width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth(chr) + 20;
            g2d.drawString("Strand: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("Strand: ");
            g2d.drawString(strand, width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth(strand) + 20;
            g2d.drawString("Start: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("Start: ");
            g2d.drawString(Integer.toString(start), width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth(Integer.toString(start)) + 20;
            g2d.drawString("End: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("End: ");
            g2d.drawString(Integer.toString(end), width_counter, genome * 165 + 9 * 15);

// Find the starting and ending indices of the shared section
            int startIndex = sequence.indexOf(red_sequence);
            int endIndex = startIndex + red_sequence.length();

            if (startIndex > -1) {
// Draw the black portion before the shared section
                g2d.setColor(blackColor);
                String beforeSharedSection = sequence.substring(0, startIndex);
                g2d.drawString(beforeSharedSection, 0, genome * 165 + 7 * 15);

// Draw the red shared section
                g2d.setColor(redColor);
                String sharedSection = sequence.substring(startIndex, endIndex);
                g2d.drawString(sharedSection, g2d.getFontMetrics().stringWidth(beforeSharedSection), genome * 165 + 7 * 15);

// Draw the black portion after the shared section
                g2d.setColor(blackColor);
                String afterSharedSection = sequence.substring(endIndex);
                g2d.drawString(afterSharedSection, g2d.getFontMetrics().stringWidth(beforeSharedSection)
                        + g2d.getFontMetrics().stringWidth(sharedSection), genome * 165 + 7 * 15);
            } else {
                g2d.setColor(blackColor);
                g2d.drawString(sequence, 0, genome * 165 + 7 * 15);
            }

// Draw the symbols
            int i = 0;
            int j = symbols.length() - 1;
            int column_no = 0;
            //g2d.setColor(blackColor);

            char whileholder_i = symbols.charAt(i);
            char whileholder_j = symbols.charAt(j);

            if ((whileholder_i == '.') & (whileholder_j == '.')) {
                int difference = (symbols.length() - symbols.lastIndexOf(')') - 1) - symbols.indexOf('(');
                if (difference < 0) {
                    difference = difference * (-1);
                    while (column_no < difference) {
                        if ((i >= startIndex) & (i < endIndex)) {
                            g2d.setColor(redColor);
                        } else {
                            g2d.setColor(blackColor);
                        }
                        g2d.drawString(String.valueOf(sequence.charAt(i)), 10 * column_no, genome * 165 + 1 * 15);
                        i = i + 1;
                        column_no = column_no + 1;
                    }
                } else if (difference > 0) {
                    while (column_no < difference) {
                        if ((j >= startIndex) & (j < endIndex)) {
                            g2d.setColor(redColor);
                        } else {
                            g2d.setColor(blackColor);
                        }
                        g2d.drawString(String.valueOf(sequence.charAt(j)), 10 * column_no, genome * 165 + 5 * 15);
                        j = j - 1;
                        column_no = column_no + 1;
                    }
                }
            }

            while (i < j) {
                //for (int column_no = 0; column_no < width; column_no++) {
                char symbol_i = symbols.charAt(i);
                char symbol_j = symbols.charAt(j);
                char sequence_i = sequence.charAt(i);
                char sequence_j = sequence.charAt(j);

                if (symbol_i == '.') {
                    //draw sequence.charAt(i) in row 1 column column_no
                    if ((i >= startIndex) & (i < endIndex)) {
                        g2d.setColor(redColor);
                    } else {
                        g2d.setColor(blackColor);
                    }
                    g2d.drawString(String.valueOf(sequence_i), 10 * column_no, genome * 165 + 1 * 15);
                    i = i + 1;
                    if (symbol_j == '.') {
                        //draw sequence.charAt(j) in row 5 column column_no
                        if ((j >= startIndex) & (j < endIndex)) {
                            g2d.setColor(redColor);
                        } else {
                            g2d.setColor(blackColor);
                        }
                        g2d.drawString(String.valueOf(sequence_j), 10 * column_no, genome * 165 + 5 * 15);
                        j = j - 1;
                    }
                } else if ((symbol_i == '(') & (symbol_j == ')')) {
                    //draw sequence.charAt(i) in row 2 column column_no
                    if ((i >= startIndex) & (i < endIndex)) {
                        g2d.setColor(redColor);
                    } else {
                        g2d.setColor(blackColor);
                    }
                    g2d.drawString(String.valueOf(sequence_i), 10 * column_no, genome * 165 + 2 * 15);
                    //draw sequence.charAt(j) in row 4 column column_no
                    if ((j >= startIndex) & (j < endIndex)) {
                        g2d.setColor(redColor);
                    } else {
                        g2d.setColor(blackColor);
                    }
                    g2d.drawString(String.valueOf(sequence_j), 10 * column_no, genome * 165 + 4 * 15);
                    //draw | in row 3 column column_no
                    g2d.setColor(blackColor);
                    g2d.drawString(String.valueOf('|'), 10 * column_no, genome * 165 + 3 * 15);
                    i = i + 1;
                    j = j - 1;

                } else if ((symbol_i == '(') & (symbol_j == '.')) {
                    //draw sequence.charAt(j) in row 5 column column_no
                    if ((j >= startIndex) & (j < endIndex)) {
                        g2d.setColor(redColor);
                    } else {
                        g2d.setColor(blackColor);
                    }
                    g2d.drawString(String.valueOf(sequence_j), 10 * column_no, genome * 165 + 5 * 15);
                    j = j - 1;
                }

                // Draw the symbol
                //g2d.drawString(String.valueOf(sequence.charAt(i)), i, row);
                column_no = column_no + 1;
            }
        }

        g2d.dispose();

        return image;
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int image_width = 9000;
        try ( PrintWriter out = response.getWriter()) {

            String gene_id = request.getParameter("gene_id");
            String toggle_switch = request.getParameter("toggle_switch");
            
            String rawdatalab, rawdataqld;
            if (gene_id.length() > 3) {
                if (gene_id.substring(0, 3).equals("mir")) {
                    rawdatalab = getLabDataPureNum(gene_id.substring(3));
                    rawdataqld = getQldDataPureNum(gene_id.substring(3));
                } else {
                    rawdatalab = getLabData(gene_id);
                    rawdataqld = getQldData(gene_id);
                }
            } else {
                rawdatalab = getLabData(gene_id);
                rawdataqld = getQldData(gene_id);
            }

            if (rawdatalab.equals("")) {
                //access gff3 table and get all possile searches
                //go back to index and give popup of new searches
                System.out.println("noimage#" + rawdataqld);
                out.print(String.format("%04d", image_width));
                out.print("noimage#" + rawdataqld);

            } else {

                System.out.println(rawdatalab);
                //String[] splitrawdatalab = rawdatalab.split(",");
                BufferedImage image;
                if (toggle_switch.equals("qld")){
                    image = createDiagram(rawdataqld);
                } else {
                    image = createDiagram(rawdatalab);
                }
                
                //String input = "CtgacagaagagagagagcaC(CTGACAGAAGAGAGTGAGCAC),ttggaattcatagaGTTTGCTCTTTGATCATGTCcgtctctctgcatttcaggaacactaactctgggagattttgcaagattatgatgatcatctttaatgtaggcatgatcacctcctttaaccatccacggcagagttggtgaaatgccggttgaattgtgcagaaggttttgtgagtgggCtgacagaagagagagagcaCaagag,..............((((.((((((..(((.(((((.((((((((((((((((..(((((((((((....................................................................))))))))))).....))...))))..))))))).........)))))))))))..))))))...)))).......";
                //BufferedImage image = createDiagram(input);
                
                out.print(String.format("%04d", image_width));
                out.print(encodeToBase64String(image, "png"));
                out.print("#" + rawdatalab);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
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
