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

### [DataPrepare](./NeuralNetworkClassification/DataPrepare.py)

> 使用sklearn绘制一个圆圈, 并划分训练集和测试集
>
> ```python
> X, y = sklearn.datasets.make_circles(n_samples, noise=0.03, random_state=42)
> 
> x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(
>     x_set, y_set,test_size=0.2,random_state=42)
> ```





### BuildModel

> [!Note]
>
> - 初始数据是非线性的, 这里分别构建线性和非线性模型

 - [线性基础模型](./NeuralNetworkClassification/BuildModel.py)

   ```python
   class CircleModelV0(nn.Module):
       def __init__(self):
           super().__init__()
           self.layer_1 = nn.Linear(in_features=2, out_features=5)
           self.layer_2 = nn.Linear(in_features=5, out_features=1)
           
       def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
           return self.layer_2(self.layer_1(input_tensor))
   ```

   模型中定义了两个线性层, 传播为`layer_1`->`layer_2`的线性顺序, 

   因此可以使用`nn.Sequential()`直接定义层次顺序(该函数会按参数顺序向前传播):

   ```python
   class CircleModelV0(nn.Module):
       def __init__(self):
           super().__init__()
           self.layer = nn.Sequential(
               nn.Linear(in_features=2, out_features=5),
               nn.Linear(in_features=5, out_features=1)
   )
   
   def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
   return self.layer(input_tensor)

- [非线性模型](./NeuralNetworkClassification/BuildNonLinearModel.py)

  ```python
  class CircleModelV1(nn.Module):
      def __init__(self):
          super().__init__()
          self.layer_1 = nn.Linear(in_features=2, out_features=10)
          self.layer_2 = nn.Linear(in_features=10, out_features=10)
          self.layer_3 = nn.Linear(in_features=10, out_features=1)
          # set a ReLU activate function
          self.relu = nn.ReLU()
  
      def forward(self, input_tensor: torch.Tensor):
          # put non-linear activate function in hide layers between in common
          return self.layer_3(self.relu(self.layer_2(self.layer_1(input_tensor))))
  ```
  
  > 非线性模型在原有的线性层中添加了[ReLU激活函数](./ActivationFunction.py))
  
  

### TrainModel

> [!Note]
>
> 分别训练线性和非线性模型
>
> - 由于模型输入为浮点数, 真实值为0或1, 故将模型的输出经过[sigmoid函数](./ActivationFunction.py)然后取整

- [训练线性模型](./NeuralNetworkClassification/TrainModel.py)
- [训练非线性模型](./NeuralNetworkClassification/TrainNonLinearModel.py)



### [MultipleClassification](./NeuralNetworkClassification/MultipleClassification.py)

> [!Note]
>
> - 这里使用sklearn的[`make_blobs()`](https://scikit-learn.org/stable/modules/generated/sklearn.datasets.make_blobs.html)来创建多类别数据作多分类

- 一些评估指标

| **度量标准名称/评估方法**                                    | **定义**                                                     | **可参考代码**                                               |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 准确性                                                       | 在 100 个预测中，你的模型有多少次预测是正确的？例如，95%的准确率意味着模型在 100 个预测中有 95 次是正确的 | [`torchmetrics.Accuracy()`](https://torchmetrics.readthedocs.io/en/stable/classification/accuracy.html#id3) 或 [`sklearn.metrics.accuracy_score()`](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.accuracy_score.html) |
| 精确性                                                       | 真阳性结果所占的比例与样本总数的比例。精度越高，误报就越少（模型在应该为 0 的情况下预测为 1 的情况就会减少） | [`torchmetrics.Precision()`](https://torchmetrics.readthedocs.io/en/stable/classification/precision.html#id4) 或 [`sklearn.metrics.precision_score()`](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.precision_score.html) |
| 召回                                                         | 真正阳性结果所占的比例与总真正阳性结果数量的比例。模型预测为 1 时实际结果为 0 的情况就被称为假阴性。召回率越高，假阴性的数量就越少 | [`torchmetrics.Recall()`](https://torchmetrics.readthedocs.io/en/stable/classification/recall.html#id5) 或 [`sklearn.metrics.recall_score()`](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.recall_score.html) |
| F1 分数                                                      | 将精确度和召回率合并为同一个评价指标。数值为 1 表示最佳，为 0 则表示最差 | [`torchmetrics.F1Score()`](https://torchmetrics.readthedocs.io/en/stable/classification/f1_score.html#f1score) 或 [`sklearn.metrics.f1_score()`](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.f1_score.html) |
| [混淆矩阵](https://www.dataschool.io/simple-guide-to-confusion-matrix-terminology/) | 以表格形式比较预测值与真实值。如果完全正确，则矩阵中的所有值都将从左上角到右下角显示（即对角线方向） | [`torchmetrics.ConfusionMatrix`](https://torchmetrics.readthedocs.io/en/stable/classification/confusion_matrix.html#confusionmatrix) 或 [`sklearn.metrics.plot_confusion_matrix()`](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.ConfusionMatrixDisplay.html#sklearn.metrics.ConfusionMatrixDisplay.from_predictions) |
| 分类报告                                                     | 收集了一些主要的分类度量指标，例如精确度、召回率以及 F1 分数 | [`sklearn.metrics.classification_report()`](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.classification_report.html) |



## 03.ComputerVIsion



## 04.CustomDataSets



## 05.TransferLearning





## Resources 

### Onnx for Java

> [!Note] 
>
> 模型通常使用任何知名训练框架进行训练并导出为 ONNX 格式, 代码通常使用 Java 10 及更高版本可用的语法
>
> - 基本流程代码
>
> ```java
> import ai.onnxruntime.*;
> import java.nio.file.Paths;
> import java.util.Map;
> 
> public class OnnxInferenceExample {
>     public static void main(String[] args) throws OrtException {
>         OrtEnvironment env = OrtEnvironment.getEnvironment("env_name");
>         OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
>         OrtSession session = env.createSession("./best.onnx", sessionOptions);       
>         
>         float[] inputData = ...; 
>         long[] shape = {1, 3, 640, 640}; 
> 
>         OnnxTensor inputTensor = OnnxTensor.createTensor(env,FloatBuffer.wrap(inputData), shape);
> 
>         Map<String, OnnxTensor> inputs = Map.of("images", inputTensor); 
> 		OrtSession.Result result = session.run(inputs)
>         OnnxTensor outputTensor = (OnnxTensor) result.get(0); 
>         float[] outputData = outputTensor.getFloatBuffer().array();
>         System.out.println(outputTensor.getInfo().getShape());
>     }
> }
> ```

- 首先创建 `OrtEnvironment`, 即设定运行环境, 该环境是**单例**的, 可传入环境名称

  ```java
  OrtEnvironment env = OrtEnvironment.getEnvironment("ONNX-RUNTIME");
  ```

- 导入模型前, 需要配置参数`OrtSession.SessionOptions()`, 该参数用于配置引入模型的具体配置, 如线程数, 是否使用cuda等

  通过模型文件路径和配置参数, 可在env下引入多个不同的onnx模型

  ```java
  // 默认使用 CPU
  OrtSession.SessionOptions options = new OrtSession.SessionOptions();
  session = env.createSession("./best.onnx", options);
  
  // 使用 GPU, 配置自定义参数
  OrtSession.SessionOptions opt2 = new OrtSession.SessionOptions();
  opt2.addCUDA(0);                    // 启用 CUDA
  opt2.setIntraOpNumThreads(8);       // 线程配置
  opt2.setInterOpNumThreads(4);
  opt2.setOptimizationLevel(OrtSession.SessionOptions.OptLevel.BASIC_OPT); // 不同优化级别
  OrtSession session2 = env.createSession("./best.onnx", opt2);
  ```

  多个`OrtSession`可以复用同一个`SessionOptions`

- 定义模型后, 通常需要整理输入: 通常使用`Map<String,OnnxTensor>`

  `OnnxTensor`为` session.getInputInfo()`中指定形状shape的张量对象

  ```java
  // 检查输入的形状
  long[] shape = Arrays.toString(((TensorInfo) session.getInputInfo().get("images").getInfo()).getShape())
      
  // 随机数的张量示例
  long size = 1;
  for (long dim : shape) {
      size *= dim;
  }
  float[] inputData = new float[(int) size];
  Random rand = new Random(42);
  for (int i = 0; i < size; i++) {
      inputData[i] = rand.nextFloat() * 2 - 1;
  }
  OnnxTensor inputTensor = OnnxTensor.createTensor(env, FloatBuffer.wrap(inputData), shape);
  ```
  
- 运行推理, 通常`session.run()`期待接收一个`Map<String, OnnxTensor>`格式

  其中`String`为`session.getInputNames()`中的名称

  ```java
  Map<String, OnnxTensor> inputs = Map.of("images", inputTensor);
  OrtSession.Result results = session.run(inputs);
  ```

- 推理返回的结果为`OrtSession.Result`类型, 该对象是autoClose的(包括其子对象)

  ```java
  System.out.println(imgResults.get(0));
  System.out.println(imgResults.size());
  imgResults.forEach(entry -> {
      OnnxTensor outputTensor = (OnnxTensor) entry.getValue();
      long[] outputShape = outputTensor.getInfo().getShape();
      try {
          System.out.println("name:" + entry.getKey());
          System.out.println("shape:" + Arrays.toString(outputShape));
          float[] outputData = outputTensor.getFloatBuffer().array();
          System.out.println(Arrays.toString(outputData));
      } catch (Exception e) {
           throw new RuntimeException(e);
      }
  });
  ```

​	该结果可在python的onnxruntime框架中作输出对比, 依据情况判定后续处理方式
