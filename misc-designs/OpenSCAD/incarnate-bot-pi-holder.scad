w = 57;
d = 23;
h = 60;
top = 50;

wall = 2;

difference() {
    cube([w + 2 * wall, d + 2 * wall, h + wall]);
    translate([wall, wall, wall])
        cube([w, d, h + 1]);
    translate([w + wall - 1, d + wall - 17.5 - 1.5, 4 + wall])
        cube([wall + 2, 17.5, 50]);
    translate([wall + 21, d + wall - 5, -1])
        cube([14, 5, wall + 2]);
    translate([-1, -1, top])
        cube([w + 2 * wall + 2, d + wall + 1, h]);
    translate([w/3 + wall - 5, d + wall - 1, h - ((h - top) / 2)])
        rotate([90, 0, 0])
            cylinder(h = wall + 6, d = 3.5, $fn = 12, center = true);
    translate([(2 * w)/3 + wall + 5, d + wall - 1, h - ((h - top) / 2)])
        rotate([90, 0, 0])
            cylinder(h = wall + 6, d = 3.5, $fn = 12, center = true);
}