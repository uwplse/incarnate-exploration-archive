w = 24;
d = 150;
h = 5;

cutW = 12;
cutH = 2.5;

holeDiam = 3.4;

difference() {
    cube([w, d, h ]);
    translate([-1, -1, h - cutH])
        cube([cutW + 1, d + 2, cutH + 1]);
    translate([3 * w / 4, d / 4, -1])
        cylinder(d = holeDiam, h = h + 2, $fn = 30);
//    translate([3 * w / 4, d / 2, -1])
//        cylinder(d = holeDiam, h = h + 2, $fn = 30);
    translate([3 * w / 4, 3 * d / 4, -1])
        cylinder(d = holeDiam, h = h + 2, $fn = 30);
}