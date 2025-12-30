package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class jdbc_pro5 {

    String driver = "oracle.jdbc.OracleDriver";
    String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbuname = "SUREND";
    String dbpwd = "Pass@2000";

    Scanner sc = new Scanner(System.in);

    Connection connect() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dburl, dbuname, dbpwd);
            System.out.println("Connected to the DataBase");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    void meth1() {
        Connection con = connect();
        try {
            PreparedStatement pstm1 = con.prepareStatement("insert into patient values(?,?,?,?)");
            PreparedStatement pstm2 = con.prepareStatement("select * from patient");
            PreparedStatement pstm3 = con.prepareStatement("select * from patient where pid=?");
            PreparedStatement pstm4 = con.prepareStatement("update patient set age=? where pid=?");
            PreparedStatement pstm5 = con.prepareStatement("delete from patient where pid=?");

            while (true) {
                System.out.println("\nWelcome to Patient DataBase");
                System.out.println("1) Add Patient Details");
                System.out.println("2) View Patient Details");
                System.out.println("3) Retrieve Patient Details");
                System.out.println("4) Update Patient Details");
                System.out.println("5) Delete Patient Details");
                System.out.println("6) Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.println("\nEnter Patient ID:");
                        String pid = sc.nextLine();

                        System.out.println("Enter Patient Name:");
                        String pname = sc.nextLine();

                        System.out.println("Enter Patient Age:");
                        int age = Integer.parseInt(sc.nextLine());

                        System.out.println("Enter Patient Contact No:");
                        long contact = sc.nextLong();

                        pstm1.setString(1, pid);
                        pstm1.setString(2, pname);
                        pstm1.setInt(3, age);
                        pstm1.setLong(4, contact);

                        int rc = pstm1.executeUpdate();
                        System.out.println(rc > 0 ? "Data Inserted Successfully" : "Insert Failed");
                        break;

                    case 2:
                        ResultSet rs = pstm2.executeQuery();
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + " " +
                                               rs.getString(2) + " " +
                                               rs.getInt(3) + " " +
                                               rs.getLong(4));
                        }
                        break;

                    case 3:
                        System.out.println("Enter Patient ID:");
                        String pid1 = sc.nextLine();

                        pstm3.setString(1, pid1);
                        ResultSet rs1 = pstm3.executeQuery();

                        if (rs1.next()) {
                            System.out.println(rs1.getString(1) + " " +
                                               rs1.getString(2) + " " +
                                               rs1.getInt(3) + " " +
                                               rs1.getLong(4));
                        } else {
                            System.out.println("No record found!");
                        }
                        break;

                    case 4:
                        System.out.println("Enter Patient ID:");
                        String pid2 = sc.nextLine();

                        System.out.println("Enter New Age:");
                        int age1 = Integer.parseInt(sc.nextLine());

                        pstm4.setInt(1, age1);
                        pstm4.setString(2, pid2);

                        int rc1 = pstm4.executeUpdate();
                        System.out.println(rc1 > 0 ? "Data Updated Successfully" : "Update Failed");
                        break;

                    case 5:
                        System.out.println("Enter Patient ID:");
                        String pid3 = sc.nextLine();

                        pstm5.setString(1, pid3);
                        int rc2 = pstm5.executeUpdate();
                        System.out.println(rc2 > 0 ? "Data Deleted Successfully" : "Delete Failed");
                        break;

                    case 6:
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jdbc_pro5 obj = new jdbc_pro5();
        obj.meth1();
    }
}
