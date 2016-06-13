lipWidth = 20;

frontH = 45;

rot = 60;
pW = 146.1;
pD = 120.7;
pH = 25.4;

wall = 3;

// recall sin(90) == 1

cBotX = lipWidth + (sin(rot) * pH);
cBotY = frontH - (sin(90 - rot) * pH);

cTopX = cBotX + (sin(90 - rot) * pD);
cTopY = cBotY + (sin(rot) * pD);

bX = cTopX + lipWidth;

cWalldZ = (wall / sin(90 - rot)) - wall;
cWalldX = (cWalldZ * sin(90 - rot)) / sin(rot);
cWalldY = cWalldZ / sin(rot);

echo(cWalldZ);
echo(cWalldX);
echo(cWalldY);


/*
cWalldX = (wall - (wall * sin(90 - rot))) / sin(rot);
cWalldY = ((wall / sin(90 - rot)) - wall) / sin(rot);
*/

polygon(
    [ [0, 0]
    , [0, frontH]
    , [lipWidth, frontH]
    , [cBotX, cBotY]
    , [cTopX, cTopY]
    , [cTopX + lipWidth, cTopY]
    , [cTopX + lipWidth, 0]
    , [cTopX + lipWidth - wall, 0]
    , [cTopX + lipWidth - wall, cTopY - wall]
    , [cTopX + cWalldX, cTopY - wall]
    , [cBotX + cWalldX, cBotY - wall - cWalldX]
    , [lipWidth - cWalldX, frontH - wall]
    , [wall, frontH - wall]
    , [wall, 0]
    ]);




translate([180, 0, 0])
linear_extrude(height = pW) {
union() {
    difference() {
        square([20, 45]);
        translate([5, 0]) square ([20, 40]);
    }
    
    translate([43.8, 25.47])
    rotate([0, 0, 60])
        difference() {
            square([pD + 5, pH + 5]);
            translate([5, 5]) square([pD + 1, pH + 1]);
        }
        
     translate([102.31, 0]) {
         difference() {
            square([20, 136.82]);
            translate([-1, -1]) square([16, 132.82]);
         }
     }
 }
 }