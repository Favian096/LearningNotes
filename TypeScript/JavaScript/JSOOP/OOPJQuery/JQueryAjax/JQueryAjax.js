// 使用JQuery实现Ajax请求

/*
* 基本语法
*   $.Ajax({本次的ajax配置项})
*
*       配置项:
*           url: 地址
*           method: 请求方式(选填)
*           data:字符串,即携带给后端的参数(选填)
*           async: 表示是否异步, 默认为true(选填)
*           success: 表示请求成功的回调函数(选填)
*           error: 表示请求失败的回调函数(选填)
* */

let urlAddress = "http://localhost:8090/JSOOPRespond"
let dataStr = ''

$('.sentAjax').on("click", () => {
    $.ajax({
        url: urlAddress,
        method: "GET",
        data: dataStr,
        async: true,
        success: (res) => {
            console.log(res.data)
        },
        error: (res) => {
            console.log("请求失败!\n" + res)
        }
    })
})

$('.sentAjaxById').on("click", () => {
    $.ajax({
        url: urlAddress + "/1",
        method: "GET",
        data: null,
        success: (res) => {
            console.log(res.data)
        },
        error: (res) => {
            console.log("请求失败!\n" + res)
        }
    })
})

let obj = {}
obj.name = "jianglinyan";
obj.password = "jianglinyan";
obj.age = 20;
obj.tel = "19987968069";


$('.sentAjaxPost').on("click", () => {
    $.ajax({
        url: urlAddress,
        method: "POST",
        data: JSON.stringify(obj),
        contentType: "application/json",
        success: (res) => {
            console.log(res)
        },
        error: (res) => {
            console.log("请求失败!\n" + res)
        }
    })
})


