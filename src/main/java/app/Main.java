package app;

import db.DB;
import db.DBIntegrityException;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        Connection conn;
        PreparedStatement st = null;

        try {

            conn = DB.getConnection();

            st = conn.prepareStatement("DELETE FROM department "
                                         + "WHERE Id = ?");

            st.setInt(1, 4);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);


        } catch (SQLException e) {

            throw new DBIntegrityException(e.getMessage());

        } finally {

            DB.closeStatement(st);
            DB.closeConnection();

        }

    }

}