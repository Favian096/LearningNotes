import torch
from tqdm import trange
from timeit import default_timer as timer
from BuildCNNModel import model_2, loss_fun, optimizer
from DataPrepare import train_dataloader, test_dataloader, device, class_names
from OperateModel import train_model, eval_model
from helper_functions import accuracy_fn
from Utils import printl

# train model
printl('Train CNN Models')
epochs = 3

cnn_model_train_start = timer()
for epoch in trange(epochs):
    print('Epoch: ', epoch)

    train_parameters = train_model(
        model=model_2,
        data_loader=train_dataloader,
        loss_fun=loss_fun,
        optimizer=optimizer,
        accuracy_fun=accuracy_fn,
        device=device
    )

    test_parameters = eval_model(
        model=model_2,
        data_loader=test_dataloader,
        loss_fun=loss_fun,
        accuracy_fun=accuracy_fn,
        device=device
    )
    print('train loss: ', train_parameters['loss'],
          'train acc: ', train_parameters['acc'],
          'test loss: ', test_parameters['loss'],
          'test acc: ', test_parameters['acc'])

cnn_model_train_end = timer()
print('Total training time: ', cnn_model_train_end - cnn_model_train_start, ' seconds')

# save model
PATH = './Models/FashionMNIST_CNN_Model.pth'
torch.save(obj=model_2.state_dict(), f=PATH)
