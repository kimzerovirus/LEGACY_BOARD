<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<title>KIMZEROVIRUS | 글쓰기</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/summernote-lite.min.css"
      integrity="sha256-IKhQVXDfwbVELwiR0ke6dX+pJt0RSmWky3WB2pNx9Hg=" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@yaireo/tagify@4.17.8/dist/tagify.min.css">

<jsp:include page="../../layout/header.jsp"></jsp:include>

<div class="card">
    <div class="card-body">
        <h4 class="card-title m-0">작성하기</h4>
    </div>
</div>

<h5 class="mt-3">제목</h5>
<input type="text" class="form-control" placeholder="제목을 입력해주세요." name="title" id="title">

<h5 class="mt-3">태그<small class="text-info" style="font-size: 0.875rem"> - 내용을 대표하는 태그 3개 정도 입력해주세요.</small></h5>
<div id="tag"></div>

<h5 class="mt-3">본문</h5>
<div class="card">
    <div id="summernote"></div>
</div>

<div class="d-flex justify-content-center mt-3">
    <a href="/" class="btn btn-outline-secondary btn-lg px-5">취소</a>
    <button type="button" class="btn btn-primary btn-lg px-5" id="createBoard">등록</button>
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

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script src="https://unpkg.com/@yaireo/tagify"></script>

<script src="/resources/js/api.js"></script>
<script src="/resources/js/autotaginput.js"></script>
<script>
    $('#summernote').summernote({
        placeholder: '내용을 입력해주세요.',
        tabsize: 2,
        height: 400,
        lang: 'ko-KR',
        callbacks: {
            onImageUpload: function(files) {
                console.log(files[0])
                const url = 'http://localhost:8080/api/file/upload'
                const formData = new FormData();
                formData.append("file", files[0]);

                axios.post(url, formData)
                    .then(({data}) => {
                        const url = data.data.url
                        const imgurl = $('<img>').attr({ 'src': url });
                        $('#summernote').summernote('insertNode', imgurl[0]);
                    })
            }
        }
    });

    document.getElementById('createBoard').addEventListener('click', () => {
        const url = 'http://localhost:8080/api/board/write'
        const method = 'POST'
        const body = {
            title: document.getElementById('title').value || '',
            content: $('#summernote').summernote('code') || '',
            // tags: tagData
        }

        if (body.title === '') {
            alert('제목을 입력해주세요.')
        } else if (body.content === '') {
            alert('본문을 입력해주세요.')
        } else {
            apiCall(url, method, body).then(({data}) => { window.location = '/board/view/' + data.id })
        }
    })

    const tagWrap = document.getElementById("tag");
    const tagData = new AutoTagInput(tagWrap,'').getTags();
</script>