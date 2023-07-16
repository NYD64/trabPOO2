<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        body {
            padding: 20px;
        }
        h1, h5 {
            margin-bottom: 20px;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin-bottom: 20px;
        }
        ul li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="display-4">Página do Funcionário</h1>
    <h5 class="mb-4">Olá ${logado.nome}</h5>

    <ul>
        <li><a href="controlador?opcao=socios" class="btn btn-primary">Cadastrar Sócio</a></li>
        <li><a href="controlador?opcao=instrutores" class="btn btn-primary">Cadastrar Instrutor</a></li>
    </ul>
    <a href="controlador?opcao=sair" class="btn btn-danger">SAIR</a>
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
