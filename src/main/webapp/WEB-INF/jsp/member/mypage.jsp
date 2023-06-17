<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>KIMZEROVIRUS | 마이페이지</title>

<jsp:include page="../../layout/header.jsp"></jsp:include>

<div class="card">
    <div class="card-body">
        <h4 class="card-title">마이페이지</h4>
        <a href="/logout" class="card-link">로그아웃</a>
    </div>
</div>

<c:if test="${!empty memberActivityList.content}">

    <table class="table mt-3">
        <thead><th>활동내역</th></thead>
        <tbody>
        <c:forEach var="activity" items="${memberActivityList.content}">
            <tr>
                <td class="py-3">
                    <div class="d-flex flex-column justify-content-between">
                        <div>
                            <div class="d-flex justify-content-between text-secondary mb-2">
                                <p style="margin: 0; font-size: 0.875rem;">
                                    <span class="text-primary fw-bold"><c:if test="${activity.tableName == 'BOARD'}">게시글</c:if><c:if test="${activity.tableName == 'REPLY'}">댓글</c:if></span>을 작성하였습니다.
                                </p>
                                <c:set var="today" value="<%=new java.util.Date()%>"/>
                                <fmt:formatDate var="now" type="date" value="${today}" pattern="yyyy-MM-dd"/>
                                <fmt:parseDate value="${ activity.createdAt }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                                <fmt:formatDate var="parsedDate" pattern="yyyy-MM-dd" value="${parsedDateTime}"/>
                                <p style="margin: 0; font-size: 0.875rem;">
                                    <c:if test="${now eq parsedDate}"><fmt:formatDate var="parsedDate" pattern="HH:mm" value="${parsedDateTime}"/></c:if>
                                    <c:if test="${now ne parsedDate}">${parsedDate}</c:if>
                                </p>
                            </div>
                            <h5 class="mt-2">
                                <a
                                        href="/board/view/${activity.id}<c:if test="${activity.tableName == 'REPLY'}">#note-${activity.replyId}</c:if>"
                                        style="text-decoration: none; font-weight: bold;"
                                >${activity.content}</a>
                            </h5>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${!empty memberActivityList.pageList}">
    <div class="d-flex justify-content-center mt-3">
        <ul class="pagination pagination-sm">
            <li class="page-item <c:if test="${!memberActivityList.checkPrev}">disabled</c:if>">
                <a class="page-link" href="${memberActivityList.pageList[0] - 1}">&laquo;</a>
            </li>
            <c:forEach var="page" items="${memberActivityList.pageList}">
                <li class="page-item <c:if test="${page eq memberActivityList.cpage}">active</c:if>">
                    <a class="page-link" href="?page=${page}">${page}</a>
                </li>
            </c:forEach>
            <li class="page-item <c:if test="${!memberActivityList.checkNext}">disabled</c:if>">
                <a class="page-link" href="?page=${memberActivityList.pageList[9] + 1}">&raquo;</a>
            </li>
        </ul>
    </div>
</c:if>

<jsp:include page="../../layout/footer.jsp"></jsp:include>
