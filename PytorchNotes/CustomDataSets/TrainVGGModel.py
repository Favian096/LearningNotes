import torch
import matplotlib

matplotlib.use('TKAgg')
from matplotlib import pyplot as plt
from CustomDataSets.BuildTinyVGGModel import model
from CustomDataSets.DataLoadByImageFolder import train_dataloader, test_dataloader
from CustomDataSets.OperateModel import train_eval_model
from timeit import default_timer as timer

from Utils import printl

printl('start train model')
start = timer()
loss_fun = torch.nn.CrossEntropyLoss()
optimizer = torch.optim.Adam(params=model.parameters(), lr=0.001)

result = train_eval_model(
    model=model,
    train_dataloader=train_dataloader,
    eval_dataloader=test_dataloader,
    loss_fun=loss_fun,
    optimizer=optimizer,
    epochs=3
)

end = timer()

printl('result visualization')
print('Training time: ' + str(end - start), 'seconds')

plt.figure(figsize=(12, 6))
plt.subplot(1, 2, 1)
plt.plot(result['train_batches'], result['train_loss_arr'], c='b', label='train loss')
plt.plot(result['train_batches'], result['train_acc_arr'], c='r', label='train acc')
plt.xlabel('batches')
plt.ylabel('loss')
plt.legend()
plt.subplot(1, 2, 2)
plt.plot(result['eval_batches'], result['eval_loss_arr'], c='b', label='eval loss')
plt.plot(result['eval_batches'], result['eval_acc_arr'], c='r', label='eval acc')
plt.xlabel('batches')
plt.ylabel('acc')
plt.legend()
plt.show()

print('====>Obviously, result performed pretty poorly. But it can still use to predict')

torch.save(obj=model.state_dict(), f='./Models/TinyVGGModel.pth')
