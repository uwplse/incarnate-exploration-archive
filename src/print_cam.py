from printrun.printcore import printcore
from printrun import gcoder
import cv2
import csv

p = printcore('/dev/ttyUSB0', 115200)
gcode = [i.strip() for i in open('filename.gcode')]
gcode = gcoder.LightGCode(gcode)
p.startprint(gcode)

cap = cv2.VideoCapture(0)

with open('print.csv', 'wb') as f:
    writer = csv.writer(f)
    #TODO: allow resuming with p.paused
    frameno = 0
    while p.printing: #not synchronized
        frame = cap.read()[1]
        cv2.imwrite('frame' + frameno + '.jpg', frame)
        writer.writerows([frameno, p.lineno])

cap.release()
p.disconnect()