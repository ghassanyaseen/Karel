import stanford.karel.SuperKarel;


public class Homework extends SuperKarel {

    /* You fill the code here */

int movecounter=0;

    public void run() {
        int width = measureWidth();
        int height = measureHeight();

        if (width <= 2 || height <= 2) {
            if((width ==  1 && height == 1) || (width ==  2 && height == 1) || (width ==  1 && height == 2)){
                System.out.println("It can not be divided");
            }else if(width == 2 && height ==2){
                divideIntoSquare();
            }else if ((Math.abs(width - 3) % 4 == 0 && width != 3) || (Math.abs(height - 3) % 4 == 0) && height != 2 && height != 3) {
                divideIntoFourLine(width, height);
            }else if ((Math.abs(width - 2) % 3 == 0 && width != 2) || (Math.abs(height - 2) % 3 == 0) && height != 2) {
                divideIntoThree(width, height);
            } else if (Math.abs(width - 1) % 2 == 0 || Math.abs(height - 1) % 2 == 0 || (width != 2 && width%2 ==0) ||(height != 2 && height %2 ==0)) {
                divideIntoTwo(width, height);
            }
        } else {
            divideIntoFour(width, height);
        }
        System.out.println("The Number of moves: "+movecounter);
    }

    private int measureWidth() {
        int width = 1;
        while (frontIsClear()) {
            move();
            movecounter++;
            width++;
        }
        turnAround();
        moveToWall();
        turnAround();
        return width;
    }

    private int measureHeight() {
        int height = 1;
        turnLeft();
        while (frontIsClear()) {
            move();
            movecounter++;
            height++;
        }
        turnAround();
        moveToWall();
        turnAround();
        turnRight();
        return height;
    }

    private void divideIntoFour(int width, int height) {
        if (width % 2 != 0) {
            placeVerticalLine((int) Math.ceil((double) width / 2));
        } else {
            placeVerticalLine(width / 2);
            move();
            movecounter++;
            placeVerticalLine(1);
        }
        if (height % 2 != 0) {
            placeHorizontalLine((int) Math.ceil((double) height / 2));
            turnRight();
            moveToWall();
            turnLeft();
        } else {
            placeHorizontalLine(height / 2);
            turnLeft();
            move();
            movecounter++;

            turnLeft();
            placeHorizontalLine(1);
            moveToWall();
            turnLeft();
            moveToWall();
            turnLeft();
        }
    }

    private void divideIntoFourLine(int width, int height){
        for (int i = Math.max(width, height) / 4; i <= Math.max(width, height); i += 4) {
            divide(width, height, 4);
        }
        if (width < height) {
            turnRight();
            moveToWall();
            turnLeft();
        } else {
            turnAround();
            moveToWall();
            turnRight();
            turnRight();
        }
    }

    private void divideIntoThree(int width, int height) {
        for (int i = Math.max(width, height) / 2; i <= Math.max(width, height); i += 3) {
            divide(width, height, 3);
        }
        if (width < height) {
            turnRight();
            moveToWall();
            turnLeft();
        } else {
            turnAround();
            moveToWall();
            turnRight();
            turnRight();
        }
    }

    private void divideIntoTwo(int width, int height) {
        if(width > height) {
            if (width % 2 != 0) {
                placeVerticalLine((int) Math.ceil((double) width / 2));
                turnAround();
                move();
                movecounter++;
                turnAround();
            } else {
                placeVerticalLine(width / 2);
                move();
                movecounter++;
                placeVerticalLine(1);
                turnAround();
                moveToWall();
                turnAround();
            }
        }else {
            if (height % 2 != 0) {
                placeHorizontalLine((int) Math.ceil((double) height / 2));
                turnRight();
                moveToWall();
                turnLeft();
            } else {
                placeHorizontalLine(height / 2);
                turnLeft();
                move();
                movecounter++;

                turnLeft();
                placeHorizontalLine(1);
                moveToWall();
                turnLeft();
                moveToWall();
                turnLeft();
            }
        }
    }

    private void divideIntoSquare() {
        if(noBeepersPresent())
            putBeeper();
        move();
        turnLeft();
        move();
        if(noBeepersPresent())
            putBeeper();
        turnAround();
        move();
        turnRight();
        move();
        turnAround();

        movecounter= movecounter +4;
    }

    private void divide(int width, int height, int place) {
        if (width < height) {

            moveToRow((int) Math.ceil((double)height/ place));
            while (frontIsClear()) {
                if (noBeepersPresent())
                    putBeeper();
                move();
                movecounter++;
            }
            if (noBeepersPresent())
                putBeeper();

            turnLeft();
            turnLeft();
            moveToWall();
            turnRight();
            move();
            movecounter++;

            turnLeft();
            turnAround();
        } else {
            moveToColumn((int) Math.ceil((double)width / place));
            turnLeft();
            while (frontIsClear()) {
                if (noBeepersPresent())
                    putBeeper();
                move();
                movecounter++;
            }
            if (noBeepersPresent())
                putBeeper();
            turnAround();
            moveToWall();
            turnLeft();
            move();
            movecounter++;
        }
    }

    private void placeVerticalLine(int column) {
        moveToColumn(column);
        turnLeft();
        while (frontIsClear()) {
            if(noBeepersPresent())
                putBeeper();
            move();
            movecounter++;
        }
        if(noBeepersPresent())
            putBeeper();
        turnAround();
        while (frontIsClear()) {
            if(noBeepersPresent()){

                putBeeper();
                move();
                movecounter++;
            }
            else{
                move();
                movecounter++;
            }
        }
        turnLeft();
    }

    private void placeHorizontalLine(int row) {
        moveToRow(row);
        while (frontIsClear()) {
            if(noBeepersPresent())
                putBeeper();
            move();
            movecounter++;
        }
        if(noBeepersPresent())
            putBeeper();
        turnAround();

        while (frontIsClear()) {
            if(noBeepersPresent()){
                putBeeper();
                move();
                movecounter++;
            }
            else{
                move();
                movecounter++;
            }
        }
        if(noBeepersPresent())
            putBeeper();

        turnAround();
    }

    private void moveToColumn(int column) {
        for (int i = 1; i < column; i++) {
            move();
            movecounter++;
        }
    }

    private void moveToRow(int row) {
        turnLeft();
        for (int i = 1; i < row; i++) {
            move();
            movecounter++;
        }
        turnRight();
    }

    private void moveToWall() {
        while (frontIsClear()) {
            move();
            movecounter++;
        }
    }
}
