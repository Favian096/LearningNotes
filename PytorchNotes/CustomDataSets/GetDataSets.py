import zipfile

DATA_PATH = 'DataSets/Downloads/pizza_steak_sushi.zip'
IMG_PATH = 'DataSets/Pizza_Steak_Sushi'

# extractall data set
with zipfile.ZipFile(DATA_PATH, 'r') as zip_ref:
    zip_ref.extractall(IMG_PATH)
