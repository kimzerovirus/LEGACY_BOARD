<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>
    .signin-form {
        max-width: 600px;
        width: 100%;
        padding: 20px;
    }
</style>
<jsp:include page="../../layout/header.jsp"></jsp:include>
<div class="d-flex justify-content-center w-100">
    <form id="signinForm" action="/signin" method="POST" class="signin-form card">
    <h1 class="my-3 text-center">로그인</h1>
        <div class="mb-3 mt-3">
            <label for="username" class="form-label">Email:</label>
            <input type="email" class="form-control" id="username" placeholder="Enter email" name="username">
            <strong class="text-danger small" id="emailConfirm"></strong>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
            <strong class="text-danger small" id="passwordConfirm"></strong>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
<jsp:include page="../../layout/footer.jsp"></jsp:include>

<script>
    addEventListener("DOMContentLoaded", () => {
        const URLSearch = new URLSearchParams(location.search);
        if(URLSearch.has('error')) alert("로그인 실패")
    });

    const form = document.getElementById('signinForm')

    form.addEventListener('submit', e => {
        e.preventDefault()
        const isEmailValue = document.getElementById('username').value !== ""
        const isPasswordValue = document.getElementById('password').value !== ""

        if (!isEmailValue) {
            document.getElementById('emailConfirm').innerText = "이메일을 입력해주세요."
            return;
        }

        if (!isPasswordValue) {
            document.getElementById('passwordConfirm').innerText = "비밀번호를 입력해주세요."
            return;
        }

        form.submit();
    })

</script>