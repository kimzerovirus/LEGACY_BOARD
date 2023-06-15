<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>KIMZEROVIRUS | HOME</title>

<script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="card position-relative overflow-hidden">
    <div class="card-body">
        <h4 class="card-title">커뮤니티</h4>
        <a href="/board/write" class="card-link">글쓰기</a>
    </div>

    <c:set var="ran"><%= java.lang.Math.round(java.lang.Math.random() * 4 + 1) %></c:set>
    <c:if test="${ran == 1}">
        <lottie-player class="position-absolute" src="https://assets3.lottiefiles.com/packages/lf20_d7SbS5.json"  background="transparent"  speed="1" style="width: 100px; height: 100px; bottom: -30px; right: 20px; transform: scaleX(-1)"  loop autoplay></lottie-player>
    </c:if>
    <c:if test="${ran == 2}">
        <lottie-player class="position-absolute" src="https://assets2.lottiefiles.com/packages/lf20_x1loSU.json"  background="transparent"  speed="1" style="width: 100px; height: 100px; bottom: -30px; right: 20px; transform: scaleX(-1)"  loop autoplay></lottie-player>
    </c:if>
    <c:if test="${ran == 3}">
        <lottie-player class="position-absolute" src="https://assets2.lottiefiles.com/packages/lf20_NiUhhS.json"  background="transparent"  speed="0.6" style="width: 80px; height: 80px; bottom: -15px; right: 20px; transform: scaleX(-1)"  loop autoplay></lottie-player>
    </c:if>
    <c:if test="${ran == 4}">
        <lottie-player class="position-absolute" src="https://assets8.lottiefiles.com/packages/lf20_mladlvha.json"  background="transparent"  speed="1" style="width: 100px; height: 100px; bottom: -30px; right: 10px; transform: scaleX(-1)"  loop autoplay></lottie-player>
    </c:if>
    <c:if test="${ran == 5}">
        <lottie-player class="position-absolute" src="https://assets4.lottiefiles.com/packages/lf20_iYvSqSMKZB.json"  background="transparent"  speed="1" style="width: 80px; height: 80px; bottom: -5px; right: 20px; transform: scaleX(-1)"  loop autoplay></lottie-player>
    </c:if>

</div>

<c:if test="${!empty boardList.content}">

    <table class="table mt-3">
    <c:if test="${param.keyword ne null}">
        <c:if test="${boardList.count > 0}">
        <thead>
        <th>검색 결과 (${boardList.count})</th>
        </thead>
        </c:if>
    </c:if>
        <thead>
        <th></th>
        </thead>
        <tbody>
        <c:forEach var="board" items="${boardList.content}">
            <tr>
                <td class="py-3">
                    <div class="d-flex flex-column justify-content-between" style="min-height: 87px;">
                        <div>
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
                            <h5 class="mb-3">
                                <a href="/board/view/${board.id}" style="text-decoration: none; font-weight: bold;">${board.title}</a>
                            </h5>
                        </div>
                        <div class="d-flex">
                            <small class="text-secondary border-1 border border-secondary block" style="padding: 2px 6px; font-size: 12px;">사는 얘기</small>
                            <c:forEach var="tag" items="${board.tags}">
                                <small class="block" style="padding: 2px 6px;">#${tag.tag.name}</small>
                            </c:forEach>
                        </div>
                    </div>
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
                <a class="page-link" href="${boardList.pageList[0] - 1}<c:if test="${param.type ne null}">&type=${param.type}</c:if><c:if test="${param.keyword ne null}">&keyword=${param.keyword}</c:if>">&laquo;</a>
            </li>
            <c:forEach var="page" items="${boardList.pageList}">
                <li class="page-item <c:if test="${page eq boardList.cpage}">active</c:if>">
                    <a class="page-link" href="?page=${page}<c:if test="${param.type ne null}">&type=${param.type}</c:if><c:if test="${param.keyword ne null}">&keyword=${param.keyword}</c:if>">${page}</a>
                </li>
            </c:forEach>
            <li class="page-item <c:if test="${!boardList.checkNext}">disabled</c:if>">
                <a class="page-link" href="?page=${boardList.pageList[9] + 1}<c:if test="${param.type ne null}">&type=${param.type}</c:if><c:if test="${param.keyword ne null}">&keyword=${param.keyword}</c:if>">&raquo;</a>
            </li>
        </ul>
    </div>
</c:if>

<%--<%=request.getParameter("type") %>--%>
<%--<%=request.getParameter("keyword") %>--%>

<c:if test="${param.keyword ne null}">
    <c:if test="${empty boardList.content}">
        <div class="d-flex justify-content-center h-75 mt-3 align-items-center">
            <div class="d-flex flex-column justify-content-center">
                <h3 class="text-center">"<span class="text-danger">${param.keyword}</span>"에<br/>대한 검색 결과가 없습니다.</h3>
                <a href="/" class="card-link text-center">홈으로 되돌아가기</a>
            </div>
        </div>
    </c:if>
</c:if>
<c:if test="${param.keyword eq null}">
    <c:if test="${empty boardList.content}">
        <div class="d-flex justify-content-center h-75 mt-3 align-items-center">
            <div class="d-flex flex-column justify-content-center">
                <h3 class="text-center">게시글이 존재하지 않습니다.</h3>
                <a href="/board/write" class="card-link text-center">첫번째 게시글을 올려주세요.</a>
            </div>
        </div>
    </c:if>
</c:if>

<jsp:include page="../layout/footer.jsp"></jsp:include>


