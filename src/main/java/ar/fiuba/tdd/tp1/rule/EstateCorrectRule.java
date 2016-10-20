//package ar.fiuba.tdd.tp1.rule;
//
//import ar.fiuba.tdd.tp1.cell.Cell;
//import ar.fiuba.tdd.tp1.gameboard.GameBoard;
//import ar.fiuba.tdd.tp1.graph.Graph;
//
///**
// */
//public class EstateCorrectRule {
//    GameBoard myGameBoard;
//    Graph myGraph;
//    EstateCorrectRule(GameBoard gameBoard,Graph graph){
//        myGameBoard = gameBoard;
//        myGraph = graph;
//    }
//
//
//    private boolean checkConnection(int roWIndx, int colIndx){
//        if (myGameBoard.getCell(i,j ).getSetCell() != myGameBoard.getCell(i,j+1).getSetCell()){
//            return false;
//        }
//
//        for(int i = -1;i < 2;i++){
//            if(myGameBoard.getCell(roWIndx,colIndx+i).getSetCell() != myGameBoard.getCell(roWIndx,colIndx).getSetCell()){return false;}
//        }
//        for(int i = -1;i < 2;i++){
//            if(myGameBoard.getCell(roWIndx+i,colIndx).getSetCell() != myGameBoard.getCell(roWIndx,colIndx).getSetCell()){return false;}
//        }
//        return true;
//
//    }
//    public boolean check(){
//        for (int i = 0;i < myGameBoard.getWidth();i++){
//            for(int j = 0;j <myGameBoard.getHeigth();j++){
//                Cell cell = myGameBoard.getCell(i,j);
//                if(cell.getData().equals("")){
//                   if(!checkConnection(i,j)){
//                       return false;
//                   }
//                }
//            }
//        }
//    }
//
//}
