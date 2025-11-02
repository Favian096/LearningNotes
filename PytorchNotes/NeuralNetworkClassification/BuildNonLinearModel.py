import torch

import torch.nn as nn
from DataPrepare import device

from Utils import printl


# create non-linear model
class CircleModelV1(nn.Module):
    def __init__(self):
        super().__init__()

        self.layer_1 = nn.Linear(in_features=2, out_features=10)
        self.layer_2 = nn.Linear(in_features=10, out_features=10)
        self.layer_3 = nn.Linear(in_features=10, out_features=1)
        # set a ReLU activate function
        self.relu = nn.ReLU()

    def forward(self, input_tensor: torch.Tensor):
        # put non-linear activate function in hide layers between in common
        return self.layer_3(self.relu(self.layer_2(self.layer_1(input_tensor))))


printl('construct non-linear model_1')
model_1 = CircleModelV1().to(device=device)

# set loss function and optimizer
loss_fun = nn.BCEWithLogitsLoss()
optimizer = torch.optim.SGD(model_1.parameters(), lr=0.1)
