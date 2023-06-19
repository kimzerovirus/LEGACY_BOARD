<button id="f">fetch</button>
<button id="a">api</button>
<button id="u">url</button>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    document.getElementById('f').addEventListener('click',() => {
        fetch('http://localhost:8080/test/api').then(res => res.json()).then(data => console.log(data))
    })
    document.getElementById('a').addEventListener('click',() => {
        axios.get('http://localhost:8080/test/api').then(res => console.log(res.data))
    })
    document.getElementById('u').addEventListener('click',() => {
        axios.get('http://ec2-3-36-35-135.ap-northeast-2.compute.amazonaws.com:8080/test/api').then(res => console.log(res.data))
    })
</script>