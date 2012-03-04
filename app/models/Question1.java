package models;

public class Question1 implements QuestionExecutable {
	public String execute(String arg) {
		String[] args = arg.split(",");
		return String.valueOf(Integer.parseInt(args[0]) + Integer.parseInt(args[1]));
	}
}
