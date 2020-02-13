###############################################################################################################
# LIST SCORING IN R	
# Example: List Scoring 300 NEW Names (HOLDOUT) using Data from 200 Names (ESTIMATION)
###############################################################################################################

###############################################################################################################
# Set Path to Directory with Data
# 1) By Hand: setwd("C:/Users/orutz/Documents/2 UW/1 Data-driven Marketing (DDM)/1 Class/1 Lectures/0 CURRENT Version/2 Lecture Materials/7 List Scoring")
# 2) Better: Set Directory to where your code is saved. I recommend saving your data in the SAME Folder
setwd(dirname(rstudioapi::getActiveDocumentContext()$path))

# Clear All Variables & Clear the Screen
rm(list=ls())
cat("\014")

# Read in the Data
data.list = read.csv("Data_List Scoring_R.csv")

# Explore the data
str(data.list)
summary(data.list)


###############################################################################################################
# BINARY LOGIT 
# Running the Model on the FIRST 200 IDs

# Define ESTIMATION Data (i.e, using the FIRST 200 IDs)
# run entire line then only left side of the arrow
# sub dataset
data.estimation <- data.list[1:200,]

# You need: y (Dependent Variable)
# For this example, y_Response (a consumer either buys or not) is used

# You need: X (Independent Variables)
# For this example, x1_Gender, x2_Hotline1 and x3_Hotline2 are used

# Run the Binary Logit Model (includes an INTERCEPT)
glm.model <- glm(y_Response~ x1_Gender+ x2_Hotline1+ x3_Hotline2,family=binomial(link='logit'),data=data.estimation)

# Display Results
summary(glm.model)


###############################################################################################################
# SCORING the HOLDOUT Data (i.e, IDs 201-500 which are NOT used for estimation)

# Define HOLDOUT Data (i.e, IDs 201-500)
# forecasting dataset. Only forecasting for the last 300 people
data.holdout <- data.list[201:500,]

# Predicting Buy/No Buy for the 300 HOLDOUT IDs based on the Model Estimates
# data frame = matrix
(prediction.holdout <- data.frame(ID = data.holdout$ID, 
                                  BinaryLogitProbability   = predict(glm.model,data.holdout,type = c("response")),
                                  BinaryLogitPredict = round(predict(glm.model,data.holdout,type = c("response")), digits = 0)))

# Prediciton of Number of Buyers
sum(prediction.holdout["BinaryLogitPredict"])

# Exporting the Predictions to Excel

# If BOTH of these do not work for you one can save this as a csv file
# You can open a csv file in xl
write.csv(x = prediction.holdout, file='Prediction.csv')

# Need to install Package rJava and xlsx
# install.packages('rJava')
# install.packages('xlsx')

# Need to load the Packages
# library(rJava)
# library(xlsx)

# Write to Excel (write the file into your current Directory)
#write.xlsx(x = prediction.holdout, file='Prediction_Holdout.xlsx',sheetName="Predictions_Holdout")


# There is sometimes an issue with the xlsx libray on OS.
# Here's an alternative way if you cannot get the code above to run
#install.packages("XLConnect" )
#install.packages("XLConnectJars" )
#library(XLConnectJars)
#library(XLConnect)

#wb <- loadWorkbook("Prediction_Holdout.xlsx",create = TRUE )
#createSheet(wb, name = "Predictions_Holdout")
#writeWorksheet(wb,prediction.holdout,sheet="Predictions_Holdout",startRow=1,startCol=1)
#saveWorkbook(wb)

###############################################################################################################
# Create Confusion Matrix
# Need to instal some more packages (a common theme in R)
install.packages('caret')
library(caret)
install.packages('e1071')
library(e1071)

confusionMatrix(prediction.holdout$BinaryLogitPredict,data.holdout$y_Response)


###############################################################################################################