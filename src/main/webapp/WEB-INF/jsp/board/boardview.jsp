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
            <div class="d-flex align-items-end flex-column">
                <textarea name="reply" id="reply" class="w-100 p-2" style="resize: none; height: auto"></textarea>
                <button class="btn btn-primary my-1" id="createReply">댓글 등록</button>
            </div>

            <div class="mt-3" id="replyContent">
                <c:if test="${!empty board.replyList}">
                    <c:forEach var="reply" items="${board.replyList}">
                        <div class="border-bottom border-1 border-primary">
                            <div class="d-flex justify-content-between text-secondary mt-3 mb-1">
                                <p style="margin: 0; font-size: 0.875rem;">${reply.member.nickname}</p>
                                <c:set var="today" value="<%=new java.util.Date()%>"/>
                                <fmt:formatDate var="now" type="date" value="${today}" pattern="yyyy-MM-dd"/>
                                <fmt:parseDate value="${ reply.createdAt }" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="parsedDateTime" type="both"/>
                                <fmt:formatDate var="parsedDate" pattern="yyyy-MM-dd" value="${parsedDateTime}"/>
                                <p style="margin: 0; font-size: 0.875rem;">
                                    <c:if test="${now eq parsedDate}"><fmt:formatDate var="parsedDate" pattern="HH:mm"
                                                                                      value="${parsedDateTime}"/></c:if>
                                    <c:if test="${now ne parsedDate}">${parsedDate}</c:if>
                                </p>
                            </div>

                            <div class="d-flex justify-content-between">
                                <p class="m-0">${reply.content}</p>
                                <sec:authorize access="isAuthenticated()">
                                    <sec:authentication property="principal.id" var="currentUserId"/>
                                    <c:if test="${board.member.id == currentUserId}">
                                        <div class="d-flex">
                                            <p class="m-0 px-1" style="font-size: 0.875rem; cursor: pointer;">수정</p>
                                            <p class="m-0 px-1" style="font-size: 0.875rem; cursor: pointer;"
                                               id="deleteReply">삭제</p>
                                        </div>
                                    </c:if>
                                </sec:authorize>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>

</c:if>
<jsp:include page="../../layout/footer.jsp"></jsp:include>

<script src="https://cdnjs.cloudflare.com/ajax/libs/dayjs/1.11.7/dayjs.min.js"
        integrity="sha512-hcV6DX35BKgiTiWYrJgPbu3FxS6CsCjKgmrsPRpUPkXWbvPiKxvSVSdhWX0yXcPctOI2FJ4WP6N1zH+17B/sAA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="/resources/js/autotextarea.js"></script>
<script src="/resources/js/api.js"></script>
<script>
    function loginAlert() {
        alert("로그인이 필요합니다")
    }

    const deleteBoard = document.getElementById('deleteBoard')

    if (deleteBoard) {
        document.getElementById('deleteBoard').addEventListener('click', () => {
            const url = 'http://localhost:8080/api/v1/board/delete'
            const method = 'POST'
            const body = {
                boardId: ${board.id},
                memberId: ${board.member.id}
            }
            const msg = '삭제완료!!'
            apiCall(url, method, body, goMain(msg))
        })
    }

    const reply = document.getElementById('reply')
    const replyContent = document.getElementById('replyContent')
    const replyWrapper = {
        init: function () {
            new Autotextarea(reply)
            this.create();
            this.edit();
            this.delete();
        },
        create: function () {
            document.getElementById('createReply').addEventListener('click', () => {
                <sec:authorize access="!isAuthenticated()">
                alert("로그인이 필요합니다.")
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                const url = 'http://localhost:8080/api/v1/reply/create/${board.id}'
                const method = 'POST'
                const body = {
                    content: reply.value
                }
                apiCall(url, method, body).then(res => this._html(res))
                </sec:authorize>
            })
        },
        edit: function () {

        },
        delete: function () {

        },
        _html: function({ data }){
            replyContent.innerHTML = data.map(item => ( this._component(item) )).join('')
        },
        _component: function (data) {
            let html = `
                    <div class="border-bottom border-1 border-primary">
                        <div class="d-flex justify-content-between text-secondary mt-3 mb-1">
                            <p style="margin: 0; font-size: 0.875rem;">` + data.nickname + `</p>
                            <p style="margin: 0; font-size: 0.875rem;">` + this._dateFormatter(data.date) + `</p>
                        </div>

                        <div class="d-flex justify-content-between">
                            <p class="m-0">` + data.content + `</p>

            `
            html += data.isReplyer ? `
                    <div class="d-flex">
                        <p class="m-0 px-1" style="font-size: 0.875rem; cursor: pointer;" id="editReply" reply-id="` + data.id + `">수정</p>
                        <p class="m-0 px-1" style="font-size: 0.875rem; cursor: pointer;" id="deleteReply" reply-id="` + data.id + `">삭제</p>
                    </div>
            ` : ``
            html += `
                        </div>
                    </div>
            `

            return html
        },
        _dateFormatter: function (date) {
            const isToday = dayjs().isSame(date, "day")
            return isToday ? dayjs(date).format('HH:mm') : dayjs(date).format('YYYY-MM-DD')
        }
    }

    replyWrapper.init()
</script>