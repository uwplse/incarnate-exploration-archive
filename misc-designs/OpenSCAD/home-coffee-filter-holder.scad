iw = 30;
ih = 120;

wall = 2;

w = iw + 2 * wall;
h = ih + wall;

difference() {
    translate([0, h, 0]) {
        difference() {
            rotate([0, 90, 0])
            cylinder(h = w, d = h * 2, $fn = 300);
            
            translate([-1, 0, -1])
            cube([w + 2, h + 1, h + 2]);
            
            translate([-1, -(h + 1), -(h + 1)])
            cube([w + 2, 2 * (h + 1), h + 1]);
            
        }
    }
    translate([wall, 0, wall])
    cube([iw, ih, h]);
}