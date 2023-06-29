<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KIMZEROVIRUS</title>
    <link rel="stylesheet" href="/resources/vendor/bootstrap/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <style>
        @font-face {
            font-family: 'Dovemayo-Medium';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.0/Dovemayo-Medium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        html, body, p, span, div, a, h1, h2, h3, h4, h5, h6, small, strong, input, textarea, label, select, option {
            font-family: 'Dovemayo-Medium', serif !important;
        }

        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        .wrapper {
            display: flex;
            flex-direction: column;
            height: 100%;
        }

        .content {
            flex: 1;
        }
    </style>
    <script src="/resources/vendor/bootstrap/bootstrap.min.js" defer></script>
</head>
<body>
<div class="wrapper">
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-3 p-3">
        <div class="container">
            <a class="navbar-brand" href="/">Home</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav me-auto">
                    <sec:authorize access="!isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/signin">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/signup">회원가입</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/mypage"><sec:authentication property="principal.nickname"/>님</a>
                        </li>
                    </sec:authorize>
                </ul>
                <form class="d-flex" style="margin:0 !important" action="/" method="GET">
                    <select class="form-select" style="width: 90px;" name="type">
                        <option value="all" <c:if test="${param.type eq 'all'}">selected</c:if>>전체</option>
                        <option value="title" <c:if test="${param.type eq 'title'}">selected</c:if>>제목</option>
                        <option value="content" <c:if test="${param.type eq 'content'}">selected</c:if>>내용</option>
                        <option value="nickname" <c:if test="${param.type eq 'nickname'}">selected</c:if>>닉네임</option>
                        <option value="tag" <c:if test="${param.type eq 'tag'}">selected</c:if>>태그</option>
                    </select>
                    <input class="form-control me-2" type="text" placeholder="Search" name="keyword" value="<c:if test="${param.keyword ne null}">${param.keyword}</c:if>">
                    <button class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="content container" style="max-width: 840px;">
