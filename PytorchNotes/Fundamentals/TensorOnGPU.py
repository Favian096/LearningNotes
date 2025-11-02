import torch
from Utils import printl

# tensor to gpu
printl('check GPU available')
device = 'cuda' if torch.cuda.is_available else 'cpu'
tensor = torch.rand(size=(2, 2))
print(tensor, 'tensor is on ', tensor.device)
tensor_gpu = tensor.to(device)
print(tensor_gpu, 'tensor_gpu is on ', tensor_gpu.device)

# transform tensor on gpu to numpy on cpu
# ndarray only works on cpu, the tensor.cpu() function only copy to cpu
printl('transform tensor_gpu to numpy')
numpy_array = tensor_gpu.cpu().numpy()
print(numpy_array)
