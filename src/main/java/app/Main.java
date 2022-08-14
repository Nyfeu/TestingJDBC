package app;

import db.DB;
import db.DBException;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {

            conn = DB.getConnection();

            conn.setAutoCommit(false);  // transaction logic!

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.printf("\nDone! Rows affected: \n\nRows1: %d\nRows2: %d\n\n",rows1,rows2);


        } catch (SQLException e) {

            try {

                conn.rollback();
                throw new DBException("Transaction rolled back! Caused by: " + e.getMessage());

            } catch (SQLException e1) {

                throw new DBException("Error trying to rollback! Caused by: " + e.getMessage());

            }


        } finally {

            DB.closeStatement(st);
            DB.closeConnection();

        }

    }

}