
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./Styles/styles.css">
        <title>Tela inicial</title>
    </head>
    <body>

        <form action="./logout" method="post">
            <button type="submit">Logout</button>
        </form>
        
        <br>
        <h1>Bem vindo</h1>
        <p>ID: ${id_professor}</p>
        <p>NOME: ${nome}</p>
        <img src="${imagem}" alt="Icon professor" width="400px" height="400px">
    </body>
</html>
