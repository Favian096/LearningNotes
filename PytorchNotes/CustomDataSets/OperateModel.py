import torch
from tqdm import trange
from CustomDataSets.DataTransform import device


def train_model(
        model: torch.nn.Module,
        dataloader: torch.utils.data.DataLoader,
        loss_fun: torch.nn.Module,
        optimizer: torch.optim.Optimizer
):
    model.train()
    train_loss, train_acc = 0, 0
    train_loss_count, train_acc_count = [], []

    for images, labels in dataloader:
        images, labels = images.to(device), labels.to(device)

        pred_label_logits = model(images)
        loss = loss_fun(pred_label_logits, labels)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        acc = (torch.softmax(pred_label_logits, dim=1).argmax(dim=1) == labels).sum().item() / len(labels)
        train_loss_count.append(loss.item())
        train_acc_count.append(acc)

        train_loss += loss.item()
        train_acc += acc

    train_loss /= len(dataloader)
    train_acc /= len(dataloader)
    return {
        'loss': train_loss,
        'acc': train_acc,
        'loss_count': train_loss_count,
        'acc_count': train_acc_count
    }


def eval_model(
        model: torch.nn.Module,
        dataloader: torch.utils.data.DataLoader,
        loss_fun: torch.nn.Module
):
    model.eval()
    eval_loss, eval_acc = 0, 0
    eval_loss_count, eval_acc_count = [], []

    with torch.inference_mode():
        for images, labels in dataloader:
            images, labels = images.to(device), labels.to(device)

            pred_label_logits = model(images)
            loss = loss_fun(pred_label_logits, labels)
            acc = (torch.softmax(pred_label_logits, dim=1).argmax(dim=1) == labels).sum().item() / len(labels)

            eval_loss_count.append(loss.item())
            eval_acc_count.append(acc)

            eval_loss += loss_fun(pred_label_logits, labels).item()
            eval_acc += acc

        eval_loss /= len(dataloader)
        eval_acc /= len(dataloader)

    return {
        'loss': eval_loss,
        'acc': eval_acc,
        'loss_count': eval_loss_count,
        'acc_count': eval_acc_count
    }


def train_eval_model(
        model: torch.nn.Module,
        train_dataloader: torch.utils.data.DataLoader,
        eval_dataloader: torch.utils.data.DataLoader,
        loss_fun: torch.nn.Module,
        optimizer: torch.optim.Optimizer,
        epochs: int,
):
    train_loss_count = []
    train_acc_count = []
    eval_loss_count = []
    eval_acc_count = []
    for epoch in trange(epochs):
        print('Epoch: ', epoch + 1)

        train_info = train_model(model, train_dataloader, loss_fun, optimizer)
        eval_info = eval_model(model, eval_dataloader, loss_fun)

        print('\nTrain loss: ', train_info['loss'], 'acc:', train_info['acc'])
        print('Eval loss: ', eval_info['loss'], 'acc:', eval_info['acc'])

        train_loss_count.extend(train_info['loss_count'])
        train_acc_count.extend(train_info['acc_count'])
        eval_loss_count.extend(eval_info['loss_count'])
        eval_acc_count.extend(eval_info['acc_count'])

    return {
        'train_batches': [epoch + 1 for epoch in range(epochs * len(train_dataloader))],
        'train_loss_arr': train_loss_count,
        'train_acc_arr': train_acc_count,
        'eval_batches': [epoch + 1 for epoch in range(epochs * len(eval_dataloader))],
        'eval_loss_arr': eval_loss_count,
        'eval_acc_arr': eval_acc_count
    }
