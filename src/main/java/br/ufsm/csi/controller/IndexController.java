package br.ufsm.csi.controller;

import br.ufsm.csi.dao.SocioDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("index...");

        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp"); // Redireciona para a página inicial
        rd.forward(req, resp);

    }
}
