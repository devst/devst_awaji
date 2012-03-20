package controllers;

public enum Team {

	A("えーちーむ", "features.a"),
	B("B-TEAM", "features.b"),
	;

	final String teamName;
	final String pkg;

	Team(String teamName, String pkg) {
		this.teamName = teamName;
		this.pkg = pkg;
		
	}
}
