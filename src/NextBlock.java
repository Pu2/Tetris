import java.util.Random;

public class NextBlock {
	private static Cell[][] SheetArray = makeSheetArray();
	private static XYcoordinate centerPosition;
	private static String color = "null";
	private static int blockType = 7;
	private static int angle;
	
	static Cell[][] makeSheetArray(){
		Cell[][] nextBlockSheetArray = new Cell[3][4];
		for (int i = 0; i < 3; ++i) {
			for (int h = 0; h < 4; ++h) {
				nextBlockSheetArray[i][h] = new Cell(20 * i + 1, 20 * h + 1, "white");
			}
		}
		return nextBlockSheetArray;
	}
	
	static Cell[][] getSheetArray(){
		return NextBlock.SheetArray;
	}

	static void resetSheetArray(){
		for (int i = 0; i < 3; ++i) {
			for (int h = 0; h < 4; ++h) {
				SheetArray[i][h].setColour("white");
			}
		}
	}
	
	static void makeBlock() {
		resetSheetArray();
		String[] colors = { "red", "blue", "yellow", "green", "black", "orange", "gray" };
		Random r = new Random();
		color = colors[r.nextInt(7)];
		//blocksShape: 0:"reverseL", 1:"L", 2:"square", 3:"reverseZ", 4:"Z", 5:"convex", 6:"stick"
		blockType = r.nextInt(7);
		SheetArray[1][1].setColour(color);
		//angle 0度, 1 = 90度, 2 = 180度, 3 = 270度
		angle = 0;
		centerPosition = new XYcoordinate(1,1);
		XYcoordinate relativeAroundPosition[] = new XYcoordinate[3];
		relativeAroundPosition = AroundBlocks.getRelativePosition(getBlockType(), angle);
		XYcoordinate allAroundPosition[] = new XYcoordinate[4];
		allAroundPosition =	getAllPosition(centerPosition, relativeAroundPosition);
		changerCellColor(allAroundPosition, color);
	}

	static void changerCellColor(XYcoordinate[] blockPosition, String color) {
		for (int i = 0; i < blockPosition.length; i++ ) {
			int Xcoordinate = blockPosition[i].getX();
			int Ycoordinate = blockPosition[i].getY();
			SheetArray[Xcoordinate][Ycoordinate].setColour(color);
		}
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
		return blockType;	
	}
	
	static String getBlockColor() {
		return color;
	}
	
}
