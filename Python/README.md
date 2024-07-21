# Python



## Anaconda(minianaconda)

#### 配置镜像

---

- ###### user目录下的.condarc文件配置镜像([参考URL](https://mirrors.tuna.tsinghua.edu.cn/help/anaconda/))

  ```bash
  channels:
    - defaults
  show_channel_urls: true
  default_channels:
    - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main
    - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/r
    - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/msys2
  custom_channels:
    conda-forge: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
    msys2: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
    bioconda: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
    menpo: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
    pytorch: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
    pytorch-lts: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
    simpleitk: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
    deepmodeling: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/
  ```




#### 基本命令

---

- **查看所有环境(base即默认环境)**

  ```shell
  conda env list
  ```

- **创建一个名为 myenv 的环境**

  ```shell
  conda create --name myenv
  ```

- **创建指定 Python 版本的环境**

  ```shell
  conda create --name myenv python=3.8
  ```

- **激活(切换)环境**

  ```shell
  conda activate myenv
  ```

- **退出当前环境**

  ```shell
  deactivate
  ```

- **复制(clone)一份环境**

  ```shell
  conda create --name mycloneenv --clone myenv
  ```

- **删除环境**

  ```shell
  conda env remove --name myenv
  ```

- **查看已安装的包**

  ```shell
  conda list
  ```

- **搜索某一个包**

  ```shell
  conda search package_name
  ```

- **更新包**

  ```shell
  conda update package_name
  ```

- **移除包**

  ```shell
  conda remove package_name
  ```




---

