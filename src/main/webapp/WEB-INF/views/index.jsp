<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

    <title>Accident</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <li class="nav-item">
                Пользователь : ${user.username} |&nbsp
            </li>
            <li class="nav-item">
                <a href="<c:url value='/logout'/>">Выйти |&nbsp</a>
            </li>
        </ul>
        <ul class="nav">
            <li class="nav-item">
                <a href="<c:url value='/create'/>">Добавить инцидент</a>
            </li>
        </ul>
    </div>
    <div class="row">
    </div>
    <div class="card" style="width: 100%">
        <div class="card-header">
            Список заявлений
        </div>
        <div class="card-body">
            <table class = "table table-bordered">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Заявитель</th>
                    <th scope="col">Тип происшествия</th>
                    <th scope="col">Статья(и)</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Адрес</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="accident" items="${accidents}">
                    <tr>
                        <td><c:out value="${accident.id}"/></td>
                        <td><c:out value="${accident.name}"/></td>
                        <td><c:out value="${accident.type.name}"/></td>
                        <td>
                        <c:forEach var="rule" items="${accident.rules}">
                            <c:out value="${rule.name}"/><br>
                        </c:forEach>
                        </td>
                        <td><c:out value="${accident.text}"/></td>
                        <td><c:out value="${accident.address}"/></td>
                        <td>
                        <a href="<c:url value='/update?id=${accident.id}'/>">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                            <a href="<c:url value='/delete?id=${accident.id}'/>">
                                <i class="bi bi-trash" style="color: red;"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>