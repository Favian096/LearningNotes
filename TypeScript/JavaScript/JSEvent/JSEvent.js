// 事件的绑定和触发->document.on事件名 = function(事件对象){coding....}
/**
 * 基本事件分类
 *      鼠标事件->单击-双击-左键单击-按下-抬起-移入移除...
 *      键盘时间->键盘按下-抬起-键盘输入...
 *      浏览器事件->加载完毕-滚动-尺寸改变...
 *      触摸事件...
 *      表单事件...
 */

document.onmousemove = function(e) {
    console.log("鼠标的位置=>X = " + e.offsetX + "  Y = " + e.offsetY)
}

/**
 * 浏览器时间传播机制
 *        捕获阶段: window最先知道事件的发生->document->html->body->div
 *        目标阶段: div成功接收到事件
 *        冒泡阶段: 从div->body->html->document->window
 */

// 阻止事件传播 ->事件对象.stopPropagation()

// 事件委托 ->可以将子元素的事件冒泡给父元素进行响应

ol = document.querySelector('ol')

ol.onclick = function(e) {
    if (e.target.tagName == 'LI') {
        ol.style.borderRadius = '50px'
        console.log("鼠标点击了ol的li")
    }

}