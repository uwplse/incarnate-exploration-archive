width = 45;
length = 8;
height = 10;

holes = [6, 13, 19, 24, 28];

difference() {
    cube([width,length,height]);
    translate([holes[0], length / 2, -1])
        cylinder(h=height + 2, d=4.8, $fn=100);
    translate([holes[1], length / 2, -1])
        cylinder(h=height + 2, d=3.6, $fn=100);
    translate([holes[2], length / 2, -1])
        cylinder(h=height + 2, d=3.1, $fn=100);
    translate([holes[3], length / 2, -1])
        cylinder(h=height + 2, d=2.4, $fn=100);
    translate([holes[4], length / 2, -1])
        cylinder(h=height + 2, d=1.9, $fn=100);
}

/*
translate([0, height + 10, 0]) {

wallThickness = 5;
spacing = 7.5;

difference() {
    cube([width,length,height]);
    translate([wallThickness + 7.5 * 1, length / 2, -1])
        cylinder(h=height + 2, d=4.1, $fn=100);
    translate([wallThickness + 7.5 * 2, length / 2, -1])
        cylinder(h=height + 2, d=3.1, $fn=100);
    translate([wallThickness + 7.5 * 3, length / 2, -1])
        cylinder(h=height + 2, d=2.6, $fn=100);
    translate([wallThickness + 7.5 * 4, length / 2, -1])
        cylinder(h=height + 2, d=2.1, $fn=100);
    translate([wallThickness + 7.5 * 5, length / 2, -1])
        cylinder(h=height + 2, d=1.6, $fn=100);
}

}
*/