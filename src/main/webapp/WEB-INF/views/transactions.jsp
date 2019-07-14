<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
    <head>
        <title>Транзакції</title>

        <link href="<c:url value="/resources/main.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp" />
        <c:if test="${!empty transactionsList}">
            <h1>Транзакції</h1>

            <table class="tg">
                <tr>
                    <th width="15">#</th>
                    <th width="60">Категорія</th>
                    <th width="60">Тип операції</th>
                    <th width="30">Сума</th>
                    <th width="60">Дата</th>
                    <th width="120">Опис</th>
                    <th width="60">Управління</th>
                </tr>
                <c:set var="count" value="1" scope="page" />
                <c:forEach items="${transactionsList}" var="tr">
                    <tr>
                        <td><c:out value="${count}" /></td>
                        <td>${tr.category.name}</td>
                        <td>${tr.type}</td>
                        <td>${tr.total}</td>
                        <td>${tr.date}</td>
                        <td>${tr.description}</td>
                        <td>
                            <a href="<c:url value="/transactions/edit/${tr.id}"/>">Змінити</a> <a href="<c:url value='/transactions/remove/${tr.id}'/>">Видалити</a>
                        </td>
                    </tr>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${!empty transaction.type}">
            <h3>Редагувати Транзакцію</h3>
        </c:if>
        <c:if test="${empty transaction.type}">
            <h3>Додати Транзакцію</h3>
        </c:if>

        <c:url var="addAction" value="/transactions/add"/>
        <form:form action="${addAction}" modelAttribute="transaction">
            <table>
                <form:hidden path="id"/>
                <tr>
                    <td>
                        <form:label path="category.name">
                            <spring:message text="Назва Категорії"/>
                        </form:label>
                    </td>
                    <td>
                        <form:label path="category.id">
                            <select name="category.id">
                                <c:forEach items="${categoriesList}" var="cat">
                                    <option value="${cat.id}"><c:out value="${cat.name}" /></option>
                                </c:forEach>
                            </select>
                        </form:label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="type">
                            <spring:message text="Тип операції"/>
                        </form:label>
                    </td>
                    <td>
                        <form:label path="type">
                            <select name="type">
                                <option value="Витрата">Витрата</option>
                                <option value="Дохід">Дохід</option>
                            </select>
                        </form:label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="total">
                            <spring:message text="Сума"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="total" onkeypress="validate(event)"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="description">
                            <spring:message text="Короткий опис"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="description"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="date">
                            <spring:message text="Дата DD-MM-YYYY"/>
                        </form:label>
                    </td>
                    <td>
                        <fmt:formatDate value="${transaction.date}" pattern="dd-MM-yyyy" var="myDate" />
                        <form:input path="date" type="text" value="${myDate}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty transaction.type}">
                            <input type="submit"
                                   value="<spring:message text="Редагувати Транзакцію"/>"/>
                        </c:if>
                        <c:if test="${empty transaction.type}">
                            <input type="submit"
                                   value="<spring:message text="Додати Транзакцію"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    <script>
        function validate(evt) {
            var theEvent = evt || window.event;

            if (theEvent.type !== 'paste') {
                var key = theEvent.keyCode || theEvent.which;
                key = String.fromCharCode(key);
            } else {
                key = event.clipboardData.getData('text/plain');
            }
            var regex = /[0-9]|\./;
            if( !regex.test(key) ) {
                theEvent.returnValue = false;
                if(theEvent.preventDefault) theEvent.preventDefault();
            }
        }
    </script>
    </body>
</html>
