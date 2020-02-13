###############################################################################################################
# NEURAL NETWORKS IN r
# Example: Churn Modeling Example from Slides
###############################################################################################################

###############################################################################################################
# Install Neural Network Package and "open" it (call the library)
# Type "?neuralnet" for more information on the neuralnet library
install.packages('neuralnet')
library("neuralnet")
 
###############################################################################################################
# Set Path to Directory with Data
# 1) By Hand: setwd("C:/Users/orutz/Documents/2 UW/1 Data-driven Marketing (DDM)/1 Class/1 Lectures/0 CURRENT Version/2 Lecture Materials/10 Churn")
# 2) Better: Set Directory to where your code is saved. I recommend saving your data in the SAME Folder
setwd(dirname(rstudioapi::getActiveDocumentContext()$path))

# Clear All Variables & Clear the Screen
rm(list=ls())
cat("\014")

# Read in the Data
data.churn = read.csv("Data_Churn_R.csv")

# Explore the data
str(data.churn)
summary(data.churn)


###############################################################################################################
# Need to Create Training Data (i.e., Estimation Data) and Test Data (i.e., Holdout Data)
# The data has 2,000 observations. use the first 1,000 for training
data.train <- data.churn[1:1000,]
data.test  <- data.churn[1001:2000,]
 
# You need: y (Dependent Variable)
# For this example, y (a consumer either churns or not) is used

# You need: X (Independent Variables)
# For this example, lp1, lp2, lp3, and lp4 are used (see class slides for how we caculated these)

# Train the neural network
# Going to have X hidden layers (e.g., I am using 10 hidden layers below)
# Threshold is a numeric value specifying the threshold stopping criteria
neuralnet.model <- neuralnet(y~lp1+lp2+lp3+lp4,data.train, hidden=10, threshold=0.01,stepmax=1e6,)

# Lets see what properties neuralnet.fit has
summary(neuralnet.model)
 
#Plot the neural network
plot(neuralnet.model)

 
###############################################################################################################
# Test the Neural Network on the Test Data
# Predict who will churn (ID 1001-2000) based on their observed covariates
data.holdout      <- data.churn[1001:2000,1]
data.test         <- data.churn[1001:2000,2:5]

# Use the Neural Network to compute the probabiltiies of chrun for IDs 1001-2000
neuralnet.predict <- compute(neuralnet.model, data.test) 

# Lets see what properties neuralnet.predict has
summary(neuralnet.predict)

# Prediction of Holdout data (i.e., who churns?)
NeuralNetPredict <- round(neuralnet.predict$net.result, digits = 0)
print(neuralnet.predict$net.result)
 

###############################################################################################################
# Lets display a better version of the results
neuralnet.prediction <- data.frame(data.holdout, NeuralNetPredict,neuralnet.predict$net.result)
print(neuralnet.prediction)

# Create Confusion Matrix
# You should have already installed the package "gmodels" in our Intro_R code
library(gmodels)
CrossTable(NeuralNetPredict, data.holdout,prop.r=FALSE, prop.c=FALSE,prop.t=FALSE,
           prop.chisq=FALSE,dnn = c("Predict", "Actual"))


###############################################################################################################
