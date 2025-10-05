<h1 align="center">Jaikin</h1>

## Overview
This project allows us to implement Chaikin's algorithm and create a step-by-step animation using a Canvas.
It allows the user to draw with their mouse (left button) inside the canvas one or more points representing small circle.
After choosing the points, the user must click Enter to start the algorithm that manages the animation.
- If the user selects one point, the program does not respond.
- If they select two points, the program simply draws a line between the two points.
- If they select three or more points, the program connects the points and starts the animation.

Buttons:
- ESC : closes the window.
- C : clears the screen.

## Algorithm explanation
Chaikin's algorithm works iteratively, which means it can be applied several times to obtain a smoother result. Each iteration follows these steps:

We start with a control polygon: we begin with a series of points, connected to each other, which form the baseline.

We create two new points on each segment: for each line segment from point Pi to point Pi+1, we calculate two new points, which we will call Qi and Ri.

Qi is placed at a quarter of the distance between Pi and Pi+1.
Ri is placed at three-quarters of the difference between Pi and Pi+1.
The formula for calculating these new points is a simple linear interpolation (LERP):
```
🔹Qi=(1−t)×Pi+t×Pi+1, t=0.25       
🔹Ri=(1−t)×Pi+t×Pi+1, t=0.75
```   
A new polygon is formed: The new curve is formed by connecting the new points Qi and Ri in order. The point Ri is connected to the point Qi+1 of the next segment. The control polygon for the next iteration is therefore made up of the sequence of points Q0,R0 Q1,R1,Q2,R2,...

We repeat the process: we can reapply the algorithm to the new polygon. With each iteration, the number of points on the curve doubles and the curve becomes smoother and smoother.


## Compilation & Run
```sh
    javac -d build Main.java
    java -cp build Main
```
## Collaborators
[ABDELFATTAH ELIDRISSI](https://github.com/aelidris)    
[Achraf Margoum](https://github.com/Margoumachraf)    
[Ndiasse Dieye](https://github.com/majnun917)    


## References
[Chaikin Algorithm](https://how.dev/answers/what-is-chaikins-algorithm)         
[Java awt](https://docs.oracle.com/javase/8/docs/api/java/awt/package-summary.html)             
[java.awt.event](https://docs.oracle.com/javase/8/docs/api/java/awt/event/package-summary.html)     
[Class Graphics2D ](https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html)     
[Class RenderingHints](https://docs.oracle.com/javase/8/docs/api/java/awt/RenderingHints.html)      
[javax.swing](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)       
[Class JPanel](https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html)
