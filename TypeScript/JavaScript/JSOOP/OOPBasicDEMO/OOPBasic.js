//      面向对象的核心: 高内聚低耦合 ---> 对面向过程的高度封装

// 字面量创建
Liulili = {
    name: 'Dudu',
    age: 20,
    words: function() {
        console.warn("Hello World!")
    }
}

// 构造函数创建
var Liulilia = new Object()
Liulilia.name = 'Dudu'

// 工厂函数创建
function createObj(name, age) {
    var obj = {}
    obj.name = name
    obj.age = age
    return obj
}

var Liuliliaa = createObj('Dudu', 20)

// 自定义构造函数创建(节省内存)  -------->首字母大写(Bean, 类名)
function CreateObject(name, age) {
    this.name = name
    this.age = age
}

var liuli = new CreateObject('Dudu', 20)



/**
 * 原型 -> prototype
 *      每个函数自带一个属性 .prototype
 * 
 *  由此来看, 每一个由构造函数创建的对象(实例化), 都自带有prototype的所有属性(etc.   toString, valueOf.....)
 * 
 *  具体实现时, 我们可以在构造函数上的prototype添加对应的属性, 
 *              该属性不属于构造函数, 但可以在实例化的对象中使用, 
 *              多个实例化对象调用该属性本质上只是调用了prototype的属性, 不消耗内存(perfect!)
 *          即:只要向构造函数的原型prototype添加属性
 *              所有的构造函数的实例化对象均可使用, 且不消耗空间
 * 
 * 实例化之后的对象自带一个属性 __proto__  , 该属性指向构造函数的prototype
 * 即: obj.__proto__ === Fun.prototype 
 * 由此, 当访问对象的属性时, 优先在对象本身查找, 找不到再去__proto__中查找(即构造函数的prototype)
 * 
 * 
 */
console.log(CreateObject.prototype)
console.log(CreateObject.toString)

CreateObject.prototype.say = function() { console.log('Hello world!') }
console.log(liuli.say)


console.log(liuli.__proto__ === CreateObject.prototype)