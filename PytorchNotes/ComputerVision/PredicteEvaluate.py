import random
import matplotlib

matplotlib.use('TKAgg')
import matplotlib.pyplot as plt
import torch
from BuildCNNModel import FashionMNISTModelV2
from ComputerVision.DataPrepare import test_set, class_names, device, test_dataloader
from Utils import printl

# load model
model = FashionMNISTModelV2(
    in_features=1,
    hidden_unit=10,
    out_features=len(class_names)
)
model.load_state_dict(torch.load('./Models/FashionMNIST_CNN_Model.pth'))
model.to(device=device)

printl('get test sample')
# import test data set
samples = torch.Tensor().to(device=device)
labels = []
# get 9 test samples
for sample, label in random.sample(list(test_set), k=9):
    samples = torch.cat([samples, sample.unsqueeze(dim=0).to(device=device)])
    labels.append(label)

print(len(samples), samples.shape)
print('true labels:', labels)

printl('make prediction')
model.eval()
with torch.inference_mode():
    pred_logits = model(samples)
    pred_probs = torch.softmax(pred_logits, dim=1)
    pred_labels = torch.argmax(pred_probs, dim=1)
print(pred_labels)

# pred data visualize
printl('predict visualize')

plt.figure(figsize=(9, 9))
for index, sample in enumerate(samples):
    plt.subplot(3, 3, index + 1)
    plt.imshow(sample.detach().cpu().squeeze(dim=0), cmap='gray')

    pred_title = class_names[pred_labels[index]]
    true_title = class_names[labels[index]]
    title = f'true: {true_title} | pred: {pred_title}'

    plt.title(title, fontsize=10, c='g' if pred_labels[index] == labels[index] else 'r')
    plt.axis('off')

# confusion matrix
test_datas = torch.Tensor().to(device=device)
true_test_labels = test_set.targets
for test_data, test_label in test_dataloader:
    test_datas = torch.cat([test_datas, test_data.to(device=device)])

print(test_datas.shape, len(true_test_labels))

model.eval()
with torch.inference_mode():
    pred_test_labels = torch.softmax(model(test_datas), dim=1).argmax(dim=1)

from torchmetrics import ConfusionMatrix
from mlxtend.plotting import plot_confusion_matrix

confusion_matrix = ConfusionMatrix(
    num_classes=len(class_names),
    task='multiclass'
).to(device=device)

print(len(pred_test_labels), type(pred_test_labels))
print(len(true_test_labels), type(true_test_labels))

confusion_matrix_tensor = confusion_matrix(
    preds=pred_test_labels,
    target=true_test_labels.to(device=device)
).to(device=device)

fig, ax = plot_confusion_matrix(
    conf_mat=confusion_matrix_tensor.detach().cpu().numpy(),
    figsize=(9, 7),
    class_names=class_names
)
plt.show()
