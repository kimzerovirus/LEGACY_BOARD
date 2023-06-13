class AutoTagInput {
    constructor(target, url) {
        this.target = target;
        this.el = document.createElement('input');
        this.url = url;
        this.flag = true;

        this.tagData = new CustomSet();

        this.target.classList.add('form-group')
        this.el.classList.add('form-control')
        this.target.appendChild(this.el);
        this.createTagBox();
        this.el.addEventListener('input', this.onInput.bind(this));
        this.target.addEventListener('focusout', this.removeDropdown.bind(this));
    }

    onInput() {
        this.removeDropdown();
        if (this.el.value === '') return;
        if (this.flag) {
            this.flag = false;
            setTimeout(() => {
                this.flag = true;
                this.removeDropdown();
                const value = this.el.value.toLowerCase();
                if (value === '') return;
                const data = this.getData()
                this.createDropdown(data)
            }, 1000)
        }
        this.createLoadingBox();
    }

    createTagBox() {
        const tagBox = document.createElement('div');
        tagBox.classList.add('w-100', 'position-relative', 'my-2');

        this.target.appendChild(tagBox);
        this.tagBox = tagBox;
    }

    addTag(name, id) {
        const size = this.tagData.size();
        this.tagData.add({name, id});
        if (size === this.tagData.size()) return;

        const badge = document.createElement('span');
        badge.classList.add('badge', 'bg-secondary');
        badge.style.marginRight = '0.25rem';
        badge.dataset.name = name;
        badge.dataset.id = id;
        badge.innerText = '#' + name;

        const anchor = document.createElement('a');
        anchor.classList.add('alert-link')
        badge.appendChild(anchor);

        this.tagBox.appendChild(badge);
        this.clearInput();
    }

    removeTag() {

    }

    createLoadingBox() {
        const div = document.createElement('div');
        div.classList.add('card', 'w-100', 'position-absolute', 'py-2');
        div.style.zIndex = 1000;
        div.style.top = '12px'
        div.style.textAlign = 'center';
        div.innerText = 'Loading...'

        this.tagBox.appendChild(div);
        this.loadingBox = div;
    }

    removeLoadingBox() {
        if (this.loadingBox) this.loadingBox.remove();
    }

    createDropdown(tags) {
        this.whitelist = document.createElement('ul');
        this.whitelist.classList.add('list-group', 'position-absolute', 'w-100');
        this.whitelist.style.zIndex = 1000;
        this.whitelist.style.top = '12px';
        this.whitelist.style.maxHeight = '360px';
        this.whitelist.style.overflowY = 'auto';

        if (tags.length === 0) {
            const inputVal = this.el.value;
            this.createListItem('create: ' + inputVal);
            this.listItemBtn.addEventListener('mousedown', () => {
                this.addTag(inputVal);
            })
        } else {
            tags.forEach(({name, id}) => {
                this.createListItem(name, id);
                this.listItemBtn.addEventListener('mousedown', () => {
                    this.addTag(name, id);
                })
            });
        }

        this.tagBox.appendChild(this.whitelist)
    }

    createListItem(name, id) {
        const listItem = document.createElement('li');
        listItem.classList.add('list-group-item', 'w-100');
        listItem.style.padding = 0;
        listItem.style.margin = 0;

        const btn = document.createElement('button');
        btn.classList.add('btn', 'w-100', 'btn-outline-primary', 'border-0');
        btn.style.textAlign = 'left';
        btn.innerText = name;

        listItem.appendChild(btn);
        this.whitelist.appendChild(listItem);
        this.listItemBtn = btn;
    }

    removeDropdown() {
        if (this.whitelist) this.whitelist.remove();
        this.removeLoadingBox();
    }

    hideDropdown(){
        if (this.whitelist) this.whitelist.style.display = 'none';
    }

    clearInput() {
        this.el.value = '';
        this.removeDropdown();
    }

    getData(data) {
        // const res = await fetch(this.url);
        // return await res.json();
        return [
            {name: '태그1', id:1},
            {name: '태그2', id:1},
            {name: '태그3', id:1},
            {name: '태그4', id:1},
            {name: '태그5', id:1},
            {name: '태그6', id:1},
            {name: '태그7', id:1},
            {name: '태그8', id:1},
            {name: '태그9', id:1},
            {name: '태그10', id:1},
            {name: '태그11', id:1},
            {name: '태그12', id:1},
            {name: '태그13', id:1},
            {name: '태그14', id:1},
            {name: '태그15', id:1},
        ]
    }

    getTags() {
        return this.tagData.getAll();
    }
}

class CustomSet {
    constructor() {
        this.arr = [];
    }

    add(data) {
        if (this.size() === 10) {
            alert('태그는 최대 10개 까지 설정 가능합니다.');
            return;
        }
        let flag = true;
        this.arr.forEach(({name}) => {
            if (data.name === name) {
                flag = false;
                return;
            }
        })
        if (flag) this.arr.push(data);
    }

    remove(name) {
        this.arr.forEach(n => {
            if (n.name === name) {
                this.arr.splice(this.arr.indexOf(n), 1);
                return;
            }
        })
    }

    size() {
        return this.arr.length;
    }

    getAll() {
        return this.arr;
    }
}