
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <h1>Criação da conta</h1>
        <br>
        <form action="./cadastrar" method="post" enctype="multipart/form-data">
            <input type="text" name="inputNome" placeholder="Nome">
            <input type="password" name="inputSenha" placeholder="Senha">
            <input type="number" name="inputCpf" placeholder="Cpf">
            <input type="date" name="inputDate">
            <div class="input-group mb-3">
  <input type="file" class="form-control" id="inputGroupFile02" name="inputImagem">
  <label class="input-group-text" for="inputGroupFile02">carregar imagem</label>
</div>
            <select name="selectArea">
                <option selected>Selecione a area</option>
                <c:forEach var="area" items="${areas}">
                    <option value="${area.id_area}">${area.nome}</option>
                </c:forEach>
            </select>
            <button type="submit">Cadastrar</button>
        </form>
    </body>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</html>
