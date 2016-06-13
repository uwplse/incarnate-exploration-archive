lipW = 15;
frontH = 40;
rot = 60;
wall = 4;

pW = 146.1;
pD = 120.7;
pH = 25.4;

linear_extrude(height = pW) union() {
    // front
    difference() {
        square([wall + lipW, frontH + wall]);
        translate([wall, -1])
            square([lipW + 1, frontH + 1]);
    }
    
    
    // check
    translate([lipW + wall, frontH + wall])
    translate([ -1 * sin(90 - rot) * wall
              , -1 * sin(rot) * wall
              ])
    translate([ sin(rot) * (pH + wall)
              , -1 * sin(90 - rot) * (pH + wall)
              ])
    rotate(rot) difference() {
        square([pD + wall, pH + wall]);
        translate([wall, wall])
            square([pD + 1, pH + 1]);
    }
    
    // back
    backH = frontH + wall +
            sin(rot) * (pD + wall) +
            sin(90 - rot) * wall -
            sin(90 - rot) * (pH + wall) -
            sin(rot) * wall;
    translate([ wall + lipW +
                sin(rot) * (pH + wall) -
                sin(90 - rot) * wall +
                sin(90 - rot) * (pD + wall) -
                sin(rot) * wall
              , 0])
    difference() {
        square([lipW + wall, backH]);
        translate([-1, -1])
            square([lipW + 1, backH - wall + 1]);
    }
}