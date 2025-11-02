import pandas as pd
import torch
from sklearn.datasets import make_circles
from sklearn.model_selection import train_test_split
import matplotlib

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt

from Utils import printl

# create data set
printl('data set')
n_samples = 1000
X, y = make_circles(n_samples, noise=0.03, random_state=42)

print('shape of X: ', X.shape, '\tshape of y:', y.shape)

# transform to tensor
device = 'cuda' if torch.cuda.is_available() else 'cpu'
x_set = torch.from_numpy(X).to(dtype=torch.float, device=device)
y_set = torch.from_numpy(y).to(dtype=torch.float, device=device)

# split train and test data set by 8:2
x_train, x_test, y_train, y_test = train_test_split(x_set, y_set,
                                                    test_size=0.2,
                                                    random_state=42)

if __name__ == '__main__':
    circles = pd.DataFrame({'X1': X[:, 0], 'X2': X[:, 1], 'label': y})
    print('circles:\n', circles.head(5), '\n ... ... ')
    print('circles data value count:', circles.label.value_counts())

    plt.figure(figsize=(8, 6))
    plt.scatter(circles.values[:, 0], circles.values[:, 1], c=y, cmap=plt.cm.RdYlBu)
    plt.title('circles dataset')
    plt.show()
