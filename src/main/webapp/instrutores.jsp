<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Instrutores</title>
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

    <form action="instrutor-controller" method="post">

        <c:choose>
            <c:when test="${instrutor.id != null}">
                <h1>Editar Instrutor</h1>
                <input type="hidden" name="idinstrutor" value="${instrutor.id}"/>
                <input type="hidden" name="idusuario" value="${instrutor.usuario.id}"/>
            </c:when>
            <c:otherwise>
                <h1>Cadastrar Instrutor</h1>
                <input type="hidden" name="idinstrutor" value="0"/>
            </c:otherwise>
        </c:choose>

        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" value="${instrutor.usuario.nome}" class="form-control" id="nome" required>
            </div>
            <div class="form-group col-md-4">
                <label for="email">Email:</label>
                <input type="email" name="email" value="${instrutor.usuario.email}" class="form-control" id="email" required>
            </div>
            <div class="form-group col-md-4">
                <label for="senha">CPF:</label>
                <input type="senha" name="senha" value="${instrutor.usuario.senha}" class="form-control" id="senha" required>
            </div>
            <div class="form-group col-md-4">
                <label for="cracha">Crachá:</label>
                <input type="text" name="cracha" value="${instrutor.cracha}" class="form-control" id="cracha" required>
            </div>
        </div>
        <input type="hidden" name="opcao" value="cadastrar"/>
        <button type="submit" class="btn btn-primary">Gravar</button>

    </form>

    <c:if test="${retorno == 'OK'}">
        <h2>Novo instrutor cadastrado com sucesso!</h2>
    </c:if>

    <h1>Instrutores</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Crachá</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="i" items="${instrutores}">
                <c:if test="${i.usuario.ativo != false}">
                    <tr>
                        <td>${i.usuario.nome}</td>
                        <td>${i.usuario.email}</td>
                        <td>${i.cracha}</td>
                        <td>
                            <a href="http://localhost:8080/trabPOO2/instrutor-controller?opcao=editar&&id=${i.id}"
                               class="btn btn-primary">Editar</a>

                            <a href="http://localhost:8080/trabPOO2/instrutor-controller?opcao=excluir&&id=${i.id}"
                               class="btn btn-danger">Excluir</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="controlador?opcao=dashbord" class="btn btn-secondary mt-3">Voltar</a>
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
