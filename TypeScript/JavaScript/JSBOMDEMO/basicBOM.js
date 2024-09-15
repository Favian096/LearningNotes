// 获取浏览器当前的视口高宽 window.innerWidth   window.innerHeight
document.getElementById('viewWH').innerText =
    '浏览器当前视口-> 宽度:' + window.innerWidth + " 高度:" + window.innerHeight


// 浏览器弹出层
// 提示框 - 询问框 - 输入框
function touchAlert() {
    alert('点击触发了 alert 提示框')
    console.log("用户点击触发了 alert 弹出框")
}

function touchConfirm() {
    var res = confirm('点击触发了 confirm 询问框')
    console.log("用户点击触发了 confirm 询问框 -> 返回值:" + res)
}

function touchPrompt() {
    var res = prompt('点击触发了 prompt 输入框')
    console.log('用户点击触发了 confirm 询问框 -> 返回值:' + res)
}


//资源加载完毕时执行
window.onload = function() {
    console.info("网页资源加载完毕")
}

// 平滑回到网页上层
// window.scrollTo({
//     left: 300
//     top: 400
//     behavior: 'smooth'
// })




// JS定时器
/**
 * (定时器有返回值, 返回值表示这是页面的第几个定时器
由此可以使用clearInterval(num)或者clearTimeout(num)进行关闭, 不区分种类)
 */
// 间隔定时器
var i = 1
timer1 = setInterval(function funSetIntercal() {
    console.log("间隔定时器第" + i + "次执行")
}, 1000)


// 延时定时器
timer2 = setTimeout(function() {
    console.log("延时定时器启动执行")
}, 5000)

offTimer.onclick = function() {
    clearInterval(timer1)
    clearTimeout(timer2)
}