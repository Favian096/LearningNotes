from tqdm import trange
from timeit import default_timer as timer

from ComputerVision.BuildNonLinearModel import model_1, loss_fun, optimizer
from ComputerVision.OperateModel import train_model, eval_model
from DataPrepare import device, train_dataloader, test_dataloader
from helper_functions import accuracy_fn

# start non-linear model train on gpu
epochs = 3

train_start_on_gpu = timer()
for epoch in trange(epochs):
    print('Epoch: ', epoch)

    # start train
    train_parameters = train_model(
        model=model_1,
        data_loader=train_dataloader,
        loss_fun=loss_fun,
        optimizer=optimizer,
        accuracy_fun=accuracy_fn,
        device=device
    )

    # evaluate model
    evaluate_parameters = eval_model(
        model=model_1,
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

train_end_on_gpu = timer()
print('train on cpu speed ', train_end_on_gpu - train_start_on_gpu, ' seconds.')
