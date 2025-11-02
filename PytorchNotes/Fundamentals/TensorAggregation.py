import torch
from Utils import printl

printl('tensor aggregation')
tensor = torch.arange(0, 100, 10)
print(tensor)
# data aggregation
print(tensor.shape)
print(tensor.max())
print(tensor.min())
print(tensor.sum())
# average data need float data type, use tensor.type(torch.<type>) to transform
float16_tensor = tensor.type(torch.float16)
print(tensor.type(torch.float32).mean())

# get index of max/min tensor value
print(f'index of max tensor value occurs: {tensor.argmax()}')
print(f'index of min tensor value occurs: {tensor.argmin()}')

printl('tensor reshape, view, stack, squeeze')
# tensor transform without change tensor item value
tensor43 = torch.tensor([[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12]], dtype=torch.int)
# reshape tensor, need same number of elements, better than tensor.view()
print(torch.reshape(tensor43, shape=(2, 6)))
print(tensor43.view(2, 6))
print(tensor43)
print(tensor43.reshape(shape=(3, 4)))

# stack tensor
print('-' * 32)
stack_tensor = torch.tensor([1, 2, 3])
stack_tensor = torch.stack([stack_tensor, stack_tensor, stack_tensor], dim=0)
print(f'stack tensor 3 times: \n{stack_tensor}')

# squeeze/unsqueeze tensor only dimension 1 over, use for add/remove dimension that equals 1
print('-' * 32)
squeeze_tensor = torch.tensor([[1, 2, 3, 4, 5]])
squeeze_tensor = squeeze_tensor.squeeze()
unsqueeze_tensor = squeeze_tensor.unsqueeze(0)
print(f'squeeze tensor to dimension 1: \n{squeeze_tensor}')
print(f'unsqueeze tensor to dimension 1: \n{unsqueeze_tensor}')

printl('tensor multidimensional transpose')
# permute tensors, use for transpose multidimensional
permute_tensor = torch.rand(size=(3, 2, 5))  # create a 3 block, 2 row, 5 col tensor
# origin_permute_tensor.permute(0, 1, 2)
# the number of 0 point 3 block, 1 point 2 row, 5 point 3 column
print(permute_tensor.permute(0, 1, 2))  # so, the result will not change
# change this tensor to 2 block, 5 row, 3 col tensor
print(permute_tensor.permute(1, 2, 0))
print(permute_tensor.transpose(1, 2))  # transpose only change 2 dimensions
