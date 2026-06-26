import torch
import numpy as np
import matplotlib
from ultralytics.utils.plotting import plot_results

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt


def sigmoid(x):
    return 1.0 / (1 + np.exp(-x))


def plot_sigmoid():
    x = np.arange(-8, 8, 0.1)
    y = sigmoid(x)
    plt.plot(x, y)
    plt.show()


def relu(x):
    return torch.maximum(torch.tensor(0), x)


def plot_ReLU():
    x = torch.arange(-8, 8, 0.1)
    y = relu(x)
    plt.plot(x, y)
    plt.show()


if __name__ == '__main__':
    # plot_sigmoid()
    plot_ReLU()
