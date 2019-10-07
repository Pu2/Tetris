public class AroundBlocks {
	private static XYcoordinate positionNow[] = new XYcoordinate[3];
	//relativePositionData[ブロックの種類][ブロックの傾き][相対位置]
	private static XYcoordinate relativePositionData[][][] = makeRelativePositionData();
	
	public static XYcoordinate[][][] makeRelativePositionData() {
		XYcoordinate makeBlocksArray[][][] = new XYcoordinate[7][4][3];
		//相対位置名
		final XYcoordinate up = new XYcoordinate(0,-1);
		final XYcoordinate upRight = new XYcoordinate(1,-1);
		final XYcoordinate right = new XYcoordinate(1,0);
		final XYcoordinate downRight = new XYcoordinate(1,1);
		final XYcoordinate down = new XYcoordinate(0,1);
		final XYcoordinate downLeft = new XYcoordinate(-1,1);
		final XYcoordinate left = new XYcoordinate(-1,0);
		final XYcoordinate upLeft = new XYcoordinate(-1,-1);
		final XYcoordinate leftleft = new XYcoordinate(-2,0);
		final XYcoordinate downdown = new XYcoordinate(0,2);
		
		//reverseL型:0
		makeBlocksArray[0][0][0] = up;
		makeBlocksArray[0][0][1] = down;
		makeBlocksArray[0][0][2] = downLeft;
		//reverseL型:1
		makeBlocksArray[0][1][0] = right;
		makeBlocksArray[0][1][1] = left;
		makeBlocksArray[0][1][2] = upLeft;
		//reverseL型:2
		makeBlocksArray[0][2][0] = down;
		makeBlocksArray[0][2][1] = up;
		makeBlocksArray[0][2][2] = upRight;
		//reverseL型:3
		makeBlocksArray[0][3][0] = left;
		makeBlocksArray[0][3][1] = right;
		makeBlocksArray[0][3][2] = downRight;
		
		//L型:0
		makeBlocksArray[1][0][0] = up;
		makeBlocksArray[1][0][1] = down;
		makeBlocksArray[1][0][2] = downRight;
		//L型:1
		makeBlocksArray[1][1][0] = right;
		makeBlocksArray[1][1][1] = left;
		makeBlocksArray[1][1][2] = downLeft;
		//L型:2
		makeBlocksArray[1][2][0] = down;
		makeBlocksArray[1][2][1] = up;
		makeBlocksArray[1][2][2] = upLeft;
		//L型:3
		makeBlocksArray[1][3][0] = left;
		makeBlocksArray[1][3][1] = right;
		makeBlocksArray[1][3][2] = upRight;
		
		//square型:0
		makeBlocksArray[2][0][0] = up;
		makeBlocksArray[2][0][1] = upRight;
		makeBlocksArray[2][0][2] = right;
		//square型:1
		makeBlocksArray[2][1][0] = up;
		makeBlocksArray[2][1][1] = upRight;
		makeBlocksArray[2][1][2] = right;
		//square型:2
		makeBlocksArray[2][2][0] = up;
		makeBlocksArray[2][2][1] = upRight;
		makeBlocksArray[2][2][2] = right;
		//square型:3
		makeBlocksArray[2][3][0] = up;
		makeBlocksArray[2][3][1] = upRight;
		makeBlocksArray[2][3][2] = right;
		
		//reverseZ型:0
		makeBlocksArray[3][0][0] = up;
		makeBlocksArray[3][0][1] = upRight;
		makeBlocksArray[3][0][2] = left;
		//reverseZ型:1
		makeBlocksArray[3][1][0] = right;
		makeBlocksArray[3][1][1] = downRight;
		makeBlocksArray[3][1][2] = up;
		//reverseZ型:2
		makeBlocksArray[3][2][0] = down;
		makeBlocksArray[3][2][1] = downLeft;
		makeBlocksArray[3][2][2] = right;		
		//reverseZ型:3
		makeBlocksArray[3][3][0] = left;
		makeBlocksArray[3][3][1] = upLeft;
		makeBlocksArray[3][3][2] = down;		
		
		//Z型:0
		makeBlocksArray[4][0][0] = up;
		makeBlocksArray[4][0][1] = upLeft;
		makeBlocksArray[4][0][2] = right;
		//Z型:1
		makeBlocksArray[4][1][0] = right;
		makeBlocksArray[4][1][1] = upRight;
		makeBlocksArray[4][1][2] = down;
		//Z型:2
		makeBlocksArray[4][2][0] = down;
		makeBlocksArray[4][2][1] = downRight;
		makeBlocksArray[4][2][2] = left;
		//Z型:3
		makeBlocksArray[4][3][0] = left;
		makeBlocksArray[4][3][1] = downLeft;
		makeBlocksArray[4][3][2] = up;
		
		//convex型:0
		makeBlocksArray[5][0][0] = up;
		makeBlocksArray[5][0][1] = left;
		makeBlocksArray[5][0][2] = right;
		//convex型:1
		makeBlocksArray[5][1][0] = right;
		makeBlocksArray[5][1][1] = up;
		makeBlocksArray[5][1][2] = down;
		//convex型:2
		makeBlocksArray[5][2][0] = down;
		makeBlocksArray[5][2][1] = right;
		makeBlocksArray[5][2][2] = left;
		//convex型:3
		makeBlocksArray[5][3][0] = left;
		makeBlocksArray[5][3][1] = down;
		makeBlocksArray[5][3][2] = up;
		
		//stick型:0
		makeBlocksArray[6][0][0] = up;
		makeBlocksArray[6][0][1] = down;
		makeBlocksArray[6][0][2] = downdown;
		//stick型:1
		makeBlocksArray[6][1][0] = left;
		makeBlocksArray[6][1][1] = right;
		makeBlocksArray[6][1][2] = leftleft;
		//stick型:2
		makeBlocksArray[6][2][0] = up;
		makeBlocksArray[6][2][1] = down;
		makeBlocksArray[6][2][2] = downdown;
		//stick型:3
		makeBlocksArray[6][3][0] = left;
		makeBlocksArray[6][3][1] = right;
		makeBlocksArray[6][3][2] = leftleft;
			
		return makeBlocksArray;
	}

	public static XYcoordinate[] getPositionNow() {
		return positionNow;
	}

	public static void setNowOfaroundBlocks(XYcoordinate nowOfaroundBlocks[]) {
		AroundBlocks.positionNow = nowOfaroundBlocks;
	}
	
    //ブロックタイプと傾きから相対位置を取得
	public static XYcoordinate[] getRelativePosition(int blockType, int angle) {
		XYcoordinate relativePosition[] = new XYcoordinate[3];
		for (int i = 0; i < 3; ++i) {
			relativePosition[i] = AroundBlocks.relativePositionData[blockType][angle % 4][i];
		}
		return relativePosition;
	}

	
}
