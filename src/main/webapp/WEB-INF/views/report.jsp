<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
    <head>
        <title>Звіт</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/main.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp" />
        <h3>Генератор звітів</h3>

        <c:url var="action" value="/charts"/>
        <form:form action="${action}" method="post" modelAttribute="reportForm">
            <table>
                <tr>
                    <td>
                        <form:label path="date1">
                            <spring:message text="Дата DD-MM-YYYY"/>
                        </form:label>
                    </td>
                    <td>
                        <fmt:formatDate value="${reportForm.date1}" pattern="dd-MM-yyyy" var="myDate1" />
                        <form:input path="date1" type="text" value="${myDate1}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="date2">
                            <spring:message text="Дата DD-MM-YYYY"/>
                        </form:label>
                    </td>
                    <td>
                        <fmt:formatDate value="${reportForm.date2}" pattern="dd-MM-yyyy" var="myDate2" />
                        <form:input path="date2" type="text" value="${myDate2}"/>
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
                                <option selected value="Витрата">Витрата</option>
                                <option value="Дохід">Дохід</option>
                            </select>
                        </form:label>
                    </td>
                </tr>

                <tr>
                    <td>
                        <input type="submit" name="byDates" class="byDateB" value="<spring:message text="По датах"/>"/>
                    </td>
                    <td>
                        <input type="submit" name="byCategories" class="byCategoriesB" value="<spring:message text="По категоріях"/>"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
