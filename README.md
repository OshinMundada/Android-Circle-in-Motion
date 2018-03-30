# Android-Circle-in-Motion

<img src="https://github.com/OshinMundada/Android-Circle-in-Motion/blob/master/android_moving_circle.gif" data-canonical-src="https://github.com/OshinMundada/Android-Circle-in-Motion/blob/master/android_moving_circle.gif" width="200" height="400" />

Three modes: Draw, Delete, Move

DRAW: When the user places one finger on the screen a circle is drawn on the screen. The center of the circle is
placed where the user touched the screen. The user moves their finger to increase the radius
of the circle. While the user is moving their finger the circle is drawn, with the edge of the circle
under the users finger. So as the user moves their finger farther from the center of the circle
the circle gets larger. When the user lifts their finger the size of the circle is fixed. The user can
put multiple circles on the screen by touching it multiple times.
The user can select the colors blue, red, green and black. 

DELETE: When the user touches inside a circle the circle is removed and not drawn again. If the spot where the user touches the screen is inside
multiple circles at the same time they are all deleted.

MOVE: The user sets a circle in motion by placing
a finger in a circle and swiping in any direction. The circles will start moving in direction and
velocity indicated by the swipe. Different circles can be moving in different directions with
different speeds. When a circle reaches the edge of the screen it will bounce off the edge
properly. That is it will lose some velocity and take into account the angle it strikes the edge. 
