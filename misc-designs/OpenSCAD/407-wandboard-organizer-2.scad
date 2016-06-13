hdW = 83; // 81
hdD = 124;
hdH = 17;

wbW = 104; // 102
wbD = 102; // 100
wbH = 39;

btW = 70;
btD = 70;
btH = 24;

usbW = 93; // 90
usbD = 43; // 40
usbH = 17;

wall = 2;



difference() {
    cube([ 5 * wall + usbD + hdW
         , 4 * wall + hdD
         , 3 * wall + hdH
         ]);
    
    // stacking lip
    translate([ wall
              , wall
              , 2 * wall + hdH
              ])
    cube([ 3 * wall + usbD + hdW
         , 2 * wall + hdD
         , 1000
         ]);
    
    // usb main
    translate([ 3 * wall + hdW
              , 2 * wall
              , wall
              ])
    cube([ usbD
         , usbW
         , 1000
         ]);
    
    // usb cord
    translate([ 3 * wall + hdW + 5
              , -1
              , wall
              ])
    cube([ 20
         , 2 * wall + 2
         , 1000
         ]);
    
    // usb sockets
    translate([ 3 * wall + usbD + hdW - 1
              , 2 * wall + 5
              , wall
              ])
    cube([ 2 * wall + 2
         , 80
         , 1000
         ]);
    
    // usb bottom cutout
    translate([ 5 * wall + hdW
              , 4 * wall
              , -1
              ])
    cube([ usbD - 4 * wall
         , usbW - 4 * wall
         , 1000
         ]);
         
    // hd main
    translate([ 2 * wall
              , 2 * wall
              , wall
              ])
    cube([ hdW
         , hdD
         , 1000
         ]);
         
    // hd cord
    translate([ 2 * wall + hdW - 45
              , -1
              , wall
              ])
    cube([ 25
         , 2 * wall + 2
         , 1000
         ]);
    
    // hd bottom cutout
    translate([ 4 * wall
              , 4 * wall
              , -1
              ])
    cube([ hdW - 4 * wall
         , hdD - 4 * wall
         , 1000
         ]);
    
    // corner cutout
    translate([ 3 * wall + hdW
              , 3 * wall + usbW
              , -1
              ])
    cube([ usbD
         , hdD - usbW - wall
         , 1000
         ]);
    
}




translate([wall, wall, 43])
difference() {
    union() {
        // base plate
        cube([ 3 * wall + usbD + hdW
             , 2 * wall + hdD
             , wall + 0.1
             ]);
        
        // wb holder
        cube([ 4 * wall + wbW
             , 4 * wall + wbD
             , 3 * wall + wbH
             ]);
    }
    
    // stacking lip
    translate([ wall
              , wall
              , 2 * wall + wbH
              ])
    cube([ 2 * wall + wbW
         , 2 * wall + wbD
         , 1000
         ]);
    
    // wb main
    translate([ 2 * wall
              , 2 * wall
              , wall
              ])
    cube([ wbW
         , wbD
         , 1000
         ]);
    
    // wb cords left
    wbLcords = 10;
    translate([ -1
              , 2 * wall + wbLcords
              , wall
              ])
    cube([ 2 * wall + 2
         , wbD - 2 * wbLcords
         , 1000
         ]);
         
    // wb cords back
    wbBcords = 10;
    translate([ 2 * wall + wbBcords
              , -1
              , wall
              ])
    cube([ wbW - 2 * wbBcords
         , 2 * wall + 2
         , 1000
         ]);
         
    // wb cords right
    wbRcords = 10;
    translate([ 2 * wall + wbW -1
              , 2 * wall + wbRcords
              , wall
              ])
    cube([ 2 * wall + 2
         , wbD - 2 * wbRcords
         , 1000
         ]);

    // wb cords front
    wbFcords = 10;
    translate([ 2 * wall + wbFcords
              , 2 * wall + wbD - 1
              , wall
              ])
    cube([ wbW - 2 * wbFcords
         , 2 * wall + 2
         , 1000
         ]);         
   
    // wb bottom cutout
    translate([ 6 * wall
              , 6 * wall
              , -1
              ])
    cube([ wbW - 8 * wall
         , wbD - 8 * wall
         , 1000
         ]);
}



translate([ 2 * wall
          , 2 * wall
          , 108
          ])
difference() {
    union() {
        // base plate
        cube([ 2 * wall + wbW
             , 2 * wall + wbD
             , wall + 0.1
             ]);
        
        // bt holder
        cube([ 2 * wall + btW
             , 2 * wall + btD
             , 0.75 * btH
             ]);        
    }
    
    translate([ wall
              , wall
              , wall
              ])
    cube([ btW
         , btD
         , 1000
         ]);
    
    translate([ wall + 10
              , -1
              , wall
              ])
    cube([ btW - 20
         , 2 * wall + btD + 2
         , 1000
         ]);
    
    translate([ -1
              , wall + 10
              , wall
              ])
    cube([ 2 * wall + btW + 2
         , btD - 20
         , 1000
         ]);
         
    // bt bottom cutout
    translate([ 3 * wall
              , 3 * wall
              , -1
              ])
    cube([ btW - 4 * wall
         , btD - 4 * wall
         , 1000
         ]);
              
}















translate([150, 0, 0])
union() {
    // base plate
    difference() {
        cube([ 3 * wall + usbD + hdW
             , 2 * wall + hdD
             , wall
             ]);
        for(i = [0:9]) {
            for(j = [0:9]) {
                xW = (wall + usbD + hdW - 9 * wall) / 10;
                yW = (hdD - 9 * wall) / 10;
                translate([ wall + i * (wall + xW)
                          , wall + j * (wall + yW)
                          , -1
                          ])
                cube([ xW
                     , yW
                     , 1000
                     ]);
            }
        }
    }
    
    // wb holder
    difference() {
        cube([ 4 * wall + wbW
             , 4 * wall + wbD
             , 3 * wall + wbH
             ]);
    }
}



/*
translate([150, 0, 0])
difference() {
    cube([ 3 * wall + usbD + hdW
         , hdD + 2 * wall
         , hdH + wall + wall
         ]);
    
    translate([wall, wall, hdH + wall])
    cube([usbD + hdW + wall, hdD, 1000]);
    
    translate([wall, wall, wall])
    cube([ usbD
         , usbW
         , 1000
         ]);
    
    translate([ wall + 17
              , wall + usbW - 1
              , wall
              ])
    cube([ 6
         , 1000
         , 1000
         ]);
         
    translate([ -1
              , wall + 5
              , wall
              ])
    cube([ 20
         , 80
         , 1000
         ]);
    
    translate([ usbD + 2 * wall
              , wall
              , wall
              ])
    cube([ hdW
         , hdD
         , 1000
         ]);
         
    translate([ wall + usbD + wall + 26
              , wall + hdD - 1
              , wall
              ])
    cube([ 16
         , 100
         , 1000
         ]);
         
}
*/


/*
difference() {
    cube([121 + 3 * wall, 123 + wall, 75 + 2 * wall]);
    
    translate([wall, -1, wall])
    cube([41, 1000, 16]);
    
    translate([41 + 2 * wall, -1, wall])
    cube([80, 1000, 16]);
    
    translate([
}
*/

/*
translate([0, 0, -100]) {
    color("green")
    cube([hdW, hdD, hdH]);
    
    color("green")
    translate([85, 0, 0])
    cube([wbW, wbD, wbH]);
    
    color("green")
    translate([190, 0, 0])
    cube([btW, btD, btH]);
    
    color("green")
    translate([280, 0, 0])
    cube([usbW, usbD, usbH]);
}
*/