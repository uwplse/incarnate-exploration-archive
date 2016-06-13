id = 6;
ih = 13;
connH = 7;

wall = 1.75;

od = id + 2 * wall;
oh = ih + wall + connH;

rotate([0, 180, 0])
translate([0, 0, -oh])
difference() {
    cylinder(h = oh, d = od, $fn = 50);
    translate([0, 0, connH + wall])
        cylinder(h = ih + 1, d = id, $fn = 50);
    
    translate([-0.5 * od, wall / 2, -1])
        cube([od, od, connH + 1]);
    rotate([0, 0, 180])
    translate([-0.5 * od, wall / 2, -1])
        cube([od, od, connH + 1]);
    
    translate([0, 0.5 * wall + 1, 0.5 * connH])
    rotate([90, 0, 0])
    cylinder(h = wall + 2, d = 2, $fn = 50);

    translate([0, 0.5 * od, 0.5 * od])
    rotate([90, 0, 0])
    difference() {
        cylinder(h = od, d = 2 * od, $fn = 50);
        cylinder(h = od, d = od, $fn = 50);
        translate([-od/2, 0, 0])
        cube([od, od, od]);
    }
}
