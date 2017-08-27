package batchExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.DBUtil;

public class ManipulaDados {
	Connection con;
	double soma = 0.0;
	public ManipulaDados() {
		DBUtil db = DBUtil.getInstance();
		con = db.getConnection();
	}
	
	
	public void inserirDados(){
		String sql = "insert into tb_customer_account (cpf_cnpj, nm_customer,vl_total) values (?,?,?) ";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			for(int i = 0; i < 4000; i++){
				stmt.setLong(1, 11111111111L);
				stmt.setString(2, "Cliente: " + i);
				double vl = 1 + Math.random() * 2700;					
				stmt.setDouble(3, Math.round(vl));
				stmt.addBatch();
			}
			stmt.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double calculaMedia() throws SQLException{
		String sql = "select vl_total, nm_customer " 
				+ "from tb_customer_account"  
				+ " where vl_total > 560 and id_customer between 1500 and 2700" 
				+ " order by vl_total desc";
		try(PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){
			int linha = 0;
			while(rs.next()){
				soma += rs.getDouble(1);
				linha = rs.getRow();
				
			}
			System.out.println(linha);
			soma /= linha;
		}
				
		return soma;
	}
	
	public void exibirClientes() throws SQLException{
		String sql = "select * " 
				+ "from tb_customer_account"  
				+ " where vl_total > 560 and id_customer between 1500 and 2700" 
				+ " order by vl_total desc";
		try(PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){
			
			while(rs.next()){
				System.out.println("Nome: " + rs.getString("nm_customer"));
				System.out.println("CPF: " + rs.getString("cpf_cnpj"));
				if(rs.getInt("is_active") == 1)
					System.out.println("Ativo");
				else	System.out.println("Inativo");
			}
		}
				
	}
	
}