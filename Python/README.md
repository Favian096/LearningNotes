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

- 



## Jupyter NoteBook

#### 基本使用

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
  
  

