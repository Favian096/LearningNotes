import torch
from BuildTinyVGGModel import TinyVGGModel
from torchvision import transforms, datasets
from DataLoadByImageFolder import class_names
from pathlib import Path

device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
test_img_path = Path('DataSets/Prediction/')

model = TinyVGGModel(
    in_features=3,
    hidden_units=10,
    output_features=3
)

model.load_state_dict(torch.load(f='./Models/TinyVGGModel.pth'))
model.to(device=device)
model.eval()

data_transforms = transforms.Compose([
    transforms.Resize(size=(64, 64)),
    transforms.ToTensor()
])

images = datasets.ImageFolder(
    root=test_img_path,
    transform=data_transforms
)
image, label = images[0]

pred_label_logits = model(image.unsqueeze(dim=0).to(device=device))
pred_label = torch.softmax(pred_label_logits, dim=1).argmax(dim=1)

print(pred_label_logits)
print('predict result: ', pred_label.item(), class_names[pred_label])

print('Although the model was poor, it still got right answer')
