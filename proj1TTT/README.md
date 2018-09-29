1. 0915: implement simple MAXMIN algorithm for adversarial search (TTT). The program is able to play with human and play with itself. The choice is in main method. To play with another AI, do not know how to control read sequence of two AI, how they communicate when to read, which to read.
2. Add AlphaBetaPrune to the code. add three lines at the end of method MaxValue and MinValue
3. Advanced 9board, no heuristic yet.
4. Add Heuristic function, cut off at 7 or 8. The program run within seconds.
5. For advanced TTT, at beginning I used the same method as basic TTT, but it did not work well. The problem is that every turn changes the grid, I think we should not only consider next grid but also current grid.
6. Add SuperTTT. Add check whether human input is valid.
