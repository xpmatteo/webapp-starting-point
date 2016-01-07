package it.xpug.todolist;

import java.util.*;
import java.util.function.*;


public class Router {

	static class RouterEntry {
		String path;
		Consumer<WebRequest> answer;
		boolean isPostRequired = false;
		String nonEmptyParameterRequired;

		public static  RouterEntry forPost(String path, Consumer<WebRequest> answer) {
			return new RouterEntry(path, answer) {{
				this.isPostRequired = true;
			}};
		}

		public RouterEntry(String path, Consumer<WebRequest> answer) {
	        this.path = path;
	        this.answer = answer;
        }

		protected RouterEntry withNonEmptyParameter(String name) {
			nonEmptyParameterRequired = name;
			return this;
		}
	}

	private Consumer<WebRequest> defaultAnswer;
	private List<RouterEntry> entries = new ArrayList<RouterEntry>();

	public RouterEntry onAnyMethod(String path, Consumer<WebRequest> answer) {
		RouterEntry entry = new RouterEntry(path, answer);
		this.entries.add(entry);
		return entry;
    }

	public void onPost(String path, Consumer<WebRequest> answer) {
		this.entries.add(RouterEntry.forPost(path, answer));
	}

	public void defaultAnswer(Consumer<WebRequest> answer) {
		this.defaultAnswer = answer;
    }

	public void onRequest(WebRequest request) {
		for (RouterEntry entry : entries) {
			if (matchesMethod(entry, request)
					&& matchesParameters(entry, request)
					&& request.matches(entry.path)) {
				entry.answer.accept(request);
				return;
			}
        }
		defaultAnswer.accept(request);
    }

	private boolean matchesMethod(RouterEntry entry, WebRequest request) {
		return !entry.isPostRequired || entry.isPostRequired && request.isPost();
    }

	public boolean matchesParameters(RouterEntry entry, WebRequest request) {
        return null == entry.nonEmptyParameterRequired
        		|| null != request.getParameter(entry.nonEmptyParameterRequired);
    }

}
