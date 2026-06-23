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



## 02.NeuralNetworkClassification



## 03.ComputerVIsion



## 04.CustomDataSets



## 05.TransferLearning

