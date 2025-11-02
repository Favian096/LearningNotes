import torch
import torchvision
from torchinfo import summary

from TransferLearning.DataPrepare import class_names

# set model
weight = torchvision.models.EfficientNet_B0_Weights.DEFAULT

model = torchvision.models.efficientnet_b0(weights=weight)

# frozen all feature layer's params
for param in model.features.parameters():
    param.requires_grad = False

# set output
output_features = len(class_names)

model.classifier = torch.nn.Sequential(
    torch.nn.Dropout(
        p=0.2,
        inplace=True
    ),
    torch.nn.Linear(
        in_features=1280,
        out_features=output_features,
        bias=True
    )
)

if __name__ == '__main__':
    print(summary(
        model=model,
        input_size=(32, 3, 256, 256),
        col_names=["input_size", "output_size", "num_params", "trainable"],
        col_width=20,
        row_settings=["var_names"]
    ))
