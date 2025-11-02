import torch
import matplotlib

matplotlib.use('TKAgg')
import matplotlib.pyplot as plt

# base a line create a data set
x_set = torch.arange(0, 1, 0.02)
y_set = x_set

# split train data, test data as 8:2
x_train, x_test = x_set[:int(len(x_set) * 0.8)], x_set[int(len(x_set) * 0.8):]
y_train, y_test = y_set[:int(len(y_set) * 0.8)], y_set[int(len(y_set) * 0.8):]


# data visualization
def plot_train_test_predictions(train_data: torch.Tensor,
                                train_label: torch.Tensor,
                                test_data: torch.Tensor,
                                test_label: torch.Tensor,
                                prediction: torch.Tensor = None):
    plt.figure(figsize=(8, 6))

    plt.scatter(train_data, train_label, c='b', s=10, label='training data')
    plt.scatter(test_data, test_label, c='g', s=10, label='testing data')

    if prediction is not None:
        plt.scatter(test_data, prediction, c='r', s=10, label='predictions')
    plt.legend(prop={'size': 14})
    plt.show()


def plot_predictions(prediction: torch.Tensor = None):
    plot_train_test_predictions(x_train, y_train, x_test, y_test, prediction)
