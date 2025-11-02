import matplotlib
import torch
import torchvision
from torchvision import datasets
from torchvision.transforms import ToTensor

from Utils import printl

matplotlib.use('TKAgg')

print(torch.__version__)
print(torchvision.__version__)

printl('download data set from fashion MNIST')
train_set = datasets.FashionMNIST(
    root='./DataSets/Downloads',  # down path
    train=True,
    download=True,
    transform=ToTensor(),
    target_transform=None
)

test_set = datasets.FashionMNIST(
    root='./DataSets/Downloads',
    train=False,
    download=True,
    transform=ToTensor()
)

# check data set
image, label = train_set[0]
print(image)
print(label)
