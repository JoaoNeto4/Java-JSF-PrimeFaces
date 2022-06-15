
package br.com.carros.entidade;

import br.com.carros.exception.ErroSistema;
import br.com.carros.util.FabricaConexao;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarroDAO  implements CrudDAO<Carro>{
    
    public void salvar(Carro carro) throws ErroSistema{
        try {
            //nao precisa de "new" pois o metodo é do tipo "static"
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(carro.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO carros(modelo, fabricante, cor, ano)VALUES(?, ?, ?, ?)");
            }else{
                ps = conexao.prepareStatement("UPDATE carros SET modelo=?, fabricante=?, cor=?, ano=? WHERE id=?");
                ps.setInt(5, carro.getId());
            }
            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getFabricante());
            ps.setString(3, carro.getCor());
            //ps.setDate(4, new Date(carro.getAno().getTime()));
            ps.setInt(4, carro.getAno());
            ps.executeUpdate();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar Carro", ex);
            //Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletar(Carro carro) throws ErroSistema{
        try {
            Connection con = FabricaConexao.getConexao();
            PreparedStatement ps = con.prepareStatement("DELETE FROM carros WHERE id=?");
            ps.setInt(1, carro.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar Carro", ex);
        }
        
    }
    
    public List<Carro> buscar()throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM carros");
            ResultSet rs = ps.executeQuery();
            List<Carro> carros = new ArrayList<>();
            while(rs.next()){
                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setModelo(rs.getString("modelo"));
                carro.setFabricante(rs.getString("fabricante"));
                carro.setCor(rs.getString("cor"));
                carro.setAno(rs.getInt("ano"));
                carros.add(carro);
            }
            FabricaConexao.fecharConexao();
            return carros;
        } catch (SQLException ex) {
            throw new ErroSistema("Erroa o Buscar os Carros", ex);
        }
        
    }

}
