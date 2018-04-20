<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="/header.jsp"></c:import>
<div class="form-canvas">
    <form class="form" method="POST" action="article/create">
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Post New Article</h1>
            <p>Fill in the title and content then press the submit button to create a new article.</p>
        </div>
        <div class="form-label-group">
            <input autofocus="" class="form-control" id="title" name="title" placeholder="Type the title of your content" required="" type="text"> <label for="title">Title</label>
        </div>
        <div class="form-label-group">
            <textarea class="form-control" id="content"  name="content" placeholder="Type the content of your content" required="" type="text"></textarea> <label for="content">Content</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Publish</button>
        <div class="nav-to-home">
            <a href="#" onclick="window.history.go(-1); return false;">Back to Home</a>
        </div>
    </form>
</div>
<c:import url="/footer.jsp"></c:import>