package br.ufsm.csi.controller;

import br.ufsm.csi.dao.AtividadeDAO;
import br.ufsm.csi.model.Atividade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/atividades-controller")
public class AtividadesController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String nomeAtividade = request.getParameter("nomeAtividade");
        String diaAtividade = request.getParameter("diaAtividade");
        String horaAtividade = request.getParameter("horaAtividade");

        // Criar uma nova atividade com os dados fornecidos
        Atividade novaAtividade = new Atividade(nomeAtividade, diaAtividade, horaAtividade, idUsuario);

        // Adicionar a nova atividade ao banco de dados
        AtividadeDAO.adicionarAtividade(novaAtividade);

        // Redirecionar de volta para a página de atividades com o ID do usuário selecionado
        response.sendRedirect("atividades.jsp?id=" + idUsuario);
    }
}
