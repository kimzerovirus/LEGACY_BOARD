<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<title>KIMZEROVIRUS | ${board.title}</title>

<jsp:include page="../../layout/header.jsp"></jsp:include>

<c:if test="${!empty board}">
    <div class="card">
        <div class="card-body">
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

            <div class="d-flex justify-content-between">
                <h3 class="card-title">${board.title}</h3>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.id" var="currentUserId"/>
                    <c:if test="${board.member.id == currentUserId}">
                        <div class="d-flex">
                            <a href="/board/edit/${board.id}" class="btn px-1" style="font-size: 0.875rem">수정</a>
                            <span class="btn px-1" style="font-size: 0.875rem" id="deleteBoard">삭제</span>
                        </div>
                    </c:if>
                </sec:authorize>
            </div>
        </div>
    </div>

    <div class="card mt-3">
        <div class="card-body" style="min-height: 300px;">
            ${board.content}
        </div>
        <div class="card-footer pb-3">
            <table class="table">
                <tr>
                    <th>댓글 목록</th>
                </tr>
            </table>
            <form id="createReply">
                <div class="d-flex align-items-end flex-column">
                        <textarea name="reply" id="reply" class="w-100 p-2" style="resize: none; height: auto"></textarea>
                        <button class="btn btn-primary my-1">댓글 등록</button>
                </div>
            </form>

            <div class="border-bottom border-1 border-primary">
                <div class="d-flex justify-content-between text-secondary mb-2">
                    <p style="margin: 0; font-size: 0.875rem;">reply.member.nickname</p>
                    <p style="margin: 0; font-size: 0.875rem;">2023-01-01</p>
                </div>
                <p class="m-0">댓글</p>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="../../layout/footer.jsp"></jsp:include>

<script>
    document.getElementById('deleteBoard').addEventListener('click', () => {
        const url = 'http://localhost:8080/api/v1/board/delete'
        const method = 'POST'
        const body = {
            boardId: ${board.id},
            memberId: ${currentUserId}
        }
        apiCall(url, method, body)
    })

    async function apiCall(url, method, body) {
        return fetch(url, {
            method,
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(body)
        }).then(res => {
            if (res.status === 200) {
                alert("삭제 완료!!")
                window.location = '/'
                return res.json();
            } else {
                alert('요청이 실패하였습니다.')
                return Promise.reject(res);
            }
        }).catch(err => {
        })
    }

    const textarea = document.getElementById('reply');
    function resize(obj) {
        if(obj.scrollHeight < 200){
            obj.style.overflowY = 'hidden';
            obj.style.height = '1px'
            obj.style.height = (16 + obj.scrollHeight) + 'px';
        } else {
            obj.style.height = '200px';
            obj.style.overflowY = 'scroll';
        }
    }

    textarea.addEventListener('keyup', ()=> resize(textarea))
    textarea.addEventListener('keydown', ()=> resize(textarea))
</script>