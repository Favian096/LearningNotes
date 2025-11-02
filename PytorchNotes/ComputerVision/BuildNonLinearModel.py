import torch
import torch.nn as nn

from ComputerVision.DataPrepare import class_names
from Utils import printl


# create model
class FashionMNISTModelV1(nn.Module):
    def __init__(self, in_features: int, hidden_units: int, out_features: int):
        super().__init__()
        self.layer_stack = nn.Sequential(
            nn.Flatten(),
            nn.Linear(in_features=in_features, out_features=hidden_units),
            nn.ReLU(),
            nn.Linear(in_features=hidden_units, out_features=out_features),
            nn.ReLU()
        )

    def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
        return self.layer_stack(input_tensor)


printl('construct non-linear model')
model_1 = FashionMNISTModelV1(
    in_features=28 * 28,
    hidden_units=10,
    out_features=len(class_names)
)
print(model_1)

# create loss function and optimizer
loss_fun = nn.CrossEntropyLoss()
optimizer = torch.optim.SGD(model_1.parameters(), lr=0.01)
