# Python



## Anaconda(minianaconda)

#### 配置镜像

---

- ###### user目录下的.condarc文件配置镜像(复制粘贴即可)

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
  ```





#### 数据类型

- Number

  数值的除法包含两个运算符：/ 返回一个浮点数，// 返回一个整数

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
  
  ```

  
