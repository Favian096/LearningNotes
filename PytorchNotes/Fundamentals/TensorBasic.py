import torch
from Utils import printl

print(torch.__version__)
print(torch.cuda.is_available())
print(torch.cuda.device_count())
print(torch.cuda.get_device_name(0))

printl('tensor basic info')

scalar = torch.tensor(7)
print(scalar)
print(scalar.ndim)
print(scalar.item())

vector = torch.tensor([3, 8])
print(vector)
print(vector.ndim)
print(vector.shape)

matrix = torch.tensor([[1, 2, 3], [4, 5, 6]])
print(matrix)
print(matrix.ndim)
print(matrix.shape)

tensor = torch.tensor([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
print(tensor)
print(tensor.ndim)
print(tensor.shape)

# random_tensor = torch.rand(3, 4)
random_tensor = torch.rand(size=(3, 4))
print(random_tensor)
print(random_tensor.ndim)
print(random_tensor.shape)

zeros_tensor = torch.zeros(2, 3)
print(zeros_tensor)

ones_tensor = torch.ones(3, 6)
print(ones_tensor)

zero_ten = torch.arange(0, 10, 3)
print(zero_ten)

four_num = torch.zeros_like(zero_ten)
print(four_num)
four_num = torch.ones_like(zero_ten)
print(four_num)

float32_tensor = torch.tensor([3.0, 4.0, 4.0], dtype=None, device=None, requires_grad=False, pin_memory=False)
print(float32_tensor.shape)
print(float32_tensor)
print(float32_tensor.dtype)
print(float32_tensor.device)

float16_tensor = torch.tensor([3.9, 4.0], dtype=torch.float16, device=0)
print(float16_tensor.dtype)
print(float16_tensor.device)
print(float16_tensor.is_cuda)

some_tensor = torch.rand(size=(3, 4)).to(device='cuda')
print('some tensor\'s shape: ', some_tensor.shape)
print('some tensor\'s data type: ', some_tensor.dtype)
print('some tensor\'s device: ', some_tensor.device)
