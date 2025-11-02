import torch
import torch.nn as nn
from DataPrepare import x_train, y_train, x_test, y_test, plot_predictions
from Utils import printl


class LinearRegressionModel(nn.Module):
    def __init__(self):
        super().__init__()

        self.weight = nn.Parameter(torch.rand(1, dtype=torch.float),
                                   requires_grad=True)
        self.bias = nn.Parameter(torch.rand(1, dtype=torch.float),
                                 requires_grad=True)

    def forward(self, input_tensor: torch.Tensor) -> torch.Tensor:
        return self.weight * input_tensor + self.bias


# torch.manual_seed(64)

model_0 = LinearRegressionModel()
printl('model_0 parameters info')
# print(list(model_0.parameters()))
print(model_0.state_dict())

if __name__ == '__main__':
    # make predictions with model by inference
    printl('make inference by x_test')
    with torch.inference_mode():
        y_pred = model_0(x_test)

    print('inference tensor: \n', y_pred)
    plot_predictions(y_pred)
