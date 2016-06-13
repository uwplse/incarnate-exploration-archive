h = 50;

mw = 5.1;
bw = 5.1;

over = 10;

wall = 1;

linear_extrude(height = h) {
    difference() {
        square([mw + bw + 3 * wall, over + wall]);
        translate([wall, -1])
        square([mw, over + 1]);
        translate([mw + 2 * wall, wall])
        square([bw, over + 1]);
    }
}