import torch
import numpy as np
from Utils import printl

printl('convert between torch and numpy')
# transform tensor and ndarray
tensor_matrix = torch.rand(size=(2, 3, 2))
ndarray_matrix = np.random.randint(1, 5, size=(2, 2, 3))
tensor_to_ndarray = tensor_matrix.numpy()  # convert
ndarray_to_tensor = torch.from_numpy(ndarray_matrix)  # convert
print(tensor_to_ndarray)
print(ndarray_to_tensor)

printl('random tensor and seed')
rand_tensor_A = torch.rand(size=(2, 2))
rand_tensor_B = torch.rand(size=(2, 2))
# print(torch.equal(rand_tensor_A, rand_tensor_B))
print(rand_tensor_A == rand_tensor_B)

# set seed for tensor
torch.manual_seed(seed=222)
rand_tensor_C = torch.rand(size=(2, 2))
# have to reset the seed every time when a new tensor is created
torch.manual_seed(seed=222)
rand_tensor_D = torch.rand(size=(2, 2))
print(rand_tensor_C == rand_tensor_D)
