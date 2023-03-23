class Autotextarea {
    constructor(obj) {
        this.obj = obj;

        this.onKeyUp();
        this.onKeyDown();
    }

    resize(){
        const obj = this.obj;
        if(obj.scrollHeight < 200){
            obj.style.overflowY = 'hidden';
            obj.style.height = '1px'
            obj.style.height = (16 + obj.scrollHeight) + 'px';
        } else {
            obj.style.height = '200px';
            obj.style.overflowY = 'scroll';
        }
    }

    onKeyUp(){
        this.obj.addEventListener('keyup', ()=> this.resize());
    }

    onKeyDown(){
        this.obj.addEventListener('keydown', ()=> this.resize());
    }
}