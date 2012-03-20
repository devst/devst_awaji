package controllers;

public enum Team {

	A("えーちーむ", "answers.a"),
	B("B-TEAM", "answers.b"),
	;

	final String teamName;
	final String pkg;

	Team(String teamName, String pkg) {
		this.teamName = teamName;
		this.pkg = pkg;
		
	}
}
