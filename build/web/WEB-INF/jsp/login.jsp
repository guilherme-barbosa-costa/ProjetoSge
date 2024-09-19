
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./Styles/styles.css">
        <title>Login</title>
    </head>
    <body>

    <c:if test="${very == true}">
        <h2 class="alert">Faça o Login!</h2>
    </c:if>

        <br>
        <h1>Fazer Login</h1>
        <br>
        <form action="./logar" method="post">
            <input type="number" min="0" name="inputCpf" placeholder="Cpf">
            <input type="password" name="inputSenha" placeholder="Senha">
            <button type="submit">Entrar</button>
        </form>
        <br>
        <br>
        <a href="./cadastro">Criar Conta</a>
    </body>
</html>
