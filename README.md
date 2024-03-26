# MscProject
Identification of Products from Image

[Trained Model Link](https://drive.google.com/file/d/10M6RDzUpR6AKpLp8Er6JmnJlzgg8Y5-W/view?usp=drive_link)

# Identification-of-Product-from-an-Image [Advanced Computer Science]

## University Of Liverpool - [Angeline Sara Sabu] [201684974] 
This repository is dedicated to the development of an application for identifying products from images by applying the concepts of machine learning through convolutional neural networks and deploying the trained model using an Android app. 


## Description
The aim of this project is to design and develop an application for identifying products from images through the following objectives:
*.* To train a CNN model to classify fashion and clothing products from images
*.* To design an application in Android Studio that enables usersto capture and upload images for classification.
*.* To implement a system through Android studio , to transfer the image to an externally hosted CNN model via an API through a POST request.
*.* To integrate the application with the Google Search API to retrieve product information.
*.* To effectively display product information through the mobile application for user experience.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Contribution Guidelines](#contribution-guidelines)

## Installation
1. Clone the repository:  
git clone https://github.com/angelsara1998/MscProject.git


## 2.Install dependencies:
-conda create --name highwayenv python==3.9  
- ```pip install numpy```
-```pip install pandas```
-```pip install matplotlib```
-```pip install scikit-learn```
-```pip install opencv-python``` 
-```pip install tensorflow```
-```pip install flask```
-```pip install serpapi```
-```pip install sklearn```



## Usage
3.To check the environment how it works .py files are given in project directory
5. The main python files are Untitles27(3).ipynb ,Untitles28-Copy.ipynb
6. Untitles27(3).ipynb if the file used for preprocessing the dataset and training the model, Untitles28-Copy.ipynb is used for hosting the trained model and running the results through Google Search API.
7. best_model.h5 is the .h5 containing the trained ML model.
8. MainActivity.java, GetImage.java,CheckImage.java are the files for the main functions of the Android application.
9. activity_main.xml,activity_get_image.xml,activity_check_image.xml,AndroidManifest.XML are the XML files for the interactive components of the Android app.
10. kaggle.json and styles_new.csv are the dataset files from Kaggle to be uploaed in project folder in the IDE (eg. Visual Studio)
11. The gradle build.gradle.kts is the compiled android file which can be uploaded in Assets of Android Studio.


## Features
- *Train a CNN model with a dataset  :* Preprocess a Kaggle dataset of fashion clothing images and train a MobileNetV2 model and saves the .h5 model file
- *External API Integration:* The intermediary between the Android application and the externally hosted CNN model is a Flask based web application.
- *Android Development:* The application provides interface to the user for accessing the functionalities of the system such as providing the image input and viewing the product search information.
- *Evaluation Metrics:* Metrics to assess the accuracy the precision score and accuracy of the prediction model.

## Contribution Guidelines
We welcome contributions from the community! If you'd like to contribute, please follow these steps:
1. Fork the repository.
2. Make your changes.
3. Submit a pull request with a detailed description of your changes.



# -------COMP702 CS MSc project--------
