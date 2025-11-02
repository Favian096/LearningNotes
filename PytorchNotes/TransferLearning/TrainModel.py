import torch
import matplotlib

matplotlib.use('TKAgg')
from TransferLearning.DataPrepare import train_loader, test_loader, class_names, class_dict
from ModelTransfer import model
from timeit import default_timer as timer
from matplotlib import pyplot as plt
from tqdm import trange
from Utils import printl

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
loss_fun = torch.nn.CrossEntropyLoss()
optimizer = torch.optim.Adam(params=model.parameters(), lr=0.001)

# train model
epochs = 5
epoch_count = [epoch + 1 for epoch in range(epochs)]
train_loss_count = []
train_acc_count = []
test_loss_count = []
test_acc_count = []

printl('train transfer model')
start = timer()
model = model.to(device)
model.train()
for epoch in trange(epochs):
    print('Epoch: ', epoch + 1)

    train_loss, train_acc, test_loss, test_acc = 0, 0, 0, 0
    for images, labels in train_loader:
        images, labels = images.to(device), labels.to(device)

        pred_label_logits = model(images)
        loss = loss_fun(pred_label_logits, labels)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        acc = torch.softmax(pred_label_logits, dim=1).argmax(dim=1).eq(labels).float().mean()
        train_loss += loss.item()
        train_acc += acc.item()

    train_loss /= len(train_loader)
    train_acc /= len(train_loader)
    train_loss_count.append(train_loss)
    train_acc_count.append(train_acc)

    model.eval()
    with torch.inference_mode():
        for images, labels in test_loader:
            images, labels = images.to(device), labels.to(device)

            pred_label_logits = model(images)
            loss = loss_fun(pred_label_logits, labels)
            acc = torch.softmax(pred_label_logits, dim=1).argmax(dim=1).eq(labels).float().mean()
            test_loss += loss.item()
            test_acc += acc.item()

        test_loss /= len(test_loader)
        test_acc /= len(test_loader)
        test_loss_count.append(test_loss)
        test_acc_count.append(test_acc)
    print('Train Loss: {:.4f} Acc: {:.4f}'.format(train_loss, train_acc))
    print('Test Loss: {:.4f} Acc: {:.4f}'.format(test_loss, test_acc))

end = timer()
printl('train transfer model took {} seconds'.format(end - start))
plt.figure(figsize=(8, 4))
plt.subplot(1, 2, 1)
plt.plot(epoch_count, train_loss_count, label='train loss')
plt.plot(epoch_count, test_loss_count, label='test loss')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.subplot(1, 2, 2)
plt.plot(epoch_count, train_acc_count, label='train acc')
plt.plot(epoch_count, test_acc_count, label='test acc')
plt.xlabel('Epochs')
plt.ylabel('Accuracy')
plt.legend()
plt.show()

print('Obviously, the model get pretty high accuracy.')
