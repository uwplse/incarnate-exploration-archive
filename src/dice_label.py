import argparse
import matplotlib.pyplot as plt
from scipy.misc import imread
import sys

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('start', type=int)
    parser.add_argument('stop', type=int)
    parser.add_argument('dir')
    args = parser.parse_args()

    def readImg(i):
        return imread('%s/%d.jpeg' % (args.dir, i), True, 'F')

    fig, ax = plt.subplots()
    cmap = plt.get_cmap('gray')
    disp = ax.imshow(readImg(args.start), cmap=cmap)
    fig.show()

    for i in range(args.start, args.stop):
        disp.set_data(readImg(i))
        fig.canvas.draw()
        num = int(sys.stdin.readline())
        print(str(i) + ', ' + str(num))
        plt.pause(.001) # force gui redraw
    plt.close(fig)

if __name__ == '__main__':
    main()
