<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson"%>

<%
        String dataPoints = new Gson().toJson(request.getAttribute("chartList"));
%>

<!DOCTYPE HTML>
<html>
        <head>
                <title>Звіт по днях</title>

                <link href="<c:url value="/resources/main.css" />" rel="stylesheet">
        </head>
        <body>
                <jsp:include page="_nav.jsp" />
                <fmt:formatDate value="${reportForm.date2}" pattern="dd.MM.yyyy" var="date2"/>
                <fmt:formatDate value="${reportForm.date1}" pattern="dd.MM.yyyy" var="date1"/>
                <h1>Звіт за період: <c:out value="${date1}" /> - <c:out value="${date2}" /></h1>
                <h3><c:out value="${reportForm.type}" /></h3>

                <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

                <script type="text/javascript">
                        window.onload = function() {

                                var chart = new CanvasJS.Chart("chartContainer", {
                                        animationEnabled: true,
                                        theme: "light2",
                                        title: {
                                                text: "Розподіл витрат за період [По датах]"
                                        },
                                        axisX: {
                                                crosshair: {
                                                        enabled: true,
                                                        snapToDataPoint: true
                                                }
                                        },
                                        axisY: {
                                                title: "Гривні",
                                                includeZero: false,
                                                crosshair: {
                                                        enabled: true,
                                                        snapToDataPoint: true,
                                                        valueFormatString: "##0.00",
                                                }
                                        },
                                        data: [{
                                                type: "area",
                                                yValueFormatString: "##0.00 гривень",
                                                dataPoints: <%out.print(dataPoints);%>
                                        }]
                                });
                                chart.render();

                        }
                </script>
        </body>
</html>