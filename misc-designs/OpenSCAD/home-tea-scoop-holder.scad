w = 33;
d = 20;
h = 30;

difference() {
    cube([w, d, h]);
    translate([-1, 3, 3])
        cube([w + 2, 7, h + 1]);
    translate([w/2 - 19/2, 13, -1])
        cube([19, 3.5, h + 2]);
}