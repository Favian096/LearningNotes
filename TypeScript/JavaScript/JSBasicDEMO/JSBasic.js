// 在此实现基本的JS

// 数据类型
/*
// 基本数据类型
// 数值number
// 字符串string -> JS中不区分单双引号(py)
// boolean ->小写
// 空类型
// Unfefined -> 表示没有值
// NULL -> 表示有一个值, 为空

// 引用数据类型
*/

var q = 2e5;
var w = Math.pow(2, 4)
var a = 0x144;

var str = 'Hello world'

var t = true;
var f = false;

var u = undefined;
var n = null;

// 检测数据类型 typeof
console.log(typeof a)
console.log(typeof str)
console.log(typeof t)
console.log(typeof u)
console.log(typeof n)
console.log('----------------------------------------------------------------')

// 类型转换
/*
Number() - String() - toString() - Boolean()
Boolean中->NaN, null, '', undefined, 0 均为false
*/

var str2 = "100"
console.log(typeof str2)
    // 如果不可以转为数字, 就会转为NaN
var numStr2 = Number(str2)
console.log(typeof numStr2)
console.log(numStr2 += ++a)

// 此外, 还有可以使用parseInt 和 parseFloat方法, 二者都是一位一位的转换(100aaa转为100)


// 运算符
// + - * / %
// 值等于 ==        === 值和类型全等于       !=  值不等于      !=== 不全等于
// &&   ||   !
// ++ --


// 分支语句
if (2 >= 1) {
    console.log('-----------------------------------------------------------')
} else {
    console.log('null')
}

numRand = Math.random()
console.log(numRand)

switch (numRand) {
    case numRand > 0.5:
        console.log(numRand);
        break;
    default:
        console.log('-------------------------------------------------')
}


// 循环
nWhile = 0
while (nWhile < 10) {
    console.log('')
    nWhile++;
}

do {
    console.log('')
    nWhile++;
} while (nWhile === 15)

for (let i = 0; i < 3; i++) {
    console.log('==========================================================')
}


// 函数
function fun1(a, b) {
    return a + b
}
console.log(fun1(1, 2))

function fun2(num) {
    if (num == 1 || num == 2)
        return 1;
    return fun2(num - 1) + fun2(num - 2)
}
console.log(fun2(10))

// 作用域
function fun3() {
    var n = 123;

    function fun4() {
        var n = 456
    }
}


// 对象
var obj = {}
obj.name = 'Dudu'
obj['age'] = 20
obj.A = 12
console.log(obj)

delete obj.A;
console.log(obj)

console.log(obj.age)

console.log('-----------------------------------------------------')

// 数组
MyList = [1, 2, 3, 'Dudu', true]
console.log(MyList)
console.log(MyList.length)
MyList[1] = 20
for (let i = 0; i < MyList.length + 1; i++)
    console.log(MyList[i])

/**
 * 数组方法
 * List.push(var) -> 在数组末尾添加数据
 * List.pop() ->删除数组末尾的数据(返回值为该数据)
 * List.unshift(var) ->在数组的头部添加数据
 * List.shift() ->删除数组的最前方的一个(第一个数据), 返回值为该数据
 * List.reverse() -> 反转数组
 * List.sort() ->数组排序
 * List.join('-') ->将数元素用 - 连接后返回为字符串
 * List.concat(arr) ->数组拼接
 *
 * List.splice(开始索引, 多少个, 要插入的数据) ->从开始索引, 插入多少个数据(param3为空即表示删除多少个数据)
 * List.slice(start, end) ->在start, end之间截取数组(不填参数即拷贝数组), 包前不包后
 * List.indexOf(var) ->查找var在List中的索引位置(不存在为 -1 )
 * 
 * List.forEach(function (item, index, arr){}) ->3个参数依次为该个数据, 该数据索引, 整个数组
 * List.map(functon (item, index, arr){处理方法}) ->映射数组到另一个数组(可对每一个items进行变化)
 * List.filter(function (item, index, arr){过滤方法}) ->过滤数组, 返回新数组
 * List.every(function (item, index, arr){}) ->返回boolean值, 判断数组的每一个item是否复合条件(一个不符合就为false)
 * List.some(function (item, index, arr){}) ->返回boolean值, 判断数组是否存在item是否复合条件(一个符合就为true )
 */

MyList.push('啊啊')
console.log(MyList.pop())
MyList.unshift("aa")
console.log(MyList.shift())
MyList.reverse()
console.log(MyList)

console.log(MyList.some(function(item, index, arr) {
    return item == 'Dudu'
}))

console.log("----------------------------------------------------------------------")
    // 字符串

/**
 * 字符串方法
 * str.charAt(index) ->获取index位置的字符
 * str.toLowerCase() str.toUpperCase()->字符串转大小写
 * str.trim() ->去除首尾的空格
 * str.replace(换下内容, 换上内容) ->只替换第一次出现的
 * 
 * str.split('-') ->在字符串中按 - 将字符串分割为数组
 * str.substr(start, 个数)     str.substring(start, end)     str.slice(start, end) ->截取字符串
 */
console.log(str.substring(1, 3))

// 获取m ~ n的随机整数
function funRandomInt(m, n) {
    return Math.ceil(Math.random() * (n - m)) + m;
}
console.log(funRandomInt(5, 10))

// 时间对象(/getTime为时间戳)
var time = new Date();
console.log("秒数: " + time.getSeconds())