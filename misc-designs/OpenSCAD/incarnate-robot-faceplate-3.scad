shaftD = 5.6;
shaftH = 19;
dOff = 0.5 * (shaftD - 1) - 0.15;
botBuffer = 5;
setScrewD = 2.6;
nScrews = 5;
screwD = 3.8;

topD = 3 * shaftD;
botD = 10 * shaftD;

topH = 0.7 * shaftH;
botH = 0.3 * shaftH + botBuffer;

setScrewBackW = shaftD + 0.5 * setScrewD;
setScrewBackD = 1.25 * shaftD;

screwOff = 0.75 * (0.5 * botD);

plateH = 3;

difference() {
    union() {
        difference() {
            union() {
                translate([0, 0, botH])
                    cylinder(h = topH, d = topD, $fn = 200);
                cylinder(h = botH, d = botD, $fn = 200);
                translate([0, 0, botH - 1])
                    cylinder( h = 0.4 * topH
                            , r1 = 0.3 * botD
                            , r2 = topD / 2
                            , $fn = 100);
            }
            translate([0, 0, botBuffer])
                cylinder(h = shaftH + 1, d = shaftD, $fn = 200);
        }
        translate([-0.5 * setScrewBackW, dOff, botBuffer - 1])
            cube([setScrewBackW, setScrewBackD, shaftH + 1]);
    }
    
    translate([0, topD, botH + 0.5 * topH])
    rotate([90, 0, 0])
    cylinder(h = topD, d = setScrewD, $fn = 50);

/*
    translate([0, topD, botH + 0.7 * topH])
    rotate([90, 0, 0])
    cylinder(h = topD, d = setScrewD, $fn = 50);
*/

    for(i = [1:nScrews]) {
        rotate(i * (360 / nScrews) + 180)
        translate([0, screwOff, -1])
        cylinder(h = botH + 2, d = screwD, $fn = 50);
    }
}

translate([botD + 10, 0, 0])
difference() {
    cylinder(h = plateH, d = botD, $fn = 200);

    for(i = [1:nScrews]) {
        rotate(i * (360 / nScrews) + 180)
        translate([0, screwOff, -1])
        cylinder(h = plateH + 2, d = screwD, $fn = 50);
    }
}    


/*

plateH = 11;
plateD = 50;

shaftH = 20;

translate([100, 0, 0])
difference() {
    union() {
        difference() {
            cylinder(h = plateH, d = plateD, $fn = 100);
            for(i = [1:nScrews]) {
                rotate(i * (360 / nScrews))
                translate([screwOff, 0, -1])
                cylinder(h = plateH + 2, d = screwD, $fn = 50);
            }
        }
        translate([0, 0, plateH - 1])
        union() {
            difference() {
                cylinder(h = shaftH, d = 10, $fn = 50);
                cylinder(h = shaftH + 1, d = 5.2, $fn = 50);
            }
            translate([-2, 1.8, 0])
                cube([4, 2, shaftH]);
        }
    }
    translate([0, 0, 2])
    cylinder(h = 1, d = plateD + 1);
}

*/