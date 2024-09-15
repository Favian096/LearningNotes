// DOM元素基本操作

/**
 * JS 获取元素方法
 *  根据id名称获取 -> document.getElementById('id')
 *  根据类名获取 ->document.getElemmentsByClass('class')
 *  根据标签名获取 ->document.getElementsByTagName('标签名')
 *  根据选择器获取第一个 ->document.querySelector('选择器')
 *  根据选择器获取一组 ->document.querySelectorAll('选择器')
 */

/**
 * 操作元素方法
 *  文本内容
 *      获取-> 元素.innerText
 *      设置0> 元素.innerText = '内容'
 *  超文本内容
 *      获取-> 元素.innerHTML
 *      设置-> 元素.innerHTML = '内容'
 */

var i = 1
buttonOnClick.onclick = function() {
    var buttonOnClick = document.getElementById('buttonOnClick').innerText += i++
}

/**
 * JS操作元素属性
 *  操作原生属性
 *      元素.属性名
 *  操作自定义属性(etc. hello = 'world')
 *      元素.getAttribute('属性名')
 *      元素.setAttribute('属性名', '属性值')
 *      元素.removeAttribute('属性名')
 */

/**
 * JS获取类名
 *  元素.className
 */

/**
 * JS获取元素行内样式================>  注: JS中的样式要写成 驼峰命名(background-color  -> backgroundColor)
 *      获取->元素.style.样式名
 *      设置0>元素.style.样式名 = 'red'
 */

buttonStyle.onclick = function() {
    buttonStyle.style.backgroundColor = 'red'
}


// ----------------------------------------------------------------------------------
window.onscroll = function() {
    var height = document.documentElement.scrollTop || document.body.scrollTop
    if (height >= 100) {
        document.querySelector('.header').style.top = '0px'
        document.querySelector('.goTop').style.display = 'block'
    } else {
        document.querySelector('.header').style.top = '-800px'
        document.querySelector('.goTop').style.display = 'none'
    }
}
document.querySelector('.goTop').onclick = function() {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
}

var checkBox = document.querySelectorAll('input')
var checkBoxAll = document.querySelector('input:nth-child(2)')

console.log(checkBox)
console.log(checkBoxAll.checked)

selectAll.onclick = function() {
    for (let i = 1; i < checkBox.length; i++) {
        checkBox[i].checked = checkBoxAll.checked;
    }


}

for (let i = 1; i < checkBox.length; i++) {
    checkBox[i].onclick = function() {
        var checkBoxAllFlag = true;
        for (let j = 1; j < checkBox.length; j++) {
            if (checkBox[j].checked == false) {
                checkBoxAllFlag = false;
                break;
            }
        }
        checkBoxAll.checked = checkBoxAllFlag;
    }
}


// ----------------------------------------------------------------------
var blocks = document.querySelectorAll('ul > li')
var colorBlocks = document.querySelectorAll('ol > li')

blocks.forEach(function(blockItem, blockIndex) {
    blockItem.onclick = function() {
        colorBlocks.forEach(function(colorBlockItem, colorBlockIndex) {
            if (blockIndex == colorBlockIndex)
                colorBlockItem.style.display = 'block'
            else
                colorBlockItem.style.display = 'none'
        })
    }
})


// =======================================================================

/**
 * JS结点的 
 * 创建 
 * 插入 
 * 删除
 * 替换
 * 克隆
 */

document.querySelector('.tag').appendChild(document.createElement('p'))

/**
 * JS获取元素尺寸
 *  元素.offsetHeight   和    元素.offsetWidth  获取到元素的 content + padding + border 尺寸
 *  元素.clinetHeight   和    元素.clientWidth  获取到元素的 content + padding  尺寸
 */


// 表格渲染
User = [
    { id: 1, name: 'AAA', age: 16 },
    { id: 2, name: 'BBB', age: 19 },
    { id: 3, name: 'CCC', age: 20 },
]

User.forEach(function(item) {
    var tr = document.createElement('tr')

    for (let key in item) {
        var td = document.createElement('td')
        td.innerText = item[key]

        tr.appendChild(td)
    }
    document.querySelector('table').appendChild(tr)
})