import matplotlib
import torch
import torch.nn as nn
from torchvision import datasets
from torchvision.transforms import ToTensor

from Utils import printl

matplotlib.use('TKAgg')
import matplotlib.pyplot as plt

train_set = datasets.FashionMNIST(
    root='./DataSets/Downloads',
    train=True,
    download=False,
    transform=ToTensor(),
    target_transform=None
)

test_set = datasets.FashionMNIST(
    root='./DataSets/Downloads',
    train=False,
    download=False,
    transform=ToTensor()
)

class_names = train_set.classes
device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')

# data loader process
from torch.utils.data import DataLoader

# set batch size to 32
BATCH_SIZE = 32
# load data as batch size
train_dataloader = DataLoader(
    dataset=train_set,
    batch_size=BATCH_SIZE,
    shuffle=True  # shuffle data every epoch
)

test_dataloader = DataLoader(
    dataset=test_set,
    batch_size=BATCH_SIZE,
    shuffle=False  # don't necessarily shuffle test data
)

if __name__ == '__main__':
    # show data set
    printl('data set info')

    image, label = train_set[0]
    print(train_set)
    print(len(train_set.data), len(train_set.targets), len(test_set.data), len(test_set.targets))
    print('image:', image.shape)
    print('label:', label)
    print('cloth types:', train_set.classes, len(train_set.classes))

    printl('data visualization')
    print(image.squeeze().shape)  # turn to dim=1

    plt.figure(num=1, figsize=(6, 6))
    plt.imshow(image.squeeze())
    plt.title(label)
    plt.show()

    plt.figure(num=2, figsize=(6, 6))
    plt.imshow(image.squeeze(), cmap='gray')
    plt.title(class_names[label])
    plt.show()

    # Plot more images
    torch.manual_seed(42)
    fig = plt.figure(num=3, figsize=(9, 9))
    rows, cols = 4, 4
    for i in range(1, rows * cols + 1):
        random_index = torch.randint(0, len(train_set), size=[1]).item()
        random_image, random_label = train_set[random_index]
        fig.add_subplot(rows, cols, i)
        plt.imshow(random_image.squeeze(), cmap='gray')
        plt.title(class_names[random_label])
        plt.axis(False)  # don't show x/y axis
    plt.show()

    printl('data loader info')
    print(train_dataloader, test_dataloader)
    # 60000 / 32 = 1875     10000 / 32 = 313
    print(len(train_dataloader), len(test_dataloader))

    # need use iteration to access every batch
    train_feature_batch, train_label_batch = next(iter(train_dataloader))
    print(train_feature_batch.shape, train_label_batch.shape)

    plt.figure(num=4, figsize=(6, 6))
    plt.imshow(train_feature_batch[0].squeeze(), cmap='gray')
    plt.title(class_names[train_label_batch[0]])
    plt.show()

    printl('test flatten layer')
    flatten_model = nn.Flatten()
    # flatten layer transform length and width to a dim [1, 28, 28] -> [1, 28 * 28]
    print(flatten_model(train_feature_batch[0]).shape)
