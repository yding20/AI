1. The fist part is done. The code works well for provided three.xml files. The only problem is that the recursive call must be executed in Topological Order. So it is better to read the file in topological order. The first two files are fine, the third one I modified the code a little bit, which might not work for other input files. Since I wrote the read input by myself. It might not work for other formats.

2. The Rejection sampling is implemented and tested on all three cases.

3. Add LikelihoodWeighting method 

4. Add Gibbs Sampling. Two important notice : 
	a) the probability of a variable given its Markov blanket is proportional to the probability of the variable
	given it parents times the probability of each child given its respective parents. This probability should be
	normalized using both A and -A
	b) no matter A -A the transition P should keep the same

5. submit 
The file shows how to bulid the project and output of the project. The detailed analysis and demenstation to meet the requirement is explained in the .pdf write up report. The sample outputs are shown in Exact.txt and Appro.txt. Thanks!

Build the Project:
	Part1 : Exact Inference
		javac ExactInference.java    (ignore warnings java array generics cast)
		java ExactInference aima-alarm.xml B J true M true
		
		output : The result ditribution is <0.28417184 0.71582816 >  time : 0.00050173
		More output sample is in Exact.txt

	Part2 : Approximate Inference
		javac ApproxInferencer.java    (ignore warnings java array generics cast)
		java ApproxInferencer 10 aima-alarm.xml B J true M true

		output : 
		RejectionSampling :  <0.00000000, 1.00000000>    time : 0.00200000   total : 49  accepted : 10
		LikelihoodWeighting :  <0.00000000, 1.00000000>    time : 0.00000000
		GibbSampling :  <0.30000000, 0.70000000>    time : 0.00000000
		More output sample is in Exact.txt
