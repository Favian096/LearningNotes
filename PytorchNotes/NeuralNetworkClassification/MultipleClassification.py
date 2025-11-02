import torch
import torch.nn as nn
import matplotlib

from helper_functions import plot_decision_boundary

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.datasets import make_blobs
from Utils import printl

device = 'cuda' if torch.cuda.is_available() else 'cpu'
NUM_CLASSES = 4
NUM_FEATURES = 2
RANDOM_SEED = 42

# create data set
printl('create data set')
X, y = make_blobs(
    n_samples=1000,
    n_features=NUM_FEATURES,
    centers=NUM_CLASSES,
    cluster_std=1.5,  # 表示聚类标准差, 越小则生成的数据越集中center
    random_state=RANDOM_SEED)

plt.figure(figsize=(8, 6))
plt.scatter(X[:, 0], X[:, 1], c=y, cmap=plt.cm.RdYlBu)
plt.title('DataSets Distribution')
# plt.show()

x_blob = torch.from_numpy(X).to(device=device, dtype=torch.float)
y_blob = torch.from_numpy(y).to(device=device, dtype=torch.long)  # CrossEntropyLoss need long type
print('x_set:', x_blob[:3, :])
print('y_set: ', y_blob[:3])

# transform data into tensor
x_train, x_test, y_train, y_test = train_test_split(
    x_blob, y_blob, test_size=0.2, random_state=RANDOM_SEED)

print(x_train.shape, y_train.shape)


# create multiple classification model


class MultipleClassificationModel(nn.Module):
    def __init__(self, in_features: int, out_features: int, hidden_units: int):
        super().__init__()
        self.linear_layer_stack = nn.Sequential(
            nn.Linear(in_features=in_features, out_features=hidden_units),
            nn.Linear(in_features=hidden_units, out_features=hidden_units),
            nn.Linear(in_features=hidden_units, out_features=out_features)
        )

    def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
        return self.linear_layer_stack(input_tensor)


printl('create model')
model = MultipleClassificationModel(
    in_features=NUM_FEATURES,
    out_features=NUM_CLASSES,
    hidden_units=8).to(device=device)
print(model)

# create loss function and optimizer for multiple classification
loss_fun = nn.CrossEntropyLoss()
optimizer = torch.optim.SGD(model.parameters(), lr=0.1)

printl('logits -> predict probabilities -> predict labels')
# softmax activate function let array sum equals 1 -> y_pred_probs_demo[0].sum() == 1
y_pred_logits_demo = model(x_train)[:3, :]
y_pred_probs_demo = torch.softmax(y_pred_logits_demo, dim=1)
y_pred_labels_demo = torch.argmax(y_pred_probs_demo[0])

print(y_pred_logits_demo, '\n',
      y_pred_probs_demo, '\nthe sum of an array==>',
      y_pred_probs_demo[1].sum(), '\n',
      y_pred_labels_demo)


# evaluate accuracy
def accuracy_fun(y_pred: torch.Tensor, y_true: torch.Tensor) -> str:
    correct = torch.eq(y_true, y_pred).sum().item()
    acc = (correct / len(y_true)) * 100
    return str(acc) + '%'


# start train model
printl('train model')
epochs = 100
epoch_counts = []
train_loss_counts = []
test_loss_counts = []

for epoch in range(epochs):
    model.train()
    y_train_pred_logits = model(x_train)
    train_loss = loss_fun(y_train_pred_logits, y_train)
    optimizer.zero_grad()
    train_loss.backward()
    optimizer.step()

    if epoch % 10 == 0:
        epoch_counts.append(epoch)
        train_acc = accuracy_fun(
            y_true=y_train,
            y_pred=torch.softmax(y_train_pred_logits, dim=1).argmax(dim=1)
        )
        train_loss_counts.append(train_loss.item())

        y_test_pred_logits = model(x_test)
        test_loss = loss_fun(model(x_test), y_test)
        test_loss_counts.append(test_loss.item())
        test_acc = accuracy_fun(
            y_true=y_test,
            y_pred=torch.softmax(y_test_pred_logits, dim=1).argmax(dim=1)
        )
        print('Epoch:', epoch,
              '\tTrain loss:', train_loss, 'train acc:', train_acc,
              '\tTest loss:', test_loss, 'Test acc:', test_acc)

plt.figure(figsize=(8, 6))
plt.plot(epoch_counts, train_loss_counts, label='Train loss')
plt.plot(epoch_counts, test_loss_counts, label='Test loss')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.show()

# model evaluation
printl('model evaluation')
model.eval()
with torch.inference_mode():
    y_pred_logits = model(x_test)

y_pred_probs = torch.softmax(y_pred_logits, dim=1)
y_pred_labels = y_pred_probs.argmax(dim=1)

print('pred:', y_pred_labels[:10])
print('true:', y_test[:10])
print('the prediction accuracy: ', accuracy_fun(y_true=y_test, y_pred=y_pred_labels))

plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.title("Train")
plot_decision_boundary(model, x_train, y_train)
plt.subplot(1, 2, 2)
plt.title("Test")
plot_decision_boundary(model, x_test, y_test)
plt.show()
