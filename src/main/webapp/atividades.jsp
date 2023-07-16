<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.ufsm.csi.dao.AtividadeDAO" %>
<%@ page import="br.ufsm.csi.model.Atividade" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Atividades</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        body {
            padding: 20px;
        }
        form {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            margin-bottom: 20px;
        }
        th {
            background-color: #f2f2f2;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>

<div class="container">

    <h1>Atividades do Sócio</h1>

    <% int userId = Integer.parseInt(request.getParameter("id"));
        ArrayList<Atividade> atividades = AtividadeDAO.getAtividadesByUserId(userId);
    %>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nome da Atividade</th>
            <th>Dia</th>
            <th>Hora</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for (Atividade atividade : atividades) { %>
            <tr>
                <td><%= atividade.getNome() %></td>
                <td><%= atividade.getDia() %></td>
                <td><%= atividade.getHora() %></td>
                <td>
                    <a href=""
                       class="btn btn-primary">Editar</a>

                    <a href=""
                       class="btn btn-danger">Excluir</a>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

    <h2>Adicionar Atividade</h2>

    <form action="atividades-controller" method="post">
        <input type="hidden" name="idUsuario" value="<%= userId %>"/>

        <div class="form-group">
            <label for="nomeAtividade">Nome da Atividade:</label>
            <input type="text" name="nomeAtividade" class="form-control" id="nomeAtividade" required>
        </div>
        <div class="form-group">
            <label for="diaAtividade">Dia:</label>
            <input type="text" name="diaAtividade" class="form-control" id="diaAtividade" required>
        </div>
        <div class="form-group">
            <label for="horaAtividade">Hora:</label>
            <input type="text" name="horaAtividade" class="form-control" id="horaAtividade" required>
        </div>

        <button type="submit" class="btn btn-primary">Gravar</button>
    </form>

    <a href="socio-controller?opcao=socios" class="btn btn-secondary mt-3">Voltar</a>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-U7Q2izraxOrjiwN+6b3W/cXePh0iskge9k9VZZc5uubF9GXfM5BKI0F5zWJ5alC"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZU1bj4f4Vo7+O4rD59bvoNPjHwvq5b45f0PBwt0K+Xp5"
        crossorigin="anonymous"></script>

</body>
</html>