- **window端的TensorFlow最该支持到2.10.0版本**

  [先安装cudatoolkit和cudnn并配置环境变量:](https://blog.csdn.net/weixin_43412762/article/details/129824339)

  [TensorFlow, cudatoolkit, cudnn的版本查询](https://tensorflow.google.cn/install/source_windows?hl=en#gpu)

  [下载cudatoolkie工具包](https://developer.nvidia.com/cuda-toolkit-archive)

  [下载cudnn加速包](https://developer.nvidia.com/rdp/cudnn-archive)

  配置cudnn后再配置环境变量:

  ```
  C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.5\bin
  C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.5\libnvvp
  C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.5
  C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.5\lib\x64
  ```

  ```
  配置环境变量完成后到目录下cmd
  Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.5\extras\demo_suite
  
  依次执行命令
  .\bandwidthTest.exe
  
  .\deviceQuery.exe
  
  都显示PASS即为配置成功
  ```

  

  配置TensorFlow步骤:

  ```shell
   // 配置最高的3.7py环境
   conda create --name tfpy37 python=3.7
   
   // 激活进入环境
   conda activate tfpy37
   
   // 检索cuda的加速包
   conda search cudnn
   
   // 检索cuda工具包
   conda serch cudatoolkit
  ```

- 安装工具包[对于版本](https://tensorflow.google.cn/install/source_windows?hl=zh-cn)

  ```bash
  conda install -c conda-forge cudatoolkit=10.1.243 cudnn=7.6.5
  ```

- 安装window的最高版本TensorFlow 2.10.0

  ```bash
  conda install tensorflow==2.10.0
  ```

  测试配置状态是否成功

  ```shell
  python
  
  import tensorflow as tf
  cpus = tf.config.experimental.list_physical_devices(device_type='CPU')
  gpus = tf.config.experimental.list_physical_devices(device_type='GPU')
  print('CPU List : ', cpus)
  print('GPU List : ', gpus)
  print('built with cuda : 'tf.test.is_built_with_cuda())
  print('can access a GPU : 'tf.test.is_gpu_available())
  print('built with GPU : 'tf.test.is_built_with_gpu_support())
  ```
  
  



## Jupyter NoteBook

#### 基本使用

- **安装**

  ```shell
  conda install jupyter
  ```

- **默认端口启动**

  ```bash
  jupyter notebook
  ```

- **自定义端口启动**

  ```bash
  jupyter notebook --port <port_number>
  ```

- **启动服务器但不打开浏览器**

  ```bash
  jupyter notebook --no-browser
  ```

- **获得配置文件路径**

  ```bash
  jupyter notebook --generate-config
  ```

- **安装jupyter拓展程序**(在Anaconda prompt中执行)

  ```bash
  //运行安装拓展程序
  conda install -c conda-forge jupyter_contrib_nbextensions
  
  //再配置安装依赖程序
  conda install -c conda-forge webcolors
  conda install -c conda-forge jsonschema
  conda install -c conda-forge uri-template
  conda install -c conda-forge isoduration
  conda install -c conda-forge fqdn
  
  //最后启用配置
  jupyter contrib nbextension install --user
  ```
  
  ```bash
  //安装配置器
  conda install -c conda-forge jupyter_nbextensions_configurator
  
  //启用配置器
  jupyter nbextensions_configurator enable --user
  
  //安装nodejs包以便插件使用
  conda install -c conda-forge nodejs
  ```
  
  

## Python Grammar

#### 基础和语法糖

- **多行语句**

  ```python
  #可以使用反斜杠 \ 来实现多行语句
  total = item_one + \
          item_two + \
          item_three
  # 在 [], {}, 或 () 中的多行语句，不需要使用反斜杠 \
  total = ['item_one', 'item_two', 'item_three',
          'item_four', 'item_five']
  ```

- **字符串基础**

  ```python
  # r前缀表示原始字符串（raw string）适用于window中目录字符串
  # 如 r"this is a line with \n" 则 \n 会显示，并不是换行
  print(r"C:\ProgramApps\miniconda")
  
  # b前缀表示字节字符串（bytes string）用于处理二进制数据，而不是文本数据
  binary_data = b'\x48\x65\x6c\x6c\x6f'  # 字母 'Hello' 的字节表示
  
  # u前缀表示Unicode字符串, Python 3中，所有的字符串都是Unicode字符串
  
  # f前缀表示格式化字符串（formatted string）
  print(f"hello { val } ")
  
  # 字符串可以用 + 运算符连接，用 * 重复
  print("OK" * 3) # 输出 OKOKOK
  
  # 字符串两种索引方式，从左往右以 0 开始，从右往左以 -1 开始。
  str[0], str[-1], str[3, -2]
  
  # 切片 str[start:end]，其中 start（包含），end（不包含）
  # 切片可以加上步长参数 step，如下：str[start:end:step] 表每个step取一个
  ```

- **导入模块和函数**

  ```python
  在 python 用 import 或者 from...import 来导入相应的模块。
  
  将整个模块(somemodule)导入，格式为： import somemodule
  
  从某个模块中导入某个函数,格式为： from somemodule import somefunction
  
  从某个模块中导入多个函数,格式为： from somemodule import firstfunc, secondfunc, thirdfunc
  
  将某个模块中的全部函数导入，格式为： from somemodule import *
  ```

- **多变量赋值, 交换变量值**

  ```python
  a, b, c = 1, 2, "runoob"
  
  d = 100_0000_000 # 下划线分割数字
  
  e, f = 123, 456
  # 交换 e f 的值
  f, e = e, f
  ```

- **判断变量范围**

  ```python
  if 90 <= a <= 100
  	print("支持连续判断")
  ```

- **列表拼接**

  ```python
  a = [1, 2, 3]
  b = [4, 5, 6]
  c = a + b # 即 c = [1, 2, 3, 4, 5, 6]
  ```

- **切片**

  ```python
  # list[begin : end] 包含begin, 不包含end
  arr = [1, 2, 3, 4, 5, 6]
  arr[0 : 6]
  arr[0 : ], arr[ : -1], arr[ : ]
  arr[0 : -2]
  
  arr[2 : 4] = [8, 9]
  
  # list[begin : end : step]
  # 若step为复数则表示逆向读取
  arr[-1: : -1]
  ```

- **打包与解包**

  ```python
  a = [1, 2, 3]
  # 解包
  x, y, z = a # 自动赋值(个数要一致)
  
  #打包
  a = (y, z, x)
  ```

- **文件with**

  ```python
  with open('text.txt', 'r') as f:
      data = f.read()
  
  # 无效写f.close()
  ```

- **推导式**

  ```python
  b = [item for item in a]
  
  # 三元表达式
  x, y = 3, 4
  print(x if x > y else y)
  ```





#### 基本数据类型

- Number

  数值的除法包含两个运算符：/ 返回一个浮点数，// 返回一个整数(往下取)

  x**y 表示x的y次幂

  - int

    长整形

  - float

  - bool

    True为1, False为0

  - complex(复数)

- String

- bool

  - 即True和False

- List

- Tuple

  - 一个元素的Tuple要表示为` (ele, )`

- Set

  - 无序、可变的*元素不会重复*的数据类型
  - 普通Set用{ele1, ele2} 表示, 空Set要用Set()表示(用{}会被识别为字典)

- Dictionary

  - dict() 函数用于创建一个字典。

    ```python
    class dict(**kwarg)
    class dict(mapping, **kwarg)
    class dict(iterable, **kwarg)
    ```





- **数据类型的转换**

  ```python
  a = 9.0
  
  int(a)
  float(a)
  str(a)
  
  # repr 返回一个对象的string格式
  repr(a) # 表 'a'
  
  # eval 执行字符串表达式
  eval('a * 4') # 返回36
  
  # tuple(iterable)  可迭代序列转换为元组
  
  # list( seq ) 元组或字符串转换为list
  
  # set([iterable]) 可迭代序列转换为Set
  
  # chr(num) 返回数字的ASCII字符 chr(65)即 'A'
  
  # ord(char) 返回ASCII字符的数值 ord('A')即 65
  ```



- **运算符**

  `:=` 海象运算符, 同时进行赋值和返回赋值的值

  ```python
  if (n := len(a)) > 10:
      print(f'给n赋值同时用返回n的值 = { n }')
  ```

  ```python
  # 位运算赋 | & ^ ~ << >>
  # 逻辑运算符 and or not 即&& || ! 还是可以使用!= 表示
  # 成员运算符 in	not in 判断元素在序列中否(if a in arr: ...)
  # 身份运算符 is	is not 比较两个对象的存储单元(地址, 即id())
  ```



- 随机数

  | 函数                              | 描述                                                         |
  | --------------------------------- | ------------------------------------------------------------ |
  | choice(seq)                       | 从序列的元素中随机挑选一个元素，比如random.choice(range(10))，从0到9中随机挑选一个整数 |
  | randrange([start,\] stop [,step]) | 从指定范围内，按指定基数递增的集合中获取一个随机数，基数默认值为 1 |
  | random()                          | 随机生成下一个实数，它在[0,1)范围内。                        |
  | seed([x\])                        | 改变随机数生成器的种子seed。如果你不了解其原理，你不必特别去设定seed，Python会帮你选择seed。 |
  | shuffle(lst)                      | 将序列的所有元素随机排序                                     |
  | uniform(x, y)                     | 随机生成下一个实数，它在[x,y]范围内。                        |





#### 迭代器和生成器

- 迭代器

  ```python
  # 迭代器有两个基本的方法：iter() 和 next()
  
  list=[1,2,3,4]
  it = iter(list)    # 创建迭代器对象 下一次调用时迭代
  print (next(it))   # 输出迭代器的下一个元素 1
  print (next(it))   # 2
  
  
  list=[1,2,3,4]
  it = iter(list)    # 创建迭代器对象
  for x in it:       # 循环中使用
      print (x, end=" ")
      
  # 迭代完毕后再调用会 StopIteration
  ```

  ```python
  #创建
  class MyNumbers:
    def __iter__(self):
      self.a = 1
      return self
   
    def __next__(self):
      if self.a <= 20:
        x = self.a
        self.a += 1
        return x
      else:
        raise StopIteration
   
  myclass = MyNumbers()
  myiter = iter(myclass)
   
  print(next(myiter))
  print(next(myiter))
  print(next(myiter))
  print(next(myiter))
  print(next(myiter))
  ```

  

- 生成器

  ```python
  # 生成器函数中使用 yield 语句时，函数的执行将会暂停，并将 yield 后面的表达式作为当前迭代的值返回, 在此调用函数时, 函数会从上次暂停的地方继续执行，直到再次遇到 yield 语句形成循环
  
  def countdown(n):
      while n > 0:
          yield n
          n -= 1
   
  # 创建生成器对象
  generator = countdown(5)
   
  # 通过迭代生成器获取值
  print(next(generator))  # 输出: 5 在 yield n 为5处暂停返回
  print(next(generator))  # 输出: 4 继续运行 n-=1 后循环到 yield n 为4处暂停返回
  print(next(generator))  # 输出: 3
   
  # 使用 for 循环迭代生成器
  for value in generator:
      print(value)  # 输出: 2 1
      
      
  # Fibonacci数列示例
   
  import sys
   
  def fibonacci(n): # 生成器函数 - 斐波那契
      a, b, counter = 0, 1, 0
      while True:
          if (counter > n): 
              return
          yield a
          a, b = b, a + b
          counter += 1
  f = fibonacci(10) # f 是一个迭代器，由生成器返回生成
   
  while True:
      try:
          print (next(f), end=" ")
      except StopIteration:
          sys.exit()
  ```



#### 函数和Lambda

- Type Hint

  ```python
  # 在python中可以使用 : <type> 对变量的类型进行标注
  # 使用 -> <type> 对函数的返回值进行标注
  def add(a : int, b : int) -> int:
      ...
      
  # 若存在多种类型, 可以使用 | 
  def sub(a : int | float, b : int | float) -> int | float:
      ...
  ```

  

- 可变参数

  ```python
  # 一个 * 以元组形式导入
  def functionname([formal_args,] *var_args_tuple ):
     "函数_文档字符串"
     function_suite
     return [expression]
  
  
  # 两个 ** 以字典形式导入
  def functionname([formal_args,] **var_args_dict ):
     "函数_文档字符串"
     function_suite
     return [expression]
  
  
    
  # 可写函数说明
  def printinfo( arg1, *vartuple ):
     "打印任何传入的参数"
     print ("输出: ")
     print (arg1)
     print (vartuple)
   
  # 调用printinfo 函数
  printinfo( 70, 60, 50 )
  
  # 输出 输出: 
  70
  (60, 50)
  
  
  
    
  # 可写函数说明
  def printinfo( arg1, **vardict ):
     "打印任何传入的参数"
     print ("输出: ")
     print (arg1)
     print (vardict)
   
  # 调用printinfo 函数
  printinfo(1, a=2,b=3)
  
  # 输出: 
  1
  {'a': 2, 'b': 3}
  
  ```

- 匿名函数

  ```python
  # 使用lambda 定义一行的函数(相似但不等同于内联函数)
  
  lambda [arg1 [,arg2,.....argn]]:expression
  
  # 示例1
  f = lambda: "Hello, world!"
  print(f())  # 输出: Hello, world!
  x = lambda a : a + 10
  print(x(5))
  
  
  # 示例2
  #!/usr/bin/python3
   
  # 可写函数说明
  sum = lambda arg1, arg2: arg1 + arg2
   
  # 调用sum函数
  print ("相加后的值为 : ", sum( 10, 20 ))
  print ("相加后的值为 : ", sum( 20, 20 ))
  
  # 示例3
  def myfunc(n):
    return lambda a : a * n
   
  mydoubler = myfunc(2)
  mytripler = myfunc(3)
   
  print(mydoubler(11))
  print(mytripler(11))
  
  # 示例4 map filter 和 reduce
  numbers = [1, 2, 3, 4, 5]
  squared = list(map(lambda x: x**2, numbers))
  print(squared)  # 输出: [1, 4, 9, 16, 25]
  
  
  numbers = [1, 2, 3, 4, 5, 6, 7, 8]
  even_numbers = list(filter(lambda x: x % 2 == 0, numbers))
  print(even_numbers)  # 输出：[2, 4, 6, 8]
  
  
  from functools import reduce
  numbers = [1, 2, 3, 4, 5]
  # 计算乘积
  product = reduce(lambda x, y: x * y, numbers)
  print(product)  # 输出：120
  ```





#### 文件读写和异常

- 读写模式

  | 模式 | 描述                                                         |
  | ---- | ------------------------------------------------------------ |
  | r    | 以只读方式打开文件。文件的指针将会放在文件的开头。这是默认模式。 |
  | rb   | 以二进制格式打开一个文件用于只读。文件指针将会放在文件的开头。 |
  | r+   | 打开一个文件用于读写。文件指针将会放在文件的开头。           |
  | rb+  | 以二进制格式打开一个文件用于读写。文件指针将会放在文件的开头。 |
  | w    | 打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。 |
  | wb   | 以二进制格式打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。 |
  | w+   | 打开一个文件用于读写。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。 |
  | wb+  | 以二进制格式打开一个文件用于读写。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。 |
  | a    | 打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。也就是说，新的内容将会被写入到已有内容之后。如果该文件不存在，创建新文件进行写入。 |
  | ab   | 以二进制格式打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。也就是说，新的内容将会被写入到已有内容之后。如果该文件不存在，创建新文件进行写入。 |
  | a+   | 打开一个文件用于读写。如果该文件已存在，文件指针将会放在文件的结尾。文件打开时会是追加模式。如果该文件不存在，创建新文件用于读写。 |
  | ab+  | 以二进制格式打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。如果该文件不存在，创建新文件用于读写。 |

- 使用with

  ```python
  with open('/tmp/foo.txt', 'r') as f:
       read_data = f.read()
          
  
          """
  open(file, mode, buffering, encoding, errors, newline, closefd, opener)
      file: 必需，文件路径（相对或者绝对路径）。
      mode: 可选，文件打开模式
      buffering: 设置缓冲
      encoding: 一般使用utf8
      errors: 报错级别
      newline: 区分换行符
      closefd: 传入的file参数类型
      opener: 设置自定义开启器，开启器的返回值必须是一个打开的文件描述符。
  		"""
          
          
          
  f.write("content...") # 将 string 写入到文件中, 然后返回写入的字符数
  f.read() # 读取一定数目的数据, 然后作为字符串或字节对象返回
  f.readline() # 从文件中读取单独的一行
  f.readlines(sizehint) # 可选参数 sizehint,读取指定长度的字节
  ```



- 异常处理

  - 抛出异常 `raise`

    ```python
    x = 10
    if x > 5:
        raise Exception('x 不能大于 5。x 的值为: {}'.format(x))
    ```

  - 捕获和处理try except finally

    ```python
    try: 
        # 执行代码...
    except: # 可含多个except子句,最后一个except子句可以忽略异常的名称作通配符使用
        # 发生异常时执行的代码, 
    else: # else 子句将在 try 子句没有发生任何异常的时候执行
        # 没有异常时执行的代码
    finally: 
        #无论有没有异常都执行
    ```

    
