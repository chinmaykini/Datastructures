package Misc;

import java.util.LinkedList;
import java.util.Queue;

public class DiseaseSpread {

	public enum STATE {
		UNVISTED,VISITING,VISITED
	}
	public static void main(String args[]){

		char[][] currState = {{'C','B','B','C'},
				{'C','G','G','C'},
				{'C','G','G','C'},
		};
		STATE[][] currVisitedState= new STATE[currState.length][currState[0].length];
		System.out.println(spreadStop(currState,currVisitedState));
	}

	public static class InfectedPoint{
		int x;
		int y;
		boolean isNull;
		public InfectedPoint (boolean isNull){
			this.isNull=isNull;
		}
		public InfectedPoint (int x,int y){
			this.x =x;
			this.y=y;
		}
		public void populateNeigbor(char[][] state,Queue<InfectedPoint> qu,STATE[][] currVisitedState){
			int m = state.length;
			int n=state[0].length;
			int[] xdev = {0,1,0,-1};
			int[] ydev = {1,0,-1,0};

			for( int i=0; i <xdev.length; i++){
				if( x+xdev[i] < m && y+ydev[i] < n &&
						x+xdev[i] >= 0 && y+ydev[i] >=0 &&
						currVisitedState[x+xdev[i]][y+ydev[i]]==STATE.UNVISTED
						&& state[x+xdev[i]][y+ydev[i]]== 'G'){
					qu.offer(new InfectedPoint(x+xdev[i],y+ydev[i]));
					currVisitedState[x+xdev[i]][y+ydev[i]]=STATE.VISITING;
				}
			}
		}
	}
	static int spreadStop(char[][] state,STATE[][] currVisitedState){
		int m = state.length;
		int n=state[0].length;

		int time=0;
		Queue<InfectedPoint> qu= new LinkedList<InfectedPoint>();
		for( int i =0; i < m; i++){
			for ( int j=0; j < n; j++){
				currVisitedState[i][j]=STATE.UNVISTED;
				if(state[i][j] == 'B'){
					qu.offer(new InfectedPoint(i,j));
					currVisitedState[i][j]=STATE.VISITING;
				}

			}
		}
		qu.offer(new InfectedPoint(true));
		while ( !qu.isEmpty()){
			InfectedPoint currG = qu.poll();
			currVisitedState[currG.x][currG.y]=STATE.VISITED;
			if( currG.isNull){
				if( !qu.isEmpty()){
					time++;
					qu.offer(new InfectedPoint(true));
				}
					
			}
			currG.populateNeigbor(state,qu,currVisitedState);
		}
		return time;
	}


}
