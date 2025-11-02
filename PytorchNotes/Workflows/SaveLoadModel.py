import torch
from DataPrepare import x_test
from BuildModel import model_0, LinearRegressionModel
import EvaluateModel
from Utils import printl

MODEL_PATH = './Models/'

# Only save model state_dict(Recommended)
printl('save and load model only state_dict')
model_state_dict_name = 'Workflows_model_0_state_dict.pth'
torch.save(obj=model_0.state_dict(), f=MODEL_PATH + model_state_dict_name)

# load model from state_dict (Recommended)
loaded_state_dict_model_0 = LinearRegressionModel()
loaded_state_dict_model_0.load_state_dict(torch.load(MODEL_PATH + model_state_dict_name))

loaded_state_dict_model_0.eval()
with torch.inference_mode():
    y_pred_load = loaded_state_dict_model_0(x_test)

print(y_pred_load)
print(EvaluateModel.y_pred == y_pred_load)

# save whole model (Not recommended)
printl('save and load whole model')
model_name = 'Workflows_model_0.pth'
torch.save(obj=model_0, f=MODEL_PATH + model_name)

# load model from whole model (Not recommended)
loaded_whole_model_0 = torch.load(MODEL_PATH + model_name, weights_only=False)

loaded_whole_model_0.eval()
with torch.inference_mode():
    y_pred_whole = loaded_whole_model_0(x_test)

print(y_pred_whole)
print(y_pred_whole == EvaluateModel.y_pred)

# save model as TorchScript
printl('save and load TorchScript')
model_TorchScript_name = 'Workflows_model_0_scripted.pt'
torch.jit.script(model_0).save(MODEL_PATH + model_TorchScript_name)

# load model from TorchScript
loaded_scripted_model_0 = torch.jit.load(MODEL_PATH + model_TorchScript_name)

loaded_scripted_model_0.eval()
with torch.inference_mode():
    y_pred_scripted = loaded_scripted_model_0(x_test)

print(y_pred_scripted)
print(y_pred_scripted == EvaluateModel.y_pred)
