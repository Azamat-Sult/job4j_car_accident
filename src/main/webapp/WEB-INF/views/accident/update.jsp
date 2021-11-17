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

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

    <title>Accident: update accident</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <li class="nav-item">
                Пользователь : ${user.username} |
            </li>
        </ul>
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>"> На главную</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <ul class="nav nav-pills nav-fill">
                    <li class="nav-item">
                        Редактирование заявления о происшествии
                    </li>
                </ul>
            </div>
            <div class="card-body">
                <form id="form2" name="form2" action="<c:url value='/save'/>" method="POST">
                    <div class="row">
                        <input type="text" class="form-control" name="id" id="id" value="<c:out value="${accident.id}"/>" hidden>
                        <div class="col">
                            <div class="form-group">
                                <label for="name">Заявитель</label>
                                <input type="text" class="form-control" name="name" id="name" value="<c:out value="${accident.name}"/>">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="type.id">Тип происшествия</label>
                                <select class="form-control" name="type.id" id="type.id">
                                    <c:forEach var="type" items="${types}" >
                                        <c:if test="${accident.type.id == type.id}">
                                            <option value="${type.id}" selected>${type.name}</option>
                                        </c:if>
                                        <c:if test="${accident.type.id != type.id}">
                                            <option value="${type.id}">${type.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="rules">Статья(и) правонарушения</label>
                                <div class="col-sm-2">
                                    <select name="rIds" multiple id="rules">
                                        <c:forEach var="rule" items="${rules}">
                                            <c:set var="selected" scope="page" value="false"/>
                                            <c:forEach var="accidentRule" items="${accident.rules}">
                                                <c:if test="${rule.id == accidentRule.id}">
                                                    <option value="${rule.id}" selected>${rule.name}</option>
                                                    <c:set var="selected" scope="page" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${selected == false}">
                                                <option value="${rule.id}">${rule.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="address">Адрес</label>
                                <input type="text" class="form-control" name="address" id="address" value="<c:out value="${accident.address}"/>">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="text">Описание</label>
                        <textarea class="form-control" name="text" id="text" rows="2"><c:out value="${accident.text}"/></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>