function apiCall(url, method, body, callback) {
    return fetch(url, {
        method,
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(body)
    }).then(res => {
        if (res.status === 200) {
            if(callback) callback()
            return res.json();
        } else {
            alert('요청이 실패하였습니다.')
            return Promise.reject(res);
        }
    }).catch(err => {})
}

function goMain(msg, location = '/'){
    alert(msg)
    window.location = location
}