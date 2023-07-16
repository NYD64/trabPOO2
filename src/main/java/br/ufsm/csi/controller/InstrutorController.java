package br.ufsm.csi.controller;

import br.ufsm.csi.dao.InstrutorDAO;
import br.ufsm.csi.dao.SocioDAO;
import br.ufsm.csi.model.Cargo;
import br.ufsm.csi.model.Instrutor;
import br.ufsm.csi.model.Socio;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("instrutor-controller")
public class InstrutorController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = "/";

        InstrutorDAO dao = new InstrutorDAO();
        String retorno = "";
        String opcao = req.getParameter("opcao");

        if(opcao.equals("excluir")){
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("id instrutor excluir: " + id);
            Instrutor instrutor = new InstrutorDAO().getInstrutor(id);

            retorno = dao.excluir(instrutor);
            req.setAttribute("retorno", retorno);

        }else if(opcao.equals("editar")){
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("id instrutor editar: "+id);
            Instrutor instrutor = new InstrutorDAO().getInstrutor(id);

            req.setAttribute("instrutor", instrutor);

        }else {


            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            String cracha = req.getParameter("cracha");

            Cargo c = new Cargo(3, "INSTRUTOR");
            Usuario usuario = new Usuario(nome, email, senha, true, c);
            int id = Integer.parseInt(req.getParameter("idinstrutor"));

            if(id > 0){

                usuario.setId(Integer.parseInt(req.getParameter("idusuario")));
                Instrutor instrutor = new Instrutor(id, usuario, cracha);
                retorno = dao.editar(instrutor);

            }else{
                Instrutor instrutor = new Instrutor(usuario);
                instrutor.setCracha(cracha);
                System.out.println("vai cadastrar socio....");
                retorno = dao.cadastrar(instrutor);
            }

        }
        req.setAttribute("retorno", retorno);
        uri = "instrutores";
        req.setAttribute("instrutores", new InstrutorDAO().getInstrutores());

        RequestDispatcher rd = req.getRequestDispatcher("/"+uri+".jsp");
        rd.forward(req, resp);
    }
}

