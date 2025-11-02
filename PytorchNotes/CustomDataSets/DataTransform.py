import torch
from torch.utils.data import DataLoader
from torchvision import datasets, transforms
import random
import os
from pathlib import Path
from PIL import Image
import matplotlib

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt

from Utils import printl

device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
IMG_PATH = 'DataSets/Pizza_Steak_Sushi'
img_path = Path(IMG_PATH)

# set base dir
train_dir = img_path / 'train'
test_dir = img_path / 'test'

# transform img to tensor, include reshape img size, random rotate img in horizon
data_transform = transforms.Compose([
    transforms.Resize(size=(64, 64)),
    transforms.RandomHorizontalFlip(p=0.5),  # probability
    transforms.ToTensor()
])


def plot_transforms_img(images_path, transforms_fun, num=1, seed=42):
    random.seed(seed)
    random_image_paths = random.sample(images_path, k=num)

    for image_path in random_image_paths:
        with Image.open(image_path) as img_f:
            fig, ax = plt.subplots(1, 2)
            ax[0].imshow(img_f)
            ax[0].set_title(f'Original \nSize: {img_f.size}')
            ax[0].axis('off')

            # pytorch's transform to [C, H, W], but matplotlib is [H, W, C], so need permute fun
            transformed_image = transforms_fun(img_f).permute(1, 2, 0)
            ax[1].imshow(transformed_image)
            ax[1].set_title(f'Transformed \nSize: {transformed_image.shape}')
            ax[1].axis('off')

            fig.suptitle(str(image_path.parent.stem), fontsize=16)
            plt.show()


if __name__ == '__main__':
    printl('check folder and file')
    for dirPath, dirNames, fileNames in os.walk(IMG_PATH):
        print('There are ' + str(len(dirNames)) + ' directories and ' + str(len(fileNames)) + ' img in' + dirPath)

    img_path_list = list(img_path.glob('*/*/*.jpg'))
    random_img_path = random.choice(img_path_list)
    random_img_path_class = random_img_path.parent.stem
    img = Image.open(random_img_path)
    printl('random img attribution')
    print(random_img_path)
    print(random_img_path_class)
    print('img width: ', img.width, 'img height: ', img.height)
    plt.figure(figsize=(6, 6))
    plt.imshow(img)
    plt.show()

    printl('test transform tensor fun')
    plot_transforms_img(img_path_list, data_transform)
