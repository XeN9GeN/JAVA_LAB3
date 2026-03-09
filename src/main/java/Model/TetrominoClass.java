package Model;

public  class TetrominoClass {
    private int x, y;//ВВЕРХНИЙ ЛЕВЫЙ УГОЛ
    private int[][] tetromino;
    private final int color;
    private final String type;


    public TetrominoClass(int[][] shape, int color, String t) {
        this.tetromino = shape;
        this.color = color;
        this.x = 4;
        this.y = 0;
        this.type=t;
    }


    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getColor() {
        return this.color;
    }
    public String getType() {
        return type;
    }
    public int[][] getTetromino() {
        return tetromino;
    }


    public void setTetromino(int[][] tetromino) {
        this.tetromino = tetromino;
    }


    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public void rotateTet(BoardClass b){
        int[][] rotated_tet = getRotatedTet();
        if(b.boardCanPlaceTet(rotated_tet,getX(),getY())){
            setTetromino(rotated_tet);
        }
    }
    public int[][] getRotatedTet(){
        int l = tetromino.length;
        int[][] rotated = new int[l][l];

        for(int i=0;i<l;i++){
            for(int j=0;j<l;j++){
                rotated[j][l-1-i]=tetromino[i][j];
            }
        }
        return rotated;
    }


    //3 обёртки
    public boolean canMove(BoardClass b,int dx, int dy){
        return b.boardCanPlaceTet(this.tetromino,this.x+dx, this.y+dy);
    }
    public boolean canRotate(BoardClass b){
        int[][]r = getRotatedTet();
        return b.boardCanPlaceTet(r,this.x,this.y);
    }
    public boolean canPlaceRightHere(BoardClass b) {
        return b.boardCanPlaceTet(this.tetromino, this.x, this.y);
    }
}



