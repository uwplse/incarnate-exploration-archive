side = 20;
hole = 6;
wall = 4;

difference() {
    cube([side, side, side]);
    translate([wall, -1, wall]) {
        cube([side - 2 * wall, side + 2, side]);
    }
    translate([side / 2, side / 2, -1]) {
        cylinder(r = hole, h = side + 2, $fn = 500);
    }
    translate([-1, side / 2, side / 2]) {
        rotate([0, 90, 0]) {
            cylinder(r = hole, h = side + 2, $fn = 500);
        }
    }   
}
    