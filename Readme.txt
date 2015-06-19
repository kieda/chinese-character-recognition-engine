[Readme]
Note that this project was created in 2011 (when I was in 11th grade). Note
that I was a novice to coding, and thought that an algorithm was "some code
that gets the job done". 

This project is very meager compared to real chinese character recognition programs, 
yet is an interesting sketch for recognition methods.

Here, we gather data by analyzing geometrical properties of written chinese strokes.
The database search, however, is so rudimentary that it requires an exact match
in order to recognize the character. 

For anyone looking at this, a suggested extension of this project would involve 
1) Improving the geometrical algorithms. One Idea I had was to categorize 
the strokes into stroke types. Improvements would also involve uncertainties
in the heuristics. 
E.g. currently I have an algorithm that counts the number of stroke intersections.
It would be better to keep track of the stroke intersection positions, and compare
these to other strokes.
2) Improving the database search. Currently, finding a character requires an 
EXACT match. This is terrible and will fail for different users or even
from trial to trial. It would be better to provide a match percentage, then
sort by the amount two characters match.
3) refactoring some of my old code into something less horrid. I literally 
have a kill-switch in a toString() method. Ugh.
