import torch
import torchvision
from torch.utils.data import DataLoader
from torchvision import datasets, transforms
from DataTransform import train_dir, test_dir, data_transform
import matplotlib

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from Utils import printl

# 1. use ImageFolder to transform
# define transform function
train_transform = transforms.Compose([
    transforms.Resize(size=(64, 64)),
    transforms.TrivialAugmentWide(num_magnitude_bins=31),
    transforms.ToTensor()

])
test_transform = transforms.Compose([
    transforms.Resize(size=(64, 64)),
    transforms.ToTensor()
])

train_data = datasets.ImageFolder(
    root=train_dir,
    # transform=data_transform,
    transform=train_transform,
    target_transform=None
)
class_names = train_data.classes
class_dict = train_data.class_to_idx

test_data = datasets.ImageFolder(
    root=test_dir,
    # transform=data_transform
    transform=test_transform
)

# transform to dataloader
batch_size = 5
num_workers = 4  # means 4 threads to load data

train_dataloader = DataLoader(
    dataset=train_data,
    batch_size=batch_size,
    num_workers=num_workers,
    shuffle=True
)

test_dataloader = DataLoader(
    dataset=test_data,
    batch_size=batch_size,
    num_workers=num_workers,
    shuffle=False
)

if __name__ == '__main__':
    printl('Transform by ImageFolder')
    print(train_data)
    print(test_data)
    print(class_names)
    print(class_dict)
    img, label = train_data[0]
    print(img.shape, label)

    print('check dataloader')
    print(train_dataloader)
    print(test_dataloader)
    img_dataLoader, label_dataLoader = next(iter(train_dataloader))
    print(img_dataLoader.shape, label_dataLoader)
