od = 20;
id = 15;
cut = 2.2;

iid = 10.75;

end = 3;
h = 135 + end;

union() {
    difference() {
        cylinder(h = h, d = od);

        translate([0, 0, end])
            cylinder(h = 1000, d = id);

        translate([-0.5 * cut, 0, end])
            cube([cut, 1000, 1000]);

        translate([-(sqrt(200) / 2), iid / 2 + 0.01, h + 2])
        rotate([0, 45, 0])
            cube([10, 10, 10]);

        translate([0, 1.5 * iid + 0.01, h - 10])
        rotate([90, 0, 0])
        linear_extrude(height = 10)
        polygon([ [0, 0]
                , [-5, 15]
                , [5, 15]
                ]);        
        
    }
    
    
    cylinder(h = h + 1.5, d = iid, $fn = 50);
    translate([0, 0, end - 0.01])
    cylinder(r1 = id / 2 + 0.01, r2 = iid / 2 - 0.01, h = 10);
}

