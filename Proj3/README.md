1. The fist part is done. The code works well for provided three.xml files. The only problem is that the recursive call must be executed in Topological Order. So it is better to read the file in topological order. The first two files are fine, the third one I modified the code a little bit, which might not work for other input files. Since I wrote the read input by myself. It might not work for other formats.

2. The Rejection sampling is implemented and tested on all three cases.

3. Add LikelihoodWeighting method 

4. Add Gibbs Sampling. Two important notice : 
	a) the probability of a variable given its Markov blanket is proportional to the probability of the variable
	given it parents times the probability of each child given its respective parents. This probability should be
	normalized using both A and -A
	b) no matter A -A the transition P should keep the same
