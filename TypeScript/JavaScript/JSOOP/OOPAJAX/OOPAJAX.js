/*
*   HTTP (hypertext transport protocol) 通信
*
*   请求报文(四个部分)
*       行   请求方式(get, post...)   url   http协议
*       头   Host: openai.com
*            Cookie: name = openai
*            Content-type: application/x-www-from-urlencoded
*            User-Agent: chrome 83
*       空行
*       体   请求内容
*
*
*   响应报文(四个部分)
*       行   http协议 200 OK
*       头   Content-Type: text/html;charset=utf-8
*            Content-length: 2048
*            Content-encoding: gzip
*       空行
*       体   后端返回数据结果
*
*   其中, 200 表OK
*         404 找不到资源
*         403 禁止访问
*         401 未授权
*         500 内部错误
*           ......
* */


// Ajax的基本使用

const url = "http://localhost:"
const port = "8090"
/*
*   1 创建ajax对象-> var xhr = new XMLHttpRequest()
*   2 配置本次请求信息-> xhr(请求方式, 请求地址, 是否异步(true为异步))
*   3 注册请求完成事件-> xhr.onload = function(){}
*   4 把请求发送出去-> xhr.send()
*
* 其中, 第三步中返回的数据可以用xhr.responseTest接收
* */
var userControllerAddress = "/JSOOPRespond"
var responseAllUser;

var xhr = new XMLHttpRequest();
xhr.open("GET", url + port + userControllerAddress, true);
xhr.onload = () => {
    responseAllUser = xhr.responseText.toString();
    console.log("使用ajax发出get请求, 获得一下数据:\n" + responseAllUser)

    // 由于接收到的数据是字符串类型(xhr.responseText)
    // 需要进行解析后即可成为对象
    console.log(JSON.parse(responseAllUser))
}
xhr.send();

//-------------------------------------------------------------------------------------------------
// 基础 请求测试 get
let userId = 4;
var getByIdXhr = new XMLHttpRequest();
getByIdXhr.open("GET",
    url + port + userControllerAddress + "/" + userId,
    true);
getByIdXhr.onload = () => {
    console.log("使用ajax发出get请求id = " + userId + " 获取到用户信息:\n")
    console.log(JSON.parse(getByIdXhr.responseText))
}
getByIdXhr.send();

//post请求
var insertObj;
document.getElementById("saveUser").onclick = () => {
    let Dudu = {};
    Dudu.name = "ccccc"
    Dudu.password = "ccccc"
    Dudu.age = 25
    Dudu.tel = "4444444444444"

    //    将对象转化为标准的json数据类型字符串, 便于后端进行处理
    let DuduStr = JSON.stringify(Dudu);

    const postXhr = new XMLHttpRequest();
    postXhr.open("POST", url + port + userControllerAddress, true);

    //设置请求头, 设置请求头名称和类型(json数据类型, utf-8编码)
    postXhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    postXhr.onload = () => {
        console.log("使用ajax发出post请求, 在数据库创建了一个对象");
        insertObj = postXhr.responseText;
        insertObj = JSON.parse(insertObj);
        console.log(insertObj)
    }
    postXhr.send(DuduStr);
}


//put请求
document.getElementById("updateUser").onclick = () => {
    let Dudu = {};
    Dudu.id = 10
    Dudu.name = "qweqweqwe"
    Dudu.password = "qweqweqwe"
    Dudu.age = 25
    Dudu.tel = "4444444444444"

    //    将对象转化为标准的json数据类型字符串, 便于后端进行处理
    let DuduStr = JSON.stringify(Dudu);

    const postXhr = new XMLHttpRequest();
    postXhr.open("PUT", url + port + userControllerAddress, true);

    //设置请求头, 设置请求头名称和类型(json数据类型, utf-8编码)
    postXhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    postXhr.onload = () => {
        console.log("使用ajax发出put请求, 在数据库修改了一个对象");
        console.log(postXhr.responseText)
    }
    postXhr.send(DuduStr);
}













