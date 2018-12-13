import math
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.utils import shuffle

import warnings
with warnings.catch_warnings():
	warnings.filterwarnings("ignore",category=FutureWarning)
	import h5py
	import tensorflow as tf
	from tensorflow.python.framework import ops

np.random.seed(1)
def dataCleaning(s) : 
	# head = 1 then use first line as names
	df = pd.read_csv(s, sep=",", header=None, names = ["sl", "sw", "pl", "pw", "class"])
	data = df.reindex(np.random.permutation(df.index))
	labels1, uniques1 = pd.factorize(data['sl'])
	data['sl'] = labels1
	labels2, uniques2 = pd.factorize(data['sw'])
	data['sw'] = labels2
	labels3, uniques3 = pd.factorize(data['pl'])
	data['pl'] = labels3
	labels4, uniques4 = pd.factorize(data['pw'])
	data['pw'] = labels4
	X_train_orig = np.concatenate(([labels1], [labels2], [labels3], [labels4]), axis=0)
	X_train = X_train_orig
	
	labels, uniques = pd.factorize(data['class'])
	data['class'] = labels
	Y_train = np.eye(3)[labels.reshape(-1)].T
# 	data = pd.read_csv(s, sep=",", header=None, names = ["buying", "maint", "doors", "persons", "lug_boot", "safety", "classification"])
# 	df = shuffle(data)
# 	labels1, uniques1 = pd.factorize(data['buying'])
# 	data['buying'] = labels1
# 	labels2, uniques2 = pd.factorize(data['maint'])
# 	data['maint'] = labels2
# 	labels3, uniques3 = pd.factorize(data['doors'])
# 	data['doors'] = labels3
# 	labels4, uniques4 = pd.factorize(data['persons'])
# 	data['persons'] = labels4
# 	labels5, uniques5 = pd.factorize(data['lug_boot'])
# 	data['lug_boot'] = labels5
# 	labels6, uniques6 = pd.factorize(data['safety'])
# 	data['safety'] = labels6
# 	X_train_orig = np.concatenate(([labels1], [labels2], [labels3], [labels4], [labels5], [labels6]), axis=0)
# 	X_train = X_train_orig
	
# 	labels, uniques = pd.factorize(data['classification'])
# 	data['classification'] = labels
# 	Y_train = np.eye(4)[labels.reshape(-1)].T

	#return X_train.T[0:1000].T, Y_train.T[0:1000].T, X_train.T[1000:].T, Y_train.T[1000:].T
	return X_train.T[0:60].T, Y_train.T[0:60].T, X_train.T[60:].T, Y_train.T[60:].T

def create_placeholders(n_x, n_y):

    X = tf.placeholder(tf.float32, [n_x ,None])
    Y = tf.placeholder(tf.float32, [n_y, None])
    
    return X, Y

def initialize_parameters(n_x, n_y):
    
    tf.set_random_seed(3) 
        
    W1 = tf.get_variable("W1", [25, n_x], initializer = tf.contrib.layers.xavier_initializer(seed = 1))
    b1 = tf.get_variable("b1", [25,1], initializer = tf.zeros_initializer())
    W2 = tf.get_variable("W2", [12, 25], initializer = tf.contrib.layers.xavier_initializer(seed = 1))
    b2 = tf.get_variable("b2", [12,1], initializer = tf.zeros_initializer())
    W3 = tf.get_variable("W3", [n_y, 12], initializer = tf.contrib.layers.xavier_initializer(seed = 1))
    b3 = tf.get_variable("b3", [n_y,1], initializer = tf.zeros_initializer())

    parameters = {"W1": W1,
                  "b1": b1,
                  "W2": W2,
                  "b2": b2,
                  "W3": W3,
                  "b3": b3}
    
    return parameters

def forward_propagation(X, parameters):
    W1 = parameters['W1']
    b1 = parameters['b1']
    W2 = parameters['W2']
    b2 = parameters['b2']
    W3 = parameters['W3']
    b3 = parameters['b3']
    
    ### START CODE HERE ### (approx. 5 lines)              # Numpy Equivalents:
    Z1 = tf.add(tf.matmul(W1, X), b1)                      # Z1 = np.dot(W1, X) + b1
    A1 = tf.nn.relu(Z1)                                   # A1 = relu(Z1)
    Z2 = tf.add(tf.matmul(W2, A1), b2)                    # Z2 = np.dot(W2, a1) + b2
    A2 = tf.nn.relu(Z2)                                    # A2 = relu(Z2)
    Z3 = tf.add(tf.matmul(W3, A2), b3)                    # Z3 = np.dot(W3,Z2) + b3
    ### END CODE HERE ###
    
    return Z3


def compute_cost(Z3, Y):

    logits = tf.transpose(Z3)
    labels = tf.transpose(Y)
    
    cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits = logits, labels = labels))
    
    return cost

X_train, Y_train, X_test, Y_test = dataCleaning('iris.data.discrete.txt')
#X_train, Y_train, X_test, Y_test = dataCleaning('car.data.txt')

ops.reset_default_graph()                      
tf.set_random_seed(3)                          
seed = 5                                       
(n_x, m) = X_train.shape                       
n_y = Y_train.shape[0]    

costs = []   
accuracy_train = []
accuracy_test = [] 

X = tf.placeholder(tf.float32, [n_x ,None])
Y = tf.placeholder(tf.float32, [n_y, None])   

parameters = initialize_parameters(n_x, n_y)
Z3 = forward_propagation(X, parameters)
## it could also be calcualated directly
##cross_entropy = tf.reduce_mean(-tf.reduce_sum(y_ * tf.log(y), reduction_indices=[1]))
cost = compute_cost(Z3, Y)
optimizer = tf.train.AdamOptimizer(learning_rate = 0.0001).minimize(cost)

init = tf.global_variables_initializer()

with tf.Session() as sess:
	sess.run(init)

	correct_prediction = tf.equal(tf.argmax(Z3), tf.argmax(Y))
	accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))

	for epoch in range(10000):
		epoch_cost = 0.
		seed = seed + 1
		_ , epoch_cost  = sess.run([optimizer, cost], feed_dict={X: X_train, Y: Y_train})

		if epoch % 1000 == 0:
			print ("Cost after epoch %i: %f" % (epoch, epoch_cost))
		if epoch % 100 == 0:
			costs.append(epoch_cost)
			accuracy_train.append(accuracy.eval({X: X_train, Y: Y_train}))
			accuracy_test.append(accuracy.eval({X: X_test, Y: Y_test}))

	print ("Train Accuracy:", accuracy.eval({X: X_train, Y: Y_train}))
	print ("Test Accuracy:", accuracy.eval({X: X_test, Y: Y_test}))


