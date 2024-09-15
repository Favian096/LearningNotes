// JQuery的基本动画和综合动画

/*
* 基本动画函数1
*   show()  ->显示
*   hide()  ->隐藏
*   toggle()   ->切换(显示和隐藏)
*
*   三个基本函数有相同的参数
*       第一个表示运动时间, 第二个表示运动曲线, 第三个表示回调函数
* */

$("button#showButton").on("click", () => {
    $('div.divAnimate1').show(1000)
})

$("button#hideButton").on("click", () => {
    $("div.divAnimate1").hide(1000);
})

$("button#toggleButton").on("click", () => {
    $("div.divAnimate1").toggle(1000);
})


/*
* 基本动画函数2
*   slideDown()  ->显示
*   slideUp()  ->隐藏
*   slideToggle()   ->切换(显示和隐藏)
*
*   三个基本函数有相同的参数
*       第一个表示运动时间, 第二个表示运动曲线, 第三个表示回调函数
* */

$("button#slideDownButton").on("click", () => {
    $('div.divAnimate2').slideDown(1000)
})

$("button#slideUpButton").on("click", () => {
    $("div.divAnimate2").slideUp(1000);
})

$("button#slideToggleButton").on("click", () => {
    $("div.divAnimate2").slideToggle(1000);
})


/*
* 基本动画函数3
*   fadeIn()  ->显示
*   fadeOut()  ->隐藏
*   fadeToggle()   ->切换(显示和隐藏)
*
*   以上三个基本函数有相同的参数
*       第一个表示运动时间, 第二个表示运动曲线, 第三个表示回调函数
*   fadeTo(时间, 透明度, 曲线, 函数) ->增加了一个参数可设置透明的
*
* */

$("button#fadeInButton").on("click", () => {
    $('div.divAnimate3').fadeIn(1000)
})

$("button#fadeOutButton").on("click", () => {
    $("div.divAnimate3").fadeOut(1000);
})

$("button#fadeToggleButton").on("click", () => {
    $("div.divAnimate3").fadeToggle(1000);
})

$("button#fadeToButton").on("click", () => {
    $("div.divAnimate3").fadeTo(
        1000, 0.5, 'linear', () => console.log("opacity:0.5"));
})

/*
* 综合动画效果
*   .animate()
*       参数第一个为运动的样式(target)
*           第二个为运动的时间, 第三个为运动的曲线, 第四个为回调函数
*
*   注: 颜色类和transform类不可使用
* */


$("button#animateButton").on("click", () => {
    $("div.divAnimate4").animate(
        {
            width: "100px",
            height: "100px",
            marginLeft: "500px",
            borderRadius: "20px"
        }, 5000, 'linear', () => console.log('animate')
    )
})

/*
* 结束动画
*   .stop()  ->立即停住
*   .finish() ->直接结束动画至最后
* */

$("button#stopButton").on("click", () => {
    $("div.divAnimate4").stop()
})

$("button#finishButton").on("click", () => {
    $("div.divAnimate4").finish()
})

// 注 : 由于动画的结束在停止之前, 由此可以设置 .stop().animate(...)

$("button#stopToggleButton").on("click", () => {
    $("div.divAnimate4").stop().animate(
        {
            width: "100px",
            height: "100px",
            marginLeft: "500px",
            borderRadius: "20px"
        }, 5000
    )
})

$("button#finishToggleButton").on("click", () => {
    $("div.divAnimate4").finish().animate(
        {
            width: "100px",
            height: "100px",
            marginLeft: "500px",
            borderRadius: "20px"
        }, 5000
    )
})



