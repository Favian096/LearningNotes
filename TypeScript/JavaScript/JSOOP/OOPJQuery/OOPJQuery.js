/*
* JQuery
*   JQuery对于对DOM元素进行了封装
*   包括 [获取DOM结点/结点尺寸, 操作结点文本/样式/类名/属性/事件, 结点动画操作, ajax封装]
* */

/*
* 基本使用
*   引入JQuery(引入后就会暴露两个变量名-> $  和  JQuery, 二者等价)*/

//  获取->     $('选择器')
$('.firstLi').text("第一行的Li")
console.log($('li:nth-child(odd)'))


/*
* 筛选器->
* $('选择器').first()
*            .last()
*            .next()
*            .nextAll()
*            .prev()
*            .prevAll()
*            .parent()
*            .parents()  所有父级元素直至html
*            .siblings()  所有兄弟元素
*            .find(选择器)  所有后代元素中满足条件的
*            .eq(索引)  从0开始
*/
console.log($('li:nth-child(odd)').last())
console.log($('li:nth-child(1)').parents())


/*
* 操作文本内容
*       元素.html()   ->即原生innerHTML, 可解析标签
*       元素.text()   ->即原生innerText
*       元素.val()    ->原生的value, 用于获取表单元素value
* */
$('.firstLi').nextAll().text("其他行的LI")

/*
* 操作类名
*   addClass()
*   removeClass()
*   toggleClass()
* */


/*
* Jquery操作元素样式
*       css()  ->获取和设置(可以使用{}批量设置)
* */
// $('.container').border = "";
console.log($('li').css('width'))

$('div.container').css("border", "10px solid rgb(43, 43, 43)")
$('div.container').css("margin", "90px")
$("li.firstLi").css(
    {
        backgroundColor: "rgb(189, 142, 62)",
        textAlign: "center",
        fontSize: "bold",
        lineHeight: "30px",
        fontFamily: "黑体",
        color: "rgb(0, 163, 255)"
    }
)

/*
* 操作元素属性
*   获取和设置->attr()
*   删除->removeAttr()
*   prop()
*   removeProp()
* */

/*
* 获取元素尺寸
*   content -> 元素.width() | .height()
*   content + padding -> 元素.innerWidth() | .innerHeight()
*   content + padding + border -> 元素.outerWidth() | .outerHeight()
*   content + padding + border + margin -> 元素.outerWidth(ture) | .outerHeight(ture)
* */

console.log("divContainer的宽度:\n" + $('div.container').width())
console.log($('div.container').innerWidth())
console.log($(' div.container').outerWidth())
console.log($(' div.container').outerWidth(true))

/*
* 获取元素偏移量
*   元素.offset()
*   元素.position() ->返回{top:"值", left:"值"}
* */
console.log($('div.container').offset())
console.log($('div.container').position())

/*
* 事件绑定
*   on()    ->元素.on("事件类型", 处理函数), 可以在事件类型中进行批量设置
*   on()事件委托 ->元素.on('事件类型', "选择器", 处理函数)
*
*   可以使用one()来绑定事件, 用法与on()相同, 但绑定的事件只会执行一次
*
*   除以上外jquery将常用的事件进行了封装可以直接调用
*   元素.click(处理函数) | .hover() | mouseover() ...
*
* */

$('div.container').on("click",
    e => console.log('点击了div->' + e.offsetX + "\t" + e.offsetY))

$("div.container").on("click", "li.firstLi",
    () => console.log("使用了事件委托, 点击了div里面的第一个Li"))

$("div.container").on(
    {
        mouseover: () => console.log("鼠标移入了div"),
        mouseout: () => console.log("鼠标移出了div")
    }
)

/*
* 事件解绑与事件触发
*   解绑->元素.off("click", handleFun)
*   触发->元素.trigger("click")
* */
