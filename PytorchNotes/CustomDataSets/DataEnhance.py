import matplotlib

matplotlib.use('TKAgg')
import matplotlib.pyplot as plt
from torchvision import transforms, datasets

from CustomDataSets.DataLoadByImageFolder import train_data
from CustomDataSets.DataTransform import train_dir

img, label = train_data[0]
# test data enhance
train_transforms_enhance = transforms.Compose([
    transforms.Resize(size=(64, 64)),
    transforms.TrivialAugmentWide(num_magnitude_bins=31),
    transforms.ToTensor()
])

train_data_enhance = datasets.ImageFolder(root=train_dir, transform=train_transforms_enhance)
img_enhance, label_enhance = train_data_enhance[0]

plt.figure(figsize=(8, 4))
plt.subplot(1, 2, 1)
plt.imshow(img.permute(1, 2, 0))
plt.title('Original Image')
plt.subplot(1, 2, 2)
plt.imshow(img_enhance.permute(1, 2, 0))
plt.title('Enhanced Image')
plt.axis('off')
plt.show()
