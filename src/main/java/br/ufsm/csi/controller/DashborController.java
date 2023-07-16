package br.ufsm.csi.controller;
import br.ufsm.csi.dao.SocioDAO;
import br.ufsm.csi.dao.InstrutorDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("controlador")
public class DashborController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String uri = "/";

        String opcao = req.getParameter("opcao");
        uri = "/dashbord.jsp";

        System.out.println("Id da sessão: ----> "+req.getSession().getId());

        if(opcao.equals("socios")){

            uri = "/socios.jsp";
            req.setAttribute("socios", new SocioDAO().getSocios());
        }else if(opcao.equals("instrutores")){

            uri = "/instrutores.jsp";
            req.setAttribute("instrutores", new InstrutorDAO().getInstrutores());
        }else if(opcao.equals("sair")){
            req.getSession().invalidate();
            uri = "/";
        }

        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);

    }
}
