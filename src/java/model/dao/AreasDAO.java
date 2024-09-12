
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Areas;



public class AreasDAO {
    
    public List<Areas> listar() {
        
        List<Areas> list = new ArrayList();
        
        try{
            
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM areas");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Areas area = new Areas();  
                
                area.setId_area(rs.getInt("id_area"));
                area.setNome(rs.getString("nome"));
                
                list.add(area);
                
            }
            
            rs.close();
            stmt.close();
            conexao.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }
    
}
