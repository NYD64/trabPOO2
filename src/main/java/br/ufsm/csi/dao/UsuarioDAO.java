package br.ufsm.csi.dao;

import br.ufsm.csi.model.Cargo;
import br.ufsm.csi.model.Usuario;

import java.sql.*;

public class UsuarioDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    PreparedStatement preparedStatement;
    private String status;

    public Usuario autenticar (String email, String senha) {

        System.out.println("autenticar .... ");
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT id_usuario FROM usuario WHERE email = ? AND senha = ? ";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.preparedStatement.setString(2, senha);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println("vai executar a consulta " + this.resultSet);
            while (this.resultSet.next()) {
                System.out.println("id_usuario = " + this.resultSet.getInt("id_usuario"));
                return getUsuario(this.resultSet.getInt("id_usuario"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Usuario getUsuario(int id){
        Usuario usuario = new Usuario();
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "select * from usuario, usuario_cargo, cargo where usuario.id_usuario = usuario_cargo.id_usuario and cargo.cod_cargo = usuario_cargo.cod_cargo and usuario.id_usuario = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){

                usuario.setId(this.resultSet.getInt("id_usuario"));
                usuario.setNome(this.resultSet.getString("nome"));
                usuario.setEmail(this.resultSet.getString("email"));
                usuario.setAtivo(this.resultSet.getBoolean("ativo"));
                usuario.setSenha(this.resultSet.getString("senha"));

                Cargo cargo = new Cargo();
                cargo.setId(this.resultSet.getInt("cod_cargo"));
                cargo.setNome(this.resultSet.getString("nome_cargo"));
                usuario.setCargo(cargo);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return usuario;
    }
    public String editar(Usuario usuario, Connection connection) throws SQLException{

        this.sql = "update usuario set nome = ?, email = ?, senha = ? where id_usuario = ?;";
        this.preparedStatement = connection.prepareStatement(this.sql);
        this.preparedStatement.setString(1, usuario.getNome());
        this.preparedStatement.setString(2, usuario.getEmail());
        this.preparedStatement.setString(3, usuario.getSenha());
        this.preparedStatement.setInt(4, usuario.getId());
        preparedStatement.executeUpdate();

        if(this.preparedStatement.getUpdateCount() > 0){
            this.status = "OK";
        }

        return this.status;
    }
}