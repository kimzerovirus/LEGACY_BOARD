<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>
    .signup-form {
        max-width: 600px;
        width: 100%;
        padding: 20px;
    }
</style>
<jsp:include page="../../layout/header.jsp"></jsp:include>
<div class="d-flex justify-content-center w-100">

    <form action="/signup" method="POST" class="signup-form card">
        <h1 class="my-3 text-center">회원가입</h1>
        <div class="mb-3 mt-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
            <spring:hasBindErrors name="signupRequest">
                <c:if test="${errors.hasFieldErrors('email') }">
                    <strong class="text-danger small">${errors.getFieldError( 'email' ).defaultMessage }</strong>
                </c:if>
            </spring:hasBindErrors>
        </div>
        <div class="mb-3">
            <label for="nickname" class="form-label">Nickname:</label>
            <input type="text" class="form-control" id="nickname" placeholder="Enter nickname" name="nickname">
            <spring:hasBindErrors name="signupRequest">
                <c:if test="${errors.hasFieldErrors('nickname') }">
                    <strong class="text-danger small">${errors.getFieldError( 'nickname' ).defaultMessage }</strong>
                </c:if>
            </spring:hasBindErrors>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
            <spring:hasBindErrors name="signupRequest">
                <c:if test="${errors.hasFieldErrors('password') }">
                    <strong class="text-danger small">${errors.getFieldError( 'password' ).defaultMessage }</strong>
                </c:if>
            </spring:hasBindErrors>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<jsp:include page="../../layout/footer.jsp"></jsp:include>

<script>
    const email = `<c:out value="${email}"/>` || ""
    const nickname = `<c:out value="${nickname}"/>` || ""
    document.getElementById("email").value = email
    document.getElementById("nickname").value = nickname
</script>