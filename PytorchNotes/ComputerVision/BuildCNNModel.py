import torch
import torch.nn as nn

from ComputerVision.DataPrepare import class_names, device


# create cnn model
class FashionMNISTModelV2(nn.Module):
    def __init__(self, in_features: int, hidden_unit: int, out_features: int):
        super().__init__()

        self.block_1 = nn.Sequential(
            nn.Conv2d(
                in_channels=in_features,  # input layers, the layers of img([3, 28, 28] -> 3 layers)
                out_channels=hidden_unit,  # layers to output num
                kernel_size=3,  # calc square width (equals kernel_size=(3, 3))
                stride=1,  # the num of square to right or down
                padding=1  # the num of img border expend outward
            ),
            nn.ReLU(),
            nn.Conv2d(
                in_channels=hidden_unit,
                out_channels=hidden_unit,
                kernel_size=3,
                stride=1,
                padding=1
            ),
            nn.ReLU(),
            nn.MaxPool2d(
                kernel_size=2,
                stride=2
            )
        )

        self.block_2 = nn.Sequential(
            nn.Conv2d(
                in_channels=hidden_unit,
                out_channels=hidden_unit,
                kernel_size=3,
                padding=1
            ),
            nn.ReLU(),
            nn.Conv2d(
                in_channels=hidden_unit,
                out_channels=hidden_unit,
                kernel_size=3,
                padding=1
            ),
            nn.ReLU(),
            nn.MaxPool2d(2)
        )

        self.classifier = nn.Sequential(
            nn.Flatten(),
            nn.Linear(
                in_features=hidden_unit * 7 * 7,
                out_features=out_features
            )
        )

    def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
        return self.classifier(self.block_2(self.block_1(input_tensor)))


# construct CNN model
model_2 = FashionMNISTModelV2(
    in_features=1,
    hidden_unit=10,
    out_features=len(class_names)
).to(device=device)

print(model_2)

# create loss function and optimizer
loss_fun = nn.CrossEntropyLoss()
optimizer = torch.optim.SGD(
    params=model_2.parameters(),
    lr=0.1
)
