package br.ufsm.csi.dao;

import br.ufsm.csi.model.Cargo;
import br.ufsm.csi.model.Socio;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class SocioDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Socio> getSocios(){
        ArrayList<Socio> socios = new ArrayList<>();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "select * from socio, usuario, usuario_cargo, cargo where socio.id_usuario = usuario.id_usuario and usuario.id_usuario = usuario_cargo.id_usuario and usuario_cargo.cod_cargo = cargo.cod_cargo";

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

                int idsocio = this.resultSet.getInt("id_socio");
                String carteiraClube = this.resultSet.getString("carteiraClube");
                Socio s = new Socio(idsocio, usuario, carteiraClube);

                socios.add(s);
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = "ERROR";
        }

        return socios;
    }

    public String cadastrar(Socio socio){

        try(Connection connection = new ConectaDB().getConexao()){

            connection.setAutoCommit(false);

            this.sql = "insert into usuario (nome, email, senha, data_cadastro, ativo) values (?, ?, ?, current_date, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, preparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, socio.getUsuario().getNome());
            this.preparedStatement.setString(2, socio.getUsuario().getEmail());
            this.preparedStatement.setString(3, socio.getUsuario().getSenha());
            this.preparedStatement.setBoolean(4, socio.getUsuario().isAtivo());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                socio.getUsuario().setId(this.resultSet.getInt(1));
                this.status = "OK";
            }

            if(this.status.equals("OK")){
                this.sql = "insert into usuario_cargo (id_usuario, cod_cargo) values (?, ?);";

                this.preparedStatement = connection.prepareStatement(this.sql, preparedStatement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setInt(1, socio.getUsuario().getId());
                this.preparedStatement.setInt(2, socio.getUsuario().getCargo().getId());
                this.preparedStatement.execute();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                this.resultSet.next();

                if(this.resultSet.getInt(1) > 0){
                    this.status = "OK";
                }

                if (this.status.equals("OK")) {
                    this.sql = "insert into socio (id_usuario, carteiraClube) values (?, ?);";
                    this.preparedStatement = connection.prepareStatement(this.sql);
                    this.preparedStatement.setInt(1, socio.getUsuario().getId());
                    this.preparedStatement.setString(2,socio.getCarteiraClube());
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

    public String editar(Socio s) {

        try(Connection connection = new ConectaDB().getConexao()){

            connection.setAutoCommit(false);

            String retorno = new UsuarioDAO().editar(s.getUsuario(), connection);
            if(retorno.equals("OK")) {

                this.sql = "update socio set carteiraClube = ? where id_socio = ?;";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, s.getCarteiraClube());
                this.preparedStatement.setInt(2, s.getId());
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

    public String excluir(Socio socio) {
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            // Excluir as atividades do sócio
            this.sql = "DELETE FROM atividade WHERE id_socio = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, socio.getId());
            this.preparedStatement.executeUpdate();

            // Excluir o sócio
            this.sql = "DELETE FROM socio WHERE id_socio = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, socio.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            this.status = "OK";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERROR";
        }
        return this.status;
    }

    public Socio getSocio(int id){
        Socio s = null;

        try (Connection connection = new ConectaDB().getConexao()){

            this.sql = "select * from socio where id_socio = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                String carteiraClube = this.resultSet.getString("carteiraClube");
                int id_usuario = this.resultSet.getInt("id_usuario");
                s = new Socio(id, new UsuarioDAO().getUsuario(id_usuario), carteiraClube);
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = "ERROR";
        }

        return s;
    }

}
