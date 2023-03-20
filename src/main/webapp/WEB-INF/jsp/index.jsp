<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>KIMZEROVIRUS | HOME</title>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="card">
    <div class="card-body">
        <h4 class="card-title">커뮤니티</h4>
        <a href="/board/write" class="card-link">글쓰기</a>
    </div>
</div>

<c:if test="${!empty boardList.content}">
    <table class="table mt-3">
        <thead>
        <th></th>
        </thead>
        <tbody>
        <c:forEach var="board" items="${boardList.content}">
            <tr>
                <td class="py-3">
                    <a href="" style="text-decoration: none; cursor: pointer;">
                        <div class="d-flex justify-content-between text-secondary mb-2">
                            <p style="margin: 0; font-size: 0.875rem;">${board.member.nickname} · ${board.count}</p>
                            <c:set var="today" value="<%=new java.util.Date()%>"/>
                            <fmt:formatDate var="now" type="date" value="${today}" pattern="yyyy-MM-dd"/>
                            <fmt:parseDate value="${ board.createdAt }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                            <fmt:formatDate var="parsedDate" pattern="yyyy-MM-dd" value="${parsedDateTime}"/>
                            <p style="margin: 0; font-size: 0.875rem;">
                                <c:if test="${now eq parsedDate}"><fmt:formatDate var="parsedDate" pattern="HH:mm" value="${parsedDateTime}"/></c:if>
                                <c:if test="${now ne parsedDate}">${parsedDate}</c:if>
                            </p>
                        </div>
                        <h5 class="mb-2">${board.title}</h5>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>


<c:if test="${!empty boardList.pageList}">
    <div class="d-flex justify-content-center mt-3">
        <ul class="pagination pagination-sm">
            <li class="page-item <c:if test="${!boardList.checkPrev}">disabled</c:if>">
                <a class="page-link" href="${boardList.pageList[0] - 1}">&laquo;</a>
            </li>
            <c:forEach var="page" items="${boardList.pageList}">
                <li class="page-item <c:if test="${page eq boardList.cpage}">active</c:if>">
                    <a class="page-link" href="?page=${page}">${page}</a>
                </li>
            </c:forEach>
            <li class="page-item <c:if test="${!boardList.checkNext}">disabled</c:if>">
                <a class="page-link" href="?page=${boardList.pageList[9] + 1}">&raquo;</a>
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


