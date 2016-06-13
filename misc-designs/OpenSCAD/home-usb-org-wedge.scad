//Title: Wedge Module
//Author: Alex English - ProtoParadigm
//Date: 1/4/2013
//License: Creative Commons - Share Alike - Attribution

//Usage: Include in your other .scad projects and call wedge with arguments for the height of the wedge, the radius of the wedge, and the angle of the wedge in degrees.  The resulting wedge will be placed with the point at the origin, extending into the z axis, with the angle starting at the x axis and extending counter-clockwise as per the right-hand rule.

//Updated: 1/12/2013 - Increased dimensions of wedge to be revoved when angle is more than 180 as per suggestion from kitwallace.

module wedge_180(h, r, d)
{
	rotate(d) difference()
	{
		rotate(180-d) difference()
		{
			cylinder(h = h, r = r);
			translate([-(r+1), 0, -1]) cube([r*2+2, r+1, h+2]);
		}
		translate([-(r+1), 0, -1]) cube([r*2+2, r+1, h+2]);
	}
}

module wedge(h, r, d)
{
	if(d <= 180)
		wedge_180(h, r, d);
	else
		rotate(d) difference()
		{
			cylinder(h = h, r = r);
			translate([0, 0, -1]) wedge_180(h+2, r+1, 360-d);
		}
}

//Example: wedge(10, 20, 145); //would create a wedge 10 high, with a radius of 20, and an angle of 33 degrees.

difference() {
    wedge(20, 70, 5);
    translate([40, 4, -1])
        cube([25, 5, 100]);
}