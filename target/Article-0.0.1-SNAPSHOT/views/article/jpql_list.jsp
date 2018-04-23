<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="/header.jsp"></c:import>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Article</h1>
        <c:choose>
            <c:when test="${sessionScope.uid != null}">
                <p class="lead text-muted">Something short and leading about the article of any contents. Make it short and sweet, but not too short so folks don't simply skip over it entirely.</p>
                <p><a class="btn btn-primary my-2" href="article/jpa/new">Post a New Article</a></p>
            </c:when>
            <c:otherwise>
                <p class="lead text-muted">You need to login first to manage the articles</p>
                <p><a class="btn btn-primary my-2" href="auth/login">Login Now</a></p>
            </c:otherwise>
        </c:choose>
    </div>
</section>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="row">
            <c:forEach var="article" items="${listArticle}">
            <div class="col-md-4">
                <div class="card mb-4 box-shadow">
                    <div style="background-color: #55595c; height: 255px"></div>
                    <div class="card-body">
                        <p class="card-text card-height"><c:out value="${article[1]}" /></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button class="btn btn-sm btn-outline-secondary" type="button" onclick="location.href='article/jpa/view?id=${article[0]}'">View</button>
                                <c:choose>
                                    <c:when test="${sessionScope.uid != null}">
                                        <button class="btn btn-sm btn-outline-secondary" type="button" onclick="location.href='article/jpa/edit?id=${article[0]}'">Edit</button>
                                        <button class="btn btn-sm btn-outline-secondary" type="button" onclick="location.href='article/jpa/delete?id=${article[0]}'">Delete</button>
                                    </c:when>
                                </c:choose>
                            </div><small class="text-muted">xx mins</small>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
</div>
<c:import url="/footer.jsp"></c:import>