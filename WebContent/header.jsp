<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}/" />
<html>
<head>
    <title>Article List</title>
    <base href="${base}"/>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="resources/css/style.css" rel="stylesheet"/>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<header>
    <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
            <c:choose>
                <c:when test="${sessionScope.uid != null}">
                    <a class="navbar-brand d-flex align-items-center" href="auth/logout"> <strong>JAVA - CDC</strong></a> <a class="sign-in-btn" href="auth/logout">Sign Out</a></button>
                </c:when>
                <c:otherwise>
                    <a class="navbar-brand d-flex align-items-center" href="auth/login"> <strong>JAVA - CDC</strong></a> <a class="sign-in-btn" href="auth/login">Sign In</a></button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>