# Python



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
  conda install -c conda-forge jupyter_contrib_nbextensions
  
  jupyter contrib nbextension install --user
  ```

  ```bash
  conda install -c conda-forge jupyter_nbextensions_configurator
  
  jupyter nbextensions_configurator enable --user
  ```

  

