# Vim Notes
## 基本的4种模式
- Normal模式: 即命令模式, 默认模式
- Insert模式: 插入模式, 进行文本编辑
- Command模式: 命令行模式, 可在底部输入命令执行
- Visual模式: 可视模式, 可进行文本选择

### Normal->命令模式 
#### 基本移动
- j 下
- k 上
- h 左
- l 右
- gg 光标调到第一行
- G 光标调到最后一行
- \<Ctrl-u> 向上翻半页
- \<Ctrl-d> 向下翻半页
- \<Ctrl-b> 向上翻一页
- \<Ctrl-f> 向下翻一页
- 15gg 跳转到第15行
- zz 设置光标行 屏幕居中
- zt 设置光标行 屏幕首行
- zb 设置光标行 屏幕尾行
#### 基本操作
- w 跳转到下一处单词的开头
- b 跳转到上一处单词的开头
- e 跳转到下一处单词的结尾
- ge 跳转到上一处单词的结尾
- m{mark} 将当前位置标记为mark, mark可以是a-z的字符
- `{mark} 跳转到标记为mark的位置
- `` 回到上次跳转前的位置
- `. 回到上次修改的位置
- `^ 回到上次插入的位置
- ^ 跳转到行首
- $ 跳转到行尾
- % 跳转到匹配的配对符处
#### 基本搜索
- f{char} 行内搜索char字符出现的位置
- t{char} 行内搜索char字符出现前的位置
- ; 向后重复f或t的{char}搜索
- , 向前重复f或t的{char}搜索
- /{pattern} 文件内搜索下一个{pattern}出现的地方
- ?{pattern} 文件内搜索上一个{pattern}出现的地方
- {pattern} 可以是正则表达式
- n 向后重复pattern的搜索
- N 向前重复pattern的搜索
- * 将光标下的字符作为查找内容进行搜索
#### 基本编辑
- c 修改内容, 即删除内容并进入插入模式
- d 删除
- y 复制
- p 粘贴
- x 剪切, 删除光标下的字符
- s 删除光标下的字符并进入插入模式(修改光标处字符)
- v 选中文本
- dd 删除这一行
- cc 修改这一行
- yy 复制这一行
- u 撤销上一次操作
- \<Ctrl-r> 恢复上一步被撤销的操作(和u相反)
- . 表示重复上一次操作
#### 组合编辑(operator + motion; number + openator +  action)
- dgg 删除到第一行
- dG 删除到最后一行
- ye 复制到单词结尾
- d$ 删除到行尾
- d^ 删除到行首
- dt{char} 删除到 char 为止
- d% 删除匹配符内的内容
- dw 删除到下一个单词前
- ......
- 4j 向下移动4行
- 3dw 删除3个单词
- 2yy 复制2行
- 4p 粘贴4次
- ......



### Insert->插入模式
#### 进入Insert模式
- i 表示从当前光标之*前*开始插入
- a 表示从当前光标之*后*开始插入
- o 在当前行*下方*插入一行进行输入
- s 删除当前光标的字符然后开始输入
- I 在当前行的开头开始输入
- A 在当前行的末尾开始输入
- O 在当前行的*上方*插入一行进行输入
- S 删除当前行开始输入

### Command->命令行模式
#### 进入Command模式
- 在Normal模式下输入 : 即可进入
- :w : 保存当前文件
- :q : 退出文件 
- :wq: 保存并退出
- :q!: 不保存退出
- :h {command} : 显示关于command命令的帮助
#### 基本操作
- Ex 命令格式 :[range] {excommand} [args]
  - range 表示作用域, 不给的话默认是本行
    - range 可以由一个或两个{address}构成, 即{address}或者{address},{address}
    - address可以是 {lineno} 即行号, 如 3 表示第三行
      $ 表示最后一行
      % 表示文件的所有行(相当于1,$)
      '< >'在可视模式下按:会自动将选中的区域作为range
      . 表示光标所在行
      /pattern/ 表示下一个pattern所在行
     - address可以作加减法, 如 .+3 表示光标往下第三行; $-3表示倒数第4行
     - 1, 3 表示1到3行; .,.+4 表示光标到光标往下4行; $-3,$ 表示最后4行
  - excommand 即为命令
    - :[range] copy {address} 复制range中的行到address后面
    - :[range] move {address} 移动range中的行到address后面
    - :[address] put [x] 吧寄存器x中的内容粘贴到address后面
  - args 表后续参数
- 命令示例: 
  - :[range] delete [x] 表示删除range中的行到寄存器x中, delete可以简写为d
  - :[range] print 表示将range中的行打印出来(在最下面), print可以简写为p
#### 批量操作normal
- 格式 :[range] normal {commands} 表示对**range中的所有行执行normal模式下的命令commands**
- 示例操作: 
  - :% normal . 表示对所有行执行上一次的命令
  - :[range] normal @register 表示对range中的行运行宏操作
#### 批量操作global
- 格式 :[range] global /{pattern}/[cmd] 表示对**range中包含pattern的所有行执行Command模式下的命令, 可以嵌套复合**
  - :[range] global/{pattern}/normal {commands}
- 示例操作:
  - :.-3,$-1 global/dudu/normal d 表示删除范围内所有带dudu的行
#### 替换命令
- :[range]s/{pattern}/{string}/[flags] 表示将pattern替换为string
- flags可以是: 
  - g 表示替换每一行的所有匹配
  - i 表示忽略大小写
  - c 表示替换前进行确认
  - n 表示计数而不替换
- 示例:
  - :%s/Vim//gn 表示统计所有行中Vim出现的次数(加了n就只记数)

### Visual->可视模式
#### 进入可视模式
- 在Normal模式下输入 v 即可进入
- 在Normal模式下输入 V 即可进入行选中模式



## 文本对象操作
### 文本对象
- 基本格式:  i/a + 对象
- i 表示inner, 对象内部
- a 表示包括对象周围空格或配对符
- 常见对象: 
  - w/W  表示一个单词
  - S  表示句子
  - p  表示段落
  - (/), [/], {/}, </>, '/"  即配对符

### 基本操作
- diw  表示删除光标所处位置的单词
- ci(  表示修改小括号内部(删除小括号内部内容并开始输入)
- yi{  表示复制大括号内部
- ya[  表示中括号和内容全部复制
- viw  表示选中当前单词
- ......



## 寄存器(剪贴板)
- 一个字符对应一个寄存区(a-z), (0-9); 寄存器主要用来持久保存文本
- (A-Z)对应(a-z), 但大写字符表追加而非覆盖
- 可以通过 :reg {register} 查看寄存器的内容
- 一些特别的寄存器: 
  - " 是vim默认寄存器
 - % 是当前文件名
  - . 是上一次插入的内容
  - : 是上一次执行的命令
- 使用指定的寄存器: ( " {register} {operator} )
  - "ayy : 将这一行内容复制到a寄存器
  - "bdiw : 删除单词, 并保存到b寄存器
  - "cp : 将c寄存器的内容粘贴出



## 宏(录制键盘操作并允许重放)
### 基本操作
- 按 q{register} 开始录制宏, 存在寄存器{register}中
- 录制过程中按 q 退出录制
- 按 @{register} 重放存在{register}中的操作
- 按 @@ 重放上一次宏操作(按 . 不会重放宏操作)



## 命令与操作符补充
- g\<Ctrl-A>  将已标序号的序列创建为递增序列
- J  连接两行(将下一行连接到此行尾部)
- \<Ctrl-a>  递增光标处数字
- \<Ctrl-x>  递减光标处数字
- gu  将光标处字母转小写
- gU  将光标处字母转大写
- g~  将光标处字母切换大小写
- \<  左缩进
- \>  右缩进
- 3>  3行右缩进



## 插件管理

### vim的插件管理器

- 示例使用 plug.vim (放在vim安装文件夹下的**vim[xx]\autoload**下

### 基本使用

- :source %  表示刷新
- :PlugStatus 查看插件安装状态
- :PlugInstall 安装插件
- :PlugClean 清理插件
