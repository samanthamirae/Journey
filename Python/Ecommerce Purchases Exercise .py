
# 
# In this practice I was given some Fake Data about some purchases done through Amazon

import pandas as pd


# In[12]:


ecom = pd.read_csv('Ecommerce Purchases.csv')


# **Check the head of the DataFrame.**

# In[14]:


ecom.head(1)


# ** How many rows and columns are there? **

# In[15]:


ecom.info()


# ** What is the average Purchase Price? **

# In[16]:


ecom['Purchase Price'].mean()


# ** What were the highest and lowest purchase prices? **

# In[18]:


ecom['Purchase Price'].max()


# In[19]:


ecom['Purchase Price'].min()


# ** How many people have English 'en' as their Language of choice on the website? **

# In[21]:


sum(ecom['Language'] == 'en')


# ** How many people have the job title of "Lawyer" ? **
# 

# In[26]:


ecom[ecom['Job']== 'Lawyer'].count()


# ** How many people made the purchase during the AM and how many people made the purchase during PM ? **
# 
# **(Hint: Check out [value_counts()](http://pandas.pydata.org/pandas-docs/stable/generated/pandas.Series.value_counts.html) ) **

# In[28]:


ecom['AM or PM'].value_counts()


# ** What are the 5 most common Job Titles? **

# In[30]:


ecom['Job'].value_counts().head(5)


# ** Someone made a purchase that came from Lot: "90 WT" , what was the Purchase Price for this transaction? **

# In[33]:


ecom[ecom['Lot'] == '90 WT']['Purchase Price']


# ** What is the email of the person with the following Credit Card Number: 4926535242672853 **

# In[37]:


ecom[ecom['Credit Card'] == 4926535242672853]['Email']


# ** How many people have American Express as their Credit Card Provider *and* made a purchase above $95 ?**

# In[47]:


ecom[(ecom['CC Provider'] == 'American Express') & (ecom['Purchase Price'] > 95)].count()


# ** Hard: How many people have a credit card that expires in 2025? **

# In[61]:


ecom[ecom['CC Exp Date'].apply(lambda exp: exp[3:] =='25')].count()


# ** Hard: What are the top 5 most popular email providers/hosts (e.g. gmail.com, yahoo.com, etc...) **

# In[68]:


ecom['Email'].apply(lambda host: host.split('@')[1]).value_counts().head(5)

