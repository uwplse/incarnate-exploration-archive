import argparse
import matplotlib.pyplot as plt
from scipy.misc import imread
import sys

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('start', type=int)
    parser.add_argument('stop', type=int)
    parser.add_argument('dir')
    args = parser.parse_args()
    fig, ax = plt.subplots()
    cmap = plt.get_cmap('gray')
    disp = ax.imshow(imread(args.dir + '/' + str(args.start) + '.jpeg', True, 'F'), cmap=cmap)
    fig.show()
    for i in range(args.start, args.stop):
        disp.set_data(imread(args.dir + '/' + str(i) + '.jpeg', True, 'F'))
        fig.canvas.draw()
        num = int(sys.stdin.readline())
        print(str(i) + ', ' + str(num))
        plt.pause(.001) # force gui redraw
    plt.close(fig)
