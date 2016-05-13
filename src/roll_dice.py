#!/usr/bin/env python
from Adafruit_MotorHAT import Adafruit_MotorHAT, Adafruit_StepperMotor
import RPi.GPIO as GPIO
#import cv2
import time

# Stepper setup
mh = Adafruit_MotorHAT(addr = 0x60)
stepper = mh.getStepper(200, 1)
stepper.setSpeed(30)

# Set up camera
#vc = cv2.VideoCapture(0)

# Set up ML

# Turn on Adafruit MotorHAT (BJT wired to pin 21)
GPIO.setmode(GPIO.BCM)
GPIO.setup(21, GPIO.OUT)
GPIO.output(21, True)

while True:
    stepper.step(200, Adafruit_MotorHAT.FORWARD, Adafruit_MotorHAT.DOUBLE)
    time.sleep(1)
    print "I took a photo"
    # Take image and process
    #retval, image = vc.read()
    #if (retval)
        #proc ML
    
# Turn off Adafruit MotorHAT
GPIO.output(21, False)
