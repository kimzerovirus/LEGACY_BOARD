<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<title>KIMZEROVIRUS | 수정하기</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/summernote-lite.min.css"
      integrity="sha256-IKhQVXDfwbVELwiR0ke6dX+pJt0RSmWky3WB2pNx9Hg=" crossorigin="anonymous">

<jsp:include page="../../layout/header.jsp"></jsp:include>

<div class="card">
    <div class="card-body">
        <h4 class="card-title m-0">수정하기</h4>
    </div>
</div>

<h5 class="mt-3">제목</h5>
<input type="text" class="w-100 ps-2" placeholder="제목을 입력해주세요." id="title" name="title" value="${board.title}">

<h5 class="mt-3">본문</h5>
<div class="card">
    <div id="summernote">${board.content}</div>
</div>

<div class="d-flex justify-content-center mt-3">
    <a href="/board/view/${board.id}" class="btn btn-outline-secondary btn-lg px-5">취소</a>
    <button type="button" class="btn btn-primary btn-lg px-5" id="editBoard">등록</button>
</div>

<jsp:include page="../../layout/footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"
        integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/summernote-lite.min.js"
        integrity="sha256-5slxYrL5Ct3mhMAp/dgnb5JSnTYMtkr4dHby34N10qw=" crossorigin="anonymous"></script>

<!-- language pack -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/lang/summernote-ko-KR.min.js"
        integrity="sha256-y2bkXLA0VKwUx5hwbBKnaboRThcu7YOFyuYarJbCnoQ=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>

<script>
    $('#summernote').summernote({
        placeholder: '내용을 입력해주세요.',
        tabsize: 2,
        height: 400,
        lang: 'ko-KR',
    });

    document.getElementById('editBoard').addEventListener('click', () => {
        const url = 'http://localhost:8080/api/v1/board/edit'
        const method = 'POST'
        const body = {
            title: document.getElementById('title').value || '',
            content: $('#summernote').summernote('code') || ''
        }

        if (body.title === '') {
            alert('제목을 입력해주세요.')
        } else if (body.content === '') {
            alert('본문을 입력해주세요.')
        } else {
            apiCall(url, method, body)
        }
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
                window.location = '/'
                return res.json();
            } else {
                alert('요청이 실패하였습니다.')
                return Promise.reject(res);
            }
        }).catch(err => {
        })
    }
</script>