package csc4710_Josiah_part1;

public class cheaterPair {
	private String cheaterA;
	private String cheaterB;
	
	cheaterPair(String A, String B){
		this.cheaterA = A;
		this.cheaterB  = B;
		
	}

	public String getCheaterA() {
		return cheaterA;
	}

	public void setCheaterA(String cheaterA) {
		this.cheaterA = cheaterA;
	}

	public String getCheaterB() {
		return cheaterB;
	}

	public void setCheaterB(String cheaterB) {
		this.cheaterB = cheaterB;
	}
	
}
