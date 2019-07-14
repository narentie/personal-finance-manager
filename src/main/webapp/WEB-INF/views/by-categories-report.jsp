<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<% String dataPoints = new Gson().toJson((ArrayList) request.getAttribute("chartList")); %>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Звіт по категоріях</title>

        <link href="<c:url value="/resources/main.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp" />
        <fmt:formatDate value="${reportForm.date2}" pattern="dd.MM.yyyy" var="date2"/>
        <fmt:formatDate value="${reportForm.date1}" pattern="dd.MM.yyyy" var="date1"/>
        <h1>Звіт за період: <c:out value="${date1}" /> - <c:out value="${date2}" /></h1>
        <h3><c:out value="${reportForm.type}" /></h3>

        <table class="tg">
            <tr>
                <th width="15">Категорія</th>
                <th width="60">Сума</th>
            </tr>
            <%
                double sum = 0;
                List list = (List) request.getAttribute("transactions");
                for (Object o : list) {
                    Object[] row = (Object[]) o;
                    String name = (String) row[0];
                    double total = (double) row[1];
            %>
            <tr>
                <td><%= name %></td >
                <td ><%= total %></td >
            </tr >
            <%
                    sum += total;
                }
            %>
            <tr>
                <td colspan="2">Всього: <%= sum %> грн.</td >
            </tr>
        </table>

        <div id = "chartContainer" style = "height: 370px; width: 100%;" ></div >
        <script src = "https://canvasjs.com/assets/script/canvasjs.min.js" ></script >

        <script type="text/javascript">
            window.onload = function() {
                var chart = new CanvasJS.Chart("chartContainer", {
                    theme: "light2",
                    animationEnabled: true,
                    exportFileName: "Розподіл витрат за період",
                    exportEnabled: true,
                    title:{
                        text: "Розподіл витрат за період"
                    },
                    data: [{
                        type: "pie",
                        showInLegend: true,
                        legendText: "{label}",
                        toolTipContent: "{label}: <strong>{y}%</strong>",
                        indexLabel: "{label} {y}%",
                        dataPoints : <%out.print(dataPoints);%>
                    }]
                });
                chart.render();
            }
        </script>
    </body>
</html>
