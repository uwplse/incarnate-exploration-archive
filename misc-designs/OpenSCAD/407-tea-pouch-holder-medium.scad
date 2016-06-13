nBags = 4;
bagW = 35;
bagD = 100;
wall = 2;

w =  nBags * bagW + (nBags + 1) * wall;
h = 60;
d = bagD + wall;

curveH = 25;
curveSides = 100;

difference() {
    union() {
        difference() {
            cube([w, d, h]);
            translate([-1, -1, h - curveH - 1])
                cube([w + 2, curveH + 1, h + 1]);
        }
        translate([0, curveH, h - curveH])
            rotate([0, 90, 0])
                cylinder(d = 2 * curveH, h = w, $fn = curveSides);
    }
    for(i=[0:nBags-1]) {
        translate([wall + (i * (bagW + wall)), -1, wall])
            cube([bagW, bagD + 1, h]);
    }
}

/*
translate([w + 10, 0, 0]) {
    difference() {
        union() {
            difference() {
                cube([w, d, h]);
                translate([-1, -1, h - curveH - 1])
                    cube([w + 2, curveH + 1, h + 1]);
            }
            translate([0, curveH, h - curveH])
                rotate([0, 90, 0])
                    cylinder(d = 2 * curveH, h = w, $fn = curveSides);
        }
        for(i=[0:nBags-1]) {
            translate([wall + (i * (bagW + wall)), -1, wall])
                cube([bagW, bagD + 1, h]);
        }
        translate([2 * wall + bagW, -1, -1])
            cube([1000, 1000, 1000]);        
    }
}
*/

echo(w);
    