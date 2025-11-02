import torch

from NeuralNetworkClassification.BuildModel import accuracy_fun
from Utils import printl
from DataPrepare import x_train, x_test, y_train, y_test, device
import matplotlib

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from helper_functions import plot_decision_boundary
from BuildNonLinearModel import model_1, loss_fun, optimizer

# train the non-linear model
torch.manual_seed(42)
epochs = 1000
epoch_counts = []
train_loss_counts = []
test_loss_counts = []


def train():
    printl('start train model_1')
    for epoch in range(epochs):
        model_1.train()
        y_train_pred = model_1(x_train).squeeze()
        train_loss = loss_fun(y_train_pred, y_train)
        optimizer.zero_grad()
        train_loss.backward()
        optimizer.step()

        # evaluate model testing
        if epoch % 10 == 0:
            epoch_counts.append(epoch)
            train_loss_counts.append(train_loss.item())
            y_train_probs = torch.sigmoid(y_train_pred)
            train_acc = accuracy_fun(torch.round(y_train_probs), y_train)
            model_1.eval()
            with torch.inference_mode():
                y_test_pred = model_1(x_test).squeeze()
                y_test_probs = torch.sigmoid(y_test_pred)
                test_acc = accuracy_fun(torch.round(y_test_probs), y_test)
                test_loss = loss_fun(y_test_pred, y_test)
                test_loss_counts.append(test_loss.item())

                print('Epoch:', epoch,
                      '\ttrain loss: ', train_loss.item(), 'train acc:', train_acc,
                      '\ttest loss:', test_loss.item(), 'test acc:', test_acc)


train()

# train loss check
plt.figure(figsize=(8, 6))
plt.plot(epoch_counts, train_loss_counts, marker='o', label='Train Loss')
plt.plot(epoch_counts, test_loss_counts, marker='+', label='Train Loss')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.show()

# model evaluation
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.title('Train')
plot_decision_boundary(model_1, x_train, y_train)
plt.subplot(1, 2, 2)
plt.title('Test')
plot_decision_boundary(model_1, x_test, y_test)
plt.show()
