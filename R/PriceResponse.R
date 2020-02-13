###############################################################################################################
# PRICE RESPONSE IN R	
###############################################################################################################

###############################################################################################################
# Set Path to Directory with Data
# 1) By Hand: setwd("C:/Users/orutz/Documents/2 UW/1 Data-driven Marketing (DDM)/1 Class/1 Lectures/0 CURRENT Version/2 Lecture Materials/4 Price Promo/Price")
# 2) Better: Set Directory to where your code is saved. I recommend saving your data in the SAME Folder
setwd(dirname(rstudioapi::getActiveDocumentContext()$path))

# Clear All Variables & Clear the Screen
rm(list=ls())
cat("\014")

# Read in the Data
data.satisfaction = read.csv("Data_Price_R.csv")

# Explore the data
str(data.satisfaction)
summary(data.satisfaction)


###############################################################################################################
# PREPARE DATA

# Create log UNITS
data.price$logUNITS1 <- log(data.price$UNITS1)
data.price$logUNITS2 <- log(data.price$UNITS2)
data.price$logUNITS3 <- log(data.price$UNITS3)
data.price$logUNITS4 <- log(data.price$UNITS4)

# Create log PRICES
data.price$logPRICE1 <- log(data.price$PRICE1)
data.price$logPRICE2 <- log(data.price$PRICE2)
data.price$logPRICE3 <- log(data.price$PRICE3)
data.price$logPRICE4 <- log(data.price$PRICE4)


###############################################################################################################
# REGRESSION MODELS

# You need: y (Dependent Variable)
# For this example, Sales for Brand 1 (UNITS1) is used

# You need: X (Independent Variables)
# For this example, OWN Marketing Activity (PRICE1, FEAT1 and DISP1) is used

# Run the Regression (includes an INTERCEPT)
lm.model1 <- lm(satisfaction_level ~ last_evaluation + number_project + average_montly_hours + time_spend_company + Work_accident + left + promotion_last_5years + sales + salary, data = data.price)
lm.model1log1 <- lm(satisfaction_level  ~ last_evaluation + number_project + average_montly_hours + time_spend_company + Work_accident + left + promotion_last_5years  + sales + salary, data = data.price)
lm.model1log1a <- lm(satisfaction_level  ~ last_evaluation + number_project + average_montly_hours + time_spend_company + Work_accident + left + promotion_last_5years + sales + salary, data = data.price)


# Display Results
summary(lm.model1)
summary(lm.model1log1)
summary(lm.model1log1a)


###############################################################################################################
