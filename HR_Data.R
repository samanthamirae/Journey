###############################################################################################################
# HR RESPONSE IN R	
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
data.satisfaction = read.csv("HR_comma_sep.csv")

# Explore the data
str(data.satisfaction)
summary(data.satisfaction)


###############################################################################################################
# PREPARE DATA

# Create log of all factors
data.satisfaction$logsatisfaction_level <- log(data.satisfaction$satisfaction_level)
data.satisfaction$loglast_evaluation <- log(data.satisfaction$last_evaluation)
data.satisfaction$lognumber_project <- log(data.satisfaction$number_project)
data.satisfaction$logaverage_montly_hours <- log(data.satisfaction$average_montly_hours)
data.satisfaction$logtime_spend_company <- log(data.satisfaction$time_spend_company)
data.satisfaction$logWork_accident <- log(data.satisfaction$Work_accident)
data.satisfaction$logleft <- log(data.satisfaction$left)
data.satisfaction$logpromotion_last_5years <- log(data.satisfaction$promotion_last_5years)
data.satisfaction$logrole <- log(data.satisfaction$role)
data.satisfaction$logsalary <- log(data.satisfaction$salary)



###############################################################################################################
# REGRESSION MODELS

# You need: y (Dependent Variable)
# For this example, Sales for Brand 1 (UNITS1) is used

# You need: X (Independent Variables)
# For this example, OWN Marketing Activity (PRICE1, FEAT1 and DISP1) is used

# Run the Regression (includes an INTERCEPT)
# linear models
    # lm.model1 <- lm(satisfaction_level ~ last_evaluation + number_project + average_montly_hours + time_spend_company + Work_accident + left + promotion_last_5years + role + salary, data = data.satisfaction)

# semilog models (y=satisfaction)
lm.model1log1 <- lm(logsatisfaction_level ~ last_evaluation + number_project + average_montly_hours + time_spend_company + Work_accident + promotion_last_5years  + role + salary, data = data.satisfaction)
# y=left
lm.model2log1 <- lm(logleft ~ last_evaluation + number_project + average_montly_hours + time_spend_company + Work_accident + satisfaction_level + promotion_last_5years  + role + salary, data = data.satisfaction)
# y=last evaluation
lm.model3log1 <- lm(loglast_evaluation ~ number_project + average_montly_hours + time_spend_company + Work_accident + promotion_last_5years  + role + salary, data = data.satisfaction)
# y=satisfaction not with binary
lm.model1log1 <- lm(logsatisfaction_level ~ last_evaluation + number_project + average_montly_hours + time_spend_company + Work_accident + promotion_last_5years, data = data.satisfaction)

lm.model4log1 <- lm(logsatisfaction_level ~ last_evaluation + number_project + average_montly_hours + time_spend_company, data = data.satisfaction)

# Display Results
   # summary(lm.model1)
summary(lm.model1log1)
summary(lm.model2log1)
summary(lm.model3log1)
summary(lm.model4log1)

###############################################################################################################
