<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Socios</title>
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

    <form action="socio-controller" method="post">

        <c:choose>
            <c:when test="${socio.id != null}">
                <h1>Editar Sócio</h1>
                <input type="hidden" name="idsocio" value="${socio.id}"/>
                <input type="hidden" name="idusuario" value="${socio.usuario.id}"/>
            </c:when>
            <c:otherwise>
                <h1>Cadastrar Sócio</h1>
                <input type="hidden" name="idsocio" value="0"/>
            </c:otherwise>
        </c:choose>

        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" value="${socio.usuario.nome}" class="form-control" id="nome" required>
            </div>
            <div class="form-group col-md-4">
                <label for="email">Email:</label>
                <input type="email" name="email" value="${socio.usuario.email}" class="form-control" id="email" required>
            </div>
            <div class="form-group col-md-4">
                <label for="senha">CPF:</label>
                <input type="senha" name="senha" value="${socio.usuario.senha}" class="form-control" id="senha" required>
            </div>
            <div class="form-group col-md-4">
                <label for="carteiraClube">Carteirinha:</label>
                <input type="text" name="carteiraClube" value="${socio.carteiraClube}" class="form-control" id="carteiraClube" required>
            </div>
        </div>
        <input type="hidden" name="opcao" value="cadastrar"/>
        <button type="submit" class="btn btn-primary">Gravar</button>

    </form>

    <c:if test="${retorno == 'OK'}">
        <h2>Novo sócio cadastrado com sucesso!</h2>
    </c:if>

    <h1>Sócios</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Carteirinha</th>
                <th>Ações</th>
                <th>Atividades</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="s" items="${socios}">
                <c:if test="${s.usuario.ativo != false}">
                    <tr>
                        <td>${s.usuario.nome}</td>
                        <td>${s.usuario.email}</td>
                        <td>${s.carteiraClube}</td>
                        <td>
                            <a href="http://localhost:8080/trabPOO2/socio-controller?opcao=editar&&id=${s.id}"
                               class="btn btn-primary">Editar</a>

                            <a href="http://localhost:8080/trabPOO2/socio-controller?opcao=excluir&&id=${s.id}"
                               class="btn btn-danger">Excluir</a>
                        </td>
                        <td>
                            <a href="http://localhost:8080/trabPOO2/socio-controller?opcao=visualizar&&id=${s.id}"
                               class="btn btn-warning">Gerenciar</a>
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
