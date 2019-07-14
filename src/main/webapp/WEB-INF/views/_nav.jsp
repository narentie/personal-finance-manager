<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav>
    <ul>
        <li><a href="<c:url value="/categories"/>">Категорії</a></li>
        <li><a href="<c:url value = "/transactions"/>">Транзакції</a></li>
        <li><a href="<c:url value = "/report"/>">Звіт</a></li>
    </ul>
</nav>