# Pytorch Notes

> This learning note can refer to [learnpytorch](https://www.learnpytorch.io/)



## 00.Fundamentals

> PyTorch 是一个开源的机器学习和深度学习框架

### [Tensor Basic](./Fundamentals/TensorBasic.py)

> [!NOTE]
>
> 张量的定义类似于多维数组
>
> 使用 `ndim` 属性来检查张量的维度
>
> 使用 `shape` 属性来检查张量的形状

- 单个数字的张量称为*标量*, 亦称为*零维张量*

  ```python
  scalar = torch.tensor(7)
  ```

- 可以通过 PyTorch 张量一侧的中括号数量来判断其维度的数量（ `[` ）

- `零和一`张量, 零值来替代某个张量中的某些值, 这样模型就不会去学习这些值

- 定义张量是可配置张量的数据类型, 适合用于GPU或单CPU



### [TensorInfo](./Fundamentals/TensorInfo.py)

> [!NOTE]
>
> 对于张量, 通常关系其`形状, 数据类型, 存储位置`:`tensor.shape tensor.dtype tensor.device`

- 张量的计算通常用Pytorch的内置函数实现, 也可以使用`+ - * /`运算符

- 张量矩阵的乘法可使用运算符`@`实现, 但通常使用Pytorch的内置函数`torch.(A, B)`

- 张量矩阵的转置可使用`tensor.T`, 亦可使用`torch.transpose(B, 1, 0)`

  > 其中, 矩阵后的参数位置依次表示矩阵的每个维度, 填入的参数表示该维度要放哪一个维度的张量
  >
  > 即矩阵B, 0维的位置放1维的数据, 1维的位置放0维的数据



### [TensorAggregation](./Fundamentals/TensorAggregation.py)

> [!NOTE]
>
> 张量的汇总计算

- 可汇总张量的最小值、最大值、平均值、总和

- 获取最大值/最小值的位置, 更改张量的数据类型

- 张量的重塑、堆叠、压缩与解压

  > | 方法                                                         | 单行描述                                                     |
  > | ------------------------------------------------------------ | ------------------------------------------------------------ |
  > | [`torch.reshape(input, shape)`](https://pytorch.org/docs/stable/generated/torch.reshape.html#torch.reshape) | 将 `input` 重新转换为 `shape` （变换形状）, 也可以使用 `torch.Tensor.reshape()` |
  > | [`Tensor.view(shape)`](https://pytorch.org/docs/stable/generated/torch.Tensor.view.html) | 该函数以不同的形状呈现原始张量，但保留了与原始张量相同的数据。 |
  > | [`torch.stack(tensors, dim=0)`](https://pytorch.org/docs/1.9.1/generated/torch.stack.html) | 将序列中的 `tensors` 元素沿新维度 `dim` 进行串联，所有 `tensors` 元素的尺寸必须相同 |
  > | [`torch.squeeze(input)`](https://pytorch.org/docs/stable/generated/torch.squeeze.html) | 对 `input` 以移除指定单一的维度                              |
  > | [`torch.unsqueeze(input, dim)`](https://pytorch.org/docs/1.9.1/generated/torch.unsqueeze.html) | 与squeeze相反, 用于在制定位置添加一个维度                    |
  > | [`torch.permute(input, dims)`](https://pytorch.org/docs/stable/generated/torch.permute.html) | 置换操作会返回原始 `input` 的视图，但其尺寸被重新排序为 `dims` 的格式 |
  >
  > > 置换操作返回的是一个视图(共享与原始数据相同的数据), 因此置换后的张量中的数值会与原始张量中的数值相同, 如果你改变了视图中的数值, 那么原始数据的数值也会随之改变

- `permute`和`transpose`是Pytorch的两大转置函数

  > ```python
  > torch.transpose(input, dim0, dim1, out=None) → Tensor
  > ```
  >
  > 函数返回输入矩阵`input`的转置 -> 交换维度`dim0`和`dim1`
  >
  > ```python
  > permute(dims) → Tensor
  > ```
  >
  > 将tensor的维度换位 -> 
  >
  > 如tensor是3维的, 那么permute接收3个参数, 依次是0维的位置, 1维的位置, 2维的位置
  >
  > 每个位置可填0/1/2, 表示该维度交换为哪一个维(所有tensor.permute(0, 1, 2)表示不交换任何维度)



### [TensorNumpy](./Fundamentals/TensorNumpy.py)

> [!NOTE]
>
> 张量和Numpy之间的转换
>
> - [`torch.from_numpy(ndarray)`](https://pytorch.org/docs/stable/generated/torch.from_numpy.html) – 从 NumPy 数组转换为 PyTorch 张量
> - [`torch.Tensor.numpy()`](https://pytorch.org/docs/stable/generated/torch.Tensor.numpy.html) – PyTorch 张量 -> NumPy 数组
>
> 需要注意的是: numpy仅工作在CPU上, 当张量处于GPU上时需要先转到CPU上再转为numpy数组
>
> ```python
> tensor.cpu().numpy()
> ```



## 01.Workflows

### [DataPrepare](./Workflows/DataPrepare.py)

> 数据预备, 这里简单的配置了线性的数据点



### [BuildModel](./Workflows/BuildModel.py)

> [!NOTE]
>
> | PyTorch 模块                                                 | 功能作用                                                     |
> | ------------------------------------------------------------ | ------------------------------------------------------------ |
> | [`torch.nn`](https://pytorch.org/docs/stable/nn.html)        | 包含了计算图的所有基本要素                                   |
> | [`torch.nn.Parameter`](https://pytorch.org/docs/stable/generated/torch.nn.parameter.Parameter.html#parameter) | 存储可以与 `nn.Module` 结合使用的张量, 如果自动计算出用于通过梯度下降法更新模型参数的 `requires_grad=True` 梯度值, 这通常被称为“自动梯度计算” |
> | [`torch.nn.Module`](https://pytorch.org/docs/stable/generated/torch.nn.Module.html#torch.nn.Module) | 所有神经网络模块的基础类，神经网络的所有构建模块都属于此类子类, 同时，还需要实现 `forward()` 方法 |
> | [`torch.optim`](https://pytorch.org/docs/stable/optim.html)  | 包含多种优化算法（这些算法指导存储在 `nn.Parameter` 中的模型参数如何进行调整，以优化梯度下降过程，从而降低损失） |
> | `def forward()`                                              | 所有 `nn.Module` 子类都包含一个 `forward()` 方法，这个方法定义了将应用于传递给特定 `nn.Module` 的数据的计算过程 |

- 模型的定义通常由类继承`nn.Module`, 初始化时定义模型的计算参数, 然后在`forward`中定义计算流程

- 检查模型的内容, 通常可用`model.parameters()`检查其参数值, 用`model.state_dict()`检查其参数

- 模型推理, 使用`torch.inference_model()`可关闭模型推理不需要的一系列功能, 使得`forward()`更快 

  >     with torch.inference_mode():
  >         pred_tensor = model(input_tensor)



### [TrainModel](./Workflows/TrainModel.py)

> [!Note]
>
> - 为了让模型能够自行更新参数, 需要*损失函数*和*优化器*
>
>   | 功能         | 作用                                               | 模块位置                                                     | 备注                                                         |
>   | ------------ | -------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
>   | **损失函数** | 该指标用于衡量模型预测结果与实际标签之间的误差程度 | PyTorch 在 [`torch.nn`](https://pytorch.org/docs/stable/nn.html#loss-functions) 中提供了许多内置的损失函数 | 对于回归问题，绝对误差均值（MAE）用于衡量预测值与真实值的差距程度; 而对于二元分类问题，二元交叉熵则用于评估分类结果的准确性... |
>   | **优化器**   | 告诉你的模型如何更新其内部参数，以尽可能降低损失   | 在 `torch.optim` 中可以找到多种优化函数的实现方式            | 随机梯度下降法（ [`torch.optim.SGD()`](https://pytorch.org/docs/stable/generated/torch.optim.SGD.html#torch.optim.SGD) ）Adam 优化器（ [`torch.optim.Adam()`](https://pytorch.org/docs/stable/generated/torch.optim.Adam.html#torch.optim.Adam) ）... |

- 模型训练基本工作流:

  ```mermaid
  graph LR
  A["forward"] --> B["calc loss"] --> C["zero grad"] --> D["backwords"] --> E["optimizer"] --> F["eval"] --> A
  ```

  ```python
  for epoch in range(epochs):
      model.train()	# 0. switch to train model
      pred_tensor = model(x_tensor)	# 1. model input forward
      loss = loss_nn(pred_tensor, y_tensor)	# 2. calculate loss
      optimizer.zero_grad()	# 3. grad to zero. By default, grad is accumulated
      loss.backward()		# 4. backwards
      optimizer.step()	# 5. optimizer
  
      # evaluation mode with test data
      model.eval()	# 6. evaluate
      with torch.inference_mode():
          test_pred = model(x_test) # test evaluate data
          test_loss = loss_nn(test_pred, y_test)	# calculate evaluate data loss
          print('Epoch: ', epoch, '\t train loss: ', loss, '\t test loss: ', test_loss)
  ```



### [SaveLoadModel](./Workflows/SaveLoadModel.py)

> [!Note]
>
> - 模型的保存和加载, 主要是用`.pt`和`pth`后缀格式:
>
>   其中`.pt`用于保存完整的模型; `.pth`用于保存模型的权重和参数(即state_dict())

- 保存和加载模型state_dict[**推荐方式**]

  ```python
  torch.save(obj=model.state_dict(), f="./model_state_dict.pth")
  
  state_dict_model = LinearRegressionModel()
  state_dict_model.load_state_dict(torch.load("./model_state_dict.pth"))
  ```

- 保存和加载完整模型

  ```python
  torch.save(obj=model_0, f="./model.pt")
  
  whole_model:ModelClass = torch.load("./model.pt", weights_only=False)
  ```

- 保存和加载模型TorchScript

  ```python
  torch.jit.script(model).save("./model_script.pth")
  
  scripted_model = torch.jit.load("./model_script.pth")
  ```

  

## 02.NeuralNetworkClassification



## 03.ComputerVIsion



## 04.CustomDataSets



## 05.TransferLearning

