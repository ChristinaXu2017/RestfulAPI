/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.qut.test_bairu;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bairu
 */
public class myslqaccessgff3 {
    Connection conn = null;
    Statement stmt = null;

    public static void main(String[] args) {
        String fdsf = (new myslqaccessgff3()).accesssql("nbL00g00050.1");
        System.out.println(fdsf);
    }

    public myslqaccessgff3() {
        // Call the init method to initialize the connection and statement
        init();
    }

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://resmysql02.qut.edu.au/benth_2023", "an_rw", "P.waterh0use!!");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(myslqaccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(myslqaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String accesssql(String gene_name) {
        String returned_string = "";

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM gff3 WHERE attributes LIKE '%" + gene_name + "%'");

            String[] splitattributes;
            String[] temp;
            Boolean no_note = true;

            while (rs.next()) {
                splitattributes = rs.getString("attributes").split(";");
                for (String i : splitattributes) {
                    temp = i.split("=");
                    if (temp[0].equals("ID")) {
                        returned_string += temp[1];
                        returned_string += "=";
                    } else if (temp[0].equals("Note")) {
                        returned_string += temp[1];
                        returned_string += ";";
                        no_note = false;
                    }
                }
                if (no_note) {
                    returned_string += "NA";
                    returned_string += ";";
                } else {
                    no_note = true;
                }

                System.out.println("The attribute is: " + rs.getString("attributes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(returned_string);
        return returned_string;
    }
}

