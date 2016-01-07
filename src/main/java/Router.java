import it.xpug.todolist.*;

import java.util.*;
import java.util.function.*;


public class Router {

	static class RouterEntry {
		String path;
		Consumer<WebRequest> answer;
		boolean isPost = false;

		public static  RouterEntry forPost(String path, Consumer<WebRequest> answer) {
			return new RouterEntry(path, answer) {{
				isPost = true;
			}};
		}

		public RouterEntry(String path, Consumer<WebRequest> answer) {
	        this.path = path;
	        this.answer = answer;
        }
	}

	private Consumer<WebRequest> defaultAnswer;
	private List<RouterEntry> entries = new ArrayList<RouterEntry>();

	public void onAnyMethod(String path, Consumer<WebRequest> answer) {
		this.entries.add(new RouterEntry(path, answer));
    }

	public void onPost(String path, Consumer<WebRequest> answer) {
		this.entries.add(RouterEntry.forPost(path, answer));
	}

	public void defaultAnswer(Consumer<WebRequest> answer) {
		this.defaultAnswer = answer;
    }

	public void onRequest(WebRequest request) {
		for (RouterEntry entry : entries) {
			if (entry.isPost && request.isPost() && request.matches(entry.path)) {
				entry.answer.accept(request);
				return;
			}
			if (request.matches(entry.path)) {
				entry.answer.accept(request);
				return;
			}
        }
		defaultAnswer.accept(request);
    }
}
