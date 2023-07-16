package br.ufsm.csi.dao;

import br.ufsm.csi.model.Cargo;
import br.ufsm.csi.model.Instrutor;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class InstrutorDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Instrutor> getInstrutores(){
        ArrayList<Instrutor> instrutores = new ArrayList<>();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "select * from instrutor, usuario, usuario_cargo, cargo where instrutor.id_usuario = usuario.id_usuario and usuario.id_usuario = usuario_cargo.id_usuario and usuario_cargo.cod_cargo = cargo.cod_cargo";

            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){

                Usuario usuario = new Usuario();
                usuario.setId(this.resultSet.getInt("id_usuario"));
                usuario.setNome(this.resultSet.getString("nome"));
                usuario.setEmail(this.resultSet.getString("email"));
                usuario.setAtivo(this.resultSet.getBoolean("ativo"));

                Cargo cargo = new Cargo();
                cargo.setId(this.resultSet.getInt("cod_cargo"));
                cargo.setNome(this.resultSet.getString("nome_cargo"));
                usuario.setCargo(cargo);

                int idinstrutor = this.resultSet.getInt("id_instrutor");
                String cracha = this.resultSet.getString("cracha");
                Instrutor i = new Instrutor(idinstrutor, usuario, cracha);

                instrutores.add(i);
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = "ERROR";
        }

        return instrutores;
    }

    public String cadastrar(Instrutor instrutor){

        try(Connection connection = new ConectaDB().getConexao()){

            connection.setAutoCommit(false);

            this.sql = "insert into usuario (nome, email, senha, data_cadastro, ativo) values (?, ?, ?, current_date, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, preparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, instrutor.getUsuario().getNome());
            this.preparedStatement.setString(2, instrutor.getUsuario().getEmail());
            this.preparedStatement.setString(3, instrutor.getUsuario().getSenha());
            this.preparedStatement.setBoolean(4, instrutor.getUsuario().isAtivo());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                instrutor.getUsuario().setId(this.resultSet.getInt(1));
                this.status = "OK";
            }

            if(this.status.equals("OK")){
                this.sql = "insert into usuario_cargo (id_usuario, cod_cargo) values (?, ?);";

                this.preparedStatement = connection.prepareStatement(this.sql, preparedStatement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setInt(1, instrutor.getUsuario().getId());
                this.preparedStatement.setInt(2, instrutor.getUsuario().getCargo().getId());
                this.preparedStatement.execute();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                this.resultSet.next();

                if(this.resultSet.getInt(1) > 0){
                    this.status = "OK";
                }

                if (this.status.equals("OK")) {
                    this.sql = "insert into instrutor (id_usuario, cracha) values (?, ?);";
                    this.preparedStatement = connection.prepareStatement(this.sql);
                    this.preparedStatement.setInt(1, instrutor.getUsuario().getId());
                    this.preparedStatement.setString(2,instrutor.getCracha());
                    boolean retorno = this.preparedStatement.execute();
                    System.out.println("retorno --> "+retorno);
                    connection.commit();
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = "ERROR";
        }

        return this.status;
    }

    public String editar(Instrutor i) {

        try(Connection connection = new ConectaDB().getConexao()){

            connection.setAutoCommit(false);

            String retorno = new UsuarioDAO().editar(i.getUsuario(), connection);
            if(retorno.equals("OK")) {

                this.sql = "update instrutor set cracha = ? where id_instrutor = ?;";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, i.getCracha());
                this.preparedStatement.setInt(2, i.getId());
                this.preparedStatement.executeUpdate();

                if(this.preparedStatement.getUpdateCount() > 0){
                    this.status = "OK";
                    connection.commit();
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = "OK";
        }

        return this.status;
    }

    public String excluir(Instrutor instrutor) {
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            // Excluir o instrutor
            this.sql = "DELETE FROM instrutor WHERE id_instrutor = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, instrutor.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            this.status = "OK";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERROR";
        }
        return this.status;
    }

    public Instrutor getInstrutor(int id){
        Instrutor i = null;

        try (Connection connection = new ConectaDB().getConexao()){

            this.sql = "select * from instrutor where id_instrutor = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                String cracha = this.resultSet.getString("cracha");
                int id_usuario = this.resultSet.getInt("id_usuario");
                i = new Instrutor(id, new UsuarioDAO().getUsuario(id_usuario), cracha);
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = "ERROR";
        }

        return i;
    }

}
