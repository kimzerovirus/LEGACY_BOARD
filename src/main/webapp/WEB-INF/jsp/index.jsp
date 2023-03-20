<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>KIMZEROVIRUS | HOME</title>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="card">
    <div class="card-body">
        <h4 class="card-title">커뮤니티</h4>
        <a href="/board/write" class="card-link">글쓰기</a>
    </div>
</div>

<c:if test="${!empty boardList.content}">
    <div class="card mt-3">
        <c:forEach var="board" items="${boardList.content}">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
            </div>
        </c:forEach>
    </div>

    <div class="d-flex justify-content-center mt-3">
        <ul class="pagination pagination-sm">
            <li class="page-item disabled">
                <a class="page-link" href="#">&laquo;</a>
            </li>
            <li class="page-item active">
                <a class="page-link" href="#">1</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">3</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">4</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">5</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">&raquo;</a>
            </li>
        </ul>
    </div>
</c:if>
<c:if test="${empty boardList.content}">
    <div class="d-flex justify-content-center h-75 mt-3 align-items-center">
        <div class="d-flex flex-column justify-content-center">
            <h3 class="text-center">게시글이 존재하지 않습니다.</h3>
            <a href="/board/write" class="card-link text-center">첫번째 게시글을 올려주세요.</a>
        </div>
    </div>
</c:if>


<jsp:include page="../layout/footer.jsp"></jsp:include>
