import torch
from torch.utils.data import Dataset, DataLoader
from torchvision import datasets, transforms
from pathlib import Path

# data prepare
dataset_dir = Path('./DataSets/Pizza_Steak_Sushi')

train_dir = dataset_dir / 'train'
test_dir = dataset_dir / 'test'

# transform function
train_transforms = transforms.Compose([
    transforms.Resize(size=(256, 256)),
    transforms.ToTensor(),
    transforms.Normalize(
        mean=[0.485, 0.456, 0.406],
        std=[0.229, 0.224, 0.225]
    )
])
test_transforms = transforms.Compose([
    transforms.Resize(size=(256, 256)),
    transforms.ToTensor(),
    transforms.Normalize(
        mean=[0.485, 0.456, 0.406],
        std=[0.229, 0.224, 0.225]
    )
])

# data set
train_set = datasets.ImageFolder(
    root=train_dir,
    transform=train_transforms
)
test_set = datasets.ImageFolder(
    root=test_dir,
    transform=test_transforms
)

# data classes
class_names, class_dict = train_set.classes, train_set.class_to_idx

# data loader
train_loader = DataLoader(
    dataset=train_set,
    batch_size=32,
    shuffle=True,
    num_workers=4
)
test_loader = DataLoader(
    dataset=test_set,
    batch_size=32,
    num_workers=4
)

if __name__ == '__main__':
    print(train_loader)
    print(test_loader)
    print(class_names)
    print(class_dict)
    img, label = next(iter(train_loader))
    print(img.shape, label)
