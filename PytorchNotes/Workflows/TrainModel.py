import torch
import torch.nn as nn
from BuildModel import model_0
from Utils import printl
from DataPrepare import x_train, y_train, x_test, y_test

# create loss function and optimizer
loss_nn = nn.L1Loss()
optimizer = torch.optim.SGD(params=model_0.parameters(), lr=0.01)
epochs = 100
train_loss_value = []
test_loss_value = []
epoch_count = []

# create optimization loop
printl('create optimization loop')


def train():
    for epoch in range(epochs):
        model_0.train()
        y_pred = model_0(x_train)
        loss = loss_nn(y_pred, y_train)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        # evaluation mode for test data
        model_0.eval()
        with torch.inference_mode():
            test_pred = model_0(x_test)
            test_loss = loss_nn(test_pred, y_test)

            if epoch % 10 == 0:
                epoch_count.append(epoch)
                train_loss_value.append(loss.detach().numpy())
                test_loss_value.append(test_loss.detach().numpy())
                print('Epoch: ', epoch, '\tMAE train loss: ', loss, '\tMAE test loss: ', test_loss)


printl()
