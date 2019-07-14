<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
    <head>
        <title>Категорії</title>

        <link href="<c:url value="/resources/main.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp" />

        <c:if test="${!empty categoriesList}">
            <h1>Категорії витрат/доходів</h1>

            <table class="tg">
                <tr>
                    <th width="15">#</th>
                    <th width="60">Назва</th>
                    <th width="120">Опис</th>
                    <th width="60">Управління</th>
                </tr>
                <c:set var="count" value="1" scope="page" />
                <c:forEach items="${categoriesList}" var="cat">
                    <tr>
                        <td><c:out value="${count}" /></td>
                        <td>${cat.name}</td>
                        <td>${cat.description}</td>
                        <td><a href="<c:url value='/categories/edit/${cat.id}'/>">Змінити</a> <a href="<c:url value='/categories/remove/${cat.id}'/>">Видалити</a></td>
                    </tr>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>
            </table>
        </c:if>


        <c:if test="${!empty category.name}">
            <h3>Редагувати Категорію</h3>
        </c:if>
        <c:if test="${empty category.name}">
            <h3>Додати Категорію</h3>
        </c:if>

        <c:url var="addAction" value="/categories/add"/>
        <form:form action="${addAction}" modelAttribute="category">
            <table>
                <form:hidden path="id"/>
                <tr>
                    <td>
                        <form:label path="name">
                            <spring:message text="Назва"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="description">
                            <spring:message text="Опис"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="description"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty category.name}">
                            <input type="submit"
                                   value="<spring:message text="Редагувати Категорію"/>"/>
                        </c:if>
                        <c:if test="${empty category.name}">
                            <input type="submit"
                                   value="<spring:message text="Додати Категорію"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
