import torch
import torch.nn as nn
from CustomDataSets.DataTransform import device
from Utils import printl
from torchinfo import summary


# build model
class TinyVGGModel(nn.Module):
    def __init__(self, in_features: int, hidden_units: int, output_features: int):
        super().__init__()

        self.conv_block_1 = nn.Sequential(
            nn.Conv2d(
                in_channels=in_features,
                out_channels=hidden_units,
                kernel_size=3,
                stride=1,
                padding=1
            ),
            nn.ReLU(),
            nn.Conv2d(
                in_channels=hidden_units,
                out_channels=hidden_units,
                kernel_size=3,
                stride=1,
                padding=1
            ),
            nn.ReLU(),
            nn.MaxPool2d(kernel_size=2, stride=2)
        )

        self.conv_block_2 = nn.Sequential(
            nn.Conv2d(
                in_channels=hidden_units,
                out_channels=hidden_units,
                kernel_size=3,
                stride=1,
                padding=1
            ),
            nn.ReLU(),
            nn.Conv2d(
                in_channels=hidden_units,
                out_channels=hidden_units,
                kernel_size=3,
                stride=1,
                padding=1
            ),
            nn.ReLU(),
            nn.MaxPool2d(2)
        )

        self.classifier = nn.Sequential(
            nn.Flatten(),
            nn.Linear(
                in_features=hidden_units * 16 * 16,
                out_features=output_features
            )
        )

    def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
        return self.classifier(self.conv_block_2(self.conv_block_1(input_tensor)))


printl('build model')
model = TinyVGGModel(
    in_features=3,
    hidden_units=10,
    output_features=3
).to(device=device)

if __name__ == '__main__':
    print(model)
    print(summary(model, input_size=[5, 3, 64, 64]))
