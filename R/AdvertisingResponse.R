###############################################################################################################
# ADVERTISING RESPONSE IN R	
###############################################################################################################

###############################################################################################################
# Set Path to Directory with Data
# 1) By Hand: setwd("C:/Users/orutz/Documents/2 UW/1 Data-driven Marketing (DDM)/1 Class/1 Lectures/0 CURRENT Version/2 Lecture Materials/5 Advertising")
# 2) Better: Set Directory to where your code is saved. I recommend saving your data in the SAME Folder
setwd(dirname(rstudioapi::getActiveDocumentContext()$path))

# Clear All Variables & Clear the Screen
rm(list=ls())
cat("\014")

# Read in the Data
data.adv = read.csv("Data_Advertising_R.csv")

# Explore the data
str(data.adv)
summary(data.adv)


###############################################################################################################
# PREPARE DATA

# Create log Advertising
data.adv$logAdvertising <- log(data.adv$Advertising)

# Create Advertising^2
data.adv$Advertising2 <- (data.adv$Advertising)^2

# Create LAG Sales
data.adv$lagSales <- c(NA, head(data.adv$Sales, -1))

# Display the data
summary(data.adv)


###############################################################################################################
# REGRESSION MODELS

# You need: y (Dependent Variable)
# For this example, Sales is used

# You need: X (Independent Variables)
# For this example, Advertising and Lagged Sales (i.e, a model with carry-over) are used

# Run the Regression (includes an INTERCEPT)
lm.model1 <- lm(Sales ~ Advertising + lagSales, data = data.adv)


#simple linear
lm.model1 <- lm(Sales ~ Advertising, data = data.adv)
#concave logarithmic
lm.modellog1 <- lm(Sales ~ logAdvertising, data = data.adv)
#concave quadratic
lm.modelquad1 <- lm(Sales ~ Advertising + Advertising2, data = data.adv)

#simple linear w/ carryover
lm.model1lag <- lm(Sales ~ Advertising + lagSales, data = data.adv)
#concave logarithmic w/ carryover
lm.modellog1lag <- lm(Sales ~ logAdvertising + lagSales, data = data.adv)
#concave quadratic w/ carryover
lm.modelquad1lag <- lm(Sales ~ Advertising + lagSales + Advertising2, data = data.adv)

# Display Results
summary(lm.model1)
summary(lm.modellog1)
summary(lm.modelquad1)

summary(lm.model1lag)
summary(lm.modellog1lag)
summary(lm.modelquad1lag)


###############################################################################################################
