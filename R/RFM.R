###############################################################################################################
# RFM IN R	
# Example: RFM based on Book Binders Book Club (BBB)
###############################################################################################################

###############################################################################################################
# Set Path to Directory with Data
# 1) By Hand: setwd("C:/Users/orutz/Documents/2 UW/1 Data-driven Marketing (DDM)/1 Class/1 Lectures/0 CURRENT Version/2 Lecture Materials/8 RFM")
# 2) Better: Set Directory to where your code is saved. I recommend saving your data in the SAME Folder
setwd(dirname(rstudioapi::getActiveDocumentContext()$path))

# Clear All Variables
rm(list=ls())

# Read in the Data
data.bbb = read.csv("Data_BBB RFM_R.csv")

# Explore the data
str(data.bbb)
summary(data.bbb)


###############################################################################################################
# RFM 

# Define Data for RFM (Recency is "last", Frequency is "purch" and Monetary is "total")
# in HW, change "data.bbb$last" and data.bbb$purch and data.bbb$total
data.rfm <- data.frame(ID = data.bbb$acctnum, Recency = data.bbb$last, Frequency = data.bbb$purch, Monetary = data.bbb$total)

# Call the Functions used for RFM
# Need to have the file called "RFM_Functions.R" in the current directory
source("RFM_Functions.R")

RFM.score <-getIndependentScore(data.rfm)

# Exporting the Predictions to Excel (as a .csv file)
# Faster write to CSV file
write.csv(x = RFM.score, file='RFM_Independent.csv')

# Alterjnatively, export as an Excel File (this takes signifacntly longer)
# Need to install Package rJava and xlsx
# run "install.packages('rJava')" in the R Console window
# run "install.packages('xlsx')"  in the R Console window

# Need to load the Packages
#library(rJava)
#library(xlsx)

# Write to Excel (write the file into your current Directory)
# This will take a while given that the data has 50,000 Records!
#write.xlsx(x = RFM.score , file='RFM_Independent.xlsx',sheetName="RFM_Independent")


###############################################################################################################
