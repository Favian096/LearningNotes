// ES6基本语法

// 新增定义关键字(let const)
/**
 * var变量会进行预解析, let const不会进行预解析(定义之前不可使用)
 * var变量没有块级作用域, let const有块级作用域
 * 
 * 其中const定义的变量声明时就必须赋值, 赋值后不可修改(静态变量static)
 *     let定义的变量定义时可以不赋值
 */
let a = 2
const A = 3


/**
 * ES6箭头函数的使用(lambda)
 * (对与声明式函数不能使用)
 * 
 *      即:在使用   对象.(function(){...}) 中将function省略写为  对象.( () => {...} )
 * 
 *  在只有一个形参时, 不需要写小括号   ( a => {...})
 *  在只有一句代码时, 不需要写大括号并且会将这句话作为返回值    ( () => ... )
 * 
 *      特殊之处:
 *          箭头函数没有arguments 和 this(会调用外部的作用域this)
 */

setInterval(() => {
    console.log("使用箭头函数输出")
}, 5000)

setInterval(A => console.log("只有一个形参不需要写小括号"), 3000)

// 设置函数参数默认值
var sum = (q = 123, b = 456) => a + b
console.log(sum());

/**
 * 解构变量
 */

// 数组解构
list = ['H', 'W']
var [h, w] = list
console.log(h + w)

// 对象解构
obj = { name: 'Dudu', age: 20 }

// 获取obj中的name属性值赋值给n...
var { name: n, age: g } = obj
console.log(n + g)


/**
 * 模版字符串
 *  使用反引号 `string`
 * 
 * 特点:可以换行书写, 可以在字符串内解析变量(${var})
 */
console.log(`啊啊
age = ${g}`);



/**
 * 展开运算符 ...
 *  用于展开数组和对象
 */

arr1 = [10, 20, 30]
arr2 = [40, 50, 60]
console.log(Math.max(...arr2))
arr3 = [...arr1, ...arr2]
console.log(...arr3)

obj2 = {
    ...obj,
    gender: 'W'
}
console.log(obj2)


/**
 * 类class
 */

class DuduClass {
    constructor(name, age) {
        this.name = name
        this.age = age
    }

    say() {
        console.log("使用类中的方法");
    }

    static NUM = 4
}
var d = new DuduClass('Dudu', 20)
console.log(d)
console.log(d.say())
console.log(DuduClass.NUM)