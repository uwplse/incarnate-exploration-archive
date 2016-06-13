module cups() {
    union () {
        difference() {
            cube([56, 51, 95]);
            translate([3, 3, 3]) {
                cube([50, 45, 95]);
            }
        }
        translate([0, 48, 0]) {
            difference() {
                cube([56, 51, 50]);
                translate([3, 3, 3]) {
                    cube([50, 45, 50]);
                }
            }
        }
    }
}

union() {
    cups();
    translate([54, 0, 0])
        cups();
}