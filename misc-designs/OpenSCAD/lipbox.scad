module lipbox(oX, oY, oZ, wall, lip, recess) {
    difference() {
        cube([oX, oY, oZ]);
        translate([wall + lip, wall + lip, wall]) {
          cube([ oX - 2 * (wall + lip)
               , oY - 2 * (wall + lip)
               , oZ]);
        }
        translate([wall, wall, oZ - recess]) {
          cube([ oX - 2 * wall
               , oY - 2 * wall
               , oZ]);
        }
    }
}

module sqmesh(cut, space, m, n, depth) {
    for(i = [0:m-1]) {
        for(j = [0:n-1]) {
            translate([ i * (cut + space)
                      , 0
                      , j * (cut + space)
                      ])
              cube([cut, depth, cut]);
        }
    }
}

difference() {
    lipbox(150, 100, 50, 2, 4, 4);
    translate([8, -1, 6])
      sqmesh(3, 0.75, 35, 10, 102);
}











/*

module meshlipbox2(oX, oY, oZ, wall, lip, recess) {
    difference() {
        cube([oX, oY, oZ]);
        translate([wall + lip, wall + lip, wall]) {
          cube([ oX - 2 * (wall + lip)
               , oY - 2 * (wall + lip)
               , oZ]);
        }
        translate([wall, wall, oZ - recess]) {
          cube([ oX - 2 * wall
               , oY - 2 * wall
               , oZ]);
        }
        
        xMeshX = oX - 2 * (wall + lip);
        xMeshZ = oZ - wall - recess;
        difference() {
            rotate([0, 45, 0])
            translate([-100, -1, 0])
              mesh(6, 1.5, 50, 50, oY + 2);
            
            difference() {
                cube([oX, oY, oZ]);
                translate([ wall + lip
                          , -1
                          , wall
                          ])
                  cube([ oX - 2 * (wall + lip)
                       , oY + 2
                       , oZ - wall - recess
                       ]);
            }
        }
    }
}

module meshlipbox(oX, oY, oZ, wall, lip, recess) {
    difference() {
        cube([oX, oY, oZ]);
        translate([wall + lip, wall + lip, wall]) {
          cube([ oX - 2 * (wall + lip)
               , oY - 2 * (wall + lip)
               , oZ]);
        }
        translate([wall, wall, oZ - recess]) {
          cube([ oX - 2 * wall
               , oY - 2 * wall
               , oZ]);
        }
        
        xMeshX = oX - 4 * wall - 2 * lip;
        xMeshZ = oZ - 3 * wall - recess;
        
        cut = 5;
        minSpace = cut / 3 + 1;
        nCutsX = floor(xMeshX / (cut + minSpace));
        space = (xMeshX - (nCutsX * cut)) / (nCutsX - 1);
        nCutsZ = floor(xMeshZ / (cut + space));
        
        translate([ 2 * wall + lip
                  , -1
                  , 2 * wall
                  ]) {
            for(i = [0:nCutsX-1]) for(j = [0:nCutsZ-1]) {
                translate([i * (cut + space), 0, j * (cut + space)]) {
                    translate([cut/2, 0, cut/2])
                    rotate([-90, 0, 0])
                    cylinder(h = oY + 2, d = cut, center = false, $fn = 6);
                }
            }
            translate([(cut + space)/2, 0, (cut + space)/2])
            for(i = [0:nCutsX-2]) for(j = [0:nCutsZ-2]) {
                translate([i * (cut + space), 0, j * (cut + space)]) {
                    translate([cut/2, 0, cut/2])
                    rotate([-90, 0, 0])
                    cylinder(h = oY + 2, d = cut, center = false, $fn = 6);
                }
            }
        }
          
        
    }
}

meshlipbox(150, 100, 50, 2, 4, 4);

*/
