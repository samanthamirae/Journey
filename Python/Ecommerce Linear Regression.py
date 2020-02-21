import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
%matplotlib inline

customers = pd.read_csv('Ecommerce Customers')
customers.describe()
customers.info()

sns.jointplot(x='Time on Website', y='Yearly Amount Spent', data=customers,color='gray')

sns.set_style('whitegrid')
sns.jointplot(x='Time on App', y='Yearly Amount Spent', data=customers,color='gray')

sns.jointplot(x='Time on App',y='Length of Membership', data=customers, kind='hex')
sns.pairplot(customers)




#Linear model plot
sns.lmplot(x='Length of Membership', y='Yearly Amount Spent', data=customers)



#Training and testing data
from sklearn.model_selection import train_test_split
y = customers['Yearly Amount Spent']
X = customers[['Avg. Session Length', 'Time on App','Time on Website', 'Length of Membership']]

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.3, random_state=101)

from sklearn.linear_model import LinearRegression
lm = LinearRegression()
lm.fit(X_train,y_train)

print('Coefficients:\n', lm.coef_)


#Predicting test data
predictions = lm.predict(X_test)

plt.scatter(y_test, predictions)
plt.xlabel('Y Test')
plt.ylabel('Predicted Y')


#Evaluating the model
from sklearn.metrics import mean_absolute_error
from sklearn.metrics import mean_squared_error

print('MAE: ', mean_absolute_error(y_test,predictions))
print('MSE: ', mean_squared_error(y_test, predictions))
print('RMSE: ', np.sqrt(mean_squared_error(y_test, predictions)))


#Residuals 
sns.distplot(y_test - predictions, bins=50)


#Conclusion

X.columns
df = pd.DataFrame(lm.coef_, X.columns, columns=['Coefficient'])



- Holding all other features fixed, a 1 unit increase in **Avg. Session Length** is associated with an **increase of 25.98 total dollars spent**.
- Holding all other features fixed, a 1 unit increase in **Time on App** is associated with an **increase of 38.59 total dollars spent**.
- Holding all other features fixed, a 1 unit increase in **Time on Website** is associated with an **increase of 0.19 total dollars spent**.
- Holding all other features fixed, a 1 unit increase in **Length of Membership** is associated with an **increase of 61.27 total dollars spent**.
df
