import torch
import torch.nn as nn
from DataPrepare import x_train, x_test, y_train, y_test, device
from Utils import printl


# create linear model
class CircleModelV0(nn.Module):
    def __init__(self):
        super().__init__()
        # create 2 layers capable of handling x and y input and output
        # 5 is a customized num, indicate hide unit num
        # matching for x that 2 dim input 5 dim output
        self.layer_1 = nn.Linear(in_features=2, out_features=5)
        # matching for y that 5 dim input 1 dim output
        self.layer_2 = nn.Linear(in_features=5, out_features=1)

    def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
        return self.layer_2(self.layer_1(input_tensor))


printl('construct model_0')
model_0 = CircleModelV0().to(device=device)

# define loss function and optimizer
# base on binary classification, use binary cross entropy loss function
loss_fun = nn.BCEWithLogitsLoss()
optimizer = torch.optim.SGD(params=model_0.parameters(), lr=0.1)


# evaluate accuracy
def accuracy_fun(y_pred: torch.Tensor, y_true: torch.Tensor) -> str:
    correct = torch.eq(y_true, y_pred).sum().item()
    acc = (correct / len(y_true)) * 100
    return str(acc) + '%'


if __name__ == '__main__':
    printl('use model_0 prediction without training')
    y_pred_without_training = model_0(x_test)
    print('pred: ', y_pred_without_training.squeeze()[:5])
    print('real: ', y_test[:5])
