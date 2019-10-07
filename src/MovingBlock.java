import java.util.ArrayList;
import java.util.Random;

public class MovingBlock {
	private static Cell[][] SheetArray = makeSheetArray();
	private static XYcoordinate centerPosition;
	private static String color;
	private static int blockType;
	private static int angle;

	static Cell[][] makeSheetArray(){
		Cell[][] movingBlockSheetArray = new Cell[12][21];
		for (int i = 0; i < 12; ++i) {
			for (int h = 0; h < 21; ++h) {
				movingBlockSheetArray[i][h] = new Cell(30 * i + 1, 30 * h + 1, "skelton");
			}
		}
		return movingBlockSheetArray;
	}

	static Cell[][] getSheetArray(){
		return MovingBlock.SheetArray;
	}

	static void resetSheetArray(){
		for (int i = 0; i < 12; ++i) {
			for (int h = 0; h < 21; ++h) {
				SheetArray[i][h].setColour("skelton");
			}
		}
	}

	static XYcoordinate getCenterPosition(){
		return centerPosition;
	}

	static int getShape(){
		return MovingBlock.blockType;
	}

	static int getAngle(){
		return MovingBlock.angle;
	}

	static void setAngle(int angle){
		MovingBlock.angle = angle;
	}

	//初期ブロックを作成
	static void makeFirstBlock() {
		color = getBlockColor();
		//blocksShape: 0:"reverseL", 1:"L", 2:"square", 3:"reverseZ", 4:"Z", 5:"convex", 6:"stick"
		MovingBlock.blockType = getBlockType();
		SheetArray[5][1].setColour(color);
		//angle 0度, 1 = 90度, 2 = 180度, 3 = 270度
		MovingBlock.angle = 0;
		centerPosition = new XYcoordinate(5,1);
		XYcoordinate relativeAroundPosition[] = new XYcoordinate[3];
		relativeAroundPosition = AroundBlocks.getRelativePosition(blockType, angle);
		XYcoordinate allAroundPosition[] = new XYcoordinate[4];
		allAroundPosition =	getAllPosition(centerPosition, relativeAroundPosition);
		changerCellColor(allAroundPosition, color);
		NextBlock.makeBlock();
		Paint.getCanvasForNextBlock().repaint();
	}

	static void setCenterPosition(String direct, int step){
		switch(direct) {
		case "down":
			int newDownYcoordinate = getCenterPosition().getY() + step;
			centerPosition = new XYcoordinate(getCenterPosition().getX(), newDownYcoordinate);
			break;
		case "left":
			int newXcoordinate = getCenterPosition().getX() - step;
			centerPosition = new XYcoordinate(newXcoordinate, getCenterPosition().getY());
			break;
		case "right":
			int newRightXcoordinate = getCenterPosition().getX() + step;
			centerPosition = new XYcoordinate(newRightXcoordinate, getCenterPosition().getY());
			break;
		}
	}

	static void moveDown(){
		XYcoordinate centertPosition = getCenterPosition();
		XYcoordinate relativeAroundPosition[] = AroundBlocks.getRelativePosition(blockType, angle);
		XYcoordinate[] absoluteAllPosition = getAllPosition(centertPosition, relativeAroundPosition);
		XYcoordinate[] movedAllPosition = getMovedPosition(absoluteAllPosition, "down", 1);
		//移動先にブロックがなければ１つ下に移動
		if(!(DeadBlock.isBlockExist((movedAllPosition)))) {
			changerCellColor(absoluteAllPosition, "skelton");
			changerCellColor(movedAllPosition, color);
			setCenterPosition("down", 1);
			//ブロックが最上段まで積み重なったらストップ
		}else if(DeadBlock.isOverLimitBlocks()){
			SingleThread.stopRunning();
			Paint.gameOver();
			Paint.activateStartButton();
			//ブロックがあればその場所でストップし新しいブロックを生成
		}else {
			changerCellColor(absoluteAllPosition, "skelton");
			DeadBlock.changerCellColor(absoluteAllPosition, color);
			if(DeadBlock.isCompleteRow()) {
				ArrayList<Integer> CompleteRows = DeadBlock.geCompleteRows();
				DeadBlock.eraseCompleteRows(CompleteRows);
				Paint.changeScore(100 * (20 - CompleteRows.size()));
			};
			Paint.isGameClear();
			makeFirstBlock();	
		}
	}

