import torch


# train model
def train_model(
        model: torch.nn.Module,
        data_loader: torch.utils.data.DataLoader,
        loss_fun: torch.nn.Module,
        optimizer: torch.optim.Optimizer,
        accuracy_fun,
        device: torch.device
):
    train_loss, train_acc = 0, 0
    model.to(device)
    model.train()
    for train_data, train_labels in data_loader:
        train_data = train_data.to(device)
        train_labels = train_labels.to(device)
        pred_train_labels = model(train_data)
        loss = loss_fun(pred_train_labels, train_labels)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        train_loss += loss
        train_acc += accuracy_fun(
            y_true=train_labels,
            y_pred=pred_train_labels.argmax(dim=1)
        )
    train_loss /= len(data_loader)
    train_acc /= len(data_loader)
    return {'device': device,
            'loss': train_loss.item(),
            'acc': train_acc}


# evaluate model
def eval_model(
        model: torch.nn.Module,
        data_loader: torch.utils.data.DataLoader,
        loss_fun: torch.nn.Module,
        accuracy_fun,
        device: torch.device
):
    loss, acc = 0, 0
    model.to(device=device)
    model.eval()
    with torch.inference_mode():
        for test_data, test_labels in data_loader:
            test_data = test_data.to(device=device)
            test_labels = test_labels.to(device=device)
            pred = model(test_data)
            loss += loss_fun(pred, test_labels)
            acc += accuracy_fun(
                y_true=test_labels,
                y_pred=pred.argmax(dim=1)
            )
        loss /= len(data_loader)
        acc /= len(data_loader)

    return {'modelName: ': model.__class__.__name__,
            'device': device,
            'loss': loss.item(),
            'acc': acc}
