###############################################################################################################
# BINARY LOGIT (or 0/1 Choice Model) IN R	
# Example: List Scoring 200 Names
###############################################################################################################

###############################################################################################################
# Set Path to Directory with Data
# 1) By Hand: setwd("C:/Users/orutz/Documents/2 UW/1 Data-driven Marketing (DDM)/1 Class/1 Lectures/0 CURRENT Version/2 Lecture Materials/6 Binary Logit")
# 2) Better: Set Directory to where your code is saved. I recommend saving your data in the SAME Folder
setwd(dirname(rstudioapi::getActiveDocumentContext()$path))

# Clear All Variables & Clear Screen
rm(list=ls())
cat("\014")

# Read in the Data
data.list = read.csv("Data_Binary Logit_List Scoring_R.csv")

# Explore the data
str(data.list)
summary(data.list)


###############################################################################################################
# BINARY LOGIT 

# You need: y (Dependent Variable)
# For this example, y_Response (a consumer either buys or not) is used

# You need: X (Independent Variables)
# For this example, x1_Gender, x2_Hotline1 and x3_Hotline2 are used

# Run the Regression (includes an INTERCEPT)
lm.model <- lm(y_Response~ x1_Gender+ x2_Hotline1+ x3_Hotline2, data = data.list)

# Display Results
summary(lm.model)

# Run the Binary Logit Model (includes an INTERCEPT)
# generalized linear model
# family=binomial(link='logit') --> is doing the choice model. We NEVER touch it. It just tells it to run a logit
glm.model <- glm(y_Response~ x1_Gender+ x2_Hotline1+ x3_Hotline2,family=binomial(link='logit'),data=data.list)

# Display Results
summary(glm.model)


###############################################################################################################
# SCORING

# Using the estimates from the LINEAR REGRESSION Model (lm.model) and the BINARY LOGIT Model (glm.model)

# Predicting Buy/No Buy for the 200 IDs based on the Model Estimates
(prediction <- data.frame(ID = data.list$ID, 
                          LinearRegressionPredict = round(predict(lm.model),digits = 0), 
                          BinaryLogitPredict = round(predict(glm.model,type = c("response")), digits = 0)))

# Prediciton of Number of Buyers
sum(prediction["LinearRegressionPredict"])
sum(prediction["BinaryLogitPredict"])

# Exporting the Predictions to Excel

# Need to install Package rJava and xlsx
# run "install.packages('rJava')" in the R Console window
# run "install.packages('xlsx')"  in the R Console window

# Need to load the Packages
library(rJava)
library(xlsx)

# Write to Excel (write the file into your current Directory)
write.xlsx(x = prediction, file='Prediction.xlsx',sheetName="Predictions")


###############################################################################################################