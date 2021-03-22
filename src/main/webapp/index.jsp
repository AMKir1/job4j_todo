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
    <div class="login visually-hidden">
        <div class="float-end">
            <button type="button" class="btn btn-primary float-end" onclick="getContent()">
                Отмена
            </button>
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

    <div class="reg visually-hidden">
        <div class="float-end">
            <button type="button" class="btn btn-primary float-end" onclick="getContent()">
                Отмена
            </button>
        </div>
        <br><br>
        <div class="card" style="width: 100%">
            <div class="card-header">
                <div class="row">
                    <h1>Регистрация</h1>
                </div>
            </div>
            <div class="card-body">
                <c:if test="${not empty error}">
                    <div class="row" id="alert-reg">
                        <div class="alert alert-primary alert-dismissible fade show" role="alert">
                            <c:out value="${error}"/>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <form action="<%=request.getContextPath()%>/reg" method="post">
                        <div class="form-group mb-3">
                            <label for="regEmail" class="form-label">Логин</label>
                            <input type="text" class="form-control" id="regEmail" name="login">
                        </div>
                        <div class="form-group mb-3">
                            <label for="regPassword" class="form-label">Пароль</label>
                            <input type="password" class="form-control" id="regPassword" name="password">
                        </div>
                        <button type="button" onclick="getLogin()" class="btn">Вход</button>
                        <button type="submit" class="btn btn-primary">Зарегистрировать</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="content">
        <div class="row">
            <div class="float-end">
                <c:if test="${empty user.name}">
                    <div class="loginarea">
                        <button type="button" class="btn btn-primary float-end" onclick="getLogin()">
                            Вход
                        </button>
                    </div>
                </c:if>
                <c:if test="${not empty user.name}">
                    <div class="logoutarea float-end">
                        <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                            <ul class="nav">
                                <li class="nav-item">
                                    <p id="username"><c:out value="${user.name}"/></p>
                                </li>
                                <li>
                                    <p>&nbsp;| Выйти</p>
                                </li>
                            </ul>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="row">
            <h1>TODO список</h1>
        </div>
        <div class="row" id="alert"></div>
        <div class="row">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    Добавить задчу
                </div>
                <div class="card-body">
                    <form>
                        <div class="form-group">
                            <label for="desc">Описание</label>
                            <br>
                            <textarea class="form-control" rows="5" id="desc" name="desc"></textarea>
                        </div>
                        <br>
                        <button type="button" class="btn btn-primary" onclick="addTask()">Сохранить</button>
                    </form>
                </div>
            </div>
        </div>
        <br>
        <div class="form-check form-switch">
            <input class="form-check-input" onchange="updateTable()" type="checkbox" id="done">
            <label class="form-check-label" for="done">Показать все</label>
        </div>
        <br>
        <div class="row">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    Список задач
                </div>
                <div class="card-body">
                    <table class="table" id='table'>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Пользователь</th>
                            <th>Задание</th>
                            <th>Дата создания</th>
                            <th>Готово</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>