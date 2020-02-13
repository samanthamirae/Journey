import sys

totalOrders = 0 #tracks number of orders in the batch
batchCost = 0 #tracks the total cost across all orders in this batch



# our functions
def orderprice(wA,wB,wC):
    #calculates the cost total of the order
    
    cTotal = 2.67 * wA + 1.49 * wB + 0.67 * wC

    if cTotal > 100:
        cTotal = cTotal * .95
    
    return cTotal

def shippingcharge(oW):
    #Finds the shipping charge for the order based on total weight
    
    if oW <= 5:
        sCost = 3.5
    elif oW <= 20:
        sCost = 10
    else:
        sCost = 9.5 + .1 * oW

    return sCost

def submitorder():
    #Lets the user submit a new order
    
    global totalOrders
    global batchCost

    try: #checks for a float
        weightA = float(input("How many pounds of Artichokes would you like to order?\n"))
                    
        weightB = float(input("How many pounds of Beets would you like to order?\n"))
                    
        weightC = float(input("How many pounds of Carrots would you like to order?\n"))
        
    

        costTotal = orderprice(weightA,weightB,weightC)
    
        orderWeight = weightA + weightB + weightC

        shippingCost = shippingcharge(orderWeight)

        orderTotal = shippingCost + costTotal

        #update totals
        totalOrders += 1
        batchCost = batchCost + orderTotal

        #Tell the user the cost, shipping charge, and final charge for their order
        print("Total Cost is: " '${:,.2f}'.format(costTotal))    
        print("Shipping Charge for this order is: " '${:,.2f}'.format(shippingCost))
        print("Order Cost and Shipping: " '${:,.2f}'.format(orderTotal))

    except ValueError:
        print("Invalid weight. Please choose again.")

def summary():
    #Shows statistics of the batch including number of orders, total cost of all orders,
    #and average cost for an order
    
    avg = 0
    if  totalOrders > 0:
        avg = batchCost / totalOrders
        
    print("Number of Orders:",totalOrders)
    print("Total Cost of All Orders: " '${:,.2f}'.format(batchCost))
    print("Average Order Cost: " '${:,.2f}'.format(avg))
    

def reset():
    #Resets the batch statistics to prepare for a new batch
    
    global totalOrders
    totalOrders = 0

    global batchCost
    batchCost = 0


#execute the program as long as "exit" isn't entered:

while True:
    print("Type submit to enter a new order, ")
    print("type summary to see batch statistics, ")
    print("type reset to reset statistics, or type exit to exit")

    line = sys.stdin.readline()
    
    if line.strip() == 'submit':
        submitorder()
    elif line.strip() == 'summary':
        summary()
    elif line.strip() == 'reset':
        reset()
    elif line.strip() == 'exit':
        break
    else:
        print("Invalid choice. Try again.")


                

                
