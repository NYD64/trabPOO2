package br.ufsm.csi.controller;

import br.ufsm.csi.dao.SocioDAO;
import br.ufsm.csi.model.Cargo;
import br.ufsm.csi.model.Socio;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("socio-controller")
public class SocioController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = "/";

        SocioDAO dao = new SocioDAO();
        String retorno = "";
        String opcao = req.getParameter("opcao");

        if (opcao.equals("socios")) {
            uri = "socios";
            req.setAttribute("socios", new SocioDAO().getSocios());

        }else if(opcao.equals("excluir")){
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("id socio excluir: " + id);
            Socio socio = new SocioDAO().getSocio(id);

            retorno = dao.excluir(socio);
            req.setAttribute("retorno", retorno);

        }else if(opcao.equals("editar")){
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("id socio editar: "+id);
            Socio socio = new SocioDAO().getSocio(id);

            req.setAttribute("socio", socio);
        }
        else if (opcao.equals("visualizar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("id socio visualizar: " + id);
            Socio socio = new SocioDAO().getSocio(id);

            req.setAttribute("socio", socio);
            RequestDispatcher rd = req.getRequestDispatcher("/atividades.jsp?id=" + id);
            rd.forward(req, resp);
        } else {


            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            String carteiraClube = req.getParameter("carteiraClube");

            Cargo c = new Cargo(2, "SOCIO");
            Usuario usuario = new Usuario(nome, email, senha, true, c);
            int id = Integer.parseInt(req.getParameter("idsocio"));

            if(id > 0){

                usuario.setId(Integer.parseInt(req.getParameter("idusuario")));
                Socio socio = new Socio(id, usuario, carteiraClube);
                retorno = dao.editar(socio);

            }else{
                Socio socio = new Socio(usuario);
                socio.setCarteiraClube(carteiraClube);
                System.out.println("vai cadastrar socio....");
                retorno = dao.cadastrar(socio);
            }

        }
        req.setAttribute("retorno", retorno);
        uri = "socios";
        req.setAttribute("socios", new SocioDAO().getSocios());

        RequestDispatcher rd = req.getRequestDispatcher("/"+uri+".jsp");
        rd.forward(req, resp);
    }
}
