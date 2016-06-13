pW = 146.1;
pD = 120.7;
pH = 25.4;

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