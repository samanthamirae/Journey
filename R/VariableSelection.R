###############################################################################################################
# VARIABLE SELECTION IN R	
###############################################################################################################

###############################################################################################################
# Set Path to Directory with Data
# 1) By Hand: setwd("C:/Users/orutz/Documents/2 UW/1 Data-driven Marketing (DDM)/1 Class/1 Lectures/0 CURRENT Version/2 Lecture Materials/9 Filter and VS")
# 2) Better: Set Directory to where your code is saved. I recommend saving your data in the SAME Folder
setwd(dirname(rstudioapi::getActiveDocumentContext()$path))

# Clear All Variables & Clear the Screen
rm(list=ls())
cat("\014")

# Read in the Data
data.vs = read.csv("Data_VariableSelection_R.csv")

# Explore the data
str(data.vs)
summary(data.vs)


###############################################################################################################
# VARIABLE SELECTION REGRESSION MODEL

# Need to load and open package 'MASS'
install.packages('MASS')
library(MASS)

# You need: y (Dependent Variable)
# For this example, y is used

# You need: X (Independent Variables)
# For this example, x1-x16 are used

# Variable Selection by Stepwise Regression
# Note instead of writing y~x1+x2+x3+... one can simply write y~. and the model will use all data in the
# current dataset (in our case, data.vs) that is not called 'y'
model.ols <- lm(y~.,data=data.vs)
model.vs  <- stepAIC(model.ols, direction="both")
model.vs$anova # display results

# Final model is most important. it tells you most important variables!!!!

# Display the Results
summary(model.vs)


###############################################################################################################
