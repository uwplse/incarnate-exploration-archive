w1 = 176;
h1 = 30;
d1 = 73;

w2 = 176;
h2 = 46;
d2 = 125;

w3 = 83;
h3 = 17;
d3 = 124;

wall = 3;

totW = max(w1, w2, w3) + 2 * wall;
totH = h1 + h2 + h3 + 4 * wall;
totD = max(d1, d2, d3);

union() {
    difference() {
        cube([totW, totD, totH]);
        translate([wall, -1, wall])
            cube([w1, totD + 2, h1]);
        translate([wall, -1, h1 + 2 * wall])
            cube([w2, totD + 2, h2]);
        translate([wall, -1, h1 + h2 + 3 * wall])
            cube([w3, totD + 2, h3]);
        translate([w3 + 2 * wall, -1, h1 + h2 + 3 * wall])
            cube([totW - w3 - 3 * wall, totD +2, 90]);
    }
    translate([wall, 0, wall])
        cube([7.5, wall, h1]);
    translate([wall + w1 - 7.5, 0, wall])
        cube([7.5, wall, h1]);
}

translate([totW + 5, 0, 0])
  cube([h3 + wall, wall, totW - w3 - 3 * wall]);
