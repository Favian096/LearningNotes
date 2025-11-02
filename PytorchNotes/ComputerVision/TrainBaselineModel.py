import torch
import tqdm
from tqdm import trange
from timeit import default_timer as timer

from ComputerVision.OperateModel import eval_model, train_model
from DataPrepare import train_dataloader, test_dataloader, class_names
from BuildBaselineModel import model_0, loss_fun, optimizer
from Utils import printl
from helper_functions import accuracy_fn

# calculate train time on cpu
train_start_on_cpu = timer()

# start train
epochs = 3
device = torch.device('cpu')

printl('train model_0 on cpu')
for epoch in trange(epochs):
    print('Epoch:', epoch)

    # train model
    train_parameters = train_model(
        model=model_0,
        data_loader=train_dataloader,
        loss_fun=loss_fun,
        optimizer=optimizer,
        accuracy_fun=accuracy_fn,
        device=device
    )

    # evaluate model_0
    evaluate_parameters = eval_model(
        model=model_0,
        data_loader=test_dataloader,
        loss_fun=loss_fun,
        accuracy_fun=accuracy_fn,
        device=device
    )
    print('train loss:', train_parameters.get('loss'),
          'train acc:', train_parameters.get('acc'),
          '\ttest loss:', evaluate_parameters.get('loss'),
          'test acc:', evaluate_parameters.get('acc')
          )

train_end_on_cpu = timer()
print('train on cpu speed ', train_end_on_cpu - train_start_on_cpu, ' seconds.')
