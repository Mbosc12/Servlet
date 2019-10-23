/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpweb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/**
 *
 * @author pedago
 */
public class NewDAO extends DAO {

    public NewDAO(DataSource dataSource) {
        super(dataSource);
    }

    public List<String> liststate() throws DAOException {
        List<String> list = new LinkedList<>();
        String sql = "SELECT DISTINCT STATE FROM CUSTOMER";
        
        try (Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(rs.getString("STATE"));
                
            }
            
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
        
        return list;
    }

}
