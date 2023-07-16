package br.ufsm.csi.dao;

import br.ufsm.csi.model.Atividade;

import java.sql.*;
import java.util.ArrayList;

public class AtividadeDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/clube";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static ArrayList<Atividade> getAtividadesByUserId(int userId) {
        ArrayList<Atividade> atividades = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM atividade WHERE id_socio = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idAtividade = resultSet.getInt("id_atividade");
                String nomeAtividade = resultSet.getString("nome_atividade");
                String dia = resultSet.getString("dia");
                String hora = resultSet.getString("hora");

                Atividade atividade = new Atividade(idAtividade, nomeAtividade, dia, hora, userId);
                atividades.add(atividade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atividades;
    }

    public static void adicionarAtividade(Atividade atividade) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO atividade (nome_atividade, dia, hora, id_socio) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, atividade.getNome());
            statement.setString(2, atividade.getDia());
            statement.setString(3, atividade.getHora());
            statement.setInt(4, atividade.getIdSocio());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}