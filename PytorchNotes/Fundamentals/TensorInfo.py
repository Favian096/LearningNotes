import torch
from Utils import printl

printl('tensor calculation')
tensor = torch.tensor([1, 2, 3]).to(device='cuda')
# add_tensor = tensor + 10
add_tensor = tensor.add(torch.tensor([10, 10, 10]).to(device='cuda'))
print(add_tensor)

# sub_tensor = add_tensor - 10
sub_tensor = add_tensor.sub(10)
print(sub_tensor)

# mul_tensor = sub_tensor * 10
mul_tensor = tensor.mul(10)
print(mul_tensor)

point_mul_tensor = mul_tensor * sub_tensor
print(point_mul_tensor)

print('-' * 32)
matrix_A = torch.rand(3, 5).to(device='cuda')
matrix_B = torch.rand(5, 2).to(device='cuda')
# tensor's multiplication by @ operator
print(matrix_A @ matrix_B)

# tensor's multiplication by torch's function, .matmul could be simplified to .mm
print(torch.matmul(matrix_A, matrix_B))

# tensor's multiplication by hand, absolutely, could use '[x]' to index tensor
result = torch.zeros(size=(len(matrix_A), len(matrix_B[0]))).to(device='cuda')
for i in range(len(matrix_A)):
    for j in range(len(matrix_B[0])):
        for index in range(len(matrix_A[0])):
            result[i, j] += matrix_A[i][index] * matrix_B[index][j]
print(result)

printl('tensor matrix transpose')
print(matrix_B)
# print(matrix_B.T)
print(torch.transpose(matrix_B, 1, 0))
# double transpose for matrix_B
print(torch.mm(matrix_A, torch.transpose(matrix_B.T, 1, 0)))

# test torch.nn.linear for matrix_A
print("-" * 32)
input_tensor = torch.tensor([[1, 2], [3, 4], [5, 6]], dtype=torch.float).to(device='cuda')
linear = torch.nn.Linear(in_features=2, out_features=5).to(device='cuda')
output_tensor = linear(input_tensor)
print("input tensor: \n", input_tensor, '\nshape: ', input_tensor.shape)
print("output tensor: \n", output_tensor, '\nshape: ', output_tensor.shape)
