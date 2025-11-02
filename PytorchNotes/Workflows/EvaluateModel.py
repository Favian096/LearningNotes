import torch
import matplotlib.pyplot as plt
from BuildModel import model_0
from Utils import printl
from DataPrepare import x_test, plot_predictions
import TrainModel

TrainModel.train()

plt.figure(figsize=(8, 6))
plt.plot(TrainModel.epoch_count, TrainModel.train_loss_value, label='Train Loss')
plt.plot(TrainModel.epoch_count, TrainModel.test_loss_value, label='Test Loss')
plt.xlabel('epochs')
plt.ylabel('loss')
plt.legend()
plt.show()

# check the parameters after train
printl('parameters after train')
print(model_0.state_dict())
print('epoch count:', TrainModel.epoch_count)
print('train loss data:', TrainModel.train_loss_value)
print('test loss data:', TrainModel.test_loss_value)

# evaluate model
printl('evaluate and predict data')
model_0.eval()
with torch.inference_mode():
    y_pred = model_0(x_test)
print(y_pred)
plot_predictions(y_pred)
