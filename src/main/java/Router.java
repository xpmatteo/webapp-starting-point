import it.xpug.todolist.*;

import java.util.*;
import java.util.function.*;


public class Router {

	static class Pair {
		String path;
		Consumer<WebRequest> answer;
		public Pair(String path, Consumer<WebRequest> answer) {
	        this.path = path;
	        this.answer = answer;
        }
	}

	private Consumer<WebRequest> defaultAnswer;
	private List<Pair> pairs = new ArrayList<Pair>();

	public void onPath(String path, Consumer<WebRequest> answer) {
		this.pairs.add(new Pair(path, answer));
    }

	public void defaultAnswer(Consumer<WebRequest> answer) {
		this.defaultAnswer = answer;
    }

	public void onRequest(WebRequest request) {
		for (Pair pair : pairs) {
			if (request.matches(pair.path)) {
				pair.answer.accept(request);
				return;
			}
        }
		defaultAnswer.accept(request);
    }

}
