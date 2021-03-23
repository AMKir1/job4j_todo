<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
            integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
            integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/todo/js/index.js"></script>
    <title>Todo list!</title>
</head>
<body>
<div class="container pt-3">
    <div class="login">
        <div class="float-end">
            <form action="<%=request.getContextPath()%>/index" method="get"><button type="submit" class="btn btn-primary float-end"> Отмена</button></form>
        </div>
        <br><br>
        <div class="card" style="width: 100%">
            <div class="card-header">
                <div class="row">
                    <h1>Вход</h1>
                </div>
            </div>
            <div class="card-body">
                <c:if test="${not empty error}">
                    <div class="row" id="alert-login">
                        <div class="alert alert-primary alert-dismissible fade show" role="alert">
                            <c:out value="${error}"/>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <form action="<%=request.getContextPath()%>/login" method="post">
                        <div class="form-group mb-3">
                            <label for="loginEmail" class="form-label">Логин</label>
                            <input type="text" class="form-control" name="login" id="loginEmail">
                        </div>
                        <div class="form-group mb-3">
                            <label for="loginPassword" class="form-label">Пароль</label>
                            <input type="password" class="form-control" name="password" id="loginPassword">
                        </div>
                        <button type="button" onclick="getReg()" class="btn">Регистрация</button>
                        <button type="submit" class="btn btn-primary">Войти</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>