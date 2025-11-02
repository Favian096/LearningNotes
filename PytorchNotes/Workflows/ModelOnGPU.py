import torch
import torch.nn as nn
import matplotlib

matplotlib.use('TKAgg')
import matplotlib.pyplot as plt

device = 'cuda' if torch.cuda.is_available() else 'cpu'

# create data set
data_set = torch.arange(0, 1, 0.01).unsqueeze(dim=1).to(device)
x_train, x_test = data_set[:int(0.8 * len(data_set))], data_set[int(0.8 * len(data_set)):]
y_train, y_test = data_set[:int(0.8 * len(data_set))], data_set[int(0.8 * len(data_set)):]


# visualize predictions
def plot_predictions(predictions: torch.Tensor,
                     r_train: torch.Tensor = x_train,
                     c_train: torch.Tensor = y_train,
                     r_test: torch.Tensor = x_test,
                     c_test: torch.Tensor = y_test):
    predictions, r_train, c_train, r_test, c_test = (
        predictions.cpu(), r_train.cpu(), c_train.cpu(), r_test.cpu(), c_test.cpu())
    plt.figure(figsize=(16, 9))
    plt.scatter(r_train, c_train, c='b', s=12, label='Train Data')
    plt.scatter(r_test, c_test, c='g', s=12, label='Test, Data')
    plt.scatter(r_test, predictions, c='r', s=12, label='Prediction')
    plt.legend(prop={'size': 14})
    plt.show()


# create linear natural network class
class LinearRegressionModel(nn.Module):
    def __init__(self):
        super().__init__()
        # create model parameters
        self.weight = nn.Parameter(torch.randn(1, device=device), requires_grad=True)
        self.bias = nn.Parameter(torch.randn(1, device=device), requires_grad=True)

    def forward(self, train_set: torch.Tensor) -> torch.Tensor:
        return self.weight * train_set + self.bias


model = LinearRegressionModel()

# start train model
epochs = 500
train_loss_array = []
test_loss_array = []
epoch_counts = []

# loss function and optimizer
loss_fun = nn.L1Loss()
optimizer = torch.optim.SGD(model.parameters(), lr=0.01)

for epoch in range(epochs):
    model.train()
    y_train_pred = model(x_train)
    train_loss = loss_fun(y_train_pred, y_train)
    optimizer.zero_grad()
    train_loss.backward()
    optimizer.step()

    # count into testing list
    if epoch % 10 == 0:
        model.eval()
        with torch.inference_mode():
            y_test_pred = model(x_test)
            test_loss = loss_fun(y_test_pred, y_test)

            train_loss_array.append(train_loss.cpu().detach().numpy())
            test_loss_array.append(test_loss.cpu().detach().numpy())
            epoch_counts.append(epoch)
            print('Epoch: ', epoch, 'training loss: ', train_loss.item(), 'test loss:', test_loss.item())

# train loss visualization
plt.figure(figsize=(16, 9))
plt.plot(epoch_counts, train_loss_array, marker='+', label='Train Loss')
plt.plot(epoch_counts, test_loss_array, marker='+', label='Test Loss')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.legend(prop={'size': 14})
plt.show()

# save model(state_dict)
MODEL_PATH = './Models/Workflows_model_1.pth'
torch.save(obj=model.state_dict(), f=MODEL_PATH)

# load model from state_dict
loaded_model = LinearRegressionModel()
loaded_model.load_state_dict(torch.load(MODEL_PATH, map_location=device))

# evaluate model
loaded_model.eval()
with torch.inference_mode():
    y_pred = loaded_model(x_test)

plot_predictions(y_pred)
print('model state dict: ', model.state_dict())
print('the final accuracy: ', 100 * (1 - loss_fun(y_pred, y_test).item()), '%')
