import torch
import torch.nn as nn
from DataPrepare import x_train, x_test, y_train, y_test, device
from BuildModel import model_0, loss_fun, optimizer, accuracy_fun
from Utils import printl
import matplotlib

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from helper_functions import plot_decision_boundary

printl('logits -> prediction probability -> prediction label')
# logits is the original output of model without training
y_pred_without_train = model_0(x_test)[:8]
print(y_pred_without_train.squeeze())

# use activation function
y_pred_probs = torch.sigmoid(y_pred_without_train)
print(y_pred_probs.squeeze())

# transform to prediction label (0 ,1)
y_pred_labels = torch.round(y_pred_probs)
print(y_pred_labels.squeeze())

# train the model
epochs = 100


def train():
    printl('model_0 start training')
    # train loop
    for epoch in range(epochs):
        model_0.train()
        y_logits = model_0(x_train).squeeze()
        train_loss = loss_fun(y_logits, y_train)
        optimizer.zero_grad()
        train_loss.backward()
        optimizer.step()

        # calculate accuracy
        y_pred = torch.round(torch.sigmoid(y_logits))
        train_acc = accuracy_fun(y_train, y_pred)

        # evaluate testing
        if epoch % 10 == 0:
            model_0.eval()
            with torch.inference_mode():
                y_test_logits = model_0(x_test).squeeze()
                test_loss = loss_fun(y_test_logits, y_test)
                y_test_pred = torch.round(torch.sigmoid(y_test_logits))
                test_acc = accuracy_fun(y_test_pred, y_test)

                print('Epoch: ', epoch, '\ttrain loss:', train_loss.item(), 'train acc:', train_acc,
                      '\ttest loss:', test_loss.item(), 'test acc:', test_acc)


train()

# evaluate model visualization
plt.figure(figsize=(8, 4))
plt.subplot(1, 2, 1)
plt.title('Train')
plot_decision_boundary(model_0, x_train, y_train)
plt.subplot(1, 2, 2)
plt.title('Test')
plot_decision_boundary(model_0, x_test, y_test)
plt.show()

printl('conclusion')
print('==>Obviously, training of model is useless.\n'
      '==>The only reason is this model is linear and constructed by linear params.')
