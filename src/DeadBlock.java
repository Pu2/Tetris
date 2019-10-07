import java.util.ArrayList;

public class DeadBlock {
	private static Cell[][] SheetArray = makeSheetArray();

	//配列を作成
	private static Cell[][] makeSheetArray(){
		Cell[][] deadBlockSheetArray = new Cell[12][21];	
		for (int i = 0; i < 12; ++i) {
			for (int h = 0; h < 21; ++h) {
				deadBlockSheetArray[i][h] = new Cell(30 * i + 1, 30 * h + 1, "white");
			}
		}
		return deadBlockSheetArray;
	}

	//配列を取得
	static Cell[][] getSheetArray(){
		return DeadBlock.SheetArray;
	}

	//壁を作成
	public static void makeWall() {
		for (int i = 0; i < 21; i++ ) {
			getSheetArray()[0][i].setColour("pink");
		}
		for (int i = 0; i < 21; i++ ) {
			getSheetArray()[11][i].setColour("pink");
		}
		for (int i = 0; i < 12; i++ ) {
			getSheetArray()[i][20].setColour("pink");
		}
	}

	static void resetSheetArray(){
		for (int i = 0; i < 12; ++i) {
			for (int h = 0; h < 21; ++h) {
				SheetArray[i][h].setColour("white");
			}
		}
		makeWall();
	}

	static void changerCellColor(XYcoordinate[] blockPosition, String color) {
		for (int i = 0; i < blockPosition.length; i++ ) {
			int Xcoordinate = blockPosition[i].getX();
			int Ycoordinate = blockPosition[i].getY();
			SheetArray[Xcoordinate][Ycoordinate].setColour(color);
		}
	}

	//指定の場所にブロックを作成
	public void makeBlock(XYcoordinate[] position, String color) {
		for (int i = 0; i < position.length; i++ ) {
			int xCoordinate = position[i].getX();
			int yCoordinate = position[i].getY();
			getSheetArray()[xCoordinate][yCoordinate].setColour(color);
		}
	}

	//指定の場所のブロックを削除
	public void eraseBlock(XYcoordinate[] position) {
		for (int i = 0; i < position.length; i++ ) {
			int xCoordinate = position[i].getX();
			int yCoordinate = position[i].getY();
			getSheetArray()[xCoordinate][yCoordinate].setColour("white");
		}
	}

	//指定のセルにブロックがあるか判定
	static Boolean isBlockExist(XYcoordinate[] position) {
		Boolean isBlockExist = false;
		for (int i = 0; i < position.length; ++i ) {
			int xCoordinate = position[i].getX();
			int yCoordinate = position[i].getY();
			if(getSheetArray()[xCoordinate][yCoordinate].getColor() != "white") {
				isBlockExist = true;
				break;
			}
		}
		return isBlockExist;
	}

	//ブロックが上限を超えて積み上がっているか判定
	static Boolean isOverLimitBlocks() {
		Boolean isOverLimitBlocks = false;
		for (int i=1; i<11; ++i) {
			if(getSheetArray()[i][0].getColor() != "white") {
				isOverLimitBlocks = true;
				break;
			}
		}
		return isOverLimitBlocks;
	}

	//ブロックが揃っている行を取得し削除
	public static void isCompleteRow2() {
		ArrayList<Integer> fullColorRows = new ArrayList<Integer>();
		//揃った行数を取得
		loop:	for (int h = 0; h < 20; ++h ) {
			for (int i = 1; i < 11; i++ ) {
				if(getSheetArray()[i][h].getColor() == "white") {
					continue loop;
				}else if(i == 10) {
					fullColorRows.add(h);
				}
			}
		}
		int copyRows[] = new int[20 - fullColorRows.size()];
		int copyRowsIndex = 0;
		//揃っいない行を取得
		if(fullColorRows.isEmpty() == false) {
			loop2:	for (int i = 0; i < 20; ++i ) {
				if(fullColorRows.contains(i)){
					continue loop2;
				}
				copyRows[copyRowsIndex] = i;
				++copyRowsIndex;
			}
		//揃っていない行だけをコピー
		int g = copyRows.length - 1;
		for (int h = 19; g  > -1; --h ) {
			for (int i = 10; i > 0; --i ) {
				getSheetArray()[i][h].setColour(getSheetArray()[i][copyRows[g]].getColor());
			}
			--g;
		}
		}
	}

	//ブロックが揃っている行があるか判定
	public static Boolean isCompleteRow() {
		Boolean isCompleteRow = false;
		//揃った行数を取得
		loop:	for (int h = 0; h < 20; ++h ) {
			for (int i = 1; i < 11; i++ ) {
				if(getSheetArray()[i][h].getColor() == "white") {
					continue loop;
				}else if(i == 10) {
					isCompleteRow = true;
				}
			}
		}
		return isCompleteRow;
	}

	//ブロックが揃っていない行番号を取得
	public static ArrayList<Integer> geCompleteRows() {
		ArrayList<Integer> nonFullColorRows = new ArrayList<Integer>();
		loop:	for (int h = 0; h < 20; ++h ) {
			for (int i = 1; i < 11; i++ ) {
				if(getSheetArray()[i][h].getColor() == "white") {
					nonFullColorRows.add(h);
					continue loop;
				}
			}
		}
		return nonFullColorRows;
	}

	//セルを貼り付け
	public static void eraseCompleteRows(ArrayList<Integer> nonFullColorRows) {
		int g = nonFullColorRows.size() - 1;
		for (int h = 19; g  > -1; --h ) {
			for (int i = 10; i > 0; --i ) {
				getSheetArray()[i][h].setColour(getSheetArray()[i][nonFullColorRows.get(g)].getColor());
			}
			--g;
		}
	}

}
