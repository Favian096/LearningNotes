import os

import torch
import matplotlib

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from Utils import printl

# torch.manual_seed(100)

print(torch.rand(1))

loss = torch.tensor(0.75, requires_grad=True)
print(loss.requires_grad)  # 输出: True

loss.detach().numpy()
# 直接调用 loss.numpy() 会报错, 需要分离grad

tensor_squeeze = torch.tensor([1, 2, 3, 4])
print(tensor_squeeze.unsqueeze(dim=1).unsqueeze_(dim=2).unsqueeze_(dim=3).shape)
printl()

# calculate the sigmoid
tensor_0 = torch.tensor([0.9213, 1.0887, -0.8858, -1.7683])
tensor_exp = torch.exp(-tensor_0) + 1
print(1 / tensor_exp)

printl()

# test torch.eq()
tensor_1 = torch.tensor([1, 0, 1, 0, 0, 1])
tensor_2 = torch.tensor([0, 0, 1, 0, 0, 1])
print(torch.eq(tensor_1, tensor_2))
print(torch.eq(tensor_1, tensor_2).sum().item())

printl('test activate function')
tensor_3 = torch.arange(-10, 10, 1)


# test relu function demo: make negative number to zero
def relu(tensor_input: torch.Tensor) -> torch.Tensor:
    return torch.maximum(torch.tensor(0), tensor_input)


def sigmoid(tensor_input: torch.Tensor) -> torch.Tensor:
    return 1 / (1 + torch.exp(-tensor_input))


print(relu(tensor_3))
plt.figure(figsize=(8, 6))
plt.plot(tensor_3, relu(tensor_3))
plt.title('same as wikipedia')
# plt.show()

print(sigmoid(tensor_3))
plt.figure(figsize=(8, 6))
plt.plot(tensor_3, sigmoid(tensor_3))
plt.title('sigmoid function')
# plt.show()

# test cnn conv2d model layer
printl('conv2d test')
tensor_4 = torch.randn(size=(4, 2, 8, 8))
conv2d = torch.nn.Conv2d(
    in_channels=2,
    out_channels=4,
    kernel_size=3,
    stride=1,
    padding=0
)
print(tensor_4.shape)
print(conv2d(tensor_4).shape)  # 3 -> 10
max_pool_stride_test = torch.nn.MaxPool2d(kernel_size=2, stride=2)
print(max_pool_stride_test(tensor_4).shape)

# test cnn MaxPool2d layer
printl('MaxPool2d test')
max_pool_layer = torch.nn.MaxPool2d(kernel_size=2)
conv2d_test = torch.nn.Conv2d(
    in_channels=1,
    out_channels=3,
    kernel_size=3,
    stride=1,
    padding=0
)
print(max_pool_layer)
tensor_5 = torch.tensor(
    [[[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]]]
).to(dtype=torch.float)
print('tensor input size: ', tensor_5.shape)
print('tensor after conv2d size: ', conv2d_test(tensor_5).shape)
print('tensor after MaxPool2d size: ', max_pool_layer(tensor_5).shape)
print('tensor after conv2d and MaxPool2d size: ', max_pool_layer(conv2d_test(tensor_5)).shape)
print('tensor input : ', tensor_5)
print('tensor after conv2d: ', conv2d_test(tensor_5))
print('tensor after MaxPool2d: ', max_pool_layer(tensor_5))
print('tensor after conv2d and MaxPool2d: ', max_pool_layer(conv2d_test(tensor_5)))

# avgPool2d test
printl('avgPool2d test')
avg_pool_layer = torch.nn.AvgPool2d(kernel_size=2)
print(avg_pool_layer(tensor_5).shape)
print(avg_pool_layer(tensor_5))

# flatten test
printl('flatten test')
tensor_6 = torch.randn(size=(4, 2, 8, 8, 2))
flatten = torch.nn.Flatten(start_dim=1, end_dim=-2)
print(flatten(tensor_6).shape)

# softmax test
printl('softmax test')
tensor_7 = torch.randn(size=(2, 4))
print(torch.softmax(tensor_7, dim=0))  # col
print(torch.softmax(tensor_7, dim=1))  # row

# torch vision's transform test
from torchvision import transforms
from pathlib import Path
from PIL import Image

img_path = Path('./CustomDataSets/DataSets/Pizza_Steak_Sushi/test/pizza/194643.jpg')
img = Image.open(img_path)
printl('vision transform test')
img_transform = transforms.Compose([
    transforms.Resize(size=(1024, 1024)),
    transforms.ToTensor()
])
plt.figure(figsize=(8, 8))
plt.imshow(img_transform(img).permute(1, 2, 0))
# plt.show()


# test num of cpu core
printl('cpu core')
print(os.cpu_count())

t1 = torch.tensor([[0, 4.3, 1], [2, 4, 1.2], [9.2, 7, 1]])
t2 = torch.tensor([1, 2, 0])

print(torch.softmax(t1, dim=1).argmax(dim=1).eq(t2).float().mean())