	static void moveLeft(){
		XYcoordinate centertPosition = getCenterPosition();
		XYcoordinate relativeAroundPosition[] = AroundBlocks.getRelativePosition(blockType, angle);
		XYcoordinate[] absoluteAllPosition = getAllPosition(centertPosition, relativeAroundPosition);
		XYcoordinate[] movedAllPosition = getMovedPosition(absoluteAllPosition, "left", 1);
		if(!(DeadBlock.isBlockExist((movedAllPosition)))) {
			changerCellColor(absoluteAllPosition, "skelton");
			changerCellColor(movedAllPosition, color);
			setCenterPosition("left", 1);
		}
	}

	static void moveRight(){
		XYcoordinate centertPosition = getCenterPosition();
		XYcoordinate relativeAroundPosition[] = AroundBlocks.getRelativePosition(blockType, angle);
		XYcoordinate[] absoluteAllPosition = getAllPosition(centertPosition, relativeAroundPosition);
		XYcoordinate[] movedAllPosition = getMovedPosition(absoluteAllPosition, "right", 1);
		if(!(DeadBlock.isBlockExist((movedAllPosition)))) {
			changerCellColor(absoluteAllPosition, "skelton");
			changerCellColor(movedAllPosition, color);
			setCenterPosition("right", 1);
		}
	}

	static void rotate(){
		XYcoordinate centertPosition = getCenterPosition();
		XYcoordinate relativeAroundPosition[] = AroundBlocks.getRelativePosition(blockType, angle);
		XYcoordinate[] absoluteAllPosition = getAllPosition(centertPosition, relativeAroundPosition);
		XYcoordinate rotate90relativeAroundPosition[] = AroundBlocks.getRelativePosition(blockType, angle + 1);
		XYcoordinate[] rotate90absoluteAllPosition = getAllPosition(centertPosition, rotate90relativeAroundPosition);
		if(!(DeadBlock.isBlockExist((rotate90absoluteAllPosition)))) {
			changerCellColor(absoluteAllPosition, "skelton");
			changerCellColor(rotate90absoluteAllPosition, color);
			setAngle((angle + 1) % 4);
		}
	}

	static void changerCellColor(XYcoordinate[] blockPosition, String color) {
		for (int i = 0; i < blockPosition.length; i++ ) {
			int Xcoordinate = blockPosition[i].getX();
			int Ycoordinate = blockPosition[i].getY();
			SheetArray[Xcoordinate][Ycoordinate].setColour(color);
		}
	}

	static XYcoordinate[] getMovedPosition(XYcoordinate[] blockPosition, String direction, int step) {
		XYcoordinate movedPosition[] = new XYcoordinate[blockPosition.length];
		switch(direction) {
		case "down":
			for (int i = 0; i < blockPosition.length; i++ ) {
				int Xcoordinate = blockPosition[i].getX();
				int Ycoordinate = blockPosition[i].getY();
				movedPosition[i] = new XYcoordinate(Xcoordinate, Ycoordinate + step);
			}
			break;
		case "left":
			for (int i = 0; i < blockPosition.length; i++ ) {
				int Xcoordinate = blockPosition[i].getX();
				int Ycoordinate = blockPosition[i].getY();
				movedPosition[i] = new XYcoordinate(Xcoordinate - step, Ycoordinate);
			}
			break;
		case "right":
			for (int i = 0; i < blockPosition.length; i++ ) {
				int Xcoordinate = blockPosition[i].getX();
				int Ycoordinate = blockPosition[i].getY();
				movedPosition[i] = new XYcoordinate(Xcoordinate + step, Ycoordinate);
			}
			break;
		}
		return movedPosition;
	}

	static XYcoordinate[] getAllPosition(XYcoordinate centerPosition, XYcoordinate[] relativeAroundPosition) {
		XYcoordinate allPosition[] = new XYcoordinate[relativeAroundPosition.length + 1];
		allPosition[0] = new XYcoordinate(centerPosition.getX(), centerPosition.getY());
		for (int i = 0; i < relativeAroundPosition.length; i++ ) {
			int Xcoordinate = centerPosition.getX() + relativeAroundPosition[i].getX();
			int Ycoordinate = centerPosition.getY() + relativeAroundPosition[i].getY();
			allPosition[1 + i] = new XYcoordinate(Xcoordinate, Ycoordinate);
		}
		return allPosition;
	}
	
	static int getBlockType() {
		int nextBlockType = NextBlock.getBlockType();
		if(nextBlockType == 7) {
			Random r = new Random();
			blockType = r.nextInt(7);
		}else {
			blockType = nextBlockType;
		}
		return blockType;
	}
	
	static String getBlockColor() {
		String nextBlockColor = NextBlock.getBlockColor();
		if(nextBlockColor == "null") {
			String[] colors = { "red", "blue", "yellow", "green", "black", "orange", "gray" };
			Random r = new Random();
			color = colors[r.nextInt(7)];
		}else {
			color = nextBlockColor;
		}
		return color;
	}
}
