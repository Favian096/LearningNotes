import os
import pathlib
import torch
import torchvision

from DataTransform import train_dir, test_dir, data_transform
from PIL import Image
from torch.utils.data import Dataset, DataLoader
from torchvision import transforms
from typing import Tuple, Dict, List

from Utils import printl

# 2. custom to transform
class_names = sorted([dirEntry.name for dirEntry in os.scandir(train_dir)])
class_dict = {class_name: index for index, class_name in enumerate(class_names)}

# custom transform fun
train_transforms = transforms.Compose([
    transforms.Resize(size=(64, 64)),
    transforms.RandomHorizontalFlip(p=0.5),
    transforms.ToTensor()
])
test_transforms = transforms.Compose([
    transforms.Resize(size=(64, 64)),
    transforms.ToTensor()
])


# custom class
class ImageFolderCustom(Dataset):
    def __init__(self, img_dir: pathlib.Path, transform_fun: torchvision.transforms.transforms.Compose):
        self.images = list(img_dir.glob('*/*.jpg'))
        self.transform = transform_fun
        self.class_names = sorted([
            dirEntry.name for dirEntry in os.scandir(img_dir)
        ])
        self.class_dict = {
            class_name: index for index, class_name in enumerate(self.class_names)
        }

    def get_image(self, index: int) -> Image.Image:
        if index in range(self.__len__()):
            return Image.open(self.images[index])
        else:
            raise IndexError(f'Index {index} is out of ImageFolderCustom images range')

    def __len__(self) -> int:
        return len(self.images)

    def __getitem__(self, index: int) -> Tuple[torch.Tensor, int]:
        return (self.transform(self.get_image(index)),
                self.class_dict[self.images[index].parent.stem])


# define train and test data by custom dataloader
train_data = ImageFolderCustom(img_dir=train_dir, transform_fun=train_transforms)
test_data = ImageFolderCustom(img_dir=test_dir, transform_fun=test_transforms)

# create dataloader
batch_size = 5
num_workers = 4

train_dataLoader = DataLoader(
    dataset=train_data,
    batch_size=batch_size,
    num_workers=num_workers,
    shuffle=True
)

test_dataLoader = DataLoader(
    dataset=test_data,
    batch_size=batch_size,
    num_workers=num_workers,
    shuffle=False
)

if __name__ == '__main__':
    printl('Init directory')
    print(list(os.scandir(train_dir)))
    print(class_names)
    print(class_dict)

    printl('Custom ImageFolder')
    img, label = train_data[0]
    print(img.shape, label)

    printl('datasets test')
    img, label = next(iter(test_dataLoader))
    print(img.shape, label)
